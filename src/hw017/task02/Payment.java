package hw017.task02;

public interface Payment {
    boolean makePayment(Account account);
    default Payment nextPayment(Payment payment){
        return (account) -> {
            if (makePayment(account)) return true;
            return payment.makePayment(account);
        };
    }
}