import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2018-08-28.
 */
public class ThenComposeTest {
    @Test
    public void thenCompose() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call)
                .thenCompose(result -> CompletableFuture.supplyAsync(() -> "composed: " + result));
        
        assertThat(future.join(), is("composed: ended!"));
    }
}
