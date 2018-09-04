# completable-future

_Reference_: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html  
_Reference_: https://www.nurkiewicz.com/2013/05/java-8-definitive-guide-to.html

# preface
Shortly: `CompletableFuture` is non-stopping future.

`CompletableFuture` class implements the `CompletionStage` interface. 
`CompletionStage` represents a stage of a certain computation which can be 
done 
* synchronously or 
* asynchronously. 

Think of it as just a single unit of a pipeline of computations that 
ultimately generates a result.

Note that `CompletableFuture` also implements `Future`, with the ability 
to explicitly complete this `Future`, hence the name `CompletableFuture`.

# manual
* `static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)`

    Returns a new `CompletableFuture` that is completed when all of
the given `CompletableFutures` complete.  If any of the given
`CompletableFutures` complete exceptionally, then the returned
`CompletableFuture` also does so, with a `CompletionException`
holding this exception as its cause.

* `boolean cancel(boolean mayInterruptIfRunning)`

    If not already completed, completes this CompletableFuture with
a `CancellationException`. Dependent `CompletableFutures`
that have not already completed will also complete
exceptionally, with a `CompletionException` caused by
this `CancellationException`.

* `boolean complete(T value)`

    If not already completed, sets the value returned by get() and 
related methods to the given value.

* `boolean completeExceptionally(Throwable ex)`

    If not already completed, causes invocations of get()
    and related methods to throw the given exception.

* `CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn)`

    Returns a new `CompletableFuture` that is completed when this
`CompletableFuture` completes, with the result of the given
function of the exception triggering this `CompletableFuture's`
completion when it completes exceptionally; otherwise, if this
`CompletableFuture` completes normally, then the returned
`CompletableFuture` also completes normally with the same value.

* `T get() throws InterruptedException, ExecutionException`

    Waits if necessary for this future to complete, and then 
    returns its result.
    ```
    @throws CancellationException if this future was cancelled
    @throws ExecutionException if this future completed exceptionally
    @throws InterruptedException if the current thread was interrupted
    ```
    
* `T join()`
    Returns the result value when complete, or throws an
    (unchecked) exception if completed exceptionally.

    ```
    @throws CancellationException if the computation was cancelled
    @throws CompletionException if this future completed exceptionally 
            or a completion computation threw an exception
    ```
    


# tests
* `allOf` - `AllOfTest`
* `cancel` - `CancelTest`
* `complete` - `CompleteTest`
* `completeExceptionally` - `CompleteExceptionallyTest`
* `exceptionally` - `ExceptionallyTest`
* `get() / join()` - `GetJoinTest`
