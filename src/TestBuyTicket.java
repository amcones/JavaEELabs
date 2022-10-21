class Ticket {
    private int price = 100;
    private int number;

    public synchronized int getNumber() {
        return number;
    }

    public Ticket(int number) {
        this.number = number;
    }

    public synchronized boolean sellTicket(String threadName) throws InterruptedException {
        if(number>0) {
            --number;
            System.out.println(threadName + "卖出去一张票，还剩" + number + "张。");
            Thread.sleep(200);
            return true;
        }
        else{
            System.out.println("票已售罄");
            return false;
        }
    }
}

class Window implements Runnable {
    Ticket ticket;

    public Window(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while(true) {
            try {
                if (!ticket.sellTicket(Thread.currentThread().getName())) break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        }
    }
}

public class TestBuyTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket(200);
        Window winA = new Window(ticket);
        Window winB = new Window(ticket);
        Window winC = new Window(ticket);
        Thread t1 = new Thread(winA);
        Thread t2 = new Thread(winB);
        Thread t3 = new Thread(winC);
        t1.start();
        t2.start();
        t3.start();
    }
}
