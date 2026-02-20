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

        if (this.parsedTask.length == 1) {
            throw new InvalidTaskFormatException("bruh... you didn't type any task");
        }
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

    private int getIdxOfTags() {
        for (int i = 0; i < parsedTask.length; i++) {
            if (parsedTask[i].equals("/tag")) {
                return i;
            }
        }
        return -1;
    }

    private String getTagsString() {
        String[] temp = this.inputString.split("/tag ");
        if (temp.length == 2) {
            return temp[1];
        }
        return "";
    }

    protected TagList getTags() {
        return new TagList(this.getTagsString().split(" "));
    }

    protected int getIdxOfSearchLimit() {
        return this.getIdxOfTags() == -1
                ? this.parsedTask.length
                : this.getIdxOfTags();
    }

    protected int getIdxOf(String target) {
        int limitOfSearch = getIdxOfSearchLimit();

        for (int i = 0; i < limitOfSearch; i++) {
            if (parsedTask[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public String getTaskName() {
        String[] temp = this.taskName.split(" /tag");
        return temp[0];
    }

    public String getTaskString() {
        return taskName;
    }

    @Override
    public String toString() {
        String checkMark = isDone ? "X" : "   ";
        return "[" + checkMark + "] "
                + this.getTaskName();
    }
}
