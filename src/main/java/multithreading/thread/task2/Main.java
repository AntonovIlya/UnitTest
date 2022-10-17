package multithreading.thread.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception {

        List<Callable<String>> tasksList = Arrays.asList(
                new MyCallable(3, 2000),
                new MyCallable(4, 1000),
                new MyCallable(5, 200));

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        Future<String> task = threadPool.submit(tasksList.get(0));
        System.out.println(task.get());

        //System.out.println(threadPool.invokeAny(tasksList));

        /*List<Future<String>> list = threadPool.invokeAll(tasksList);

        for (Future<String> f : list) {
            System.out.println(f.get());
        }*/

        threadPool.shutdown();





    }
}
