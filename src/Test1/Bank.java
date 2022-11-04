package Test1;

public class Bank {
    private static ThreadLocal<Integer> account = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 100;
        }
    };

    public synchronized void deposit(int money) {
        account.set(account.get() + money);
    }

    public int getAccount() {
        return account.get();
    }
}