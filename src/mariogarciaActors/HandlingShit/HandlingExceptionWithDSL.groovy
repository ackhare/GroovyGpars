package mariogarciaActors.HandlingShit

/**
 * Created by chetan on 25/11/16.
 */
import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor

DefaultActor buggyActor = Actors.actor {
    /*
    /* called when an exception occurs in the actor's event handler.
 * Actor will stop after return from this method. */
  //  onException(Throwable th)


    delegate.metaClass.onException = {
        println delegate
        println "Close database connection"
        println "Release other resources"
        println "Bye"
    }
    loop {
        react { String message ->
            println message
            throw new Exception("Shit happens :P")
        }
    }
}

buggyActor << "Something nice"
