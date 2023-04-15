package hw014.task01;

public class CollectionTask01 {
    public static void main(String[] args) {

        // реализовать методы класса FruitStorage
        // не использовать lambda выражения и Stream API

        Fruit fruit01 = new Fruit(Fruit.FruitType.APPLE, 120, 5);
        Fruit fruit02 = new Fruit(Fruit.FruitType.BANANA, 80, 12);
        Fruit fruit03 = new Fruit(Fruit.FruitType.APRICOT, 300, 2);
        Fruit fruit04 = new Fruit(Fruit.FruitType.APPLE, 120, 10);
        Fruit fruit05 = new Fruit(Fruit.FruitType.PEAR, 180, 2);
        Fruit fruit06 = new Fruit(Fruit.FruitType.PEAR, 180, 8);
        Fruit fruit07 = new Fruit(Fruit.FruitType.BANANA, 130, 8);

        FruitStorage storage = new FruitStorage();
        storage.addFruit(fruit01); // элемент добавлен в коллекцию ... на складе 5
        storage.addFruit(fruit02); // элемент добавлен в коллекцию ... на складе 17
        //System.out.println("Всего на складе фруктов:"+storage.getNumberOfFruits()); ...17
        storage.addFruit(fruit03); // элемент добавлен в коллекцию ... на складе 19
        storage.addFruit(fruit03); // элемент не добавлен в коллекцию, тк ссылка на этот объект уже есть в коллекции ... на складе 19
        //System.out.println("Всего на складе фруктов:"+storage.getNumberOfFruits());  ...19
        storage.addFruit(fruit04); // элемент с типом APPLE и ценой - 120 уже есть в коллекции,
        // поэтому его количество увеличивается на 10 и теперь равно 15 ... на складе 29
        //System.out.println("Всего на складе фруктов:"+storage.getNumberOfFruits()); ... на складе 29
        storage.addFruit(fruit05); // элемент добавлен в коллекцию ... на складе 31
        storage.addFruit(fruit06); // элемент с типом PEAR и ценой - 180 уже есть в коллекции,
        // поэтому его количество увеличивается на 8 и теперь равно 10 ... на складе 39
        storage.addFruit(fruit07); // элемент добавлен в коллекцию ... на складе 47

        System.out.println("Фруктов "+ Fruit.FruitType.APRICOT + " найдено:" +
                storage.getNumberOfFruitsByType(Fruit.FruitType.APRICOT)); // 2
        System.out.println("Фруктов "+ Fruit.FruitType.BANANA + " найдено:" +
                storage.getNumberOfFruitsByType(Fruit.FruitType.BANANA)); // 20
        System.out.println("Фруктов "+ Fruit.FruitType.APPLE + " найдено:" +
                storage.getNumberOfFruitsByType(Fruit.FruitType.APPLE)); // 15
        System.out.println("Фруктов "+ Fruit.FruitType.PEAR + " найдено:" +
                storage.getNumberOfFruitsByType(Fruit.FruitType.PEAR)); // 10

        System.out.println("Всего на складе фруктов:"+storage.getNumberOfFruits());

        System.out.println("До повышения цены");
        storage.printArray();
        storage.increasePrice(10);
        System.out.println("После повышения цены");
        storage.printArray();

        System.out.println("Общая стоимость фруктов на складе: " + storage.getFruitsPrice());

        System.out.println(storage.getFruitsByTypeAndPrice(Fruit.FruitType.BANANA, 150));
        // [type=BANANA, price=88, count=12; type=BANANA, price=143, count=8]



    }
}