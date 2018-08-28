import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by mtumilowicz on 2018-08-27.
 */
public class ExceptionallyTest {
    @Test
    public void exceptionally_noException() {
        CompletableFuture<String> exceptionallyWrapper = CompletableFuture.supplyAsync(SlowProcess::call).exceptionally(Throwable::getLocalizedMessage);
    
        assertThat(exceptionallyWrapper.join(), is("ended!"));
    }

    @Test
    public void exceptionally_exception() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call);
        future.completeExceptionally(new IllegalStateException("exception!"));

        CompletableFuture<String> exceptionallyWrapper = future.exceptionally(Throwable::getLocalizedMessage);

        assertThat(exceptionallyWrapper.join(), is("exception!"));
    }
}
