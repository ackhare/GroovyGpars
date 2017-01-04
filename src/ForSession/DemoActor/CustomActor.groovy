package ForSession.DemoActor

import groovyx.gpars.actor.DefaultActor

/**
 * Created by chetan on 18/11/16.
 */

//The intent of this app is to showe how default actor works
class CustomActor1 extends DefaultActor {
    @Override
    //. The loop() method allows iteration within the actor body. Unlike typical looping constructs, loop()
//  cooperates with nested react() blocks and ensures looping across subsequent message retrievals.

    protected void act() {
        loop {
            // Actors share a pool of threads, which are dynamically assigned to actors
            // when the actors need to react to messages sent to them
            react {
                // react() schedules the supplied code (closure) to be executed
                // upon next message arrival and quits the actor
                //body.
                println it

            }
        }
    }
}

def console = new CustomActor1()

console.start()
console.send('hi')

//here u will se an infinite lopp
//because        sendAndWait() methods is available to block the caller until a reply from the actor is available.
//The reply message is
//returned from the sendAndWait() method as a return value.
//No reply so infinite loop
println Thread.currentThread().name
println console.sendAndWait('hio')