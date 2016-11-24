package mariogarciaActors

/**
 * Created by chetan on 18/11/16.
 */
import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor
//Actor’s dsl is created by using Actors.actor method.
DefaultActor receiver = Actors.actor {
    //This actor never will end without an explicit termination
    loop {
        //When reacting for a given received message, the type could be established as parameter to the react closure
        react { String msg ->
            println 'lllll'
            println "Hey thanks for telling me $msg"
        }
    }
}

//You can pass messages to an actor by using the actor’s send method.
// This method can be invoked in the three ways exposed in the example.
receiver.send 'Message 1'
receiver << 'Message 2'
//This is not working
//receiver 'Message 3'
