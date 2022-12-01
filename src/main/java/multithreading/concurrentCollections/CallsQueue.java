package multithreading.concurrentCollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CallsQueue {

    private static final int INITIAL_CAPACITY = 100;

    private BlockingQueue<String> blockingQueue;

    public CallsQueue() {
        blockingQueue = new ArrayBlockingQueue<>(INITIAL_CAPACITY, true);
    }

    public BlockingQueue<String> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
}
