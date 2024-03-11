package casino.blackjack;

import casino.shared.*;
import casino.util.ReadInputFromUser;

public class BlackJack {
    Deck deck = new Deck();
    BlackJackHand dealerHand = new BlackJackHand(); 
    BlackJackHand playerHand = new BlackJackHand();  
    BettingSystem betting = new BettingSystem();

    public BlackJack(BettingSystem betting) {
        this.betting = betting;
    }

    public void startGame() {
        betting.promptBet();
        deck.shuffleDeck();
        dealCards(playerHand, 2);
        dealCards(dealerHand, 2);

        playerTurn();
        dealerPlays();
        whoWins();
    }

    private void dealCards(BlackJackHand hand, int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            Card2 card = deck.takeCard();
            hand.addCard(card);
        }
    }

    private void playerTurn() {
        while (true) {
            System.out.println("Your hand: " + playerHand.getHand() + "(Total: " + playerHand.calculateHandSum() + ")");
            System.out.println("Hit or Stay? (h/s)");
            String input = ReadInputFromUser.read();
            if (input.equalsIgnoreCase("h")) {
                hit(playerHand);
                if (playerHand.calculateHandSum() > 21) {
                    System.out.println("Busted with " + playerHand.calculateHandSum() + "!");
                    return;
                }
            } else if (input.equalsIgnoreCase("s")) {
                break;
            }
        }
    }

    private void hit(BlackJackHand hand) {
        Card2 card = deck.takeCard();
        hand.addCard(card);
        System.out.println("Recieved: " + card);
    }

    // private void stay() {
    // System.out.println("STAY: ");
    // dealerPlays();
    // whoWins();
    // }

    private void dealerPlays() {
        System.out.println("Dealer's turn...");
        while (dealerHand.calculateHandSum() < 17) {
            hit(dealerHand);
        }
        System.out.println("Dealer stays with " + dealerHand.calculateHandSum());
    }

    private void whoWins() {
        int playerSum = playerHand.calculateHandSum();
        int dealerSum = dealerHand.calculateHandSum();

        System.out.println("Final Hands: Player: " + playerSum + ", Dealer: " + dealerSum);

        if (playerSum > 21) {
            System.out.println("Player Busts. Dealer wins");
            betting.loseBet();
        } else if (dealerSum > 21 || playerSum > dealerSum) {
            System.out.println("Player wins!");
            betting.winBet();
        } else if (playerSum == dealerSum) {
            System.out.println("It's a Tie.");
            betting.tieBet();
        } else {
            System.out.println("Dealer Wins!");
            betting.loseBet();
        }
    }
}
