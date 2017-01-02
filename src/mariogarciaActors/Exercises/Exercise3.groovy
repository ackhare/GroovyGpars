package mariogarciaActors.Exercises

/**
 * Created by chetan on 2/1/17.
 */
/*


You have to create an actor that processes national and international orders.
 All orders have a field called content where they carry the message.

For national orders no reply is needed because all national orders are
supposed to reach destination. But for international orders you need the actor to

reply with a MD5 signature of the message (I’ll give you the code to get the MD5).

 */
import static groovyx.gpars.actor.Actors.messageHandler
import static java.security.MessageDigest.getInstance

import groovyx.gpars.actor.DynamicDispatchActor
import groovyx.gpars.dataflow.Promise

class Order { String content }
class NationalOrder extends Order {}
class InternationalOrder extends Order {}

final Closure<String> md5 = { String st ->
    return getInstance("MD5").digest(st.getBytes("UTF-8")).encodeHex().toString()
}

final DynamicDispatchActor orderProcessor = messageHandler {
    when { InternationalOrder international ->
        reply md5(international.content)
    }
    when { NationalOrder national ->
        println "I'm sure the order: ${national.content} will be delivered"
    }
}

Promise md5IdPromise =
    orderProcessor.sendAndPromise(new InternationalOrder(content: 'supersecret'))

orderProcessor << new NationalOrder(content: 'something really silly')
orderProcessor << new NationalOrder(content: 'something as silly as the previous one')

println "I need to hold the id: ${md5IdPromise.get()}"