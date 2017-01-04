package FRomGparsOfficial

import groovyx.gpars.group.DefaultPGroup

/**
 * Created by chetan on 3/1/17.
 */
//A little bit more realistic example of an event-driven actor that receives
// two numeric messages, sums them up and sends the result to the console actor.
//        import groovyx.gpars.group.DefaultPGroup
//not necessary, just showing that a single-threaded pool can still handle multiple actors
def group = new DefaultPGroup(1);
final def console = group.actor {
    loop {
        react {
            println 'Result: ' + it
        }
    }
}
final def calculator = group.actor {
    react {a ->
        react {b ->
            console.send(a + b)
        }
    }
}
calculator.send 2
calculator.send 3
calculator.join()
group.shutdown()

