package ActorInIngpars

import groovyx.gpars.actor.AbstractLoopingActor
/**
 * Created by chetan on 18/11/16.
 */

/*
Listing 9 contains a complete representation of the "Rock, Paper, Scissors" game implemented with GPars using a
Coordinator actor and two Player actors. The Coordinator sends a play message to both Players and then waits to receive
a response. After it has received two responses, the Coordinator prints the outcome of the match and sends a message to
itself to start a new game. The Player actors wait to be asked for a move, whereupon each one responds with an arbitrary
move.
 */


final def player1 = new Player(name: "Player 1")
final def player2 = new Player(name: "Player 2")
final def coordinator = new Coordinator(player1: player1, player2: player2, games: 10)
[player1,player2,coordinator]*.start()
coordinator << "start"
coordinator.join()
[player1,player2]*.terminate()