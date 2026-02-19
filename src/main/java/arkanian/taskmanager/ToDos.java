package arkanian.taskmanager;

import arkanian.arkanianexceptions.InvalidTaskFormatException;

public class ToDos extends Task {

    public ToDos(String todo) {
        super(todo);

        String taskName = "";

        for (int i = 1; i < super.parsedTask.length; i++) {
            String word = super.parsedTask[i];

            taskName = taskName + word + " ";
        }

        super.taskName = taskName.trim();

        if (super.taskName.isEmpty()) {
            throw new InvalidTaskFormatException("bruh... I need more deets");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
