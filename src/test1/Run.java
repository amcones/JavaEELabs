package test1;

public class Run {
    public static void main(String[] args) {
        try {
            DealThread t1 = new DealThread();
            t1.setFlag("a");

            Thread thread1 = new Thread(t1);
            thread1.start();

            Thread.sleep(100);

            t1.setFlag("b");
            Thread thread2 = new Thread(t1);
            thread2.start();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class DealThread implements Runnable {
    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setFlag(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        if (username.equals("a")) {
            synchronized (lock1) {
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(30);
//                    lock1.wait(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("按lock1->lock2代码顺序执行了");
                }
            }
        }
        else if (username.equals("b")) {
            synchronized (lock2) {
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
//                    lock2.wait(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("按lock2->lock1代码顺序执行了");
                }
            }
        }
    }
}