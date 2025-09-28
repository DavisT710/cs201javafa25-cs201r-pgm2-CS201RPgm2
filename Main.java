//Tanner Davis
// 9/22/2025
// CS201-R
// Program 2
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n\nWELCOME TO YOUR GAMES!!");
        Scanner scanInput = new Scanner(System.in);
        char choice;
        choice = menu(scanInput);
        
        while (choice != 'Q'){
            //test for choice type and call appropriate Game
            scanInput.nextLine();
            choice = menu(scanInput);
            choice = 'Q';
        }

        scanInput.close();

    }

    public static char menu(Scanner input){
        char choice = 'Z';
        String inputString;

        //menu loop
        //   print menu
        //   get response & convert to upper case
        
        System.out.println("\nPlease choose a game to play:");
        System.out.println("H - Hangman");
        System.out.println("B - Blackjack");
        System.out.println("L - Lottery");
        System.out.println("C - Craps");
        System.out.println("S - Scraps");
        System.out.println("R - Rock, Paper, Scissors");
        System.out.println("E - Rock, Paper, Scissors, Spock");
        System.out.println("Q - Quit");
        System.out.print("Enter your choice: ");
        inputString = input.nextLine().toUpperCase();
        if (inputString.length() > 0){
            choice = inputString.charAt(0);
        }
            //   validate response
        while (choice != 'H' && choice != 'B' && choice != 'L' && choice != 'Q' && choice != 'C' && choice != 'S' && choice != 'R' && choice != 'E'){
            System.out.println("Invalid choice. Please enter H, B, L, C, S, R, E, or Q.");
            System.out.print("Enter your choice: ");
            inputString = input.nextLine().toUpperCase();
            if (inputString.length() > 0){
                choice = inputString.charAt(0);
            }
        }
            //   return valid response
            if (choice == 'H'){
                PlayHangman.playHangman(input);
            } else if (choice == 'B'){
                PlayBlackjack.playBlackjack(input);
            } else if (choice == 'L'){
                Games.lotteryGame(input);
            } else if (choice == 'C'){
                Games.playCraps(input);
            } else if (choice == 'S'){
                Games.playScraps(input);
            } else if (choice == 'R'){
                Games.playRockPaperScissors(input);
            } else if (choice == 'E'){
                Games.playRockPaperScissorsSpock(input);
            }

        return choice;
    }
}

