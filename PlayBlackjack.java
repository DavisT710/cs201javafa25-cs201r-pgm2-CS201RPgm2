import java.util.*;

public class PlayBlackjack {
    public static void playBlackjack(Scanner scanner) {
        System.out.println("\nWELCOME TO BLACKJACK!!\n");

        boolean keepPlaying = true;
        while (keepPlaying) {
            // Create and shuffle deck
            List<Integer> deck = new ArrayList<>();
            for (int i = 1; i <= 52; i++) {
                deck.add(i);
            }
            Collections.shuffle(deck);

            int deckIndex = 0;
            List<Integer> playerHand = new ArrayList<>();
            List<Integer> dealerHand = new ArrayList<>();

            // Deal 2 cards each
            playerHand.add(deck.get(deckIndex++));
            dealerHand.add(deck.get(deckIndex++));
            playerHand.add(deck.get(deckIndex++));
            dealerHand.add(deck.get(deckIndex++));

            System.out.println("\nYour cards: " + getHandString(playerHand));
            System.out.println("Total: " + getHandValue(playerHand));
            System.out.println("Dealer shows: " + cardToString(dealerHand.get(0)));

            // Player Turn
            boolean playerBust = false;
            while (true) {
                System.out.print("\nWould you like another card? (hit/stand): ");
                String decision = scanner.nextLine().trim().toLowerCase();

                if (decision.equals("hit")) {
                    playerHand.add(deck.get(deckIndex++));
                    int playerValue = getHandValue(playerHand);
                    System.out.println("Your cards: " + getHandString(playerHand));
                    System.out.println("Total: " + playerValue);

                    if (playerValue > 21) {
                        System.out.println("You busted!");
                        playerBust = true;
                        break;
                    }
                } else if (decision.equals("stand")) {
                    break;
                } else {
                    System.out.println("Invalid input. Type 'hit' or 'stand'.");
                }
            }

            // Dealer Turn
            boolean dealerBust = false;
            if (!playerBust) {
                System.out.println("\nDealer's turn...");
                System.out.println("Dealer's cards: " + getHandString(dealerHand));
                while (getHandValue(dealerHand) <= 17) {
                    System.out.println("Dealer hits...");
                    dealerHand.add(deck.get(deckIndex++));
                    System.out.println("Dealer's cards: " + getHandString(dealerHand));
                }

                int dealerValue = getHandValue(dealerHand);
                System.out.println("Dealer's total: " + dealerValue);
                if (dealerValue > 21) {
                    System.out.println("Dealer busted!");
                    dealerBust = true;
                }
            }

            // Determine Winner
            int playerValue = getHandValue(playerHand);
            int dealerValue = getHandValue(dealerHand);

            if (playerBust) {
                System.out.println("\nYou busted! Dealer wins.");
            } else if (dealerBust) {
                System.out.println("\nDealer busted! You win!");
            } else if (playerValue > dealerValue) {
                System.out.println("\nYou win with " + playerValue + " against dealer's " + dealerValue + "!");
            } else if (dealerValue > playerValue) {
                System.out.println("\nDealer wins with " + dealerValue + " against your " + playerValue + ".");
            } else {
                System.out.println("\nIt's a PUSH (tie) at " + playerValue + ".");
            }

            // Ask to play again
            System.out.print("\nDo you want to play again? (Y/N): ");
            String response = scanner.nextLine().trim().toUpperCase();
            keepPlaying = response.equals("Y");
        }

        System.out.println("Thanks for playing!");
    }

    // Get Blackjack value of a card (1–13 -> 1–11, all face cards are worth 10)
    public static int getCardValue(int cardNumber) {
        int faceValue = (cardNumber - 1) % 13 + 1;
        if (faceValue >= 10) return 10;
        return faceValue;
    }

    // Get the string of a hand for display
    public static String getHandString(List<Integer> hand) {
        List<String> cards = new ArrayList<>();
        for (int card : hand) {
            cards.add(cardToString(card));
        }
        return String.join(", ", cards);
    }

    // Convert a card number to suit and face (e.g., "Ace of Hearts")
    public static String cardToString(int cardNumber) {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] faces = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        int faceIndex = (cardNumber - 1) % 13;
        int suitIndex = (cardNumber - 1) / 13;

        return faces[faceIndex] + " of " + suits[suitIndex];
    }

    // Compute Blackjack hand value with Ace handling (1 or 11)
    public static int getHandValue(List<Integer> hand) {
        int total = 0;
        int aceCount = 0;

        for (int card : hand) {
            int value = getCardValue(card);
            if (value == 1) {
                aceCount++;
                total += 11; // Initially count ace as 11
            } else {
                total += value;
            }
        }

        // If total > 21 and there are aces, count some aces as 1 instead
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }
}
