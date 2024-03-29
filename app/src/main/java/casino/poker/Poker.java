
package casino.poker;

import casino.shared.*;
import casino.util.ReadInputFromUser;

public class Poker {
    Deck deck = new Deck();
    PokerHand playerHand = new PokerHand();
    BettingSystem betting = new BettingSystem();

    public Poker(BettingSystem betting) {
        this.betting = betting;
    }

    public void startGame() {

        betting.promptBet();

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
        String handEvaluation = playerHand.evaluateHand();
        System.out.println(handEvaluation);

        payoutForHand(handEvaluation);
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

    private void payoutForHand(String evaluateHand) {
        int winnings = 0;

        if (PokerHand.ROYAL_FLUSH.equals(evaluateHand)) {
            winnings = 500;
        } else if ("straight flush".equals(evaluateHand)) {
            winnings = 400;
        } else if ("four of a kind".equals(evaluateHand)) {
            winnings = 350;
        } else if ("full house".equals(evaluateHand)) {
            winnings = 300;
        } else if ("flush".equals(evaluateHand)) {
            winnings = 200;
        } else if ("straight".equals(evaluateHand)) {
            winnings = 150;
        } else if ("three of a kind".equals(evaluateHand)) {
            winnings = 100;
        } else if ("two pair".equals(evaluateHand)) {
            winnings = 25;
        } else if ("one pair".equals(evaluateHand)) {
            winnings = 10;
        }

        if (winnings > 0) {
            betting.payoutWinnings(winnings);
            System.out.println("You win $" + winnings + "!");
        } else {
            System.out.println("Better luck next time!");
        }
    }
}
