package lastpencil;

import java.util.Random;

public class Bot extends Player {
    public Bot(String name) {
        super(name);
    }

    @Override
    public int makeMove(int pencilsLeft) {
        int[] critical = {1, 2, 3};
         for (int i = 0; i < critical.length; i++) {
             if ((pencilsLeft - critical[i]) % 4 == 1) {
                 System.out.println(critical[i]);
                 return critical[i];
             }
        }
        Random rand  = new Random();
        int value = rand.nextInt(3) + 1;
        System.out.println(value);
         return value;
    }
}
