package multithreading.thread.task1;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(2000);
                System.out.println("Hi, i'm " + Thread.currentThread().getName() + "!");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " has been interrupted");
        }

    }
}
