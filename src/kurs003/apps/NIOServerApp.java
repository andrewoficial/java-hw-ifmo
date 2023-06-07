package kurs003.apps;

import kurs003.common.Command;
import kurs003.common.Connection;
import kurs003.common.Message;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.io.ObjectInputStream;

public class NIOServerApp {
    private InetSocketAddress address;

    public NIOServerApp(InetSocketAddress address) {
        this.address = address;
    }

    public void start() {
        new NioThread().start();
    }

    private class NioThread extends Thread {
        private ServerSocketChannel serverChannel;
        private HashSet<SocketChannel> channels = new HashSet<>();

        private HashSet<Connection> connections = new HashSet<>();
        private static final int BUF_SIZE = 1024;
        private ByteBuffer byteBuffer;
        private Selector selector;

        private void init() throws IOException {
            // инициализация буфера:
            // 1. ЧТЕНИЕ: в буфер будут приходить байты из сокет соединения (канал -> буфер -> программа);
            // 2. ЗАПИСЬ: из буфера будут уходить байты в сокет соединение (программа -> буфер -> канал).
            byteBuffer = ByteBuffer.allocate(BUF_SIZE);

            // инициализация селектора:
            // 1. когда в канале подключений произойдет событие, информация об этом сохранится в коллекцию селектора
            // 2. когда в канале записи / чтения произойдет событие,
            // информация об этом сохранится в коллекцию селектора - Set<SelectionKey>
            // Экземпляр SelectionKey хранит информацию о событии.
            // Это может быть событие:
            // OP_ACCEPT - новое подключение, метод selectionKey.isAcceptable() вернет true
            // OP_READ - появились данные для чтения, метод selectionKey.isReadable() вернет true
            // OP_WRITE - канал готов к отправке данных, метод selectionKey.isWritable() вернет true
            selector = Selector.open();

            // инициализация ServerSocketChannel (аналог ServerSocket)
            serverChannel = ServerSocketChannel.open();

            // ServerSocketChannel связывается с определенным портом
            serverChannel.bind(address);

            // перевод в неблокирующий режим (иначе в канале не смогут происходить разные события)
            serverChannel.configureBlocking(false);

            // регистрация канала в селекторе:
            // селектор будет сохранять в коллекции информацию при каждом новом подключении,
            // информация сохранится как экземпляр SelectionKey(OP_ACCEPT)
            serverChannel.register(selector, SelectionKey.OP_ACCEPT); // SelectionKey.WRITE | SelectionKey.READ
        }

        private void chooseEventReaction() throws IOException {
            // ожидание новых событий, новой информации в коллекции селектора
            // программа приостанавливает работу, пока не произойдет новое событие
            selector.select();

            // получение коллекции селектора с информацией о событиях
            Set<SelectionKey> keys = selector.selectedKeys(); // можно удалять объекты
            // Set<SelectionKey> keys = selector.keys(); // нельзя удалять объекты


            // перебор итератором позволит удалить информацию о событии из коллекции
            // после того, как она будет обработана
            Iterator<SelectionKey> keyIterator = keys.iterator();

            while (keyIterator.hasNext()) {
                // selectionKey содержит информацию о событии
                SelectionKey selectionKey = keyIterator.next();

                if (selectionKey.isAcceptable()) { // true, если событие OP_ACCEPT - новое подключение
                    // получение канала (аналог Socket), ассоциирован с соединением клиент - сервер
                    SocketChannel channel = serverChannel.accept();
                    connections.add(Connection.buildConnection(channel));
                    channels.add(channel); //ToDo comment this

                    // перевод в неблокирующий режим (иначе в канале не смогут происходить разные события)
                    channel.configureBlocking(false);

                    // регистрация канала в селекторе:
                    // селектор будет сохранять в коллекции информацию, когда в канале будут появляться данные для чтения,
                    // информация сохранится как экземпляр SelectionKey (OP_READ)
                    channel.register(selector, SelectionKey.OP_READ);
                    System.out.println("Client connected " + channel.getRemoteAddress());
                } else if (selectionKey.isReadable()) { // true, если событие OP_READ - в канале есть данные для чтения
                    System.out.println("Client has sent data");
                    // если в канале есть данные для чтения,
                    // их нужно прочитать и разослать всем клиентам
                    try {
                        //Что-то совсем не оптимальное я придумал, конечно но не хочу исправлять
                        /*
                        Лирическое отступление.
                        Изначально была идея такая. Есть соедниение с сервером (сервер-клиент) и
                            к этому соединению я добавил свойство (токен и имя пользователя)
                            но теперь выясняется что что-бы передать соединение (уже существующее)
                            в метод dataDistribution по хорошему надо бы перебрать
                            коллекцию существующих соединений. Это прискорбно, но прмиерно так же проивходит при массовой
                            рассылке сообщений (spread)
                         */
                        for (Connection connection : connections) {
                            if(connection.getChannel().equals(selectionKey.channel())){//Приму на веру что оно сравнится верно
                                dataDistribution(connection);
                                break;
                            }
                        }

                    } catch (IOException e) {
                        System.out.println("IOException"+e.getMessage());
                        System.out.println("Client has disconnected");
                        System.out.println(channels.remove(selectionKey.channel()));
                        selectionKey.channel().close();
                    } catch (ClassNotFoundException e) {
                        System.out.println("ClassNotFoundException");
                        System.out.println("ClassNotFoundException"+e.getMessage());
                        //throw new RuntimeException(e);
                    }
                }

                // объект, хранящий информацию о событии нужно удалить,
                // чтобы не обрабатывать одно событие несколько раз
                keyIterator.remove();
            }
        }

