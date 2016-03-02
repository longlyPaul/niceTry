package thinking;

import java.util.concurrent.*;

/**
 * Created by liusonglin on 15/11/8.
 */
public class Test {
    public static void main(String[] args){
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=0;i<=10;i++){
            Future<String> future=executorService.submit(new CallableTest(i));
//            future.isCancelled();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            TimeUnit.SECONDS.timedWait();
            try {
                CommonUtils.printObjec(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
