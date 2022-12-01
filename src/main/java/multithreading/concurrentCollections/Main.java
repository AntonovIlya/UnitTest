package multithreading.concurrentCollections;


public class Main {

    private static final int COFFEE_BREAK = 2000;

    public static void main(String[] args) throws InterruptedException {

        CallsQueue queue = new CallsQueue();

        Thread ATC = new Thread(new ATC(queue));
        Thread specialist1 = new Thread(new Specialist(queue),"Specialist1");
        Thread specialist2 = new Thread(new Specialist(queue),"Specialist2");
        Thread specialist3 = new Thread(new Specialist(queue),"Specialist3");

        ATC.start();
        Thread.sleep(COFFEE_BREAK);
        specialist1.start();
        specialist2.start();
        specialist3.start();

        try {
            specialist1.join();
            specialist2.join();
            specialist3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
