package number;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int roundsPlayed = 0;
        int roundsWon = 0;
        String playAgain = "y";

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain.equalsIgnoreCase("y")) {
            roundsPlayed++;
            int randomNumber = random.nextInt(100) + 1;
            int attempts = 0;
            final int maxAttempts = 10;

            System.out.println("\nI have generated a random number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else if (guess > randomNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    roundsWon++;
                    break;
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The correct number was " + randomNumber + ".");
            }

            System.out.print("Do you want to play again? (y/n): ");
            playAgain = scanner.next();
        }

        System.out.println("\nGame over!");
        System.out.println("Rounds played: " + roundsPlayed);
        System.out.println("Rounds won: " + roundsWon);

        scanner.close();
    }
}
