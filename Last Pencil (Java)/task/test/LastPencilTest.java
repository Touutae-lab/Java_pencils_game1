import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.util.*;
import java.util.regex.Pattern;

public class LastPencilTest extends StageTest<String> {

    public LastPencilTest() {
        test_data = new Object[][]{
                {2, 1, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {3, 1, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {4, 1, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {5, 1, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {9, 1, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {13, 1, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {17, 1, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {21, 1, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {101, 1, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {2, 2, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {3, 2, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {4, 2, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {5, 2, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {9, 2, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {13, 2, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {17, 2, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {21, 2, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
                {101, 2, new String[]{"4", "a", "0", "-1", "_", "|", "|||||"}},
        };
        data = new String[]{"a", "_", "test", "|", "|||||", " ", "-", "two", "10g", "k5", "-0.2", "0.3"};

    }


    @DynamicTest()
    CheckResult checkOut() {//all comments correspond to before parts of tests
        TestedProgram main = new TestedProgram();
        String output = main.start().toLowerCase();
        String[] lines = output.strip().split("\\n");
        if (lines.length != 1 || !Pattern.matches("^(how many pencils would you like to use)\\??:?$", lines[0])) {
            return CheckResult.wrong("When the game starts, it should output only one line asking the user about the " +
                    "amount of pencils they would like to use with the \"How many pencils would you like to use\" string.");
        }
        String output2 = main.execute("1").replaceAll(" ", "");

        String whoWillBeRegex = "^(whowillbethefirst).*";
        String playerChecking = ".*\\([a-zA-Z_0-9]+,[a-zA-Z_0-9]+\\):?";

        if (output2.split("\\n").length != 1) {
            return CheckResult.wrong("When the user replies with the amount of pencils, the game should print 1 non-empty "
                    + "line asking who will be the first player.\n"
                    + output2.split("\n").length + " lines were found in the output.");
        }
        output2 = output2.toLowerCase().strip();

        if (!Pattern.matches(whoWillBeRegex, output2))
            return CheckResult.wrong("Discrepancy with the task, pay attention to the line " +
                    "\"Who will be the first\"");
        if (!Pattern.matches(playerChecking, output2))
            return CheckResult.wrong("Discrepancy with the task, output example" +
                    " \"Who will be the first (Name1, Name2):\"");
        return CheckResult.correct();
    }

    String[] data;

    @DynamicTest(data = "data")
//all comments correspond to before parts of tests
    CheckResult checkNumericAmount(String input) {
        TestedProgram main = new TestedProgram();
        main.start();
        String output = main.execute(input).toLowerCase();

        if (!output.contains("number of pencils") || !output.contains("numeric")) {
            return CheckResult.wrong("When the user provides the number of pencils as a non-numeric sequence, the game should "
                    + "inform the user that their input is incorrect and prompt the user for input again "
                    + "with the \"The number of pencils should be numeric\" string.");
        }
        for (int i = 1; i < 5; i++) {
            if (!output.contains("number of pencils") || !output.contains("numeric")) {
                return CheckResult.wrong("When the user provides the number of pencils as a non-numeric sequence, the game should "
                        + "inform the user that their input is incorrect and prompt the user for input again "
                        + "with the \"The number of pencils should be numeric\" string.");
            }
        }
        return CheckResult.correct();
    }

    @DynamicTest()
    CheckResult checkZero() {//all comments correspond to before parts of tests
        TestedProgram main = new TestedProgram();
        main.start();
        String output = main.execute("0").toLowerCase();

        if (!output.contains("number of pencils") || !output.contains("positive")) {
            return CheckResult.wrong("When the user provides \"0\" as a number of pencils, "
                    + "the game should inform the user that their input is incorrect and"
                    + " prompt the user for input again with the \"The number of pencils " + "should be positive\" string.");
        }

        for (int i = 1; i < 5; i++) {
            output = main.execute("0").toLowerCase();
            if (!output.contains("number of pencils") || !output.contains("positive")) {
                return CheckResult.wrong("When the user provides \"0\" as a number of pencils, "
                        + "the game should inform the user that their input is incorrect and "
                        + "prompt the user for input again with the \"The number of pencils " + "should be positive\" string.");

            }
        }
        return CheckResult.correct();
    }

    @DynamicTest()
    CheckResult checkBothAmount() {//all comments correspond to before parts of tests
        TestedProgram main = new TestedProgram();
        main.start();

        String output = main.execute("0").toLowerCase();
        if (!output.contains("number of pencils") || !output.contains("positive")) {
            return CheckResult.wrong("When the user provides \"0\" as a number of pencils, the game should "
                    + "inform the user that their input is incorrect and prompt the user for input again "
                    + "with the \"The number of pencils should be positive\" string.");
        }

        output = main.execute("a").toLowerCase();
        if (!output.contains("number of pencils") || !output.contains("numeric")) {
            return CheckResult.wrong("When the user provides the number of pencils as a non-numeric sequence, the game should "
                    + "inform the user that their input is incorrect and prompt the user for input again " + "with the \"The number of pencils should be numeric\" string.");
        }

        output = main.execute("0").toLowerCase();
        if (!output.contains("number of pencils") || !output.contains("positive")) {
            return CheckResult.wrong("When the user provides \"0\" as a number of pencils, the game should "
                    + "inform the user that their input is incorrect and prompt the user for input again "
                    + "with the \"The number of pencils should be positive\" string.");
        }

        String output2 = main.execute("1").replaceAll(" ", "").strip();
        if (!Pattern.matches(".*\\([a-zA-Z_0-9]+,[a-zA-Z_0-9]+\\):?", output2)) {
            return CheckResult.wrong("When the user inputs the number of pencils correctly," +
                    " the game should ask who will be the first player ending with the \"(\"Name1\", \"Name2\")\" string.");
        }
        return CheckResult.correct();
    }

    static Object[][] test_data;

    @DynamicTest(data = "test_data")
    CheckResult checkResult(int amount, int first, String[] incorrect) {
        TestedProgram main = new TestedProgram();
        main.start();

        String output = main.execute(String.valueOf(amount)).replace(" ", "");

        String leftName = output.substring(output.lastIndexOf("(") + 1, output.lastIndexOf(","));
        String rightName = output.substring(output.lastIndexOf(",") + 1, output.lastIndexOf(")"));
        // Checking names
        if (leftName.equals(rightName)) { //Checking player name1 != name2
            return CheckResult.wrong("The names of the players must be different," +
                    " lines were found in the output: Name1 - \""
                    + leftName + "\" Name2 - \"" + rightName + "\".");
        }
        String firstPlayer;
        String nextPlayer;

        if (first == 1) {
            firstPlayer = leftName;
            nextPlayer = rightName;
        } else {
            firstPlayer = rightName;
            nextPlayer = leftName;
        }
        String output2 = main.execute(leftName + rightName).toLowerCase();

        String format = String.format("When the user provides a name that is not '%s' or '%s'," +
                " the game should inform the user that their input is incorrect " +
                "and prompt the user for input again with the " +
                "\"Choose between '%s' and '%s'\" string.", leftName, rightName, leftName, rightName);
        if (!output2.contains("choose between") || !output2.contains(leftName.toLowerCase()) || !output2.contains(rightName.toLowerCase())) {
            return CheckResult.wrong(format); // Checking task "choose between"
        }
        for (int j = 1; j < 5; j++) { //Checking for loop stability
            output2 = main.execute(leftName + rightName).toLowerCase();
            if (!output2.contains("choose between") || !output2.contains(leftName.toLowerCase()) || !output2.contains(rightName.toLowerCase())) {
                return CheckResult.wrong(format);
            }
        }

        //nonempty lines checking
        String output3 = main.execute(firstPlayer).toLowerCase();
        String[] lines = output3.strip().split("\\r?\\n");
        List<String> linesNonEmpty = Arrays.stream(lines).filter(s -> s.length() != 0).toList();

        if (first == 1) {
            if (linesNonEmpty.size() != 2) { // checking a start task, program should print 2 non-empty lines
                return CheckResult.wrong("When the player provides the correct initial game conditions, "
                        + "the program should print 2 non-empty lines if " + firstPlayer + " is the first player.");
            }
            //checking lines with pencils
            List<String> checkPencils = Arrays.stream(lines).filter(s -> s.contains("|")).toList();
            if (checkPencils.size() != 1) {//checking initial game conditions for lines with pencils
                return CheckResult.wrong("When the player provides the correct initial game conditions, "
                        + "the program should print one line that contains vertical bar symbols if " + firstPlayer
                        + " is the first player.");
            }
            if (checkPencils.get(0).chars().distinct().count() != 1) {// checking any chars except |
                return CheckResult.wrong("The line with pencils should not contain any symbols other than the '|' symbol.");
            }
            if (checkPencils.get(0).length() != amount) {// checking matching the entered characters and the characters on the output
                return CheckResult.wrong("The line with pencils should contain as many '|' symbols as the player provided.");
            }

            if (!linesNonEmpty.get(1).matches(firstPlayer.toLowerCase() + "|.+turn[:!]?$")) { //Checking name and "turn!?:?"
                return CheckResult.wrong("When the player provides the initial game conditions, there should be a line in the output "
                        + "that contains the \"" + firstPlayer + "'s turn\" string if " + firstPlayer
                        + " is the first player.");
            }

        } else {
            if (linesNonEmpty.size() != 5) { //Checking if choices bot
                return CheckResult.wrong("When the player provides the correct initial game conditions "
                        + "and if " + firstPlayer + " goes first, your program should print 5 non-empty lines "
                        + "in such order:\n"
                        + "2 for " + firstPlayer + "\n"
                        + "1 for " + firstPlayer + "'s move\n"
                        + "2 for " + nextPlayer);
            }
            //Create variables for tests
            List<String> linesFirst = linesNonEmpty.subList(0, 2);
            int turn = Integer.parseInt(linesNonEmpty.get(2));
            List<String> linesNext = linesNonEmpty.subList(3, 5);
            List<String> checkPencils = linesFirst.stream().filter(s -> s.contains("|")).toList();

            if (checkPencils.size() != 1) {//Checking pencils in lines
                return CheckResult.wrong("When the player provides the correct initial game conditions"
                        + ", the program should print one line for each player, that contains '|' "
                        + "symbols if " + first + " is the first player.");
            }

            if (checkPencils.get(0).chars().distinct().count() != 1) { // checking any chars except |
                return CheckResult.wrong("The line with pencils should not contain any symbols other than the '|' symbol.");
            }
            if (checkPencils.get(0).length() != amount) { //checking pencils "line with pencils should contain as many '|' symbols"
                return CheckResult.wrong("When the player provides the correct initial game conditions,"
                        + "the " + firstPlayer + "'s line with pencils should contain as many '|' symbols "
                        + "as the player provided.");
            }


            boolean checkTurn = linesNonEmpty.get(1).matches(firstPlayer.toLowerCase() + "|.+turn[:!]?$");
            if (!checkTurn) { //Checking players and "turn"
                return CheckResult.wrong("When the player provides the correct initial game conditions"
                        + " there should be a line in the output for the " + firstPlayer + "'s turn that contains "
                        + "\"" + firstPlayer + "\" and \"turn\" strings if '" + firstPlayer + "' is the "
                        + "first player.");
            }

            if (turn != 1 && turn != 2 && turn != 3) { // checking correct initial game conditions "task" correct 5 non-empty lines
                return CheckResult.wrong("When the player provides the correct initial game conditions"
                        + " and the first player is " + firstPlayer + ", your program should print 5 non-empty lines, "
                        + "third of them is " + firstPlayer + "'s turn, so it should be either '1', '2' or '3'.");
            }

            int botTake = (amount - 1) % 4;

            if (botTake != 0 && turn != botTake) {// checking "winning strategy"
                return CheckResult.wrong("The " + firstPlayer + "'s move doesn't follow a winning strategy. Example:\n"
                        + "If there are " + amount + " pencils left and it's " + firstPlayer + "'s turn, he takes " + turn
                        + " pencils instead of " + botTake + ".");
            }

            checkPencils = linesFirst.stream().filter(s -> s.contains("|")).toList(); //create a list with pencils in lines


            if (checkPencils.size() != 1) {//checking the correct initial game conditions for lines with pencils
                return CheckResult.wrong("When the player provides the correct initial game conditions"
                        + ", there should be exactly one line in the output for each player that contains '|' "
                        + "symbols if " + firstPlayer + " is the first player.");
            }
            if (checkPencils.get(0).chars().distinct().count() != 1) { // checking any chars except |
                return CheckResult.wrong("The line with pencils should not contain any symbols other than the '|' symbol.");
            }
            if (linesNext.get(0).length() != amount - turn) { // checking "does not match the number of pens the player has chosen"
                return CheckResult.wrong(String.format("When the player provides the correct initial game conditions, the %s's line with pencils should contain as many '|' symbols as there is left after the %s's turn.", nextPlayer, firstPlayer));
            }


            if (!linesNext.get(1).toLowerCase().matches(nextPlayer.toLowerCase() + "|.+turn[:!]?$")) {// an again checking name and "turn"
                return CheckResult.wrong("When the player provides the correct initial game conditions"
                        + " there should be a line in the output for " + nextPlayer + "'s turn that contains "
                        + "\"" + nextPlayer + "\" and \"turn\" substrings if '" + firstPlayer + "' is the "
                        + "first player.");
            }
        }


        int onTable = amount;
        if (first == 2) {// Name swaps for continued testing
            onTable -= Integer.parseInt(linesNonEmpty.get(2));
            String temp = firstPlayer;
            firstPlayer = nextPlayer;
            nextPlayer = temp;
        }
        while (onTable > 0) { // create loop for last testings block
            for (String value : incorrect) {
                output = main.execute(value).toLowerCase();

                if (!output.contains("possible values") || !output.contains("1") ||
                        !output.contains("2") || !output.contains("3")) { //checking requirements for a task if |>3 and |<1
                    return CheckResult.wrong("If the player enters values different from '1', '2', or '3', " +
                            "the game should inform the user that their input is incorrect and " +
                            "prompt the user for input again with the \"Possible values: '1', '2', '3'\" string.");
                }
                if (!output.contains(firstPlayer.toLowerCase()) && output.contains(nextPlayer.toLowerCase())) {//checking requirements for a task if not finds correct name
                    return CheckResult.wrong("When " + firstPlayer + " provides values different from '1', '2', or '3', " +
                            "you need to prompt " + firstPlayer + " for input again.\n" +
                            "However, the " + nextPlayer + "'s name was found in your output.");
                }
            }
            int i = new Random().nextInt(3) + 1;
            output = main.execute(String.valueOf(i)).toLowerCase();
            if (i >= onTable) {
                if (onTable != i) {
                    if (!output.contains("too many") || !output.contains("pencils")) { //checking requirements for task "too many" "pencils" were taken" if player choice > pencils
                        return CheckResult.wrong("If the player enters the number of pencils that is greater than " +
                                "the current number of pencils on the table, the game should inform " +
                                "the user that their input is incorrect and prompt the user for input " +
                                "again with the \"too many pencils\" string.");
                    }
                    output = main.execute(Integer.toString(onTable)).toLowerCase();
                }

                lines = output.strip().split("\\n");
                linesNonEmpty = List.of(Arrays.stream(lines).filter(s -> s.length() != 0).toArray(String[]::new));

                if (linesNonEmpty.size() != 1 || !linesNonEmpty.get(0).toLowerCase().contains(nextPlayer.toLowerCase()) ||
                        (!linesNonEmpty.get(0).contains("win") && !linesNonEmpty.get(0).contains("won"))) { //checking requirement for a task "who is winner?"
                    return CheckResult.wrong("When the last pencil is taken, the program should print one line that informs " +
                            "who is the winner in this game with \"*Name*\" and \"win\"/\"won\" strings.");
                }

                if (!main.isFinished()) {//finished this block and checking last output
                    return CheckResult.wrong("Your program should not request anything when there are no pencils left.");
                }

                return CheckResult.correct();
            }
            //continue testing
            onTable -= i;
            lines = output.strip().split("\\n");
            linesNonEmpty = Arrays.stream(lines).filter(s -> s.length() != 0).toList();

            if (onTable == 1) {
                if (linesNonEmpty.size() != 4) {//checking requirements for task "line output after if player has 1 pencil"
                    return CheckResult.wrong(String.format("""
                            if %s is left with 1 pencil after %s inputs the number of pencils they will take, your program should print 4 non-empty lines in such order:
                            2 for %s
                            1 for %s's move
                            1 for game-results""", nextPlayer, firstPlayer, nextPlayer, nextPlayer));
                }
                //passing information to variables for further testing
                List<String> linesPrev = linesNonEmpty.subList(0, 2);
                String turn = linesNonEmpty.get(2);
                String result = linesNonEmpty.get(3);

                List<String> checkPencils = linesPrev.stream().filter(s -> s.contains("|")).toList(); // pencils list


                if (checkPencils.size() != 1) {//checking task requirement "if line with pencils !=1"
                    return CheckResult.wrong(String.format("After %s inputs the number of pencils they will take, there should be exactly one line in the output for each player that contains '|'.", firstPlayer));
                }
                if (checkPencils.get(0).length() != 1) {//checking task requirement "don't any chars in line, only |"
                    return CheckResult.wrong("The line with pencils should not contain any symbols other than the '|' symbol.");
                }
                if (checkPencils.get(0).length() != onTable) { //checking task requirement "if after player choice pencils != correct choice pencils"
                    return CheckResult.wrong(String.format("After %s inputs the number of pencils they will take, the lines with pencils for %s should contain as many '|' symbols as there are pencils left.", firstPlayer, nextPlayer));
                }
                if (!linesPrev.get(1).toLowerCase().matches(nextPlayer.toLowerCase() + "|.+turn[:!]?$")) {//checking task requirement "match the correct sequence of string name and turn"
                    return CheckResult.wrong(String.format("After %s inputs the number of pencils they will take, there should be a line in the output for %s's turn that contains \"%s\" and \"turn\" substrings.", firstPlayer, nextPlayer, nextPlayer));
                }
                if (!turn.equals("1")) {//checking task requirement "if left 1 pencils player not takes more"
                    return CheckResult.wrong(String.format("If %s is left with 1 pencil, they can't take any other amount of pencils except for 1.", nextPlayer));
                }
                if (!result.toLowerCase().contains(firstPlayer.toLowerCase()) || (!result.toLowerCase().contains("win") && !result.toLowerCase().contains("won"))) {//checking task requirement "WHO IS WINNER?"
                    return CheckResult.wrong("When the last pencil is taken, the program should print one line that informs who is the winner in this game with \"*Name*\" and \"win\"/\"won\" strings.");
                }
                if (!main.isFinished()) {//checking task requirement "if program output any request after game finished"
                    return CheckResult.wrong("Your program should not request anything when there are no pencils left.");
                }
                return CheckResult.correct();
            }

            if (linesNonEmpty.size() != 5) {//checking task requirement "if output lines not equals 5 lines"
                return CheckResult.wrong(String.format("""
                        After %s inputs the number of pencils they will take, your program should print 5 non-empty lines in such order:
                        2 for %s
                        1 for %s's move
                        2 for %s""", firstPlayer, nextPlayer, nextPlayer, firstPlayer));
            }
            //an again crete new variables
            List<String> linesPrev = linesNonEmpty.subList(0, 2);
            String turn = linesNonEmpty.get(2);
            List<String> linesNext = linesNonEmpty.subList(3, 5);

            List<String> checkPencils = linesPrev.stream().filter(s -> s.contains("|")).toList(); // pencils list

            if (checkPencils.size() != 1) { //checking task requirement "each player must have one pencils output line"
                return CheckResult.wrong(String.format("After %s inputs the number of pencils they will take, "
                        + "there should be exactly one line in output for each player, that contains '|'.", firstPlayer));
            }
            if (checkPencils.get(0).chars().distinct().count() != 1) {//checking any chars except |
                return CheckResult.wrong("The pencils-lines should not contain any other symbols except the '|'.");
            }
            if (checkPencils.get(0).length() != onTable) {//checking pencils in lines after player choice
                return CheckResult.wrong(String.format("After %s inputs the number of pencils they will take, "
                        + "the lines with pencils for %s should contain as many '|' symbols as there "
                        + "are pencils left.", firstPlayer, nextPlayer));
            }

            if (!linesPrev.get(1).toLowerCase().matches(nextPlayer.toLowerCase() + "|.+turn[:!]?$")) {//checking task requirement "match the correct sequence of string name and turn"
                return CheckResult.wrong(String.format("After %s inputs the number of pencils they will take, "
                        + "there should be a line in output for the %s's turn that contains "
                        + "\"%s\" and \"turn\" substrings.", firstPlayer, nextPlayer, nextPlayer));
            }

            if (!turn.equals("1") && !turn.equals("2") && !turn.equals("3")) {//checking task requirement "if output !=5 non-empty lines with name and turn"
                return CheckResult.wrong(String.format("After %s inputs the number of pencils they will take, "
                        + "your program should print 5 non-empty lines, third of them is %s's turn, "
                        + "so it should be either '1', '2' or '3'.", firstPlayer, firstPlayer));
            }


            int botTake = (onTable - 1) % 4;
            if (botTake != 0 && Integer.parseInt(turn) != botTake) {//checking task requirement "if bot doesn't follow a winning strategy"
                return CheckResult.wrong(String.format("The %s's move doesn't follow a winning strategy. Example:\n"
                        + "If there are %d pencils left and it's %s's turn, they take %s "
                        + "pencils instead of %d.", nextPlayer, onTable, nextPlayer, turn, botTake));

            }
            onTable -= Integer.parseInt(turn);

            checkPencils = linesNext.stream().filter(s -> s.contains("|")).toList(); // pencils list

            if (checkPencils.size() != 1) {//checking task requirement "an again check output lines with pencils if != 1 for player"
                return CheckResult.wrong(String.format("After %s inputs the number of pencils they will take, there should be exactly one line in output for each player that contains '|'.", firstPlayer));
            }

            if (checkPencils.get(0).chars().distinct().count() != 1) {//checking any chars except |
                return CheckResult.wrong("The line with pencils should not contain any symbols other than the '|' symbol.");
            }
            if (checkPencils.get(0).length() != onTable) {//checking task requirement "if inputs != correct number of pencils after take"
                return CheckResult.wrong(String.format("After %s inputs the number of pencils they will take, the line with pencils for %s should contain as many '|' symbols as there are pencils left.", firstPlayer, firstPlayer));
            }

            if (!linesNext.get(1).toLowerCase().matches(firstPlayer.toLowerCase() + "|.+turn[:!]?$")) {//checking task requirement "match the correct sequence of string name and turn"
                return CheckResult.wrong(String.format("After %s inputs the number of pencils they will take, "
                                + "there should be a line in the output for %s's turn containing \"%s\" and \"turn\" substrings.",
                        firstPlayer, firstPlayer, firstPlayer));
            }
        }
        return CheckResult.correct();
    }
}
