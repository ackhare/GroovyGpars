package FRomGparsOfficial.deliveryFailures

import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor

/**
 * Created by chetan on 3/1/17.
 */
//Undelivered messages
//
//Sometimes messages cannot be delivered to the target actor. When special action needs
// to be taken for undelivered messages, at actor termination all unprocessed messages
// from its queue have their onDeliveryError() method called.
// The onDeliveryError() method or closure defined on the message can,
// for example, send a notification back to the original sender of the message.

final DefaultActor me
me = Actors.actor {
    def message = 1

    message.metaClass.onDeliveryError = {->
        //send message back to the caller
        me << "Could not deliver $delegate"
    }

    def actor = Actors.actor {
        react {
            //wait 2sec in order next call in demo can be emitted
            Thread.sleep(2000)
            //stop actor after first message
            stop()
        }
    }

    actor << message
    actor << message

    react {
        //print whatever comes back
        println it
    }

}

me.join()
