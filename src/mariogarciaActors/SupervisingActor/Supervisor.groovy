package mariogarciaActors.SupervisingActor

/**
 * Created by chetan on 25/11/16.
 */
import groovyx.gpars.actor.DynamicDispatchActor
//this is also an actor
//	A Supervisor extends DynamicDispatchActor.
// It could receive many other type of messages other than SupervisedExceptionMessage.
class Supervisor extends DynamicDispatchActor {
    //A reference to the supervised actor
    Supervised supervised
    //The reference to the supervised actor’s class.
    // This is useful in case the supervisor wanted to create a
    // new instance of a supervised actor that stopped because of an unhandled exception.
    Class<? extends Supervised> supervisedClass

    void link(Supervised supervised) {
        this.supervised = supervised
        this.supervised.supervisor = this
        this.supervisedClass = supervised.getClass()
    }
}
