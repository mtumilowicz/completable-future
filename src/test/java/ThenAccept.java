import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * Created by mtumilowicz on 2018-08-28.
 */
public class ThenAccept {
    @Test(expected = CompletionException.class)
    public void thenAccept_exception() {
        CompletableFuture.supplyAsync(ExceptionProcess::call)
                .thenAccept(x -> System.out.println("Accepted!")).join();
    }

    @Test
    public void thenAccept_noException() {
        CompletableFuture.supplyAsync(FastProcess::call)
                .thenAccept(x -> System.out.println("Accepted!")).join();
    }
}
