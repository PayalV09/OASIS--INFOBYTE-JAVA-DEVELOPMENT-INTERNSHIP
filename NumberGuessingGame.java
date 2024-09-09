import javax.swing.JOptionPane;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        int maxRounds = 3; // Number of rounds in the game
        int maxAttempts = 5; // Maximum attempts allowed per round
        int score = 0; // Initial score
        int numberRange = 100; // Range of the random number (1 to 100)

        // Loop for multiple rounds
        for (int round = 1; round <= maxRounds; round++) {
            int numberToGuess = random.nextInt(numberRange) + 1; // Random number between 1 and numberRange
            int attemptsLeft = maxAttempts; // Attempts left for the current round
            boolean roundWon = false;

            JOptionPane.showMessageDialog(null, "Round " + round + ": Guess the number between 1 and " + numberRange);

            // Loop for attempts within a single round
            while (attemptsLeft > 0) {
                String userInput = JOptionPane.showInputDialog(null, 
                    "Guess the number between 1 and " + numberRange + 
                    "\nAttempts left: " + attemptsLeft + 
                    "\nEnter your guess or type 'quit' to give up:");

                // If the user cancels the input dialog, exit the game
                if (userInput == null) {
                    JOptionPane.showMessageDialog(null, "Game ended by the user.");
                    return;
                }

                // Check if the user wants to quit the game
                if (userInput.equalsIgnoreCase("quit")) {
                    JOptionPane.showMessageDialog(null, "You gave up! The correct number was: " + numberToGuess);
                    break;
                }

                // Validate user input to ensure it is a valid integer within the range
                int userGuess;
                try {
                    userGuess = Integer.parseInt(userInput);
                    if (userGuess < 1 || userGuess > numberRange) {
                        JOptionPane.showMessageDialog(null, "Please enter a number between 1 and " + numberRange);
                        continue; // Prompt again
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                    continue; // Prompt again
                }

                attemptsLeft--;

                if (userGuess == numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number correctly.");
                    score += attemptsLeft + 1; // Points based on remaining attempts
                    roundWon = true; // Set roundWon to true to indicate the correct guess
                    break; // Exit the loop as the user has guessed correctly
                } else if (userGuess < numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Your guess is too low. Try a higher number.");
                } else {
                    JOptionPane.showMessageDialog(null, "Your guess is too high. Try a lower number.");
                }

                // Encouraging or warning messages based on attempts left
                if (attemptsLeft == 1) {
                    JOptionPane.showMessageDialog(null, "This is your last attempt! Make it count!");
                } else if (Math.abs(userGuess - numberToGuess) <= 5) {
                    JOptionPane.showMessageDialog(null, "You're very close! Keep going!");
                }
            }

            if (!roundWon) {
                JOptionPane.showMessageDialog(null, "Sorry! You've used all attempts. The number was: " + numberToGuess);
            }

            JOptionPane.showMessageDialog(null, "Score after round " + round + ": " + score);
        }

        JOptionPane.showMessageDialog(null, "Game Over! Your final score is: " + score);
    }
}
