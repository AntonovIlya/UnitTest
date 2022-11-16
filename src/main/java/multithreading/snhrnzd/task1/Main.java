package multithreading.snhrnzd.task1;

public class Main {

    private static final int NUMBER_OF_BUYERS = 10;

    public static void main(String[] args) {

        Showroom showroom = new Showroom();
        Bayer bayer = new Bayer(showroom);
        Dealer dealer = new Dealer(showroom);
        new Thread(dealer, "Dealer").start();
        for (int i = 0; i < NUMBER_OF_BUYERS; i++) {
            new Thread(bayer, "Bayer " + i).start();
        }
    }
}
