package ForSession

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

//            println 'yyyyyy'
//first it comes here  calculates 1000, 2000 then goes to branch
            react { Integer amount ->
                println Thread.currentThread().name
                reply "Amount $amount has been stored in -- ${sender} --"
                //stop()

            }
        }
    }
}
class BankBranch extends WithId {
    void act() {
        loop {
//            println 'nnnn'
            //as per previous examples we can guess this is the sender or intiatoir who may have send request for cash
            // and the reciever is the bank who will listen the request and send the money
            react { String message ->
                println sender
                println message
            }
        }
    }
}
/*
When sending a message, a different actor can be specified as the sender so that potential replies to the message will
be forwarded to the specified actor and not to the actual originator.
 */
//we start the bank and the branch and created 2 instance of branch which is the sender
final branch1 = new BankBranch(id: '0001').start()
final branch2 = new BankBranch(id: '0002').start()
//branch1.join()
final bank = new Bank(id: 'abcd').start()
// public final <T> MessageStream send(T message, MessageStream replyTo) {
//so as per definition bank has taken the request and sending money to branches
//send(T message, MessageStream replyTo) {
bank.send(1000, branch1)//100 rupees send ho gaye  ////Specify an actor to send replies to
bank.send(2000, branch2)
bank.join()
//bank.stop()
//
//bank.sendAndWait('mmmm')

/*
mmm
abcd
Amount 1000 has been stored in -- 0001 --
mmm
abcd
Amount 2000 has been stored in -- 0002 --
 */
