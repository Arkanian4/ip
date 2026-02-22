package arkanian.taskmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import arkanian.arkanianexceptions.InvalidTaskFormatException;
import arkanian.userprompts.DateTimeParser;

/**
 * Represents a Deadline task.
 * <p>
 * A Deadline task includes a description and a due date/time.
 */
public class Deadlines extends Task {

    private final LocalDateTime dueDate;

    /**
     * Constructs a Deadline task from user input.
     *
     * Expected format:
     * deadline description /by dueDateTime
     */
    public Deadlines(String deadline) {
        super(deadline);

        int dueDateIdx = super.getIdxOf("/by");

        validateDueDateIndex(dueDateIdx);

        String[] parsedDetails = parseDeadlineDetails(dueDateIdx);

        super.taskName = parsedDetails[0];

        if (super.taskName.isEmpty()) {
            throw new InvalidTaskFormatException(
                    "Sniff sniff... I don't smell any deadlines here! T_T Can you give me some details, hooman?\n");
        }

        try {
            this.dueDate = DateTimeParser.convert(parsedDetails[1]);
        } catch (Exception e) {
            throw e; // Let Ui handle InvalidParameterException
        }
    }

    public LocalDateTime getDeadline() {
        return this.dueDate;
    }

    public String getDeadlineString() {
        return formatDateTime(this.dueDate);
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + getDeadlineString()
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
            throw new InvalidTaskFormatException(
                    "Arf! Don't forget when the tail-wagging ends! ^_^ Give me the deadline, buddy!\n");
        }
    }

    private String formatDateTime(LocalDateTime dt) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dt.format(formatter);
    }
}
