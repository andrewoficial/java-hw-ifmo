package hw015.task02;

import java.util.*;

public class Task02 {

    private static List<String> task01(Map<String, String> map, String someString) {
        // FIXME:
        //  1. Создать пустой список, для хранения строк (ArrayList<String>)
        //  2. Перебрать пары (ключ + значение) объекта map
        //  (для получения коллекции пар использовать метод map.entrySet(), пример перебора пар в файле Lesson15.java) и
        //   на каждой итерации цикла:
        //      2.1. получать значение пары (метод getValue())
        //      2.2. полученное значение сравнивать со значением someString, если они равны, ключ пары (метод getKey())
        //      добавлять в список, объявленный в п.1
        //   3. Список, объявленный в п.1, вернуть из метода

        ArrayList<String> arrList = new ArrayList<>();
        for (Map.Entry<String, String> pair : map.entrySet()) {
            if (pair.getValue().equals(someString)){
                arrList.add(pair.getKey());
                //System.out.println(pair.getKey());
            }
            // pair.getKey() - ключ
            // pair.getValue() - ассоциированное с ним значение
        }
        return arrList;
    }

    private static Map<String, Integer> task02(List<String> strings) {
        // FIXME: похожую реализацию разбирали на занятии (класс UserStorage, метод getNumberOfUsersByRole)
        //  1. Создать пустую map, для хранения строк и количества повторений (HashMap<String, Integer>)
        //  2. Перебрать список strings и на каждой итерации цикла:
        //      2.1. проверять, содержится ли ключ (элемент перебираемой коллекции) в мапе, объявленной в п.1
        //      2.2. если ключ содержится (значит информация о строке из списка уже есть в мапе), получать значение по данному ключу,
        //      увеличивать его на 1, записывать в мапу ключ и новое значение (обновляем количество повторений данной строки)
        //      2.3. если ключ не содержится, записывать в мапу ключ и 1 (строка из списка встретилась первый раз)
        //   3. Мапу, объявленную в п.1, вернуть из метода
        HashMap<String, Integer> answ = new HashMap<>();
        for (String string : strings) {
            if(answ.containsKey(string)){
                answ.put(string, answ.get(string) + 1);
                //System.out.println(answ);
            }else{
                answ.put(string, 1);
            }
        }
        return answ;
    }

    private static Map<String, Long> task0301(String text) {
        // FIXME: реализация похожа на метод task02
        //  1. Создать пустую map, для хранения строк и количества повторений (HashMap<String, Long>)
        //  2. Создать массив строк из строки text (нужный метод найти самостоятельно)
        //  3. Перебрать массив строк, полученный в п.2 и на каждой итерации цикла:
        //      3.1. проверять, содержится ли ключ (элемент перебираемой коллекции) в мапе, объявленной в п.1
        //      3.2. если ключ содержится (значит информация о строке из списка уже есть в мапе), получать значение по данному ключу,
        //      увеличивать его на 1, записывать в мапу ключ и новое значение (обновляем количество повторений данной строки)
        //      3.3. если ключ не содержится, записывать в мапу ключ и 1 (строка из списка встретилась первый раз)
        //   4. Мапу, объявленную в п.1, вернуть из метода
        HashMap<String, Long> answ = new HashMap<>();
        String[] words = text.split(" ");
        for (String word : words) {
            if(answ.containsKey(word)) {
                answ.put(word, answ.get(word) + 1L);
            }else{
                answ.put(word, 1L);
            }
        }
        return answ;
    }

    private static Map<Integer, ArrayList<String>> task0302(String text) {
        // FIXME: реализация похожа на метод getUsersByAge класса UserStorage
        //  1. Создать пустую map, для хранения строк и количества повторений (HashMap<Integer, ArrayList<String>>)
        //  2. Создать массив строк из строки text (нужный метод найти самостоятельно)
        //  3. Перебрать массив строк, полученный в п.2 и на каждой итерации цикла:
        //      3.1. получить размер строки (элемента массива)
        //      3.2. проверять, содержится ли ключ (значение полученное в п.3.1) в мапе, объявленной в п.1
        //      3.3. если ключ содержится (значит информация о строке из массива уже есть в мапе),
        //      получать значение (список) по данному ключу, добавлять строку (элемент массива) в полученный список,
        //      записывать в мапу ключ и список, с добавленной в него строкой
        //      3.4. если ключ не содержится, создавать пустой список (ArrayList<String>),
        //      добавлять в него строку (элемент массива), записывать в мапу ключ и созданный список
        //   4. Мапу, объявленную в п.1, вернуть из метода
        HashMap<Integer, ArrayList<String>> answ = new HashMap<>();
        String[] words = text.split(" ");
        for (String word : words) {
            int strLen = word.length();
            if(answ.containsKey(strLen)){
                answ.get(strLen).add(word);
                //System.out.println(answ);
            }else{
                ArrayList<String> toAdd = new  ArrayList<>();
                toAdd.add(word);
                answ.put(strLen, toAdd);
            }
        }
        return answ;
    }

