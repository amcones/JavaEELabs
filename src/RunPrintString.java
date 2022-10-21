class PrintString implements Runnable{
    private boolean isContinuePrint = true;

    public boolean isContinuePrint() {
        return this.isContinuePrint;
    }

    public void setContinuePrint(boolean isContinuePrint) {
        this.isContinuePrint = isContinuePrint;
    }

    public void printStringMethod() {
        try {
            while (isContinuePrint == true) {
                System.out.println("run printStringMethod threadName="
                        + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        printStringMethod();
    }
}

public class RunPrintString {
    public static void main(String[] args) throws Exception {
        PrintString printStringService=new PrintString();
        Thread t=new Thread(printStringService);
        t.start();
        System.out.println("我要停止它！stopThread=" + Thread.currentThread().getName());
        printStringService.setContinuePrint(false);
        //System.out.println(printStringService.isContinuePrint());
    }
}
