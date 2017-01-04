package ForSession;

import groovy.util.logging.Log4j;
import groovyx.gpars.actor.Actor;
import groovyx.gpars.actor.DynamicDispatchActor;

/**
 * Created by chetan on 31/12/16.
 */

final class MyCounterActor extends DynamicDispatchActor {
    private int counter = 0;

    void onMessage(String message) {
        System.out.println("Received a string " + message);
        counter += message.length();
    }

    void onMessage(Integer message) {
        System.out.println("Received an integer");
        counter += message;
    }

    void onMessage(Boolean message) {
        System.out.println("Received a boolean");
        reply(counter);
    }
}

class DecryptorTest {
    public static void main(String[] args) throws InterruptedException {
       /*
       Creating Actors

Actors can be created either by instantiating the appropriate class (see main()) or using appropriate factory methods.
The former approach is preferred when instantiating actors in Java, while the latter approach feels more Groovy-like.
        */
        Actor counter = new MyCounterActor().start();
       /*
       Actors are being created, started, and stopped
       . Messages are being sent, received, and replied to asynchronously.
        The message themselves are immutable values
        */
        counter.send("Hello!");
        ;
        System.out.println("Current value is: " + counter.sendAndWait(true));
       /*
       sendAndWait() methods is available to block the caller until a reply from the actor is available.
       The reply message is
returned from the sendAndWait() method as a return value.
        */
        //counter.stop();
        // counter.join();
    }
}