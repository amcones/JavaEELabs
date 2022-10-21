package test3;

public class ThreadServant extends Thread {
    private Servant p;

    public ThreadServant(Servant p) {
        this.p = p;
    }

    public void run() {
        p.addPeach();
    }
}