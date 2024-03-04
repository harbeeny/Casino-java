package casino.poker;

import casino.shared.*;
import java.util.ArrayList;

public class PokerHand {
    private Card2 cards[] = new Card2[5];

    public void addCard(int idx, Card2 card) { // adds cards to hand with limit of 5
        if (idx < 5) {
            cards[idx] = card;
        } else {
            System.out.println("Cannot add more cards");
        }
    }

    public String evaluateHand() {
        return "not implemented yet";
    }

    public void discardAndReplace(int index, Card2 card) {
        cards[index] = card;
    }

    public String getCard(int index) {
        return cards[index].toString();
    }

    public void discardAll(Card2[] cards) {
        for (int i=0; i < this.cards.length; i++) {
            this.cards[i] = cards[i];
        }
    }

    public void showHand() {
        for (int i=0; i < cards.length; i++) {
            System.out.println("[" + i + "] " + cards[i]);
        }
    }

}
// write code to figure out what user has : later keep track of score

// static final String ROYALFLUSH = "royal flush";
// static final String STRAIGHTFLUSH = "straight flush";
// static final String FOUROFAKIND = "four of a kind";
// static final String FULLHOUSE = "full house";
// static final String FLUSH = "flush";
// static final String STRAIGHT = "straight";
// static final String THREEOFAKIND = "three of a kind";
// static final String TWOPAIR = "two pair";
// static final String ONEPAIR = "one pair";
// static final String HIGHCARD = "high card";