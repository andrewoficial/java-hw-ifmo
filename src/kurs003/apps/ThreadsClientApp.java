package kurs003.apps;

import kurs003.common.Message;
import kurs003.common.TextFile;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;



public class ThreadsClientApp {
    private InetSocketAddress address;
    private OutputStream output;
    private InputStream input;
    private Socket socket;

    public ThreadsClientApp(InetSocketAddress address) {
        this.address = address;
        socket = new Socket();
    }

    public void start() {
        try {
            socket.connect(address);
            output = socket.getOutputStream();
            input = socket.getInputStream();
            new Receiver().start();
            new Sender().start();
        } catch (IOException e) {
            System.out.println("Удаленный сервер не отвечает. Проверьте адрес");
        }
    }

    private class Sender extends Thread {
        @Override
        public void run() {
            String token = Math.random() + "token";
            System.out.println("Random token is" + token);
            Scanner scanner = new Scanner(System.in);
            String clientIdentify = scanner.nextLine();
            System.out.println("Should be associate with userName" + clientIdentify);
            //Отправка сообщения для регистрации пользователя
            Message msg = new Message("/register:"+clientIdentify, token);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
                oos.writeObject(msg);
                oos.flush();
                byte[] bytes = byteArrayOutputStream.toByteArray();
                output.write(bytes);
                oos.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                System.out.println("Удаленный сервер перестал отвечать в момент регистрации");
                try {
                    input.close();
                    output.close();
                    socket.close();
                } catch (Exception ex) {
                    System.out.println("Ошибка закрытия ресурса в момент регистрации");
                }
            }
            while (true) {
                System.out.println("Введите сообщение");
                String text = scanner.nextLine().replaceAll("/register","");//Что бы не слали команду регистрации напрямую
                if("/exit".equalsIgnoreCase(text)){
                    System.out.println("Завершаю работу клиентской программы");
                    break;
                }else if("/sendFile".equalsIgnoreCase(text)){
                    System.out.println("Введите имя файла");
                    TextFile file = new TextFile(scanner.nextLine());
                    System.out.println("Введите описание");
                    file.setDescription(scanner.nextLine());
                    System.out.println("Введите текст фала (/done - для завершения накопления файла)");
                    String fileContent = "";
                    String input = "";
                    while (scanner.hasNextLine()){
                        input = scanner.nextLine();
                        if("/done".equalsIgnoreCase(input)){
                            break;
                        }
                        fileContent += input + "\n";
                    }
                    file.setContent(fileContent);
                    file.setIdentify(clientIdentify);
                    try{
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
                        oos.writeObject(file);
                        oos.flush();
                        byte[] bytes = byteArrayOutputStream.toByteArray();
                        //Передавать будем как было в уроке 27.

                        output.write(bytes);

                        oos.close();
                        byteArrayOutputStream.close();
                    }catch (IOException e){
                        System.out.println("Удаленный сервер перестал отвечать");
                        try {
                            output.close();
                            socket.close();
                        } catch (Exception ex) {
                            System.out.println("Ошибка закрытия ресурса");
                        }


                        return;
                    }

                }

                msg = new Message(text, token);
                try {
                    // Создаем ByteArrayOutputStream для записи объектов в память
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    //Для того что бы при создании контейнера он не посылал ничего в сеть

                    // Создаем ObjectOutputStream на основе ByteArrayOutputStream
                    ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
                    //Для того что бы allocate был в память а не в сеть сразу

                    // Записываем объекты в ObjectOutputStream
                    oos.writeObject(msg);
                    oos.flush();
                    //Сама серриализация объекта Message

                    // Получаем массив байтов из ByteArrayOutputStream
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    //Передавать будем как было в уроке 27.

                    output.write(bytes);

                    oos.close();
                    byteArrayOutputStream.close();
                    //Закрытие ресурсов

                    //ObjectOutputStream oos = new ObjectOutputStream(output);
                    //oos.writeObject(msg);
                    //oos.flush();

                    //output.write(text.getBytes(StandardCharsets.UTF_8));
                } catch (IOException e) {

                    System.out.println("Удаленный сервер перестал отвечать");
                    try {
                        input.close();
                        output.close();
                        socket.close();
                    } catch (Exception ex) {
                        System.out.println("Ошибка закрытия ресурса");
                    }


                    return;
                }
            }
        }
    }

    private class Receiver extends Thread {
        @Override
        public void run() {
            byte[] bytes = new byte[1024];
            while (true) {
                try {
                    int read = input.read(bytes);
                    if (read == -1) {
                        // Достигнут конец потока, прекращаем чтение
                        break;
                    }

                    // Создаем ByteArrayInputStream на основе прочитанных данных
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes, 0, read);

                    // Создаем ObjectInputStream на основе ByteArrayInputStream
                    ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);

                    // Далее можно прочитать объекты из ObjectInputStream
                    // ...
                    try {
                        Message msg = (Message) ois.readObject();
                        System.out.println(msg.getText());
                    } catch (ClassNotFoundException e) {
                        //throw new RuntimeException(e);
                        System.out.println("Неподдерживаемый формат сообщения");
                    }

                    // Закрываем ObjectInputStream и ByteArrayInputStream
                    ois.close();
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    System.out.println("Удаленный сервер перестал отвечать");
                    try {
                        input.close();
                        output.close();
                        socket.close();
                    } catch (Exception ex) {
                        System.out.println("Ошибка закрытия ресурса");
                    }
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {


        System.out.println("Представьтесь и начните чат!");
        new ThreadsClientApp(new InetSocketAddress("127.0.0.1", 2222)).start();
    }
}