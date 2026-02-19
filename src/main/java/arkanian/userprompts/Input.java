package arkanian.userprompts;

/**
 * Filters a raw String input from the user to
 * extract relevant fields
 */
public class Input {
    private final String[] splitInput;

    public Input(String input) {
        this.splitInput = input.split(" ");
    }

    public int len() {
        return splitInput.length;
    }

    public String getInstr() {
        return splitInput[0];
    }

    public String getTaskName() {
        return splitInput[1];
    }

    public int getIdx() {
        return Integer.parseInt(splitInput[1]);
    }

}
