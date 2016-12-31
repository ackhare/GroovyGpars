package mariogarciaActors.ActorSendingMessage

/**
 * Created by chetan on 18/11/16.
 */

//The intent is to show how this will work in cases without dsl like in java
import groovy.transform.Immutable
import groovyx.gpars.actor.DefaultActor

@Immutable
 class UserItem1 {
    String userName
    Long itemId
}
//Now we are explicitly declaring a DefaultActor class
class LoggingActorService extends DefaultActor {
    //Now the actor’s body is constraint within the body of the act() method.
   @Override
    void act() {
        loop {
            react { UserItem1 userItem ->
                println """
                    user ${userItem.userName} added item ${userItem.itemId}
                    at ${new Date()}
                """
            }
        }
    }
}
//In order to be able to send messages to the actor we need its reference,
// so we pass it when building our service instance

class UserService {
    def loggingService

    void trackAddedItem(final UserItem1 userItem) {
        //service has the user item now
        loggingService << userItem
    }
}
//the below extends an default actor
//so the logging service acts more as a layer which has a refrence of user item as well as an implementation of actor
def loggingService = new LoggingActorService()
//userservice gets the logging service after logging service gets an actor so which inturn can be used by userservice
def userService = new UserService(loggingService: loggingService)
//starts an actor
/*
Before sending messages to our actor, the actor should be already listening otherwise you will get an exception
*/
loggingService.start()
//here it will given to logging service
userService.trackAddedItem(new UserItem1(userName: 'John', itemId: 43L))
Thread.sleep(1000)
userService.trackAddedItem(new UserItem1(userName: 'Ronnie', itemId: 23L))