    public static void main(String[] args) {
        // FIXME:: В решениях не использовать lambda выражения и stream API

        // TODO (2.1) написать статический метод, который принимает на вход map (например, firstTaskMap) и город (например, city),
        //  формирует и возвращает список (List) логинов, которые соответствуют переданному городу

        HashMap<String, String> firstTaskMap = new HashMap<>();
        firstTaskMap.put("qwe", "Тверь");
        firstTaskMap.put("asd", "Тверь");
        firstTaskMap.put("zxc", "Москва");
        firstTaskMap.put("rty", "Тверь");
        firstTaskMap.put("fgh", "Магадан");

        String city = "Тверь";

        System.out.println(task01(firstTaskMap, city));

        // TODO (2.2) дан список слов (например, words).
        //  Написать статический метод, который будет возвращать количество одинаковых слов в списке
        //  в map вида Map<String, Integer>, где String - слово и Integer - количество повторений

        List<String> words = new ArrayList<>();
        words.add("may");
        words.add("august");
        words.add("june");
        words.add("may");
        words.add("may");
        words.add("july");
        words.add("may");
        words.add("august");
        words.add("august");

        System.out.println(task02(words));


        // TODO (2.3) Задания по тексту (например, text). На каждый пункт - минимум один метод (можно статический):
        //  1. написать метод, принимающий на вход слово и возвращающий частоту встречаемости данного слова в тексте
        //  (Map<String, Long>, где - ключи (String) - слово, значения (Long) - частота встречаемости)
        //  2. написать метод, который собирает все слова в группы по количеству букв:
        //  например, в одну группу попадут слова: 3 - [the, war, jar, get, met...], в другую 2 - [on, up, no, of...]
        //  (Map<Integer, ArrayList<String>>, где - ключи (Integer) - количество букв, значения (ArrayList<String>) - слова)
        //  3. написать метод, который выводит в консоль топ 10 самых часто встречаемых в тексте слов (артикли и предлоги тоже считаем за слова)

        // в тексте содержатся только буквы и пробельные символы
        String text = "It is a uncover long established fact that a reader will be distracted uncover by the readable content of a page " +
                "when looking at its layout The point of using uncover Lorem Ipsum is that sites it has a more-or-less normal distribution of letters" +
                "as uncover opposed to still using Content here humour uncover content here making it look like readable English Many desktop publishing " +
                "packages and web page editors now use Lorem Ipsum as their default model text and a search for lorem ipsum will " +
                "uncover many web sites still uncover in their infancy Various versions uncover have evolved over the years uncover sometimes by accident" +
                " sometimes on purpose injected humour and the like";

        System.out.println(task0301(text));
        System.out.println("Task task0302"); //Checked
        System.out.println(task0302(text));
        task0303(text);

    }

    private static void task0303(String text) {
        // FIXME: реализация похожа на метод getUsersByAge класса UserStorage
        //  1. Создать массив строк из строки text (нужный метод найти самостоятельно)
        //  2. Создать список из элементов массива, полученного в п.2 (способ создания списка из массива см в файле map.md)
        //  3. Объявить переменную типа Map<String, Integer>, где - ключи (String) - слово, значения (Integer) - частота встречаемости,
        //  вызвать метод task02, передать в него список из п.2, значение которое вернет метод присвоить объявленной переменой
        //  (так мы получим слова и частоту повторений)
        //  4. Создать список (ArrayList<Map.Entry<String, Integer>>) из пар мапы, объявленной в п.3
        //  (для получения коллекции пар использовать метод map.entrySet())
        //  5. Объявить класс ValuesComparator implements Comparator<Map.Entry<String, Integer>>,
        //  он позволит отсортировать список из п.4 по значениям (частоте встречаемости слов)
        //  6. Интерфейс обяжет Вас написать реализацию метода public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2):
        //     2.1. у объектов o1 и o2 получить значения свойства value (метод getValue) и сравнить числа
        //     2.3. вернуть из метода 0, если числа равны,
        //     вернуть из метода отрицательное число, если значение value объекта o1 больше значения value объекта o2,
        //     вернуть из метода положительное число, если значение value объекта o1 меньше значения value объекта o2
        //   7. Объявить объект класса ValuesComparator
        //   8. Вызвать метод sort() списка из п.4, передать в него компаратор из п.7
        //   9. Написать цикл на 10 итераций (или по количеству элементов списка (если их меньше 10)).
        //   Цикл должен перебирать список п.4, на каждой итерации выводить в консоль значение свойства key (метод getKey) элемента списка
        String[] words = text.split(" ");
        List<String> listWords = Arrays.asList(words);
        HashMap<String, Integer> myMapa = new HashMap <>(task02(listWords));
        //System.out.println(myMapa); //Checked
        ArrayList<Map.Entry<String, Integer>> myArrayList = new ArrayList<>(myMapa.entrySet());
        //System.out.println(myArrayList); //Checked

        class ValuesComparator implements Comparator<Map.Entry<String, Integer>>{

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        }

        ValuesComparator valComparator = new ValuesComparator();
        myArrayList.sort(valComparator);
        int count = Math.min(10, myArrayList.size());
        for (int i = 0; i < count; i++) {
            System.out.println(myArrayList.get(i).getValue() + "=" + myArrayList.get(i).getKey());
        }

        /*
        for (Map.Entry<String, Integer> stringIntegerEntry : myArrayList) {
            System.out.println(stringIntegerEntry.getKey());
            //System.out.println(stringIntegerEntry.getKey() + " contains in text " + stringIntegerEntry.getValue());
        }
        */
    }


}
