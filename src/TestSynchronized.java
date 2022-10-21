class Apple{
    private Integer num;
    public Apple(int num){
        this.num=num;
    }

    public void sellApple() throws InterruptedException {
        synchronized (num) {
            Thread.sleep(2000);
            num--;
            System.out.println("对象属性已改变，还剩" + num + "个。");
        }
    }
}

class Shop implements Runnable{
    Apple apple;
    public Shop(Apple apple){
        this.apple=apple;
    }
    @Override
    public void run(){
        try {
            apple.sellApple();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class TestSynchronized {
    public static void main(String[] args) throws InterruptedException {
        Apple apple=new Apple(20);
        Shop s1=new Shop(apple);
        Shop s2=new Shop(apple);
        Thread t1=new Thread(s1);
        Thread t2=new Thread(s2);
        t1.start();
        t2.start();
    }
}
