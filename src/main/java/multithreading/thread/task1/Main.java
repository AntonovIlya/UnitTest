package multithreading.thread.task1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("group");
        MyRunnable myRunnable = new MyRunnable();
        final Thread thread0 = new Thread(group, myRunnable);
        final Thread thread1 = new Thread(group, myRunnable);
        final Thread thread2 = new Thread(group, myRunnable);
        thread0.start();
        thread1.start();
        thread2.start();
        Thread.sleep(10000);
        group.interrupt();


    }
}
