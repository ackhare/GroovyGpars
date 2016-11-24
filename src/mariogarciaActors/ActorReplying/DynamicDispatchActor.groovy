package mariogarciaActors.ActorReplying

/**
 * Created by chetan on 24/11/16.
 */
import static groovyx.gpars.actor.Actors.messageHandler
import groovyx.gpars.actor.DynamicDispatchActor
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