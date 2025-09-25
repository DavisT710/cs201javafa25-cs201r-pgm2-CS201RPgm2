import java.util.Random;
import java.util.Scanner;

public class Games {

    // LOTTERY GAME
    public static void lotteryGame(Scanner input) {
        System.out.println("\n=== WELCOME TO THE LOTTERY!! ===\n");

        Random rand = new Random();

        int lotteryNumber = rand.nextInt(90) + 10;
        int lotteryDigit1 = lotteryNumber / 10;
        int lotteryDigit2 = lotteryNumber % 10;

        System.out.println("Lottery Number (for testing purposes): " + lotteryNumber);

        int userGuess = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter your lottery pick (two digits): ");
            if (input.hasNextInt()) {
                userGuess = input.nextInt();
                input.nextLine(); // consume newline
                if (userGuess >= 10 && userGuess <= 99) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a two-digit number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a two-digit number.");
                input.next(); // discard invalid input
            }
        }

        int guessDigit1 = userGuess / 10;
        int guessDigit2 = userGuess % 10;

        if (userGuess == lotteryNumber) {
            System.out.println("Exact match: you win $10,000");
        } else if ((guessDigit1 == lotteryDigit2 && guessDigit2 == lotteryDigit1)) {
            System.out.println("Match all digits: you win $3,000");
        } else if (
            guessDigit1 == lotteryDigit1 || guessDigit1 == lotteryDigit2 ||
            guessDigit2 == lotteryDigit1 || guessDigit2 == lotteryDigit2
        ) {
            System.out.println("Match one digit: you win $1,000");
        } else {
            System.out.println("Sorry, no match.");
        }