        private void dataDistribution(Connection connection) throws IOException, ClassNotFoundException  {
            SocketChannel channel = connection.getChannel();
            byteBuffer.clear();
            int read = channel.read(byteBuffer);
            if (read == -1) return;
            String fromChannel = new String(byteBuffer.array(), 0, byteBuffer.position(), StandardCharsets.UTF_8);
            //System.out.println("RawData:["+fromChannel+"]");

            // Создаем ByteArrayInputStream на основе данных в ByteBuffer
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array(), 0, byteBuffer.limit());

            // Создаем ObjectInputStream на основе ByteArrayInputStream

            // byteBuffer.put()...

            // буфер должен быть готов к записи (данные из буфера будут записаны в канал)
            // limit (сколько данных можно забрать из буфера) должен быть равен position,
            // чтобы забрать только валидные, а не пустые байты
            // position (курсор) должен быть в начале буфера
            byteBuffer.flip();
            System.out.println("Создали поток из буфера, начинаем десериализацию");


            // Создаем ObjectInputStream на основе ByteArrayInputStream
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            System.out.println("Создали контейнер в котором возможно есть наш класс");
            // Далее можно прочитать объекты из ObjectInputStream
            // ...
            try {
                Message inpMsg = (Message) ois.readObject();
                System.out.println("Token:" + inpMsg.getKey());
                System.out.println("MSG:" + inpMsg.getText());
                if(parseString(inpMsg.getText()) != null){
                    System.out.println("found command" + parseString(inpMsg.getText()));
                    if(Command.register.equals(parseString(inpMsg.getText()))){
                        connection.setToken(inpMsg.getKey());
                        connection.setName(inpMsg.getText().replaceAll("/"+Command.register, "")); //Почистили строку от команды - остльное имя
                    }else if(Command.help.equals(parseString(inpMsg.getText()))){
                        System.out.println("Чип и дейл спешат на помощь! Список доступных команд:");
                        Command[] commands = Command.values();
                        for (Command command : commands) {
                            System.out.println("/"+command+" ");
                        }
                        System.out.println("Данные о вашем соединении:");
                        System.out.println("Связанное имя:" + connection.getName());
                        System.out.println("Связанный токен:"+connection.getToken());
                        System.out.println("Ваш адрес:"+connection.getChannel().getRemoteAddress());

                    }
                }
            }catch (Exception ex){
                System.out.println("Some exception while upcasting..."+ex.getMessage());
            }finally {
                ois.close();
                byteArrayInputStream.close();
            }
            // Закрываем ObjectInputStream и ByteArrayInputStream


            Message msg = new Message("Пользователь "+connection.getName()+" отправил сообщение:", "SERVER");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
            oos.writeObject(msg);
            oos.flush();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            //Spread message
            for (SocketChannel socketChannel : channels) {
                if (!socketChannel.equals(channel) && (socketChannel.validOps() & SelectionKey.OP_WRITE) > 0) { // если канал доступен для записи
                    socketChannel.write(ByteBuffer.wrap(bytes));
                    socketChannel.write(byteBuffer);
                    // буфер должен быть готов к следующей записи (данные из буфера будут записаны в следующий канал)
                    // limit (сколько данных можно забрать из буфера) остается на прежнем месте,
                    // так как количество байт не изменилось
                    // position (курсор) должен быть в начале буфера
                    byteBuffer.rewind();
                }
            }
            oos.close();
            byteArrayOutputStream.close();

        }

        private Command parseString(String str){
            if(str == null) return null;
            if(str.length() < 2) return null;
            if(! str.contains("/")) return null;
            Command[] commands = Command.values();
            for (Command s:commands) {
                if(str.contains("/register")){
                    return Command.register;
                }else if(str.contains("/reRegister")){
                    return Command.reRegister;
                }else if(str.contains("/help")){
                    return Command.help;
                }

            }
            return null;
        }
        @Override
        public void run() {
            try {
                init();
            } catch (IOException e) {
                System.out.println("ошибка инициализации");
            }
            while (true) {
                try {
                    chooseEventReaction();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void main(String[] args) {
        new NIOServerApp(new InetSocketAddress(2222)).start();
    }
}
