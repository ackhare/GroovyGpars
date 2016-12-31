package mariogarciaActors.ActorSendingMessage

/**
 * Created by chetan on 24/11/16.
 */
/*


Logging is really important but sometimes we just want a response
as a consequence of sending data. Now we will be sending a message to a
given actor and expecting a response to that data.

Let’s say we want to have some word translated we want to send the word
to an actor instance and get the translation back.

 */

import static groovyx.gpars.actor.Actors.actor
//Important thing is that actor is not only reacting(printing etc) but also replying back
final def english2SpanishService = actor {
    loop {
        react { String word ->
            //The actor can only use the reply method from a non-actor-request
            // when the client uses sendAndWait for sending the message
            if (word == 'hello') {
                reply 'hola'
            } else if (word == 'goodbye') {
                reply 'adios'
            } else {
                reply 'no idea :P'
            }
        }
    }
}
//The sendAndWait method blocks the current thread until a response has been received
String helloTranslation = english2SpanishService.sendAndWait('hello')
String goodbyeTranslation = english2SpanishService.sendAndWait('goodbye')
String seeyoulaterTranslation = english2SpanishService.sendAndWait('see you later')

println  helloTranslation == 'hola'
println goodbyeTranslation == 'adios'
println seeyoulaterTranslation == 'no idea :P'

/*


 */