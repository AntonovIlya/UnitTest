package multithreading.snhrnzd.task1;

public class Dealer implements Runnable {

    private final Showroom showroom;

    private static final int DELIVERY_TIME = 1000;

    public Dealer(Showroom showroom) {
        this.showroom = showroom;
    }

    @Override
    public void run() {
        for (int i = 0; i < Showroom.QUANTITY; i++) {
            try {
                Thread.sleep(DELIVERY_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            showroom.add();
        }
    }

}
