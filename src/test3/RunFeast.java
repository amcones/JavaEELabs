package test3;

class Guest {
    private String lock;
    private int runTime=20;

    public Guest(String lock) {
        this.lock = lock;
    }

    public void getPeach() {
        while(runTime--!=0) {
            try {
                synchronized (lock) {
                    while (Plate.peach == 0) {
                        lock.notifyAll();
                        lock.wait();
                    }
                    Plate.peach--;
                    System.out.println("guest get a peach, peach number=" + Plate.peach);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Servant {
    private String lock;
    private int runTime=20;

    public Servant(String lock) {
        this.lock = lock;
    }

    public void addPeach() {
        while(runTime--!=0) {
            try {
                synchronized (lock) {
                    while (Plate.peach == 5) {
                        lock.notifyAll();
                        lock.wait();
                    }
                    Plate.peach++;
                    System.out.println("servant add a peach, peach number=" + Plate.peach);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class RunFeast {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        Servant servant = new Servant(lock);
        Guest guest = new Guest(lock);
        ThreadServant servantThread = new ThreadServant(servant);
        ThreadGuest guestThread = new ThreadGuest(guest);
        guestThread.start();
        Thread.sleep(1000);
        servantThread.start();
    }
}
