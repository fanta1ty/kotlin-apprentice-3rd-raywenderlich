package section_04

/*
Key points:
- The asynchronous approach to programming focuses on allowing you to execute several operations at the same time.
- Threads are used when you don't need a lot of them to perform the necessary tasks.
- Coroutines are like "lightweight threads", since they don't require as much memory resources and they're not based on
OS level threads like Java threads.
- A large number of coroutines could be executed on a single thread without blocking it.
- Each coroutine is bound to some CoroutineContext.
- CoroutineContext is responsible for many important parts of a coroutine such as its Job, Dispatcher and
CoroutineExceptionHandler.
- Use coroutines builders (runBlocking(), withContext(), launch(), async()) to create and launch coroutines.
- You can decide when to launch your coroutine using CoroutineStart.
- Use dispatchers to define the threads for your coroutine execution.
- Coroutines are based on the concept of a state machine, with each state referring to a suspension point. It doesn't
require extra time or resources to switch between coroutines and to restore their state.
 */

fun main() {
}