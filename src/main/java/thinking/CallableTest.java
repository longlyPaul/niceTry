package thinking;

import java.util.concurrent.Callable;

/**
 * Created by liusonglin on 15/11/8.
 */
public class CallableTest implements Callable<String> {
    private int id;
    private ThreadLocal<Integer> count=new ThreadLocal<Integer>();
//    private int count;
    public CallableTest(int id){
        count.set(10);
        this.id=id;
    }
    @Override
    public String call() throws Exception {
        return String.valueOf(id + count.get());
    }
}
