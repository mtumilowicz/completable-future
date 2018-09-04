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
    
* `isCancelled() / isCompletedExceptionally() / isDone()`

* `runAsync(Runnable runnable)`

    Returns a new CompletableFuture that is asynchronously completed
    by a task running in the `ForkJoinPool#commonPool()` after
    it runs the given action. 

* `supplyAsync(Supplier<U> supplier)`
    
    Returns a new `CompletableFuture` that is asynchronously completed
    by a task running in the `ForkJoinPool#commonPool()` with
    the value obtained by calling the given Supplier.
    
* `<U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn)`
    
    Returns a new `CompletionStage` that, when this stage completes either normally or 
    exceptionally, is executed with this stage's result and exception as arguments 
    to the supplied function.
    
* `CompletableFuture<Void> thenAccept(Consumer<? super T> action)`

    Returns a new CompletionStage that, when this stage completes normally, 
    is executed with this stage's result as the argument to the supplied action.
    
* `<U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)`

    Returns a new CompletionStage that, when this stage completes normally, 
    is executed with this stage's result as the argument to the supplied 
    function.

* `<U,V> CompletableFuture<V> thenCombine(
           CompletionStage<? extends U> other,
           BiFunction<? super T,? super U,? extends V> fn)`
           
    Returns a new CompletionStage that, when this and the other given 
    stage both complete normally, is executed with the two results as 
    arguments to the supplied function.

* `<U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn)`
    
    Returns a new CompletionStage that, when this stage completes 
    normally, is executed with this stage as the argument to the 
    supplied function.
    
* `CompletableFuture<Void> thenRun(Runnable action)`

    Returns a new CompletionStage that, when this stage completes 
    normally, executes the given action.
    
* `CompletableFuture<T> whenComplete(
           BiConsumer<? super T, ? super Throwable> action)`

    Returns a new CompletionStage with the same result or exception 
    as this stage, that executes the given action when this stage 
    completes.

# tests
* `allOf` - `AllOfTest`
* `cancel` - `CancelTest`
* `complete` - `CompleteTest`
* `completeExceptionally` - `CompleteExceptionallyTest`
* `exceptionally` - `ExceptionallyTest`
* `get / join` - `GetJoinTest`
* `handle` - `HandleTest`
* `thenAccept` - `ThenAcceptTest`
* `thenApply` - `ThenApplyTest`
* `thenCombine` - `ThenCombineTest`
* `thenCompose` - `ThenComposeTest`
* `whenComplete` - `WhenCompleteTest`
