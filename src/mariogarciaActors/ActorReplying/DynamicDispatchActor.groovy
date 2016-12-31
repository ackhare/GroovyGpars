package mariogarciaActors.ActorReplying

/**
 * Created by chetan on 24/11/16.
 */
import static groovyx.gpars.actor.Actors.messageHandler
import groovyx.gpars.actor.DynamicDispatchActor
/*


That’s cool, but sometimes you may want to send different types of messages to a given object
and you may expect the actor to behave differently depending on the type received.

That kind of behavior is acomplished by DynamicDispatchActor.
Whether you use the DSL or the Java flavor you will be able to create
different processing for different type of messages using the same actor.

 */
//TODO some issue in casting of dynamic dispatch actor and actore
DynamicDispatchActor handler = messageHandler {
    when { String name ->
       println "Nice to meet you $name"
    }
    when { Integer age ->
        println "You look younger than $age"
    }
}

handler << "Mario"
handler << 37