import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static java.util.Objects.nonNull;

/**
 * Created by mtumilowicz on 2018-08-28.
 */
public class HandleTest {
    @Test
    public void handle_exception() {
        CompletableFuture<String> handle = CompletableFuture.supplyAsync(ExceptionProcess::call)
                .handle((r, t) -> nonNull(r) ? r : String.format("Task ended with exception: %s", t));

        System.out.println(handle.join());
    }
    
    @Test
    public void handle_noException() {
        CompletableFuture<String> handle = CompletableFuture.supplyAsync(SlowProcess::call)
                .handle((r, t) -> nonNull(r) ? r : String.format("Task ended with exception: %s", t));

        System.out.println(handle.join());
    }
}
