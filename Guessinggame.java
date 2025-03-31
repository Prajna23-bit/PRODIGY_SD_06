import java.util.Random;
import java.util.Scanner;

public class Guessinggame {

    public static void main(String[] args) {
        // Create a Scanner object for reading user input
        Scanner scanner = new Scanner(System.in);

        // Create a Random object to generate random numbers
        Random random = new Random();

        // Generate a random number between 1 and 100
        int targetNumber = random.nextInt(100) + 1;
        
        // Initialize variables for the user's guess and attempt counter
        int userGuess = 0;
        int attempts = 0;

        // Print game instructions
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a random number between 1 and 100. Try to guess it!");

        // Keep asking the user for a guess until it is correct
        while (userGuess != targetNumber) {
            System.out.print("Enter your guess: ");
            userGuess = scanner.nextInt();
            attempts++;

            // Provide feedback on the guess
            if (userGuess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > targetNumber) {
                System.out.println("Too high! Try again.");
            }
        }

        // When the user guesses correctly, print the number of attempts
        System.out.println("Congratulations! You've guessed the correct number.");
        System.out.println("It took you " + attempts + " attempts to guess the number " + targetNumber + ".");

        // Close the scanner
        scanner.close();
    }
}
