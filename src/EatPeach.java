public class EatPeach {
    public static void main(String[] args) {
        Thread t=new Thread(new PeachTree());
        t.start();
    }
}

class PeachTree implements Runnable{
    private int peachNum;
    public PeachTree(){
        peachNum=100;
    }
    class Fairies implements Runnable{

        @Override
        public synchronized void run() {
            while(true){
                System.out.println("当前有"+peachNum+"个桃子");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Monkey implements Runnable{

        @Override
        public synchronized void run() {
            while(true){
                peachNum--;
                System.out.println("吃了1个桃子，还剩"+peachNum+"个桃子");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public synchronized void run() {
        Thread monkey=new Thread(new Monkey());
        Thread fairies=new Thread(new Fairies());
        monkey.start();
        fairies.start();
        while(true) {
            int newPeach = (int) (Math.random() * 10) + 1;
            peachNum += newPeach;
            System.out.println("结了" + newPeach + "个果子，现在有" + peachNum + "个果子");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

