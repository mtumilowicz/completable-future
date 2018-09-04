import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2018-09-04.
 */
public class ThenApplyTest {
    @Test
    public void thenApply() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(FastProcess::call)
                .thenApply(result -> "double " + result);
        
        assertThat(stringCompletableFuture.join(), is("double ended!"));
    }
}
