import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PlayHangman {

    public static void playHangman(Scanner input) {
        System.out.println("\nWELCOME TO HANGMAN!!\n");
        Random random = new Random();
        ArrayList<String> wordList = new ArrayList<>();

        // Load words into the ArrayList
        try (Scanner fileScanner = new Scanner(new File("words.txt"))) {
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim().toLowerCase();
                if (!word.isEmpty()) {
                    wordList.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: words.txt file not found.");
            return;
        }

        if (wordList.isEmpty()) {
            System.out.println("The words.txt file is empty.");
            return;
        }

        boolean keepPlaying = true;

        while (keepPlaying) {
            String chosenWord = wordList.get(random.nextInt(wordList.size()));
            char[] guessWord = new char[chosenWord.length()];
            for (int i = 0; i < guessWord.length; i++) {
                guessWord[i] = '_';
            }

            HashSet<Character> guessedLetters = new HashSet<>();
            int wrongGuesses = 0;
            final int maxGuesses = 6;

            while (wrongGuesses < maxGuesses && new String(guessWord).contains("_")) {
                System.out.println("\nCurrent Word: " + new String(guessWord));
                System.out.println("Wrong guesses left: " + (maxGuesses - wrongGuesses));
                System.out.print("Guess a letter: ");
                String guessInput = input.next().toLowerCase();

                if (guessInput.length() != 1 || !Character.isLetter(guessInput.charAt(0))) {
                    System.out.println("Invalid input. Please enter a single letter.");
                    continue;
                }

                char guessedChar = guessInput.charAt(0);

                if (guessedLetters.contains(guessedChar)) {
                    System.out.println("You already guessed that letter.");
                    continue;
                }

                guessedLetters.add(guessedChar);

                boolean found = false;
                for (int i = 0; i < chosenWord.length(); i++) {
                    if (chosenWord.charAt(i) == guessedChar) {
                        guessWord[i] = guessedChar;
                        found = true;
                    }
                }

                if (found) {
                    System.out.println("Correct!");
                } else {
                    System.out.println("Wrong!");
                    wrongGuesses++;
                }
            }

            // Game result
            if (new String(guessWord).equals(chosenWord)) {
                System.out.println("\nCongratulations! You guessed the word: " + chosenWord);
            } else {
                System.out.println("\nYou lost! The correct word was: " + chosenWord);
            }

            // Ask to play again
            System.out.print("\nPlay again? (y/n): ");
            String again = input.next().toLowerCase();
            keepPlaying = again.equals("y");
        }

        System.out.println("Thanks for playing Hangman!");
    }
}
