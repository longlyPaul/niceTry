package thinking;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liusonglin on 15/11/8.
 */
public class CallableTest implements Callable<String> {
    private int id;
    private AtomicInteger count = new AtomicInteger(0);
    private Random random = new Random(1);
    public CallableTest(int id){
//        count.set(10);
        this.id=id;
        System.out.println("new id :"+id);
    }
    @Override
    public String call() throws Exception {
        int time = random.nextInt(11) + 1;
        System.out.println("time = "+time);
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(Thread.currentThread().getId()+"----->"+ count.addAndGet(time));
    }
}
