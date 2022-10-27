package multithreading.snhrnzd.task1;

public class Bayer extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i ++) {
            synchronized (Main.list) {
                if (Main.list.isEmpty()) {
                    try {
                        Main.list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Main.list.remove(0);
                System.out.println("Bayer: \"Yes, i bought a new car!\"");
            }
        }
    }
}
