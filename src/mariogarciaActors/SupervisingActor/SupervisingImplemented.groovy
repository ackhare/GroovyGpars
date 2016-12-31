package mariogarciaActors.SupervisingActor

/**
 * Created by chetan on 25/11/16.
 */
/*
This time we have two actors. We’ve got a Supervised actor receiving messages through its Supervisor.
This way in case the supervised actor stops, its Supervisor could still create another Supervised actor
of the same type and keep processing messages transparently to the client.
 */
class SimpleSupervisedActor extends Supervised {
    void act() {
        loop {
            react { String message ->
                if (message == 'gift') {
                    throw new Exception(message)
                }
                println "NORMAL: $message"
            }
        }
    }
}

def supervised = new SimpleSupervisedActor().start()
def supervisor = new Supervisor() {
    void onMessage(String query) {
        println "query "+query
        supervised << query
    }
    /*
    The interesting part. Supervisor receives a Supervised’s SupervisedException message when it’s about to stop.
Then the Supervisor instance has chosen to create another Supervised instance to keep processing messages the same way.
Enough to say, the more stateless is the Supervised actor the less difficult will be to restart the Supervised actor again
     */
    void onMessage(SupervisedExceptionMessage ex) {
        println "SUPERVISED ACTOR DIES WITH MESSAGE: ${ex.message}"
        supervised = supervisedClass.newInstance().start()
    }
}.start()
//this method takes a supervised object
supervisor.link(supervised)

supervisor << "something"
Thread.sleep(1000)
supervisor << "something"
Thread.sleep(1000)
supervisor << "gift"
Thread.sleep(1000)
supervisor << "Message processed by a new supervised instance 1"
Thread.sleep(1000)
supervisor << "Message processed by a new supervised instance 2"
