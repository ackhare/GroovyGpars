package mariogarciaActors.Exercises

/**
 * Created by chetan on 2/1/17.
 */
import static groovyx.gpars.actor.Actors.actor
/*
Create an actor receiving 4 numbers an prints all of them to the console.
You can use the GroovyConsole to do that. Please try to do it using GPars DSL and plain classes.
 */
def printer = actor {
    loop {
        react { List<Integer> numbers ->
           println numbers.sum()
        }
    }
}

printer << (1..4) as List
