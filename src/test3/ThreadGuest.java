package test3;

public class ThreadGuest extends Thread {
    private Guest c;

    public ThreadGuest(Guest c) {
        this.c = c;
    }

    public void run() {
        c.getPeach();
    }
}