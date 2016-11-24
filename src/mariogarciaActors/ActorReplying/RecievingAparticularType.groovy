package mariogarciaActors.ActorReplying

/**
 * Created by chetan on 24/11/16.
 */
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

assert response == "Nice to hear: Hi there"