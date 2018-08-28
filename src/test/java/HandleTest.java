import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static java.util.Objects.nonNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2018-08-28.
 */
public class HandleTest {
    @Test
    public void handle_exception() {
        CompletableFuture<String> handle = CompletableFuture.supplyAsync(ExceptionProcess::call)
                .handle((ok, ex) -> nonNull(ok) ? ok : String.format("Task ended with exception: %s", ex));

        assertThat(handle.join(), is("Task ended with exception: " +
                "java.util.concurrent.CompletionException: java.lang.RuntimeException"));
    }
    
    @Test
    public void handle_noException() {
        CompletableFuture<String> handle = CompletableFuture.supplyAsync(SlowProcess::call)
                .handle((ok, ex) -> nonNull(ok) ? ok : String.format("Task ended with exception: %s", ex));

        assertThat(handle.join(), is("ended!"));
    }
}
