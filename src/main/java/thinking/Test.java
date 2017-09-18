package thinking;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by liusonglin on 15/11/8.
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService= Executors.newCachedThreadPool();
        List<Future<String>> futures = new ArrayList<Future<String>>(16);
        Random random = new Random(1);
        CallableTest myCall = new CallableTest(random.nextInt(11) + 1);
        for(int i=0;i<=10;i++){
            Future<String> future=executorService.submit(myCall);
            futures.add(future);
        }
        while (futures.size()>0) {
            ListIterator<Future<String>> item = futures.listIterator();
            while (item.hasNext()) {
                Future future = item.next();
                if (future.isDone()) {
                    CommonUtils.printObjec(future.get());
                    item.remove();
                }
            }
        }

        executorService.shutdown();
        if (executorService.isShutdown()){
            System.out.println("done");
        }
    }
}
