import java.util.concurrent.*;

class CalculateSum implements Callable<Boolean>
{

    @Override
    public Boolean call() throws Exception {
        int sum=0;
        for(int i=1;i<=100;i++){
            sum+=i;
            if(Thread.interrupted()){
                return false;
            }
        }
        System.out.println(sum);
        return true;
    }
}

class PrintChar implements Callable<Boolean>
{
    char c;
    public PrintChar(char c){
        this.c=c;
    }
    @Override
    public Boolean call() throws Exception{
        for(int i=1;i<=100;i++){
            System.out.print(c);
            if(Thread.interrupted()){
                return false;
            }
        }
        System.out.print('\n');
        return true;
    }
}

public class TestThread {
    public static void main(String[] args) {
        CalculateSum task1 = new CalculateSum();
        PrintChar task2 = new PrintChar('a');
        PrintChar task3 = new PrintChar('b');
        ExecutorService es = Executors.newFixedThreadPool(3);
        try {
            Future<?> future = es.submit(task1);
            future.get();
            future=es.submit(task2);
            future.get();
            future=es.submit(task3);
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        es.shutdownNow();
    }
}
