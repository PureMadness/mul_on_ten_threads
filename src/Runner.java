import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Runner {

    public static int[] generateArray(int size){
        int[] array = new int[size];
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < 100000; i++){
            array[i] = random.nextInt(999)+1;
        }
        return array;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        int[] array = generateArray(100000);
        List<Task> tasks = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Task task = new Task(i, array);
            tasks.add(task);
        }
        List<Future<BigInteger>> partRes = exec.invokeAll(tasks);
        BigInteger result = BigInteger.ONE;
        for(Future<BigInteger> res : partRes){
            result = result.multiply(res.get());
        }
        exec.shutdown();
        System.out.println("Result: " + result);

    }

}
