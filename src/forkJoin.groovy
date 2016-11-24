/**
 * Created by chetan on 18/11/16.
 */
import static groovyx.gpars.GParsPool.runForkJoin
import groovyx.gpars.GParsPool
import groovyx.gpars.forkjoin.AbstractForkJoinWorker
/*
Listing 5. Divide-and-conquer algorithm

IF problem is small enough to solve directly
THEN solve it directly
ELSE {
  Divide the problem in two or more sub-problems
  Solve each sub-problem
  Combine the results
}
 */
class Config {
    static DATA_COUNT = 2**14
    static GRANULARITY_THRESHHOLD = 128
    static THREADS = 4
}

items = [] as List<Integer>
items.addAll(1..Config.DATA_COUNT)
println items
Collections.shuffle(items)
println items
/*
The runForkJoin() factory method will use the supplied recursive code together with the provided values
and build a hierarchical Fork/Join calculation. The number of values passed to the runForkJoin() method
must match the number of expected parameters
of the closure as well as the number of arguments passed into the forkOffChild() or runChildDirectly() methods.
 */
GParsPool.withPool(Config.THREADS) {
    def computedMax = runForkJoin(1, Config.DATA_COUNT, items.asImmutable())
            {begin, end, items ->

                //As per above it should be given same number of arguments as it is giveb
                //
                int size = end - begin

                //begin=1
                //below starts a recursive scheme where a anchor condition is given (size <= Config.GRANULARITY_THRESHHOLD)
                if (size <= Config.GRANULARITY_THRESHHOLD) {

                    //solve it dirtectly
                    return items[begin..<end].max()
                } else {
                    // divide and conquer

                    def leftEnd = begin + ((end + 1 - begin) / 2)
                    /*
                     forkOffChild

protected final void forkOffChild(AbstractForkJoinWorker<T> child)

    Forks a child task. Makes sure it has a means to indicate back completion.
    The worker is stored in the internal list of workers for evidence and easy
    result retrieval through getChildrenResults().

    Parameters:
        child - The child task


                     */

                    //divided the child into two halfs as is done in mege oir quick sort and then forked fuither
                    forkOffChild(begin, leftEnd, items)
                    forkOffChild(leftEnd + 1, end, items)
                    return childrenResults.max()

                    /*
                     getChildrenResults

                    @SuppressWarnings({"unchecked"})
                    public final java.util.List getChildrenResults()

                   Waits for and returns the results of the child tasks.

                   Returns:
                    A list of results returned from the child tasks


                     */
                }
            }
/*
The important piece of the puzzle that needs to be mentioned here is that forkOffChild() doesn't wait for the child to
run. It merely schedules it for execution some time in the future. If a child fails by throwing an exception, you should
not expect the exception to be fired from the forkOffChild() method itself. The exception ise likely to happen long
after the parent has returned from the call to the forkOffChild() method.
 */
    println "expectedMax = ${Config.DATA_COUNT}"
    println "computedMax = ${computedMax}"
}

