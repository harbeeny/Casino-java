package casino.poker;

import casino.shared.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

public class PokerHand {

    private Card2 cards[] = new Card2[5];

    static final String ROYAL_FLUSH = "royal flush";
    static final String STRAIGHT_FLUSH = "straight flush";
    static final String FOUR_OF_A_KIND = "four of a kind";
    static final String FULL_HOUSE = "full house";
    static final String FLUSH = "flush";
    static final String STRAIGHT = "straight";
    static final String THREE_OF_A_KIND = "three of a kind";
    static final String TWO_PAIR = "two pair";
    static final String ONE_PAIR = "one pair";
    static final String HIGH_CARD = "high card";

    public void addCard(int idx, Card2 card) { // adds cards to hand with limit of 5
        if (idx < 5) {
            cards[idx] = card;
        } else {
            System.out.println("Cannot add more cards");
        }
        sortHand();
    }

    public String evaluateHand() {
        boolean flush = isFlush();
        boolean straight = isStraight();

        Map<Integer, Integer> valueCounts = getValueCounts();
        int pairs = 0, threes = 0, fours = 0;

        for (int count : valueCounts.values()) {
            if (count == 2)
                pairs++;
            if (count == 3)
                threes++;
            if (count == 4)
                fours++;
        }

        if (flush && straight && cards[0].getValue() == 10)
            return ROYAL_FLUSH;
        if (flush && straight)
            return STRAIGHT_FLUSH;
        if (fours == 1)
            return FOUR_OF_A_KIND;
        if (threes == 1 && pairs == 1)
            return FULL_HOUSE;
        if (flush)
            return FLUSH;
        if (straight)
            return STRAIGHT;
        if (threes == 1)
            return THREE_OF_A_KIND;
        if (pairs == 2)
            return TWO_PAIR;
        if (pairs == 1)
            return ONE_PAIR;
        return HIGH_CARD;
    }

    public boolean isFlush() { // Unit test
        String suit = cards[0].getSuit();
        for (int i = 0; i < cards.length; i++) {
            if (!cards[i].getSuit().equals(suit)) {
                return false;
            }
        }
        return true;
    }

    public boolean isStraight() { // Unit test for all of the methods
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getValue() + 1 != cards[i + 1].getValue()) {
                return false;
            }
        }
        return true;
    }

    private Map<Integer, Integer> getValueCounts() { // research hashmaps and trie
        Map<Integer, Integer> counts = new HashMap<>();
        for (Card2 card : cards) {
            counts.put(card.getValue(), counts.getOrDefault(card.getValue(), 0) + 1);
        }
        return counts;
    }

    public void discardAndReplace(int index, Card2 card) {
        cards[index] = card;
        sortHand();
    }

    public String getCard(int index) {
        return cards[index].toString();
    }

    public void discardAll(Card2[] cards) {
        for (int i = 0; i < this.cards.length; i++) {
            this.cards[i] = cards[i];
        }
    }

    public void showHand() {
        for (int i = 0; i < cards.length; i++) {
            System.out.println("[" + i + "] " + cards[i]);
        }
    }

    private void sortHand() {

        Arrays.sort(cards, new Comparator<Card2>() {
            public int compare(Card2 a, Card2 b) {
                if (a == b) {
                    return 0;
                } 
                if (a == null) {
                    return 1;
                }
                if (b == null) {
                    return -1;
                }
                return a.getValue() - b.getValue();
            } 
        });
    }
}
// write code to figure out what user has : later keep track of score
