package multithreadingexercises.numberofdivisors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by LD on 06/10/2017.
 */
public class Consumer extends Thread {

    BlockingQueue<Task> sharedTaskBlockingQueue;
    ExecutorService myThreadPool;
    boolean isFinished = false;

    public Consumer(BlockingQueue<Task> sharedTaskBlockingQueue) {
        this.sharedTaskBlockingQueue = sharedTaskBlockingQueue;
        myThreadPool = Executors.newFixedThreadPool(5);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = sharedTaskBlockingQueue.take();
                System.out.println("Consumer::Retrieved Task:" + task.num + " From queue");
                if (task.num == -999) {
                    System.out.println("Consumer::Task is Poison, break");
                    break;
                }
                myThreadPool.submit(task);
                System.out.println("Consumer::Submitted Task:" + task.num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        myThreadPool.shutdown();
        try {
            myThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Consumer::Finished");
    }
}
