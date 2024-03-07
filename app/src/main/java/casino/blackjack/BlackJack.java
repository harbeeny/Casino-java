package casino.blackjack;

import casino.shared.*;
import casino.util.ReadInputFromUser;
import java.util.ArrayList;

public class BlackJack {
    Deck deck = new Deck();
    BettingSystem betting = new BettingSystem();

    // dealer
    Card2 hiddenCard;
    ArrayList<Card2> dealerHand;
    int dealerSum;
    int dealerAceCount;

    // player
    ArrayList<Card2> playerHand;

    public BlackJack(BettingSystem betting) {
        this.betting = betting;
    }

    public void startGame() {

        betting.promptBet();
        
        // shuffle
        deck.shuffleDeck();

        // dealer
        dealerHand = new ArrayList<>();
        dealCards(dealerHand, 2);
        System.out.println("DEALER: ");
        System.out.println("[" + dealerHand.get(0) + "]"); // hidden card
        System.out.println(dealerHand.get(1));
        System.out.println(calculateHandSum(dealerHand));

        // player
        playerHand = new ArrayList<>();
        dealCards(playerHand, 2);

        System.out.println("PLAYER: ");
        System.out.println(playerHand);
        System.out.println(calculateHandSum(playerHand));

        System.out.println("Hit or Stay? (h/s)");
        String input = ReadInputFromUser.read();
        if (input.equals("h")) {
            hit();
        } else if (input.equals("s")) {
            stay();
        } else {
            System.out.println("");
            
        }
    }

    private void dealCards(ArrayList<Card2> hand, int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            Card2 card = deck.takeCard();
            hand.add(card);
        }
    }

    private void hit() { // #TODO: Remove duplicate code with a while loop
        if (calculateHandSum(playerHand) < 21) {
            Card2 card = deck.takeCard();
            playerHand.add(card);

            int playerSum = calculateHandSum(playerHand);
            if (playerSum > 21) {
                System.out.println("Player Hit and recieves: " + card);
                System.out.println("BUST! Player's total is: " + playerSum + " :(");
            } else {
                System.out.println("Hit and recieves: " + card);
                System.out.println("New total: " + playerSum);

                System.out.println("Hit or Stay? (h/s)");
                String input = ReadInputFromUser.read();
                if (input.equals("h")) {
                    hit();
                } else if (input.equals("s")) {
                    stay();
                }
            }
        }
    }

    private void stay() {
        System.out.println("STAY: ");
        dealerPlays();
        whoWins();
    }

    private void dealerPlays() {
        while (calculateHandSum(dealerHand) < 17) {
            Card2 card = deck.takeCard();
            dealerHand.add(card);
            System.out.println("Dealer draws:" + card);
        }
        System.out.println("Dealer stays with a hand of " + calculateHandSum(dealerHand));
    }

    private int calculateHandSum(ArrayList<Card2> hand) {
        int sum = 0;
        for (Card2 card : hand) {
            if (card.isAce() && sum + 11 > 21) {
                sum += 1;
            } else if (card.isAce()) {
                sum += 11;
            } else if (card.isKing() || card.isQueen() || card.isJack()) {
                sum += 10;
            } else {
                sum += card.getValue();
            }
        }
        return sum;
    }

    private void whoWins() {
        int playerSum = calculateHandSum(playerHand);
        int dealerSum = calculateHandSum(dealerHand);
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
