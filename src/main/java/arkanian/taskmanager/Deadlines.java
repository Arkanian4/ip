package arkanian.taskmanager;

import java.time.LocalDateTime;

import arkanian.arkanianexceptions.InvalidTaskFormatException;
import arkanian.userprompts.DateTimeParser;

/**
 * Represents a Deadline task.
 * <p>
 * A Deadline task includes a description and a due date/time.
 */
public class Deadlines extends Task {
    private final String dueDate;

    /**
     * Constructs a Deadline task from a user input string.
     * <p>
     * The expected format is:
     * <pre>
     * deadline task description /by due date
     * </pre>
     *
     * @param deadline The full user input string for the deadline task.
     * @throws InvalidTaskFormatException If the input format is invalid,
     *                                    or if the description or due date is missing.
     */
    public Deadlines(String deadline) {
        super(deadline);

        int dueDateIdx = super.getIdxOf("/by");

        validateDueDateIndex(dueDateIdx);

        String[] parsedDetails = parseDeadlineDetails(dueDateIdx);

        super.taskName = parsedDetails[0];
        this.dueDate = parsedDetails[1];

        validateDeadlineDetails();
    }

    public LocalDateTime getDeadline() {
        return DateTimeParser.convert(this.dueDate);
    }

    public String getDeadlineString() {
        return formatDateTime(this.getDeadline());
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.getDeadlineString()
                + ")"
                + "\n"
                + super.getTags();
    }

    private String[] parseDeadlineDetails(int dueDateIdx) {
        StringBuilder taskNameBuilder = new StringBuilder();
        StringBuilder dueDateBuilder = new StringBuilder();

        for (int i = 1; i < super.getIdxOfSearchLimit(); i++) {
            String word = super.parsedTask[i];
            if (i < dueDateIdx) {
                taskNameBuilder.append(word).append(" ");
            } else if (i > dueDateIdx) {
                dueDateBuilder.append(word).append(" ");
            }
        }

        return new String[]{
                taskNameBuilder.toString().trim(),
                dueDateBuilder.toString().trim()
        };
    }

    private void validateDueDateIndex(int dueDateIdx) {
        if (dueDateIdx == -1) {
            throw new InvalidTaskFormatException("bruh... you didn't give me a deadline");
        }
    }

    private void validateDeadlineDetails() {
        if (super.taskName.isEmpty() || this.dueDate.isEmpty()) {
            throw new InvalidTaskFormatException("bruh... I need more deets");
        }
    }

    private String formatDateTime(LocalDateTime dt) {
        String dateTime = dt.toString();
        return DateTimeParser.getDateString(dateTime)
                + " "
                + DateTimeParser.getTimeString(dateTime);
    }
}
