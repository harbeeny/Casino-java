package casino.shared;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private final ArrayList<Card2> cards = new ArrayList<Card2>();

    public Deck() {
        buildDeck();
    }

    private void buildDeck() {
        int[] value = { 1, 2, 3, 4, 5, 6, 6, 8, 9, 10, 11, 12, 13 };
        String[] suit = { Card2.HEARTS, Card2.DIAMONDS, Card2.SPADES, Card2.CLUBS };

        for (int i = 0; i < value.length; i++) {
            for (int s = 0; s < suit.length; s++) {
                Card2 card = new Card2(value[i], suit[s]);
                cards.add(card);
            }
        }
    }

    public void shuffleDeck() { // or Collections.shuffle(deck) from java.utils.Collections
        final Random random = new Random();
        for (int i = 0; i < cards.size(); i++) {
            int r = random.nextInt(cards.size());
            Card2 currentCard = cards.get(i);
            Card2 randomCard = cards.get(r);
            cards.set(i, randomCard);
            cards.set(r, currentCard);
            // Collections.shuffle(cards);
        }
    }

    public Card2 takeCard() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    public Card2[] takeCard(int count) {
        if (cards.size() < count) {
            return null;
        }

        Card2[] takenCards = new Card2[count];
        for (int i = 0; i < count; i++) {
            takenCards[i] = cards.remove(cards.size() - 1);
        }
        return takenCards;
    }

    public ArrayList<Card2> getCards() {
        return cards;
    }

}
