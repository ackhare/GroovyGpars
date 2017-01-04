package FRomGparsOfficial

import groovyx.gpars.actor.Actors

/**
 * Created by chetan on 3/1/17.
 */
//Notice the loop() method call, which ensures that the actor doesn't stop after having processed the first message.
//Here's an example with a decryptor service, which can decrypt submitted messages
// and send the decrypted messages back to the originators.
final def decryptor = Actors.actor {
    loop {
        react {String message ->
            if ('stopService' == message) {
                println 'Stopping decryptor'
                stop()
            }
            else reply message.reverse()
        }
    }
}

Actors.actor {
    decryptor.send 'lellarap si yvoorG'
    react {
        println 'Decrypted message: ' + it
        decryptor.send 'stopService'
    }
}.join()