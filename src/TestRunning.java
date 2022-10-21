class Competition {
    private Boolean isFinish;

    public Competition() {
        isFinish = false;
    }

    public void setFinish() {
        synchronized (isFinish) {
            isFinish = true;
        }
    }

    public Boolean getFinish() throws InterruptedException {
        return isFinish;
    }
}

class RunningMan implements Runnable {
    private int speed;
    private Integer distance;
    private Competition competition;

    public RunningMan(int speed, Competition competition) {
        this.speed = speed;
        this.competition = competition;
        this.distance = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
                if (competition.getFinish()) {
                    System.out.println("比赛结束！");
                    return;
                }
                synchronized (distance) {
                    distance += speed;
                    System.out.println(Thread.currentThread().getName() + "当前距离：" + distance);
                    if (distance >= 200) {
                        competition.setFinish();
                        return;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class TestRunning {
    public static void main(String[] args) {
        Competition competition = new Competition();
        RunningMan zhoushen = new RunningMan(4, competition);
        RunningMan lichen = new RunningMan(7, competition);
        RunningMan zhenkai = new RunningMan(8, competition);
        RunningMan shayi = new RunningMan(4, competition);
        RunningMan caixukun = new RunningMan(7, competition);
        RunningMan yangying = new RunningMan(3, competition);
        RunningMan bailu = new RunningMan(3, competition);
        Thread t1 = new Thread(zhoushen);
        Thread t2 = new Thread(lichen);
        Thread t3 = new Thread(zhenkai);
        Thread t4 = new Thread(shayi);
        Thread t5 = new Thread(caixukun);
        Thread t6 = new Thread(yangying);
        Thread t7 = new Thread(bailu);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
    }
}
