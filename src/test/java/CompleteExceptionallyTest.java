import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

/**
 * Created by mtumilowicz on 2018-08-27.
 */
public class CompleteExceptionallyTest {
    @Test(expected = CompletionException.class)
    public void join_completeExceptionally() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call);
        
        System.out.println("before get");
        
        future.completeExceptionally(new IllegalStateException());
        future.join();
        
        System.out.println("after get");
    }

    @Test(expected = ExecutionException.class)
    public void get_completeExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call);
        
        System.out.println("before get");
        
        future.completeExceptionally(new IllegalStateException());
        future.get();
        
        System.out.println("after get");
    }
}
