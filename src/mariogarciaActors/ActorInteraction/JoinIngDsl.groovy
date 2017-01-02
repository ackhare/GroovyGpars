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
        }
    }
}

final sender = actor {
    //Instead of loop you have reciever which is above actor and the reciever will reply to sernder which it will print
    receiver << "Sent Ping"
    react { msg ->
        println msg
    }
}

[receiver, sender]*.join()
println "Never reached"