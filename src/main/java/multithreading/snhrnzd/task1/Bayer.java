package multithreading.snhrnzd.task1;

public class Bayer implements Runnable {

    private final Showroom showroom;

    public Bayer(Showroom showroom) {
        this.showroom = showroom;
    }

    @Override
    public void run() {
        showroom.get();
    }
}
