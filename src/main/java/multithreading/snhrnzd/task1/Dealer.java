package multithreading.snhrnzd.task1;

public class Dealer extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (Main.list) {
                Main.list.add("Car " + i);
                System.out.println("Dealer: \"New car available!\"");
                Main.list.notify();
            }
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
