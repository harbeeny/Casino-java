package casino.shared;

import casino.util.ReadInputFromUser;

public class BettingSystem {
    private int balance;
    private int currentBet;

    public BettingSystem(int intialBalance) {
        this.balance = intialBalance;
    }

    public BettingSystem () {
        
    }

    public int getBalance() {
        return balance;
    }

    public void payoutWinnings(int winnings) {
        balance += winnings;
    }

    public void displayBalance() {
        System.out.println("Current balance: $" + balance);
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive number.");
        }
    }

    public void promptBet() {
        System.out.println("Your balance is $:" + balance);
        System.out.println("Please enter your bet: ");
        String betInput = ReadInputFromUser.read();
        try {
            int betAmount = Integer.parseInt(betInput);
            if (!placeBet(betAmount)) {
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for bet. Please enter a valid number.");
            return;
        }
    }

    public boolean placeBet(int amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance to place bet.");
            return false;
        }
        currentBet = amount;
        balance -= amount;
        System.out.println("Bet of $:" + amount + " placed. Current balance: $" + balance);
        return true;
    }

    public void winBet() {
        int winnings = currentBet * 2; // Assuming a 1:1 payout ratio, adjust if necessary
        balance += winnings;
        System.out.println("You win! You've earned $" + winnings + ". Current balance: $" + balance);
    }

    public void loseBet() {
        // The bet is already subtracted when the bet was placed.
        System.out.println("You lose. Current balance: $" + balance);
    }

    public void tieBet() {
        balance += currentBet; // Return the bet to the player
        System.out.println("It's a tie. Your bet has been returned. Current balance: $" + balance);
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
