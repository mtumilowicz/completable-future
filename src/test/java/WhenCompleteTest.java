import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by mtumilowicz on 2018-08-28.
 */
public class WhenCompleteTest {
    @Test
    public void onComplete_exception() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(ExceptionProcess::call)
                .whenComplete((ok, ex) -> System.out.println("completed!"));

        while (!future.isCompletedExceptionally()) {
            // wait till exception
        }
        
        assertTrue(true);
    }
    
    @Test
    public void onComplete_noException() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call)
                .whenComplete((ok, ex) -> System.out.println("completed!"));

        assertThat(future.join(), is("ended!"));
    }
}
