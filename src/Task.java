import java.math.BigInteger;
import java.util.concurrent.Callable;

public class Task implements Callable<BigInteger>{
    int numberOfThread;
    int array[];

    public Task(int numberOfThread, int[] array){
        this.numberOfThread = numberOfThread;
        this.array = array;
    }

    @Override
    public BigInteger call() throws Exception {
        BigInteger partMul = BigInteger.ONE;
        int size = array.length;
        for(int i = numberOfThread; i < size;i+=10){
            partMul = partMul.multiply(BigInteger.valueOf(array[i]));
        }
        return partMul;
    }

}
