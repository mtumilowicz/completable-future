import org.junit.Test;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by mtumilowicz on 2018-08-27.
 */
public class CancelTest {

    @Test(expected = CancellationException.class)
    public void join_cancel_true() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call);
        
        System.out.println("before get");
        
        future.cancel(true);
        future.join();
        
        System.out.println("after get");
    }

    @Test(expected = CancellationException.class)
    public void join_cancel_false() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call);

        System.out.println("before get");

        future.cancel(false);
        future.join();

        System.out.println("after get");
    }

    @Test(expected = CancellationException.class)
    public void get_cancel_true() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call);
        System.out.println("before get");
        
        future.cancel(true);
        future.get();
        
        System.out.println("after get");
    }

    @Test(expected = CancellationException.class)
    public void get_cancel_false() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call);
        System.out.println("before get");

        future.cancel(false);
        future.get();

        System.out.println("after get");
    }
    
}