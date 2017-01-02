package mariogarciaActors

/**
 * Created by chetan on 18/11/16.
 */
import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor
import groovyx.gpars.dataflow.Promise


//Actor’s dsl is created by using Actors.actor method.
//The DefaultActor class is the base for all stateful actors, who need to maintain implicit
// state between subsequent message arrivals.
DefaultActor receiver = Actors.actor {
    //TODO This actor never will end without an explicit termination
    loop {
        //When reacting for a given received message, the type could be established as parameter to the react closure
        react { String msg ->
//            println 'mmmmmmmmm'
            println "Hey thanks for telling me $msg"
        }
    }
}

//You can pass messages to an actor by using the actor’s send method.
// This method can be invoked in the three ways exposed in the example.
receiver.send 'Message 1'
receiver << 'Message 2'    //using the implicit call() method
//to see the result you need to do sendAndWait
//println receiver.sendAndWait("Message 3") //"Hey thanks for telling me Message1"

