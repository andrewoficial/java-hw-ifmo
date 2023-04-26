package hw017.task02;

public class Lesson1702 {
    public static void main(String[] args) {


        Payment sberPay = acc -> {
            // если платеж выполнен метод возвращает true
            if (Math.random() < 0.5) {
                System.out.println("sberPay: false");
                return false;
            }
            System.out.println("sberPay: true");
            return true;
        };

        Payment vkPay = acc -> {
            // если платеж выполнен метод возвращает true
            if (Math.random() < 0.5) {
                System.out.println("vkPay: false");
                return false;
            }
            System.out.println("vkPay: true");
            return true;
        };

        Payment yaPay = acc -> {
            // если платеж выполнен метод возвращает true
            if (Math.random() < 0.5) {
                System.out.println("yaPay: false");
                return false;
            }
            System.out.println("yaPay: true");
            return true;
        };


        Account account = new Account();

        // следующий способ платежа должен отработать только,
        // если предыдущий завершился неудачей
        if (account.pay(vkPay.nextPayment(sberPay).nextPayment(yaPay))) {
            System.out.println("Платёж выполнен");
        } else {
            System.out.println("Платёж не был выполнен");
        }
    }
}
