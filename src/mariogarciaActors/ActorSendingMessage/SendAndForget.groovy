package mariogarciaActors.ActorSendingMessage

/**
 * Created by chetan on 18/11/16.
 */
import groovy.transform.Immutable

import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor
/*
Immutability is always the right choice when dealing with concurrency and parallelism. Use it as much as you can.

 */
@Immutable
class UserItem {
    String userName
    Long itemId
}

final DefaultActor loggingServiceActor = Actors.actor {
    loop {
        //The react method closure can receive as parameter any type. Of course make sure
        // the type you expect is the one your are actually receiving, otherwise you’ll get a cast exception.
        //Here is not another actor sending messages to our actor but a different thread (the main thread).
        react { UserItem userItem ->
            //An actor’s message handler (react) can only expect 0 or 1 argument
            println "user ${userItem.userName} added item ${userItem.itemId} at ${new Date()}"
        }
    }
}

//Here we’are sending a message to the actor from an outer process holding the actor’s reference.

// 	Here is not another actor sending messages to our actor but a different thread (the main thread).
loggingServiceActor.send(new UserItem(userName:'Mario', itemId: 10L))
Thread.sleep(1000) // Adding a delay to see it working
loggingServiceActor << ([userName: 'Rober', itemId: 20L] as UserItem)
