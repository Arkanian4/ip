package arkanian.taskmanager;

import arkanian.arkanianexceptions.InvalidTaskFormatException;

/**
 * Represents a task with a name and completion status.
 * Provides utility methods to check, update, and retrieve task information.
 */
public class Task {
    protected String inputString;
    protected String taskName;
    protected String[] parsedTask;
    private boolean isDone = false;

    /**
     * Creates a new Task with the specified name.
     * Splits the task name into words and validates its format.
     * <p>
     * @param name the name/description of the task
     * @throws InvalidTaskFormatException if the task name is empty or consists of only one word
     */
    public Task(String name) {
        this.inputString = name;
        this.taskName = name;
        this.parsedTask = this.taskName.split(" ");

        validateTaskFormat();
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String getTaskName() {
        return removeExcessFromName();
    }

    public String getInputString() {
        return inputString;
    }

    protected TagList getTags() {
        return new TagList(parseTags());
    }

    private int getIdxOfTags() {
        for (int i = 0; i < parsedTask.length; i++) {
            if (parsedTask[i].equals("/tag")) {
                return i;
            }
        }
        return -1;
    }

    private String[] parseTags() {
        String[] parts = inputString.split("/tag ");
        if (parts.length == 2) {
            return parts[1].split(" ");
        }
        return new String[0];
    }

    protected int getIdxOfSearchLimit() {
        int tagIdx = getIdxOfTags();
        return tagIdx == -1 ? parsedTask.length : tagIdx;
    }

    protected int getIdxOf(String target) {
        int limit = getIdxOfSearchLimit();
        for (int i = 0; i < limit; i++) {
            if (parsedTask[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    private void validateTaskFormat() {
        if (parsedTask.length == 0) {
            throw new InvalidTaskFormatException("Oops! You didn’t type any task 😅 Try again, buddy!");
        }
    }

    private String removeExcessFromName() {
        String[] parts = taskName.split(" /");
        return parts[0];
    }

    @Override
    public String toString() {
        String checkMark = isDone ? "X" : "   ";
        return "[" + checkMark + "] " + getTaskName();
    }
}
