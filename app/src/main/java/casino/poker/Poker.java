
package casino.poker;

import casino.shared.*;
import casino.util.ReadInputFromUser;

public class Poker {
    Deck deck = new Deck();
    PokerHand playerHand = new PokerHand();

    public Poker() {
    }

    public void startGame() {
        deck.shuffleDeck();

        System.out.println("Dealing your hand...");
        dealHand();

        System.out.println("Your hand:");
        playerHand.showHand();

        String cardsToDiscard;
        do {
            System.out.println("Choose cards to discard (a=all, n=none):");
            cardsToDiscard = ReadInputFromUser.read();
        } while(!processInput(cardsToDiscard));
        
        System.out.println("Your final hand: ");
        playerHand.showHand();

        System.out.println("Evaluating hand...");
        System.out.println(playerHand.evaluateHand());
    }

    private void dealHand() {
        for (int i = 0; i < 5; i++) {
            playerHand.addCard(i, deck.takeCard());
        }
    }

    private boolean processInput(String input) {
        if (input.equals("a")) {
            playerHand.discardAll(deck.takeCard(5));
            return true;
        }

        if (input.equals("n")) {
            return true;
        }

        try {
            String[] cards = input.split(" ");
            for (String card : cards) {
                int cardIndex = Integer.parseInt(card);
                playerHand.discardAndReplace(cardIndex, deck.takeCard());
            }
            return true;
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            return false;
        }
    }
}
