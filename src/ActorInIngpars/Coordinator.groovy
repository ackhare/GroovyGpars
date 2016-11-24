package ActorInIngpars

import groovyx.gpars.actor.DefaultActor

/**
 * Created by chetan on 18/11/16.
 */
/*The Coordinator sends a play message to both Players and then waits to receive
a response.
*/

class Coordinator extends DefaultActor {
    Player player1
    Player player2
    int games

    void act() {
        loop {
            react {
                // start the game
                player1.send("play")
                player2.send("play")

                // decide the winner
                react { msg1 ->
                    react { msg2 ->
                        println msg1.dump()
                        announce(msg1.sender.name, msg1, msg2.sender.name, msg2)

                        // continue playing
                        if (games-- > 0)
                            send("start")
                        else
                            stop()
                    }
                }
            }
        }
    }

    void announce(p1, m1, p2, m2) {
        String winner = "tie"
        if (firstWins(m1, m2) && !firstWins(m2, m1)) {
            winner = p1
        } else if (firstWins(m2, m1) && !firstWins(m1, m2)) {
            winner = p2
        } // else tie

        if (p1.compareTo(p2) < 0) {
            println toString(p1, m1) + ", " + toString(p2, m2) + ", winner = " + winner
        } else {
            println toString(p2, m2) + ", " + toString(p1, m1) + ", winner = " + winner
        }
    }

    String toString(player, move) {
        return player + " (" + move + ")"
    }

    boolean firstWins(Move m1, Move m2) {
        return (m1 == Move.ROCK && m2 == Move.SCISSORS) ||
                (m1 == Move.PAPER && m2 == Move.ROCK) ||
                (m1 == Move.SCISSORS && m2 == Move.PAPER)
    }
}

