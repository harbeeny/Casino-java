package casino.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import casino.shared.Card2;
public class BlackJackHandTest {
    
    @Test 
    public void testIs21() {
        BlackJackHand hand = new BlackJackHand();
        hand.addCard(new Card2(4, Card2.HEARTS ));
        hand.addCard(new Card2(5, Card2.CLUBS));
        hand.addCard(new Card2(7, Card2.CLUBS)); 
        hand.addCard(new Card2(5, Card2.CLUBS)); 
        
        assertEquals(21, hand.calculateHandSum(), "The sum should be 21");
    }
    
    @Test 
    public void testFaceCardsIsTen() {
        BlackJackHand hand = new BlackJackHand();
        hand.addCard(new Card2(11, Card2.SPADES ));
        hand.addCard(new Card2(12, Card2.DIAMONDS));
        hand.addCard(new Card2(13, Card2.CLUBS));

        assertEquals(30, hand.calculateHandSum(), "The sum of all face cards should be 30");
    }
    
    @Test 
    public void testAceValueOfOne() {
        BlackJackHand hand = new BlackJackHand();
        hand.addCard(new Card2(1, Card2.HEARTS ));
        hand.addCard(new Card2(2, Card2.CLUBS));
        hand.addCard(new Card2(7, Card2.CLUBS)); 
        hand.addCard(new Card2(7, Card2.CLUBS));
        hand.addCard(new Card2(4, Card2.CLUBS));

        assertEquals(21, hand.calculateHandSum(), "The sum should be 21");
    }
    
    @Test 
    public void testAceValueOfEleven() {
        BlackJackHand hand = new BlackJackHand();
        hand.addCard(new Card2(1, Card2.HEARTS ));
        hand.addCard(new Card2(13, Card2.CLUBS));

        assertEquals(21, hand.calculateHandSum(), "The sum should be 21");
    }
    
    @Test 
    public void testValueOver21() {
        BlackJackHand hand = new BlackJackHand();
        hand.addCard(new Card2(1, Card2.HEARTS ));
        hand.addCard(new Card2(5, Card2.CLUBS));
        hand.addCard(new Card2(7, Card2.CLUBS)); 
        hand.addCard(new Card2(7, Card2.CLUBS));
        hand.addCard(new Card2(8, Card2.CLUBS));

        assertEquals(28, hand.calculateHandSum(), "The sum should be 28");        
    }
}
