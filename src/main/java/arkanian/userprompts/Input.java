package arkanian.userprompts;

import arkanian.taskmanager.Task;

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
        return new Task(splitInput[1]).getTaskName();
    }

    public int getIdx() {
        return Integer.parseInt(splitInput[1]);
    }

}
