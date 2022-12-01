package multithreading.concurrentCollections;

public class ATC implements Runnable {

    private static final int NUMBER_OF_CALLS = 10;
    private static final int CALL_DELAY = 500;

    private final CallsQueue callsQueue;

    public ATC(CallsQueue callsQueue) {
        this.callsQueue = callsQueue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < NUMBER_OF_CALLS + 1; i++) {
                callsQueue.getBlockingQueue().put("Call" + i);
                System.out.println("Call" + i + " add");
                Thread.sleep(CALL_DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
