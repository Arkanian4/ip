package arkanian.taskmanager;

import arkanian.arkanianexceptions.InvalidTaskFormatException;

public class Task {
    protected String taskName;
    protected String[] parsedTask;
    private boolean isDone = false;

    public Task(String name) {
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

    protected int getIdxOf(String target) {
        for (int i = 0; i < parsedTask.length; i++) {
            if (parsedTask[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        String checkMark = isDone ? "X" : "   ";
        return "[" + checkMark + "] " + this.taskName;
    }
}
