package ActorInIngpars

import groovyx.gpars.actor.AbstractLoopingActor
import groovyx.gpars.actor.DefaultActor

/**
 * Created by chetan on 18/11/16.
 */
class Player extends DefaultActor {
    String name
    def random = new Random()

    void act() {
        loop {
            react {
                // player replies with a random move
                reply Move.values()[random.nextInt(Move.values().length)]
            }
        }
    }
}
