package mariogarciaActors.ActorSendingMessage

/**
 * Created by chetan on 24/11/16.
 */
/*
Logging is really important but sometimes we just want a response as a
consequence of sending data. Now we will be sending a message to a given actor and expecting a response to that data.

Let’s say we want to have some word translated we want to send
the word to an actor instance and get the translation back.

 */
import static groovyx.gpars.actor.Actors.actor
import groovyx.gpars.dataflow.Promise
////Important thing is that actor is not only reacting(printing etc) but also replying back
def reallySlowService = actor {
    loop {
        react {
            Thread.sleep(2000)
            reply 43
        }
    }
}

def reallyFastService = actor {
    loop {
        react {
            reply 'No idea'
        }
    }
}
////The sendAndWait method blocks the current thread until a response has been received
//Promise is required because sendAndPromise returns promise
Promise answer1 =
    reallySlowService.
        sendAndPromise('Tell me the meaning of life the universe and everything')
String answer2 =
    reallyFastService.
        sendAndWait('Tellme how is gonna end 24 this season')

assert answer1.get() == 43
assert answer2 == 'No idea'