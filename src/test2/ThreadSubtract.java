package test2;

public class ThreadSubtract extends Thread {
    private Subtract c;

    public ThreadSubtract(Subtract c) {
        this.c = c;
    }

    public void run() {
        c.subtract();
    }
}