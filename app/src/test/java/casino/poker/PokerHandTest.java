package casino.poker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import casino.shared.Card2;

public class PokerHandTest {

    @Test
    public void testIsFlush() {
        PokerHand hand = new PokerHand();
        hand.addCard(0, new Card2(2, Card2.HEARTS));
        hand.addCard(1, new Card2(3, Card2.HEARTS));
        hand.addCard(2, new Card2(6, Card2.HEARTS));
        hand.addCard(3, new Card2(7, Card2.HEARTS));
        hand.addCard(4, new Card2(10, Card2.HEARTS));
        
        assertTrue(hand.isFlush());
    }

    @Test
    public void testIsStraightLowCards() {
        PokerHand hand = new PokerHand();
        hand.addCard(0, new Card2(2, Card2.HEARTS));
        hand.addCard(1, new Card2(3, Card2.CLUBS));
        hand.addCard(2, new Card2(4, Card2.DIAMONDS));
        hand.addCard(3, new Card2(5, Card2.HEARTS));
        hand.addCard(4, new Card2(6, Card2.HEARTS));
        
        assertTrue(hand.isStraight());
    }

        @Test
    public void testIsStraightLowCardsNotSorted() {
        PokerHand hand = new PokerHand();
        hand.addCard(0, new Card2(4, Card2.HEARTS));
        hand.addCard(1, new Card2(3, Card2.CLUBS));
        hand.addCard(2, new Card2(2, Card2.DIAMONDS));
        hand.addCard(3, new Card2(6, Card2.HEARTS));
        hand.addCard(4, new Card2(5, Card2.HEARTS));
        
        assertTrue(hand.isStraight());
    }

    @Test
    public void testIsStraightFaceCards() {
        PokerHand hand = new PokerHand();
        hand.addCard(0, new Card2(11, Card2.HEARTS));
        hand.addCard(1, new Card2(12, Card2.CLUBS));
        hand.addCard(2, new Card2(13, Card2.SPADES));
        hand.addCard(3, new Card2(1, Card2.SPADES));
        hand.addCard(4, new Card2(10, Card2.HEARTS));
        
        assertTrue(hand.isStraight());
    }

    @Test
    public void testIsNotStraight() {
        PokerHand hand = new PokerHand();
        hand.addCard(0, new Card2(3, Card2.SPADES));
        hand.addCard(1, new Card2(5, Card2.CLUBS));
        hand.addCard(2, new Card2(4, Card2.DIAMONDS));
        hand.addCard(3, new Card2(5, Card2.HEARTS));
        hand.addCard(4, new Card2(6, Card2.CLUBS));
        
        assertFalse(hand.isStraight());
    }

    @Test
    public void testIsFullHouse() {
        PokerHand hand = new PokerHand();
        hand.addCard(0, new Card2(3, Card2.SPADES));
        hand.addCard(1, new Card2(3, Card2.CLUBS));
        hand.addCard(2, new Card2(6, Card2.DIAMONDS));
        hand.addCard(3, new Card2(6, Card2.HEARTS));
        hand.addCard(4, new Card2(6, Card2.CLUBS));
        
        assertEquals(PokerHand.FULL_HOUSE, hand.evaluateHand());
    }

    @Test
    public void testIsFullHouseUnsorted() {
        PokerHand hand = new PokerHand();
        hand.addCard(0, new Card2(6, Card2.SPADES));
        hand.addCard(1, new Card2(3, Card2.CLUBS));
        hand.addCard(2, new Card2(6, Card2.DIAMONDS));
        hand.addCard(3, new Card2(3, Card2.HEARTS));
        hand.addCard(4, new Card2(6, Card2.CLUBS));
        
        assertEquals(PokerHand.FULL_HOUSE, hand.evaluateHand());
    }
}