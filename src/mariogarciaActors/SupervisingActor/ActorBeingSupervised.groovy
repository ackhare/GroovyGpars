package mariogarciaActors.SupervisingActor

/**
 * Created by chetan on 25/11/16.
 */
import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor
//	When supervised actor crashes it will create an exception message to send it to its supervisor
class SupervisedExceptionMessage {
   String message
}

class Supervised extends DefaultActor {
   //We need to hold a supervisor’s reference in order to be able to inform it whenever may come
    Supervisor supervisor
    void onException(Throwable th) {

        //	Everytime an actor throws an unhandled exception the onException(Throwable)
        // is invoked. Once the onException() method ends the actor(supervised) stops.
        // Before that we want to inform the supervisor in case it wanted to do something about it.
        supervisor << new SupervisedExceptionMessage(message: th.message)
    }
}
