package multithreadingexercises.numberofdivisors;

import multithreadingexercises.utils.Utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by LD on 06/10/2017.
 */
public class Task implements Callable<Boolean> {

    public int num;
    private ConcurrentHashMap<Integer, Integer> sharedResultMap;

    public Task(int num, ConcurrentHashMap<Integer, Integer> sharedResultMap) {
        this.num = num;
        this.sharedResultMap = sharedResultMap;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println("Task::Started Task:" + num);
        int result = Utils.getMaxDivisorsOfNumber(num);
        System.out.println("Task::result:" + result);
        sharedResultMap.put(num, result);
        return true;
    }


}
