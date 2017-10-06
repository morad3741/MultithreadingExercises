package multithreadingexercises.numberofdivisors;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by LD on 06/10/2017.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Task> sharedTaskBlockingQueue = new ArrayBlockingQueue<Task>(10);
        ConcurrentHashMap<Integer, Integer> sharedResultMap = new ConcurrentHashMap();

        Producer producer = new Producer(sharedTaskBlockingQueue, sharedResultMap, 10000);
        Consumer consumer = new Consumer(sharedTaskBlockingQueue);

        System.out.println("Main::Started Producer Thread");
        producer.start();
        System.out.println("Main::Started Consumer Thread");
        consumer.start();


        // starting iterate over the results without stopping the consumer (with join)
        int numberWithLargestDivisor = 0;
        int maxDivisor = 0;
        while (producer.isAlive() || consumer.isAlive()) {
            Thread.sleep(500);
            Set<Map.Entry<Integer, Integer>> entrySet = sharedResultMap.entrySet();
            Iterator<Map.Entry<Integer, Integer>> itr = entrySet.iterator();

            while (itr.hasNext()) {
                Map.Entry<Integer, Integer> entry = itr.next();
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                if (value > maxDivisor) {
                    maxDivisor = value;
                    numberWithLargestDivisor = key;
                }

                itr.remove();
            }
        }

        System.out.println("Main::Finished, Number With Largest Divisors = " + numberWithLargestDivisor + ", Maximum Divisors = " + maxDivisor);

    }
}
