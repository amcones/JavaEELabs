package Test1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Servant implements Runnable {
    private Plate plate;

    public Servant(Plate plate) {
        this.plate = plate;
    }

    public void run() {
        for (int i = 0; i < 1000; ++i) {
            try {
                plate.addPeach();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Guest implements Runnable {
    private Plate plate;

    public Guest(Plate plate) {
        this.plate = plate;
    }

    public void run() {
        for (int i = 0; i < 1000; ++i) {
            try {
                plate.getPeach();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Peach {

}

class Plate {
    private BlockingQueue<Peach> plate = new ArrayBlockingQueue<>(5);

    synchronized void getPeach() throws InterruptedException {
        if (!plate.isEmpty()) {
            plate.remove();
            System.out.println("拿了一个桃子，当前还有" + plate.size() + "个桃子");
        }
    }

    synchronized void addPeach() throws InterruptedException {
        Peach peach = new Peach();
        if (plate.remainingCapacity() > 0) {
            plate.add(peach);
            System.out.println("添加了一个桃子，当前还有" + plate.size() + "个桃子");
        }
    }
}

public class TestPeach {
    public static void main(String[] args) {
        Plate plate = new Plate();
        Thread servant = new Thread(new Servant(plate));
        Thread guest = new Thread(new Guest(plate));
        servant.start();
        guest.start();
    }
}
