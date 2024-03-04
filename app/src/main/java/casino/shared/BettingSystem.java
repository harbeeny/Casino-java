package casino.shared;

public class BettingSystem {
    private int balance;

    BettingSystem(int intialBalance) {
        this.balance = intialBalance;
    }

    public int getBalance() {
        return balance;
    }

    public int placeBet(int betAmount) {
        if (betAmount <= balance && betAmount > 0) {
            balance -= betAmount;
            System.out.println("Bet of $" + betAmount + " placed.");
            return betAmount;
        } else {
            System.out.println("Invalid bet amount. Please try again.");
            return 0;
        }
    }

    public void payoutWinnings(int winnings) {
        balance += winnings;
    }

    public void displayBalance() {
        System.out.println("Current balance: $" + balance);
    }
}
