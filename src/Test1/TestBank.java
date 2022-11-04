package Test1;

import java.util.concurrent.*;

class RunBank implements Callable {
    Bank bank;

    public RunBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public Integer call() throws Exception{
        for (int i = 1; i <= 10; ++i) {
            bank.deposit(10);
        }
        return bank.getAccount();
    }
}

public class TestBank {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Bank bank = new Bank();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        Future<Integer> t1 = fixedThreadPool.submit(new RunBank(bank));
        Future<Integer> t2 = fixedThreadPool.submit(new RunBank(bank));
        Integer r1 = t1.get();
        Integer r2 = t2.get();
        fixedThreadPool.shutdown();
        System.out.println("第一个线程运行后账号余额："+r1);
        System.out.println("第二个线程运行后账号余额："+r2);
    }
}
