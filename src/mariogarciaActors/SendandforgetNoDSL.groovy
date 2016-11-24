package mariogarciaActors

/**
 * Created by chetan on 18/11/16.
 */


import groovy.transform.Immutable

import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor

@Immutable
 class UserItem1 {
    String userName
    Long itemId
}

class LoggingActorService extends DefaultActor {
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

class UserService {
    def loggingService

    void trackAddedItem(final UserItem1 userItem) {
        loggingService << userItem
    }
}

def loggingService = new LoggingActorService()
def userService = new UserService(loggingService: loggingService)

loggingService.start()

userService.trackAddedItem(new UserItem1(userName: 'John', itemId: 43L))
Thread.sleep(1000)
userService.trackAddedItem(new UserItem1(userName: 'Ronnie', itemId: 23L))
/*
Now we are explicitly declaring a DefaultActor class
Now the actor’s body is constraint within the body of the act() method.
In order to be able to send messages to the actor we need its reference, so we pass it when building our service instance
Before sending messages to our actor, the actor should be already listening otherwise you will get an exception
*/