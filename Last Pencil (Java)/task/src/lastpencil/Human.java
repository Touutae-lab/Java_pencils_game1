package lastpencil;

import java.util.Scanner;

public class Human extends Player {
    public Human(String name) {
        super(name);
    }

    @Override
    public int makeMove(int pencilsLeft) {
        Scanner scanner = new Scanner(System.in);
        int nums = -1;
        while (nums < 1 || nums > 3) {
            try {
                nums = Integer.parseInt(scanner.nextLine().trim());

                if (nums > 3 || nums < 1) {
                    System.out.println("Possible values: '1', '2', '3'");
                } else if (nums > pencilsLeft) {
                    nums = -1;
                    System.out.println("too many pencils");
                }
            } catch (NumberFormatException e) {
                System.out.println("Possible values: '1', '2', '3'");
            }
        }
        return nums;
    }
}
