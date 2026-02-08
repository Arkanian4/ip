package arkanian.userprompts;

/**
 * Filters a raw String input from the user to
 * extract relevant fields
 */
public class Input {
    final private String[] split_input;

    public Input(String input) {
        this.split_input = input.split(" ");
    }

    public int len() {
        return split_input.length;
    }

    public String getInstr() {
        return split_input[0];
    }

    public String getTaskName() {
        return split_input[1];
    }

    public int getIdx() {
        return Integer.valueOf(split_input[1]);
    }

}
