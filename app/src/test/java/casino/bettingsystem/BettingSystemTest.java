package casino.bettingsystem;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import casino.shared.BettingSystem;

public class BettingSystemTest {

    @Test
    public void testPositiveDeposit() {
        BettingSystem bet = new BettingSystem(100);
        bet.deposit(10);
        bet.deposit(20);
        bet.deposit(30);

        assertEquals(160, bet.getBalance());
    }

    @Test
    public void testNegativeDeposit() {
        BettingSystem bet = new BettingSystem(100);
        bet.deposit(-50);

        assertEquals(100, bet.getBalance());
    }

    @Test
    public void testPlaceBetSufficientFunds() {
        BettingSystem bet = new BettingSystem(100);
        boolean result = bet.placeBet(50);
        assertTrue(result, "Bet should be successfully placed");
        assertEquals(50, bet.getBalance());
    }

    @Test
    public void testPlaceBetInsufficientFunds() {
        BettingSystem bet = new BettingSystem(20);
        boolean result = bet.placeBet(50);
        assertFalse(result, "Bet should not be placed due to insufficient funds");
        assertEquals(20, bet.getBalance());
    }

    @Test 
    public void testWinBet() {
        BettingSystem bet = new BettingSystem(100);
        bet.placeBet(50);
        bet.winBet();
        assertEquals(150, bet.getBalance());
    }
    
    @Test 
    public void testLoseBet() {
        BettingSystem bet = new BettingSystem(100);
        bet.placeBet(50);
        bet.loseBet();
        assertEquals(50, bet.getBalance());
    }
    
    @Test 
    public void testTieBet() {
        BettingSystem bet = new BettingSystem(100);
        bet.placeBet(50);
        bet.tieBet();
        assertEquals(100, bet.getBalance());
    }

}
