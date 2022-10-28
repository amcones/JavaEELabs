package Test4;

class Clock implements Runnable {
    private static int second, minute, hour;
    Object lock;
    private String method;
    volatile static boolean setMinute = false, setHour = false;

    public Clock(String method, Object lock) {
        this.method = method;
        this.lock = lock;
    }

    void addSecond() throws InterruptedException {
        synchronized (lock) {
            Thread.sleep(1000);
            ++second;
            if (second == 60) {
                setMinute = true;
                second = 0;
                lock.notifyAll();
                lock.wait();
            }
            System.out.println("当前时间：" + hour + ':' + minute + ':' + second);
        }
    }

    void addMinute() throws InterruptedException {
        synchronized (lock) {
            if (setMinute) {
                minute++;
                setMinute = false;
                if (minute == 60) {
                    setHour = true;
                    minute = 0;
                }
            }
            lock.notifyAll();
            lock.wait();
        }
    }

    void addHour() throws InterruptedException {
        synchronized (lock) {
            if (setHour) {
                hour++;
                setHour = false;
                if (hour == 24) {
                    hour = 0;
                }
            }
            lock.notifyAll();
            lock.wait();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (method.equals("second")) {
                try {
                    addSecond();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if (method.equals("minute")) {
                try {
                    addMinute();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    addHour();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

public class TestClock {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Clock second = new Clock("second", lock);
        Clock minute = new Clock("minute", lock);
        Clock hour = new Clock("hour", lock);
        Thread t1 = new Thread(second);
        Thread t2 = new Thread(minute);
        Thread t3 = new Thread(hour);
        t1.start();
        t2.start();
        t3.start();
    }
}
