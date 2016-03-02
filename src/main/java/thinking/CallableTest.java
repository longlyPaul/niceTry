package thinking;

import java.util.concurrent.Callable;

/**
 * Created by liusonglin on 15/11/8.
 */
public class CallableTest implements Callable<String> {
    private int id;
    private Integer count=new Integer(10);
//    private int count;
    public CallableTest(int id){
//        count.set(10);
        this.id=id;
    }
    @Override
    public String call() throws Exception {
        count++;
        return String.valueOf(id + count);
    }
}
