package mariogarciaActors.ActorReplying

/**
 * Created by chetan on 24/11/16.
 */
/*
/*


That’s cool, but sometimes you may want to send different types of messages to a given object
and you may expect the actor to behave differently depending on the type received.

That kind of behavior is acomplished by DynamicDispatchActor.
Whether you use the DSL or the Java flavor you will be able to create
different processing for different type of messages using the same actor.

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