package hw014.task02;

import java.util.*;

public class Task02 {
    public static void countEachPriority(List<Message> messageList) {
        // Подсчитать количество сообщений для каждого приоритета (Ответ в консоль)
        // FIXME:
        //  1. Объявить массив, в котором можно хранить количество повторений.
        //  Размер массива равен количеству элементов перечисления MessagePriority
        //  2. Перебрать список messageList и на каждой итерации цикла:
        //     2.1. получить значение свойства priority элемента коллекции (вызовом метода метод getPriority у элемента коллекции)
        //     2.2. у полученного в п.2.1 приоритета получить его индекс в перечислении
        //     (метод, который возвращает индекс элемента перечисления найти самостоятельно)
        //     2.3. по полученному в п.2.2 индексу получить значение элемента массива, объявленного в п.1, и увеличить его значение на 1
        //   3. Массив, объявленный в п.1, вывести в консоль
        Map<Message.MessagePriority, Integer> messagesStat = new HashMap<Message.MessagePriority, Integer>();
        messagesStat.put(Message.MessagePriority.LOW, 0);
        messagesStat.put(Message.MessagePriority.MEDIUM, 0);
        messagesStat.put(Message.MessagePriority.HIGH, 0);
        messagesStat.put(Message.MessagePriority.URGENT, 0);
        for (Message message : messageList) {
            switch(message.getPriority()){
                case LOW:
                    messagesStat.put(Message.MessagePriority.LOW, messagesStat.get(Message.MessagePriority.LOW) + 1);
                    break;
                case MEDIUM:
                    messagesStat.put(Message.MessagePriority.MEDIUM, messagesStat.get(Message.MessagePriority.MEDIUM) + 1);
                    break;
                case HIGH:
                    messagesStat.put(Message.MessagePriority.HIGH, messagesStat.get(Message.MessagePriority.HIGH) + 1);
                    break;
                case URGENT:
                    messagesStat.put(Message.MessagePriority.URGENT, messagesStat.get(Message.MessagePriority.URGENT) + 1);
                    break;
            }
        }
        System.out.println(messagesStat.toString());
    }

    public static void countEachCode(List<Message> messageList) {
        // Подсчитать количество сообщений для каждого кода сообщения (Ответ в консоль)
        // FIXME:
        //  1. Объявить массив, в котором можно хранить количество повторений. Размер массива равен 10
        //  2. Перебрать список messageList и на каждой итерации цикла:
        //     2.1. получить значение свойства code элемента коллекции (вызовом метода метод getCode у элемента коллекции)
        //     2.2. по полученному в п.2.1 коду получить значение элемента массива, объявленного в п.1, и увеличить его значение на 1
        //   3. Массив, объявленный в п.1, вывести в консоль
        Map<Integer, Integer> messagesStat = new HashMap<Integer, Integer>();
        for (Message message : messageList) {
            if(! messagesStat.containsKey(message.getCode())){
                messagesStat.put(message.getCode(), 1);
            }else{
                messagesStat.put(message.getCode(), messagesStat.get(message.getCode()) + 1);
            }
        }
        System.out.println(messagesStat.toString());
        //Такая реализация позволяет не хардкодить возможное количество кодов ошибки
    }

    public static void uniqueMessageCount(List<Message> messageList) {
        // Подсчитать количество уникальных сообщений (Ответ в консоль)
        // FIXME:
        //  1. Выбрать коллекцию, которая позволяет хранить только уникальные элементы
        //  2. Создать экземпляр данной коллекции (объект).
        //  Для создании объекта использовать конструктор, который принимает другую коллекцию, передать в него messageList
        //  3. Вывести в консоль размер созданной в п.1 коллекции (метод, который возвращает размер коллекции найти самостоятельно)
        /* вообще не понял вот этого "создании объекта использовать конструктор, который принимает другую коллекцию, передать в него "
            Set<List> treeSet; //Сортирует по порядку
            treeSet = new TreeSet<>(messageList);
         */
        HashSet<Message> uniqueMessages = new HashSet<>();
        HashSet<Message> doubledMessages = new HashSet<>();
        for (Message message : messageList) {
            //System.out.println("See msg " + message);
            if(uniqueMessages.contains(message)){
                uniqueMessages.remove(message);
                doubledMessages.add(message);
                //System.out.println("This message contains. Remove");
            }else if(! doubledMessages.contains(message)){
                uniqueMessages.add(message);
                doubledMessages.add(message);
                //System.out.println("This message is unique (or first)");
            }
        }
        //System.out.println("uniqueMessages.size()");
        for (Message uniqueMessage : uniqueMessages) {
            //System.out.println(uniqueMessage);
        }
        System.out.println(uniqueMessages.size());
    }

