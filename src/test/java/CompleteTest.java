import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by mtumilowicz on 2018-08-27.
 */
public class CompleteTest {
    @Test
    public void join_complete_notCompleted() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call);

        System.out.println("before get");

        future.complete("COMPLETE");
        assertThat(future.join(), is("COMPLETE"));

        System.out.println("after get");
    }

    @Test
    public void join_complete_completed() throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(SlowProcess::call);

        System.out.println("before get");
        
        Thread.sleep(3000);
        future.complete("COMPLETE");
        assertThat(future.join(), is("ended!"));

        System.out.println("after get");
    }
}
