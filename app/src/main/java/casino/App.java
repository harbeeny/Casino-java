/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package casino;

import casino.blackjack.BlackJack;
import casino.poker.Poker;
import casino.shared.BettingSystem;
import casino.util.ReadInputFromUser;

public class App {
    private static BettingSystem bettingSystem;
    
    public static void main(String[] args) {
        bettingSystem = new BettingSystem(100);
        runCasino(); 
    }

    private static void runCasino() {

        while (true) {
            showMenu();
            String input = ReadInputFromUser.read().trim().toUpperCase();

            switch (input) {
                case "Q":
                    System.out.println("Thanks for playing");
                    return;
                case "D":
                    handleDeposit();
                    break;    
                case "1": 
                    startBlackJack();
                    break;   
                case "2": 
                    startPoker();
                    break;   
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, D, or Q.");
                    break;    
            } 
        } 
    }
  
    private static void showMenu() {
        System.out.println("Welcome to our Casino!");
        bettingSystem.displayBalance();
        System.out.println("Which game would you like to play?");
        System.out.println("1: Blackjack");
        System.out.println("2: Poker");
        System.out.println("D: Deposit Money");
        System.out.println("Q: Quit");
        System.out.print("Please enter your choice (1, 2, or D): ");
    }

    private static void handleDeposit() {
        System.out.println("Enter amount to deposit: ");
        int depositAmount = positiveInt();
        if (depositAmount > 0) {
            bettingSystem.deposit(depositAmount);
            System.out.println(depositAmount + " has been added to your balance.");
        }
    }

    private static int positiveInt() {
        while (true) {
            String input = ReadInputFromUser.read();
            try {
                int num = Integer.parseInt(input);
                if (num > 0) {
                    return num;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number");
            }
        }
    }

    private static void startBlackJack() {
        System.out.println("You've selected Blackjack. Starting the game...");
        BlackJack blackjack = new BlackJack(bettingSystem);
        blackjack.startGame();
    }
    
    private static void startPoker() {
        System.out.println("You've selected Blackjack. Starting the game...");
        Poker poker = new Poker(bettingSystem);
        poker.startGame();
    }
}


