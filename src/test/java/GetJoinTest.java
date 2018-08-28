import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by mtumilowicz on 2018-08-27.
 */
public class GetJoinTest {
    
    @Test
    public void get() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call);
        
        System.out.println("before get");
        
        assertThat(future.get(), is("ended!"));
        
        System.out.println("after get");
    }

    @Test
    public void join() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call);
        System.out.println("before get");
        
        assertThat(future.join(), is("ended!"));
        
        System.out.println("after get");
    }

}