    public static List<Message> uniqueMessagesInOriginalOrder(List<Message> messageList) {
        // вернуть только неповторяющиеся сообщения и в том порядке, в котором они встретились в списке messageList
        // FIXME:
        //  1. Выбрать коллекцию, которая позволяет хранить только уникальные элементы, но в порядке добавления
        //  2. Создать экземпляр данной коллекции (объект).
        //  Для создании объекта использовать конструктор, который принимает другую коллекцию, передать в него messageList
        //  3. Создать пустой список, в котором будут храниться экземпляры типа Message (ArrayList<Message>)
        //  Для создании объекта использовать конструктор, который принимает другую коллекцию, передать в него коллекцию созданную в п.2
        //  4. Ссылку на список, созданный в п.3 вернуть из метода
        Set<Message> treeSetUnique = new LinkedHashSet <>();
        Set<Message> treeSetDoubled = new LinkedHashSet <>();
        for (Message message : messageList) {
            //System.out.println("See msg " + message);
            if(treeSetUnique.contains(message)){
                treeSetUnique.remove(message);
                treeSetDoubled.add(message);
                //System.out.println("This message contains. Remove");
            }else if(! treeSetDoubled.contains(message)){
                treeSetUnique.add(message);
                treeSetDoubled.add(message);
                //System.out.println("This message is unique (or first)");
            }
        }
        ArrayList<Message> answer = new ArrayList<>();
        for (Message message : treeSetUnique) {
            answer.add(message);
        }

        return answer;
    }

    public static void removeEach(List<Message> messageList, Message.MessagePriority inPriority) {
        // удалить из коллекции каждое сообщение с заданным приоритетом (вывод в консоль до удаления и после удаления)
        // FIXME:
        //  1. Вывести список messageList в консоль
        //  2. Создать итератор (Iterator<Message>) списка messageList (создание итератора смотрели на занятии в файле Lesson14.java)
        //  3. Перебрать список messageList созданным в п.2 итератором (перебор итератором смотрели на занятии в файле Lesson14.java),
        //  на каждой итерации цикла:
        //     3.1. получить значение свойства priority элемента коллекции (вызовом метода метод getPriority у элемента коллекции)
        //     3.2. полученное значение сравнить со значением inPriority, если они равны, удалить элемент коллекции
        //     (удаление итератором смотрели на занятии в файле Lesson14.java)
        //   3. Вывести список messageList в консоль
        System.out.println(messageList.toString());
        // Перебор коллекций (iterator)
        // позволяет удалять элементы коллекции в цикле
        // может быть заменен методом removeIf

        Iterator<Message> fruitIterator = messageList.iterator();
        while (fruitIterator.hasNext()) {
            Message fruitElem = fruitIterator.next();
            if (fruitElem.getPriority() ==  inPriority) fruitIterator.remove();
        }
        System.out.println(messageList.toString());
    }

    public static void removeOther(List<Message> messageList, Message.MessagePriority priority) {
        // удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет (вывод в консоль до удаления и после удаления)
        // FIXME:
        //  1. Вывести список messageList в консоль
        //  2. Создать итератор (Iterator<Message>) списка messageList (создание итератора смотрели на занятии в файле Lesson14.java)
        //  3. Перебрать список messageList созданным в п.2 итератором (перебор итератором смотрели на занятии в файле Lesson14.java),
        //  на каждой итерации цикла:
        //     3.1. получить значение свойства priority элемента коллекции (вызовом метода метод getPriority у элемента коллекции)
        //     3.2. полученное значение сравнить со значением inPriority, если они не равны, удалить элемент коллекции
        //     (удаление итератором смотрели на занятии в файле Lesson14.java)
        //   3. Вывести список messageList в консоль
        System.out.println(messageList.toString());


        Iterator<Message> fruitIterator = messageList.iterator();
        while (fruitIterator.hasNext()) {
            Message fruitElem = fruitIterator.next();
            if (fruitElem.getPriority() !=  priority) fruitIterator.remove();
        }
        System.out.println(messageList.toString());
    }

    public static void main(String[] args) {
        List<Message> messages = Message.generate(34);
        System.out.println("countEachPriority");
        countEachPriority(messages);

        System.out.println("countEachCode");
        countEachCode(messages);

        System.out.println("uniqueMessageCount");
        uniqueMessageCount(messages);

        System.out.println("uniqueMessagesInOriginalOrder");
        System.out.println(uniqueMessagesInOriginalOrder(messages));

        System.out.println("removeEach");
        removeEach(messages, Message.MessagePriority.HIGH);

        System.out.println("removeOther");
        removeOther(messages, Message.MessagePriority.LOW);
    }
}