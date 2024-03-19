package lastpencil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        int pencils = -1;


        while (pencils <= 0) {
            String value = scanner.nextLine();
            try {
                pencils = Integer.parseInt(value);
                if (pencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }

        GameThread game = new GameThread("John", "Jack", "|", pencils);
        game.run();

        scanner.close();
    }
}

class GameThread {
    private final Scanner scanner = new Scanner(System.in);
    private final String pencilSign;
    private final int maxPencilsToRemove;
    private final Player HumanPlayer;
    private final Player Bot;
    private Player playerOnMove;

    GameThread(String humanPlayer,  String botPlayer, String pencilSign, int maxPencilsToRemove)  {
        this.HumanPlayer = new Human(humanPlayer);
        this.Bot = new Bot(botPlayer);
        this.pencilSign = pencilSign;
        this.maxPencilsToRemove = maxPencilsToRemove;
    }

    public void run() {
        this.init();
        this.runnable();
    }

    private void init() {
        this.pickFirstPlayer();
    }

    private void runnable() {
        int currentPencils = this.maxPencilsToRemove;
        while (currentPencils > 0) {
            System.out.printf("%s's turn!\n", playerOnMove.getName());
            int output = playerOnMove.makeMove(currentPencils);
            currentPencils -= output;
            this.playerOnMove = this.playerOnMove == this.HumanPlayer ? this.Bot : this.HumanPlayer;
            this.checkWinner(currentPencils);
        }
    }

    private void checkWinner(int pencilsLeft) {
        if (pencilsLeft > 0) {
            showLeftPencils(pencilsLeft);
        } else {
            System.out.printf("%s won!", this.playerOnMove.getName());
        }
    }


    private void pickFirstPlayer() {
        boolean acceptedInput = false;

        System.out.printf("Who will be the first (%s, %s):\n", this.HumanPlayer.getName(), this.Bot.getName());

        while (! acceptedInput) {
            try {
                String input = scanner.nextLine();
                if (input.equals(HumanPlayer.getName())) {
                    this.showLeftPencils(this.maxPencilsToRemove);
                    this.playerOnMove = HumanPlayer;
                    acceptedInput = true;
                } else if (input.equals(Bot.getName())) {
                    this.showLeftPencils(this.maxPencilsToRemove);
                    this.playerOnMove = Bot;
                    acceptedInput = true;
                } else {
                    throw new WrongNameException(HumanPlayer.getName(), Bot.getName());
                }
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showLeftPencils(int count) {
        System.out.println(this.pencilSign.repeat(count));
    }

}

class WrongNameException extends Exception {
    public WrongNameException(String nameOne, String nameTwo) {
        super(String.format("Choose between %s and %s", nameOne, nameTwo));
    }
}