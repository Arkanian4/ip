package arkanian.taskmanager;

import arkanian.arkanianexceptions.InvalidTaskFormatException;

/**
 * Represents a ToDo task, which is a simple task without a specific date or time.
 * <p>
 * Extends the {@link Task} class.
 */
public class ToDos extends Task {

    /**
     * Creates a new ToDo task from the given input string.
     * The input string is expected to have a command word followed by the task description.
     *
     * @param todo the input string containing the command and task description
     * @throws InvalidTaskFormatException if the task description is missing or empty
     */
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
