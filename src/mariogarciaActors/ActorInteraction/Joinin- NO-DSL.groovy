package mariogarciaActors.ActorInteraction

/**
 * Created by chetan on 24/11/16.
 */
import static groovyx.gpars.actor.Actors.actor
import groovyx.gpars.actor.DefaultActor

class SendingActor extends DefaultActor {
    final DefaultActor destination
    SendingActor(DefaultActor destination) {
       this.destination = destination
    }
    //here again sender will take a refrence of reciever and print what ever reply it is getting from reciever
    //destination is the reciever itself
    void act() {
        println destination.sendAndWait("Sent Ping")
    }
}
//this will be the destination the below actor will send the response
class ReplyingActor extends DefaultActor {
    void act() {
        loop {
            react { String msg ->
                reply "ReplyingActor received: $msg'"
            }
        }
    }
}

final receiver = new ReplyingActor().start()
final sender = new SendingActor(receiver).start()

[receiver, sender]*.join()
println "Never reached"
