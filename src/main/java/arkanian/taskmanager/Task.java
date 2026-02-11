package arkanian.taskmanager;

import arkanian.arkanianexceptions.InvalidTaskFormatException;

public class Task {
    protected String task_name;
    protected String[] parsed_task;
    boolean is_done = false;

    public Task(String name) {
        this.task_name = name;
        this.parsed_task = this.task_name.split(" ");

        if (this.parsed_task.length == 1) {
            throw new InvalidTaskFormatException("bruh... you didn't type any task");
        }
    }

    public boolean getIsDone() {
        return is_done;
    }

    public void setDone() {
        this.is_done = true;
    }

    public void setNotDone() {
        this.is_done = false;
    }

    protected int getIdxOf(String target) {
        for (int i = 0; i < parsed_task.length; i++) {
            if (parsed_task[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public String getTaskName() {
        return this.task_name;
    }

    @Override
    public String toString() {
        String check_mark = is_done ? "X" : "   ";
        return "[" + check_mark + "] " + this.task_name;
    }
}
