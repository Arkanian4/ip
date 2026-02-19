package arkanian.taskmanager;

import java.time.LocalDateTime;

import arkanian.arkanianexceptions.InvalidTaskFormatException;
import arkanian.userprompts.DateTimeParser;

/**
 * Represents a Deadline task
 * includes a deadline for completion of task
 */
public class Deadlines extends Task {
    private final String dueDate;

    /**
     * Constructs a Deadline task from a user input string.
     * <p>
     * The expected format is:
     * deadline task description /by due date
     *
     * @param deadline The full user input string for the deadline task.
     * @throws InvalidTaskFormatException If the input format is invalid,
     *                                    or if the description or due date is missing.
     */
    public Deadlines(String deadline) {
        super(deadline);

        int dueDateIdx = super.getIdxOf("/by");
        String taskName = "";
        String dueDate = "";

        if (dueDateIdx == -1) {
            throw new InvalidTaskFormatException("bruh... you didn't give me a deadline");
        }

        for (int i = 1; i < super.parsedTask.length; i++) {
            String word = super.parsedTask[i];

            if (i < dueDateIdx) {
                taskName = taskName + word + " ";
            } else if (dueDateIdx < i) {
                dueDate = dueDate + word + " ";
            }
        }

        super.taskName = taskName.trim();
        this.dueDate = dueDate.trim();

        if (super.taskName.isEmpty() || this.dueDate.isEmpty()) {
            throw new InvalidTaskFormatException("bruh... I need more deets");
        }
    }

    /**
     * Returns the deadline as a LocalDateTime object
     *
     * @return the date and time of the deadline
     */
    public LocalDateTime getDeadline() {
        return DateTimeParser.convert(this.dueDate);
    }

    public String getDeadlineString() {
        String dateTime = this.getDeadline().toString();

        return DateTimeParser.getDateString(dateTime)
                + " "
                + DateTimeParser.getTimeString(dateTime);
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.getDeadlineString()
                + ")";
    }
}
