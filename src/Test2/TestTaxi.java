package Test2;

import java.util.concurrent.CountDownLatch;


class Guest implements Runnable{
    CountDownLatch order;
    public Guest(CountDownLatch order){
        this.order=order;
    }
    public void run()
    {
        order.countDown();
        System.out.println("加入一人拼车，还需"+order.getCount()+"人");
    }
}

public class TestTaxi {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch order = new CountDownLatch(3);
        for(int i=0;i<3;i++) {
            Thread guest = new Thread(new Guest(order));
            Thread.sleep(1000);
            guest.start();
        }
        try{
            order.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("订单已满三人，发车");
    }
}
