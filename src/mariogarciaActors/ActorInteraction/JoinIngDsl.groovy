package mariogarciaActors.ActorInteraction

/**
 * Created by chetan on 24/11/16.
 */
import static groovyx.gpars.actor.Actors.actor
//DefaultActor is the actor rteturned
final receiver = actor {
    loop {
        react { msg ->
            reply "Replying actor received: '$msg'"
//            stop()
        }
    }
}

final sender = actor {
    //Instead of loop you have reciever which is above actor and the reciever will reply to sernder which it will print
    receiver.send("Sent Ping")
    react { msg ->
        println msg
    }
}
//Joining actors

//Actors provide a join() method to allow callers to wait for the actor to terminate.
// A variant accepting a timeout is also available.
// The Groovy spread-dot operator comes in handy when joining multiple actors at a time.
//[receiver, sender]*.join()
receiver.join()
println "Never reached"