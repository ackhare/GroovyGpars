package ForSession.DemoActor

import groovyx.gpars.actor.DefaultActor

/**
 * Created by chetan on 18/11/16.
 */
class CustomActor1 extends DefaultActor {
    @Override
    protected void act() {
        loop {
            react {
                println it
            }
        }
    }
}

def console=new CustomActor1()

console.start()
console.send('hi')