public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new PrintNum());
        Thread t2=new Thread(new PrintA());
        Thread t3=new Thread(new PrintB());
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MIN_PRIORITY);
        t1.setPriority(Thread.MAX_PRIORITY);
        t3.start();
        t2.start();
        t3.join();
        t1.start();
    }
}

class PrintNum implements Runnable{
    public void run(){
        for(int i=1;i<=100;i++){
            if(i%10==0) System.out.println();
            System.out.print(i+" ");
        }
        System.out.println();
    }
}

class PrintA implements Runnable{
    public void run(){
        for(int i=1;i<=100;i++){
            if(i%10==0) System.out.println();
            if(i==50) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("a");
        }
    }
}

class PrintB implements Runnable{
    public void run(){
        for(int i=1;i<=100;i++){
            if(i%10==0) System.out.println();
            if(i==50) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("b");
        }
    }
}