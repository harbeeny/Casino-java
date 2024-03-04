/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package casino;

import casino.blackjack.BlackJack;
import casino.poker.Poker;
import casino.util.ReadInputFromUser;

public class App {

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to our Casino!");
            System.out.println("Which game would you like to play?");
            System.out.println("1: Blackjack");
            System.out.println("2: Poker");
            System.out.println("Q: Quit");
            System.out.print("Please enter your choice (1 or 2): ");
            String input = ReadInputFromUser.read();
            if ("Q".equalsIgnoreCase(input)) {
                System.out.println("Thanks for playing.");
                break;
            }

            try {
                int gameChoice = Integer.parseInt(input); // Convert the String input to an integer

                switch (gameChoice) {
                    case 1:
                        System.out.println("You've selected Blackjack. Starting the game...");
                        BlackJack blackjack = new BlackJack();
                        blackjack.startGame();
                        break;
                    case 2:
                        System.out.println("You've selected Poker. Starting the game...");
                        Poker poker = new Poker();
                        poker.startGame();
                        break;
                    default:
                        System.out.println("Invalid choice. Please restart and select either 1 or 2.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
            }
        } 
    }
}