        System.out.println("Thanks for playing the lottery!");
    }

    // CRAPS GAME
    public static void playCraps(Scanner input) {
        System.out.println("\n=== WELCOME TO CRAPS!! ===\n");
        double netWorth = 50.0;
        Random rand = new Random();

        while (netWorth > 0) {
            System.out.println("Your current net worth: $" + netWorth);
            double bet = 0;

            boolean validBet = false;
            while (!validBet) {
                System.out.print("Enter your bet (max $" + netWorth + "): ");
                if (input.hasNextDouble()) {
                    bet = input.nextDouble();
                    input.nextLine(); // clear newline
                    if (bet > 0 && bet <= netWorth) {
                        validBet = true;
                    } else {
                        System.out.println("Invalid bet. Bet must be > 0 and ≤ $" + netWorth);
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    input.next(); // clear invalid
                }
            }

            // First roll
            int total = rollDice(rand);
            System.out.println("You rolled a total of: " + total);

            boolean won = false;
            boolean firstRoll = true;
            int point = 0;

            if (total == 7 || total == 11) {
                won = true;
            } else if (total == 2 || total == 3 || total == 12) {
                won = false;
            } else {
                point = total;
                System.out.println("Point is set to: " + point);
                firstRoll = false;

                // Keep rolling
                while (true) {
                    System.out.print("Press Enter to roll again...");
                    input.nextLine();

                    total = rollDice(rand);
                    System.out.println("You rolled a total of: " + total);

                    if (total == point) {
                        won = true;
                        break;
                    } else if (total == 7) {
                        won = false;
                        break;
                    }
                }
            }

            // Update net worth
            if (won) {
                System.out.println("You win!");
                netWorth += bet;
            } else {
                System.out.println("You lose!");
                netWorth -= bet;
            }

            // Check if player is broke
            if (netWorth <= 0) {
                System.out.println("\nYou're out of money. Game over!");
                break;
            }

            // Ask to play again
            System.out.print("\nDo you want to play another round? (y/n): ");
            String again = input.nextLine().trim().toLowerCase();
            if (!again.equals("y")) {
                break;
            }
        }

        System.out.printf("Thanks for playing! You ended with $%.2f\n", netWorth);
    }

    // HELPER: Roll two six-sided dice
    private static int rollDice(Random rand) {
        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        System.out.println("Rolled: " + die1 + " + " + die2);
        return die1 + die2;
    }

    // SCRAPS GAME
    public static void playScraps(Scanner input) {
        System.out.println("\n=== WELCOME TO SCRAPS!! ===\n");

        Random rand = new Random();
        double netWorth = 50.0;

        while (netWorth > 0) {
            System.out.println("Your current net worth: $" + netWorth);
            double bet = 0;

            // Get valid bet
            boolean validBet = false;
            while (!validBet) {
                System.out.print("Enter your bet (max $" + netWorth + "): ");
                if (input.hasNextDouble()) {
                    bet = input.nextDouble();
                    input.nextLine(); // consume newline
                    if (bet > 0 && bet <= netWorth) {
                        validBet = true;
                    } else {
                        System.out.println("Invalid bet. Bet must be > 0 and ≤ $" + netWorth);
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    input.next(); // clear invalid input
                }
            }

            // Roll 3 eight-sided dice
            int die1 = rand.nextInt(8) + 1;
            int die2 = rand.nextInt(8) + 1;
            int die3 = rand.nextInt(8) + 1;
            int total = die1 + die2 + die3;

            System.out.println("Rolled: " + die1 + " + " + die2 + " + " + die3 + " = " + total);

            boolean won = false;
            boolean lost = false;

            // Immediate win
            if (die1 == 8 || die2 == 8 || die3 == 8) {
                System.out.println("You rolled an 8! You win!");
                won = true;
            } else if (total == 9 || total == 10 || total == 14) {
                System.out.println("Total is " + total + " — You win!");
                won = true;
            }
            // Immediate lose
            else if (die1 == 1 || die2 == 1 || die3 == 1) {
                System.out.println("You rolled a 1! You lose!");
                lost = true;
            } else if (total == 8 || total == 20 || total == 23 || total == 24) {
                System.out.println("Total is " + total + " — You lose!");
                lost = true;
            }

            // If neither win nor lose, set point
            if (!won && !lost) {
                int point = total;
                System.out.println("Point is set to: " + point);

                // Loop to try hitting the point or losing
                while (true) {
                    System.out.print("Press Enter to roll again...");
                    input.nextLine();

                    die1 = rand.nextInt(8) + 1;
                    die2 = rand.nextInt(8) + 1;
                    die3 = rand.nextInt(8) + 1;
                    total = die1 + die2 + die3;

                    System.out.println("Rolled: " + die1 + " + " + die2 + " + " + die3 + " = " + total);

                    // Check lose conditions first
                    if (die1 == 8 || die2 == 8 || die3 == 8) {
                        System.out.println("You rolled an 8 during point phase — You lose!");
                        lost = true;
                        break;
                    } else if (total == 15) {
                        System.out.println("Total is 15 — You lose!");
                        lost = true;
                        break;
                    } else if (total == point) {
                        System.out.println("You hit the point! You win!");
                        won = true;
                        break;
                    }
                }
            }

            // Update net worth
            if (won) {
                netWorth += bet;
            } else if (lost) {
                netWorth -= bet;
            }

            System.out.printf("Your updated net worth: $%.2f\n", netWorth);

            if (netWorth <= 0) {
                System.out.println("\nYou're out of money. Game over!");
                break;
            }

            // Ask to play again
            System.out.print("\nDo you want to play another round? (y/n): ");
            String again = input.nextLine().trim().toLowerCase();
            if (!again.equals("y")) {
                break;
            }
        }

        System.out.printf("Thanks for playing Scraps! You ended with $%.2f\n", netWorth);
    }

    // ROCK PAPER SCISSORS GAME
    public static void playRockPaperScissors(Scanner input) {
        System.out.println("\n=== WELCOME TO ROCK, PAPER, SCISSORS! ===\n");

        Random rand = new Random();
        boolean keepPlaying = true;

        while (keepPlaying) {
            System.out.println("Choose your move:");
            System.out.println("0 = Rock");
            System.out.println("1 = Paper");
            System.out.println("2 = Scissors");

            int userChoice = -1;

            // Get user input
            while (true) {
                System.out.print("Enter your choice (0-2): ");
                if (input.hasNextInt()) {
                    userChoice = input.nextInt();
                    input.nextLine(); // consume newline
                    if (userChoice >= 0 && userChoice <= 2) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter 0, 1, or 2.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    input.next(); // discard invalid input
                }
            }

            int computerChoice = rand.nextInt(3);

            // Display choices
            String[] choices = {"Rock", "Paper", "Scissors"};
            System.out.println("You chose: " + choices[userChoice]);
            System.out.println("Computer chose: " + choices[computerChoice]);

            // Determine winner
            if (userChoice == computerChoice) {
                System.out.println("It's a tie!");
            } else if (
                (userChoice == 0 && computerChoice == 2) ||
                (userChoice == 1 && computerChoice == 0) ||
                (userChoice == 2 && computerChoice == 1)
            ) {
                System.out.println("You win!");
            } else {
                System.out.println("You lose!");
            }

            // Ask to play again
            System.out.print("\nDo you want to play again? (y/n): ");
            String again = input.nextLine().trim().toLowerCase();
            if (!again.equals("y")) {
                keepPlaying = false;
            }
        }

        System.out.println("Thanks for playing Rock, Paper, Scissors!");
    }
    // ROCK PAPER SCISSORS SPOCK GAME
    public static void playRockPaperScissorsSpock(Scanner input) {
        System.out.println("\n=== WELCOME TO ROCK, PAPER, SCISSORS, SPOCK! ===\n");

        Random rand = new Random();
        boolean keepPlaying = true;

        while (keepPlaying) {
            System.out.println("Choose your move:");
            System.out.println("0 = Rock");
            System.out.println("1 = Paper");
            System.out.println("2 = Scissors");
            System.out.println("3 = Spock");

            int userChoice = -1;

            // Get user input
            while (true) {
                System.out.print("Enter your choice (0-3): ");
                if (input.hasNextInt()) {
                    userChoice = input.nextInt();
                    input.nextLine(); // consume newline
                    if (userChoice >= 0 && userChoice <= 3) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter 0, 1, 2, or 3.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    input.next(); // discard invalid input
                }
            }

            int computerChoice = rand.nextInt(4);

            // Display choices
            String[] choices = {"Rock", "Paper", "Scissors", "Spock"};
            System.out.println("You chose: " + choices[userChoice]);
            System.out.println("Computer chose: " + choices[computerChoice]);

            // Determine winner
            if (userChoice == computerChoice) {
                System.out.println("It's a tie!");
            } else if (
                (userChoice == 0 && computerChoice == 2) || // Rock crushes Scissors
                (userChoice == 1 && computerChoice == 0) || // Paper covers Rock
                (userChoice == 2 && computerChoice == 1) || // Scissors cuts Paper
                (userChoice == 1 && computerChoice == 3) || // Paper exposes Spock
                (userChoice == 3 && computerChoice == 0) || // Spock pulverizes Rock
                (userChoice == 3 && computerChoice == 2)    // Spock uses Scissors
            ) {
                System.out.println("You win!");
            } else {
                System.out.println("You lose!");
            }

            // Ask to play again
            System.out.print("\nDo you want to play again? (y/n): ");
            String again = input.nextLine().trim().toLowerCase();
            if (!again.equals("y")) {
                keepPlaying = false;
            }
        }

        System.out.println("Thanks for playing Rock, Paper, Scissors, Spock!");
    }

}
