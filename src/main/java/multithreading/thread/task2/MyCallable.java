package multithreading.thread.task2;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {

    private int count;
    private final int THRESHOLD;
    private final int DELAY;

    public MyCallable(int threshold,int delay) {
        this.THRESHOLD = threshold;
        this.DELAY = delay;
    }

    @Override
    public String call() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(DELAY);
                System.out.println("Hi, i'm " + Thread.currentThread().getName() + "!");
                count++;
                if (count == THRESHOLD) return String.valueOf(count);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " has been interrupted");
        }
        return null;
    }
}
