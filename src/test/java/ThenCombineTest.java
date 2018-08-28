import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2018-08-28.
 */
public class ThenCombineTest {
    @Test
    public void thenCombine() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(SlowProcess::call);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(SlowProcess::call);
        CompletableFuture<String> combined = future1.thenCombine(future2, (first, second) -> first + " " + second);
        
        assertThat(combined.join(), is("ended! ended!"));
    }
}
