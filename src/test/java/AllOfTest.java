import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mtumilowicz on 2018-09-04.
 */
public class AllOfTest {
    
    @Test
    public void allOf_join()  {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(SlowProcess::call);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(SlowProcess::call);
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(SlowProcess::call);
        CompletableFuture<String> cf4 = CompletableFuture.supplyAsync(SlowProcess::call);

        System.out.println("before allOf");
        CompletableFuture.allOf(cf1, cf2, cf3, cf4).join();
        System.out.println("after allOf");

        assertTrue(cf1.isDone());
        assertTrue(cf2.isDone());
        assertTrue(cf3.isDone());
        assertTrue(cf4.isDone());
    }

    @Test
    public void allOf_missingJoin()  {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(SlowProcess::call);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(SlowProcess::call);
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(SlowProcess::call);
        CompletableFuture<String> cf4 = CompletableFuture.supplyAsync(SlowProcess::call);
        
        CompletableFuture.allOf(cf1, cf2, cf3, cf4);
        
        assertFalse(cf1.isDone());
        assertFalse(cf2.isDone());
        assertFalse(cf3.isDone());
        assertFalse(cf4.isDone());
    }
}
