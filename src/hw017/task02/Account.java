package hw017.task02;

public class Account {
    public boolean pay(Payment payment) {
        return payment.makePayment(this);
    }
}