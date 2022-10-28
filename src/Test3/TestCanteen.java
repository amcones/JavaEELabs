package Test3;

import java.util.concurrent.Semaphore;

class Canteen {
    Semaphore windows = new Semaphore(5);

    void tryGetDish() throws InterruptedException {
        windows.acquire();
        System.out.println("学生正在取餐");
        Thread.sleep(200);
        System.out.println("学生完成取餐");
        windows.release();
    }
}

class Student implements Runnable {
    Canteen canteen;

    public Student(Canteen canteen) {
        this.canteen = canteen;
    }

    public void run() {
        try {
            canteen.tryGetDish();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class TestCanteen {
    public static void main(String[] args) {
        Canteen canteen = new Canteen();
        for (int i = 0; i < 100; ++i) {
            Thread student = new Thread(new Student(canteen));
            student.start();
        }
    }
}
