package Task1;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int minNumber = 1;
        int maxNumber = 100;
        int maxAttempts = 5;
        int totalScore = 0;


        int randomNumber = (int) (Math.random() * (maxNumber)) + minNumber;

        while (true) {
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("I'm thinking of a number between " + minNumber + " and " + maxNumber + ".");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess (Attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You've guessed the correct number: " + randomNumber);
                    int roundScore = maxAttempts - attempts + 1;
                    totalScore += roundScore;
                    System.out.println("Round Score: " + roundScore);
                    guessedCorrectly = true;
                    break;
                } else if (guess < randomNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
            }

            System.out.println("Total Score: " + totalScore);
            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes") && !playAgain.equals("y")) {
                break;
            }
        }

        System.out.println("Thanks for playing! Your final score: " + totalScore);
        scanner.close();
    }
}
