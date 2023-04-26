package hw018;

//import ru.itmo.lessons.lesson14.task.task01.Fruit;
import hw014.task01.Fruit;



import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lesson18 {
    private String string = "String";
    private static String staticString = "Static String";

    public void someVoid(String argString) {
        String localString = "Local String";
        Fruit fruit = new Fruit(Fruit.FruitType.APRICOT, 300, 4);

        Operation example = (first, second) -> {
            // в лямбда выражениях есть доступ к свойствам (static и нет) для чтения и записи
            System.out.println(string);
            System.out.println(staticString);
            System.out.println(argString);
            System.out.println(localString);
            // в лямбда выражениях есть доступ к локальным переменным метода только для чтения
            string = "Новая строка";
            staticString = "Новая строка";
            // локальные переменные метода:
            // argString = "Новая строка";
            // localString = "Новая строка";
            // fruit = new Fruit(Fruit.FruitType.APPLE, 300, 4);
            fruit.setCount(10);
            return 0;
        };
    }

    public static void main(String[] args) {

        // -> лямбда
        // слева от -> - аргументы, которые принимает метод
        // справа от -> - тело метода

        // если метод не принимает на вход аргументы или принимает 2 и более аргументов,
        // они ДОЛЖНЫ указываться в () скобках;
        // если метод принимает на вход 1 аргумент, () скобки МОЖНО опустить;
        // типы данных аргументов компилятор берет из контектса (абстрактного метода функционального интерфейса)

        // если тело метода состоит из одной инструкции, его МОЖНО не оборачивать в {} скобки;
        // если тело метода состоит из нескольких инструкции, оно ДОЛЖНО оборачиваться в {} скобки;
        // если тело метода не оборачивается в {} скобки, то метод неявно возвращает результат (return не пишем);
        // если тело метода оборачивается в {} скобки, то для возврата результата необходимо явно указать return

        Operation plus = (first, second) -> /* return */ first + second;
        // создается анонимный класс, имплементирующий Operation,
        // реализация абстрактного метода - лямбда выражение
        // создается экземпляр анонимного класса

        System.out.println(plus.action(34.9, 78.7));

        // написать реализации для вычитания и умножения
        Operation minus = (first, second) -> first - second;
        System.out.println(minus.action(34.9, 90.2));

        printResult(plus);
        printResult(minus);
        printResult((a, b) -> a * b);

        Operation div = (a, b) -> {
            if (b == 0) return 0;
            return a / b;
        };
        System.out.println(div.action(3.8, 9.9));

        // Функциональные интерфейсы объявленные в java находятся в пакете java.util.function:
        // Predicate<T> + boolean test(T t);
        // Function<T, R> + R apply(T t);
        // Consumer<T> + void accept(T t);
        // и т.д
        ArrayList<Integer> integers =
                new ArrayList<>(Arrays.asList(875, -78, 12, 56, 34, -89, 0, 344));

        // boolean test(T t);
        Predicate<Integer> negative = integer -> integer < 0;
        System.out.println(negative.test(12));

        // метод removeIf используется для удаления элементов коллекции, соответствующих условию
        integers.removeIf(negative);

        // Consumer void accept(T t);
        Consumer<Integer> sout = integer -> System.out.println(integer);
        sout.accept(1000);

        // метод forEach используется для перебора коллекции
        integers.forEach(sout); // нельзя прервать работу метода forEach,
        // пройдет по всем элементам коллекции

        for (Integer integer : integers) { // работу цикла прервать можно,
            // используя break или return
            System.out.println(integer);
        }


        ArrayList<String> strings = new ArrayList<>(
                Arrays.asList("01.txt", "02.json", "03.txt", "04.txt", "05.properties"));

        // удалить из коллекции strings строки, которые заканчиваются на .json и .properties
        Predicate<String> jsonOrProperties = str -> str.endsWith(".json") || str.endsWith(".properties");
        strings.removeIf(jsonOrProperties);


        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit(Fruit.FruitType.APPLE, 120, 5));
        fruits.add(new Fruit(Fruit.FruitType.BANANA, 80, 12));
        fruits.add(new Fruit(Fruit.FruitType.APRICOT, 300, 2));
        fruits.add(new Fruit(Fruit.FruitType.APPLE, 120, 10));
        fruits.add(new Fruit(Fruit.FruitType.PEAR, 180, 2));
        fruits.add(new Fruit(Fruit.FruitType.PEAR, 180, 8));
        fruits.add(new Fruit(Fruit.FruitType.BANANA, 130, 8));

        // удалить из коллекции fruits фрукты с типом BANANA, которые дешевле 100
        Predicate<Fruit> fruitPredicate = fruit -> fruit.getType() == Fruit.FruitType.BANANA &&
                fruit.getPrice() < 100;

        fruits.removeIf(fruitPredicate);

        for (Fruit fruit : fruits) {
            fruit.setCount(fruit.getCount() * 2);
        }

        // увеличить стоимость каждого фрукта в 2 раза методом foreach
        Consumer<Fruit> changePrice = fruit -> fruit.setPrice(fruit.getPrice() * 2);
        fruits.forEach(changePrice);

        /*for (Fruit fruit : fruits) {
            changePrice.accept(fruit);
        }*/


        ArrayList<Fruit> byPrice = getByFilter(fruits, fruit -> fruit.getPrice() > 100);
        byPrice.forEach(fruit -> System.out.println(fruit));

        ArrayList<Fruit> byType = getByFilter(fruits, fruit -> fruit.getType() == Fruit.FruitType.APPLE);
        byType.forEach(fruit -> System.out.println(fruit));

        Comparator<Fruit> compareByPrice = (fruit1, fruit2) -> (int) (fruit2.getPrice() - fruit1.getPrice());
        fruits.sort(compareByPrice); // или Collections.sort(fruits, compareByPrice);
        fruits.forEach(fruit -> System.out.println(fruit));

    }

    public static ArrayList<Fruit> getByFilter(ArrayList<Fruit> fruits, Predicate<Fruit> condition) {
        ArrayList<Fruit> newList = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (condition.test(fruit)) newList.add(fruit);
        }
        return newList;
    }

    public static void printResult(Operation operation) {
        System.out.println(operation.action(34, 89));
    }
}