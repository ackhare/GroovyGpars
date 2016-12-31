package mariogarciaActors.HandlingShit

/**
 * Created by chetan on 25/11/16.
 */
import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor

class BuggyActor extends DefaultActor {

    void onException(Throwable th) {
        println "Close database connection"
        println "Release other resources"
        println "Bye"
    }

    void act() {
        loop {
            react { String message ->
                throw new Exception("Shit happens :P")
            }
        }
    }
}

new BuggyActor().start() << "Something nice"
