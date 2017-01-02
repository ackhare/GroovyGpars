package mariogarciaActors.ActorInteraction

/**
 * Created by chetan on 24/11/16.
 */
import groovyx.gpars.actor.DefaultActor
/*
Suppose you want to send a message to a given actor and then get
 the response back to send it to another actor.
 Well good news is that you can set where the response should be sent.
 */
class WithId extends DefaultActor {
    String id
    String toString() { return this.id }
}

class Bank extends WithId {
    void act() {
        loop {
            react { Integer amount ->
                reply "Amount $amount has been stored in -- ${sender} --"
            }
        }
    }
}
class BankBranch extends WithId {
    void act() {
        loop {
            //as per previous examples we can guess this is the sender or intiatoir who may have send request for cash
            // and the reciever is the bank who will listen the request and send the money
            react { String message ->
                println message
            }
        }
    }
}
//we start the bank and the branch and created 2 instance of branch which is the sender
final branch1 = new BankBranch(id: '0001').start()
final branch2 = new BankBranch(id: '0002').start()
final bank = new Bank(id: 'SuperRichBank').start()
// public final <T> MessageStream send(T message, MessageStream replyTo) {
//so as per definition bank has taken the request and sending money to branches
//send(T message, MessageStream replyTo) {
bank.send(1000, branch1)//100 rupees send ho gaye
bank.send(2000, branch2)

println  bank.sendAndWait(3000)


