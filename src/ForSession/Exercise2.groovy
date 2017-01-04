package ForSession

/**
 * Created by chetan on 2/1/17.
 */
/*

3.6. Exercise

Now that we now how to send and receive a simple response from a given actor,
lets modify the actor you built for the first exercise for
instead of printing out the result, make it send back the result to you.

You have to send three different list of numbers,
 get back the results and sum them in the client
  once you have received the partial results from the actor.

The objective of this exercises is using promises or blocking calls to get results from a given actor.

 */
import static groovyx.gpars.actor.Actors.actor
import groovyx.gpars.dataflow.Promise
// you can use loop() to iterate, react() to receive messages, and reply()
// to send a reply to the currently processed message.

def printer = actor {
    //loop() method call, which ensures that the actor doesn't stop after having processed the first message.

    loop {
        //react() method has a special semantics to allow actors to be detached
        // from threads when no messages are available in
        //their mailbox.
                react { List<Integer> numbers ->
           println numbers.sum()
           reply numbers.sum()
        }
    }
}
//The sendAndPromise() method returns a Promise (aka Future) to the final reply
// and so allows the caller to continue
// its processing while the actor is handling the submitted message.
Promise first = printer.sendAndPromise((1..4)) //10
Integer second = printer.sendAndWait((4..8))//21
Integer third = printer.sendAndWait((8..12))//50

println  (first.get() + second + third) == 90
