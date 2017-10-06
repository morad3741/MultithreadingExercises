package multithreadingexercises.numberofdivisors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by LD on 06/10/2017.
 */
public class Producer extends Thread {

    int maxNumberToCheck;
    BlockingQueue<Task> sharedTaskBlockingQueue;
    ConcurrentHashMap<Integer, Integer> sharedResultMap;

    public Producer(BlockingQueue<Task> sharedBlockingQueue, ConcurrentHashMap<Integer, Integer> sharedResultMap, int maxNumberToCheck) {
        this.maxNumberToCheck = maxNumberToCheck;
        this.sharedTaskBlockingQueue = sharedBlockingQueue;
        this.sharedResultMap = sharedResultMap;
    }

    @Override
    public void run() {
        int currentNumber = 1;
        while (currentNumber < maxNumberToCheck) {
            Task newTask = new Task(currentNumber++, sharedResultMap);
            System.out.println("Producer::Created New Task For:" + currentNumber);
            try {
                sharedTaskBlockingQueue.put(newTask);
                System.out.println("Producer::inserted Task " + currentNumber + " In Queue, Number Of Elements in queue:" + sharedTaskBlockingQueue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Producer::Finished");
        System.out.println("Producer::Created Poison");
        Task poisonTask = new Task(-999, null);
        try {
            sharedTaskBlockingQueue.put(poisonTask);
            System.out.println("Producer::Inserted Poison Into Queue");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
