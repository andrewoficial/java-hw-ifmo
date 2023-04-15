package hw014.task01;

import java.util.Comparator;
import java.util.Objects;

public class Fruit {
    private FruitType type;
    private double price;
    private int count;

    public Fruit(FruitType type, int price, int count) {
        this.type = type;
        this.price = price;
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FruitType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return price == fruit.price && type == fruit.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, price);
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "type=" + type +
                ", price=" + price +
                ", count=" + count +
                '}';
    }

    public enum FruitType {
        APPLE, PEAR, BANANA, APRICOT;

        public String getName(){
            return this.name().substring(0,1).toUpperCase() + this.name().substring(1).toLowerCase();
        }
    }


    public static class NameComparator implements Comparator<Fruit> {

        @Override
        public int compare(Fruit o1, Fruit o2) {
            return o2.type.getName().compareTo(o1.getType().getName());
        }

    }

    public static class PriceComparator implements Comparator<Fruit> {

        @Override
        public int compare(Fruit o1, Fruit o2) {
            return (int) (o2.price - o1.price);
        }
    }

}
