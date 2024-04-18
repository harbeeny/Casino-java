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
        while (true) {
        System.out.println("Your balance is $:" + balance);
        System.out.println("Please enter your bet: ");
        String betInput = ReadInputFromUser.read();
        
        int betAmount = validateParsedBet(betInput);
        if (betAmount == -1) continue;

        if (placeBet(betAmount)) {
            System.out.println("Bet of $" + betAmount + " placed.");
            break;
        } else {
            System.out.println("Could not place a bet of $" + betAmount + ". Please try a different amount.");
        }
    }    
}    

    private int validateParsedBet(String input) {
        try {
            int betAmount = Integer.parseInt(input);
            if (betAmount <= 0) {
                System.out.println("The bet amount must be greater than 0. Please enter a valid number.");
                return -1;
            }
            return betAmount;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for bet. Please enter a valid number.");
            return -1;
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
        int winnings = currentBet * 2; // Assuming a 1:1 payout ratio
        balance += winnings;
        System.out.println("You win! You've earned $" + winnings + ". Current balance: $" + balance);
    }

    public void loseBet() {
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
