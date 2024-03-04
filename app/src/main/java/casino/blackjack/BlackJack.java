package casino.blackjack;

import casino.shared.*;
import casino.util.ReadInputFromUser;
import java.util.ArrayList;

    // #TODO: How can you remove duplicates in this code?  

public class BlackJack {
    Deck deck = new Deck();
    
    // dealer
    Card2 hiddenCard;
    ArrayList<Card2> dealerHand;
    int dealerSum;
    int dealerAceCount;
    
    // player
    ArrayList<Card2> playerHand;
    
    public BlackJack() {
    }
    
    public void startGame() {
        // shuffle
        deck.shuffleDeck();
      
        // dealer
        dealerHand = new ArrayList<Card2>();
        for (int i = 0; i < 2; i++) {
            Card2 card = deck.takeCard();
            dealerHand.add(card);
        }
        System.out.println("DEALER: ");
        System.out.println("[" + dealerHand.get(0) + "]"); // hidden card
        System.out.println(dealerHand.get(1));
        System.out.println(dealerSum());
        
        // player
        playerHand = new ArrayList<Card2>();
        
        for (int i = 0; i < 2; i++) {
            Card2 card = deck.takeCard();
            playerHand.add(card);
        }
        
        System.err.println("PLAYER: ");
        System.out.println(playerHand);
        System.out.println(playerSum());
        
        System.out.println("Hit or Stay? (h/s)");
        String input = ReadInputFromUser.read();
        if (input.equals("h")) {
            hit();
        } else if (input.equals("s")) {
            stay();
        }
    }

    private void hit() {
        if (playerSum() < 21) {
            Card2 card = deck.takeCard();
            playerHand.add(card);
            
            int playerSum = playerSum();
            if (playerSum > 21) {
                System.out.println("BUST! Player's total is " + playerSum);
            } else {
                System.out.println("Hit and recieves: " + card);
                System.out.println("New total:" + playerSum);
                
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
        System.out.println("STAY:");
        dealerPlays();
        whoWins();
    }
    
    private void dealerPlays() {
        while (dealerSum() < 17) {
            Card2 card = deck.takeCard();
            dealerHand.add(card);
            System.out.println("Dealer draws:" + card);
        }
        System.out.println("Dealer stays with a hand of " + dealerSum());
    }

    private int playerSum() {
        int sum = 0;
        for (Card2 card : playerHand) {
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

    private int dealerSum() {
        int sum = 0;
        for (Card2 card : dealerHand) {
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
        int playerSum = playerSum();
        int dealerSum = dealerSum();
        if (playerSum > 21) {
            System.out.println("Player Busts. Dealer wins");
        } else if (dealerSum > 21 || playerSum > dealerSum) {
            System.out.println("Player wins!");
        } else if (playerSum == dealerSum) {
            System.out.println("It's a Tie.");
        } else {
            System.out.println("Dealer Wins!");
        }
    }
}
