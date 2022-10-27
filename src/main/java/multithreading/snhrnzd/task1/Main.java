package multithreading.snhrnzd.task1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int QUANTITY = 10;

    public static List<String> list = new ArrayList<>(Main.QUANTITY);

    public static void main(String[] args) throws InterruptedException {
        Dealer dealer = new Dealer();
        Bayer bayer = new Bayer();
        dealer.start();
        bayer.start();
    }
}
