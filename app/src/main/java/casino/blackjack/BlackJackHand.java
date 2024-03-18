package casino.blackjack;

import casino.shared.Card2;
import casino.shared.Deck;

import java.util.ArrayList;

public class BlackJackHand {
    Deck deck = new Deck();
    private ArrayList<Card2> hand = new ArrayList<>();

    public void addCard(Card2 card) {
        hand.add(card);
    }

    public int calculateHandSum() {
        int sum = 0;
        int aceCount = 0;

        for (Card2 card : hand) {
            int value = card.getValue();
            if (value > 10) {
                value = 10;
            } else if (value == 1) {
                aceCount++;
                value = 11;
            }
            sum += value;
        }
        while (sum > 21 && aceCount > 0) {
            sum -= 10;
            aceCount--;
        }
        return sum;
    }

    public ArrayList<Card2> getHand() {
        return hand;
    }

}
