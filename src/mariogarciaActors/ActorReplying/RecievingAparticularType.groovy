package mariogarciaActors.ActorReplying

/**
 * Created by chetan on 24/11/16.
 */
// If you try to send a message with another value not expected in the actor’s closure, it will throw an exception
//Important thing is that actor is not only reacting(printing etc) but also replying back
import static groovyx.gpars.actor.Actors.actor
import groovyx.gpars.actor.DefaultActor

final DefaultActor echoActor = actor {
    loop {
        react { String message ->
            reply "Nice to hear: $message"
        }
    }
}

String response = echoActor.sendAndWait('Hi there')

println response == "Nice to hear: Hi there"