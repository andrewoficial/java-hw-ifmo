package hw001;

public class HomeWork001 {
    public static void main(String[] args) {
        /*
        Даны длина, ширина прямоугольника. Найти и вывести в консоль его площадь.
         */
        int a = 5;
        int b = 6;
        System.out.println(a * b);

        /*
        Дано целое число. Найти и вывести в консоль данное число в кубе
         */
        int c = 5;
        System.out.println(c * c * c);

        /*
        Даны две переменные типа double: время (в часах) и расстояние
        (в километрах). Найти и вывести в консоль скорость, выраженную в метрах в секунду.
         */

        double d, e;
        d = 1.5;
        e = 160;
        System.out.printf("%.2f\n",e/d);
    }
}
