package mariogarciaActors.ActorReplying

/**
 * Created by chetan on 24/11/16.
 */
import groovyx.gpars.actor.DynamicDispatchActor

class Handler extends DynamicDispatchActor {
    void onMessage(String name) {
        println "Nice to meet you $name"
    }

    void onMessage(Integer age) {
        println "You look younger than $age"
    }

}

final Handler handler = new Handler()
//when you do not use dsl you will have to make an instancxe and start it
handler.start()
//below handler takes both data types of diffrent category successfully
handler << "Mario"

handler << 37