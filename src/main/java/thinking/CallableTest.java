package thinking;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liusonglin on 15/11/8.
 */
public class CallableTest implements Callable<String> {
    private int id;
    private AtomicInteger count = new AtomicInteger(10);
    public CallableTest(int id){
//        count.set(10);
        this.id=id;
        System.out.println("new id :"+id);
    }
    @Override
    public String call() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(this.id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(Thread.currentThread().getId()+"----->"+id + count.addAndGet(1));
    }
}
