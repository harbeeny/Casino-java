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

    public ArrayList<Card2> getHand() {
        return hand;
    }

}
