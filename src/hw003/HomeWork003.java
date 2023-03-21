package hw003;

import java.util.Scanner;

public class HomeWork003 {
    public static void main(String[] args) {
        /*
        Задача на FOR
            Вывести на экран первые 10 элементов последовательности 2 4 6 8 10 и тд
        */
        for (int i = 2; i < 21; i += 2) {
            System.out.println(i);
        }


        /*
        Задача на WHILE
            Для введённого пользователем с клавиатуры целого положительного числа посчитайте сумму всех его цифр
         */
        System.out.println("Enter number");
        Scanner sc = new Scanner(System.in);
        int userInp = sc.nextInt();
        int answ = 0;
        while(userInp % 10 > 0){
            answ += userInp%10;
            userInp /= 10;
        }
        System.out.println(answ);

        /*
        Задача на использование любого цикла (выберите сами)
            Программа загадывает число в диапазоне [1;9]
            Пользователь вводит число с клавиатуры
            Программа в зависимости от введенного числа выводит в консоль следующее:
                "загаданное число больше"
                "загаданное число меньше"
                "Вы угадали" (программа завершает работу)
            Если введен 0, вывести "выход из программы" (программа завершает работу)
         */

        int randomNum = 1 + (int) (Math.random() * 9);
        System.out.println("Enter number");
        do{
            userInp = sc.nextInt();
            if (randomNum > userInp) {
                System.out.println("загаданное число больше");
            }
            if (randomNum < userInp) {
                System.out.println("загаданное число меньше");
            }
            if (randomNum == userInp) {
                System.out.println("Вы угадали");
                break;
            }
            if (0 == userInp) {
                System.out.println("выход из программы");
                break;
            }
        }while(randomNum != userInp);

        /*
        Задача на использование любого цикла (выберите сами) ***
            Пользователь загадывает число в диапазоне от [2 до 100]
            Программа пытается его угадать (используйте метод бинарного поиска, т.е деление отрезка на 2).
            Программа может задавать пользователю вопросы: Число равно ...?, Число больше ...? и в зависимоти от ответа пользователя принимать решения.
            Вместо текстовых ответов ДА/НЕТ, используйте числа 0 - НЕТ и 1 - ДА
            Подумайте, что можно сделать, если пользователь загадает число вне указанного диапазона.
        Для вывода текста и значения переменной используйте оператор сложения, например System.out.println("Число равно " + number);
         */
        answ = 49;
        int high = 100;
        int low = 2;
        while (true){
            if(answ < 2 || answ > 100){
                System.out.println("Dunno...");
                break;
            }
            System.out.println("Число равно " + answ + "?");
            userInp = sc.nextInt();
            if(userInp == 1){
                System.out.println("Ура!");
                break;
            }else{
                System.out.println("Число больше " + answ + "?");
                userInp = sc.nextInt();
                if(userInp == 1){
                    low = answ + 1;
                }else {
                    high = answ - 1;
                }
                answ = (low + high) / 2;
            }
        }
    }
}
