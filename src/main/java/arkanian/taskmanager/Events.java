package arkanian.taskmanager;

import java.time.LocalDateTime;

import arkanian.arkanianexceptions.InvalidTaskFormatException;
import arkanian.userprompts.DateTimeParser;

/**
 * Represents an event task.
 * <p>
 * An Event task includes a description, a start date/time,
 * and an end date/time.
 */
public class Events extends Task {
    private final String from;
    private final String to;

    /**
     * Constructs an Event task from a user input string.
     * <p>
     * The expected format is:
     * <pre>
     * event task description /from start date/time /to end date/time
     * </pre>
     *
     * @param event The full user input string for the event task.
     * @throws InvalidTaskFormatException If the input format is invalid,
     *                                    or if the description, start time,
     *                                    or end time is missing.
     */
    public Events(String event) {
        super(event);

        int fromIdx = getIdxOf("/from");
        int toIdx = getIdxOf("/to");

        validateEventIndices(fromIdx, toIdx);

        String[] parsedDetails = parseEventDetails(fromIdx, toIdx);

        super.taskName = parsedDetails[0];
        this.from = parsedDetails[1];
        this.to = parsedDetails[2];

        validateEventDetails();
    }

    public LocalDateTime getFrom() {
        return DateTimeParser.convert(this.from);
    }

    public String getFromString() {
        return formatDateTime(this.getFrom());
    }

    public LocalDateTime getTo() {
        return DateTimeParser.convert(this.to);
    }

    public String getToString() {
        return formatDateTime(this.getTo());
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + this.getFromString()
                + " to: "
                + this.getToString()
                + ")"
                + "\n"
                + super.getTags();
    }

    private String[] parseEventDetails(int fromIdx, int toIdx) {
        StringBuilder taskNameBuilder = new StringBuilder();
        StringBuilder fromBuilder = new StringBuilder();
        StringBuilder toBuilder = new StringBuilder();

        for (int i = 1; i < super.getIdxOfSearchLimit(); i++) {
            String word = super.parsedTask[i];
            if (i < fromIdx) {
                taskNameBuilder.append(word).append(" ");
            } else if (i > fromIdx && i < toIdx) {
                fromBuilder.append(word).append(" ");
            } else if (i > toIdx) {
                toBuilder.append(word).append(" ");
            }
        }

        return new String[]{
                taskNameBuilder.toString().trim(),
                fromBuilder.toString().trim(),
                toBuilder.toString().trim()
        };
    }

    private void validateEventIndices(int fromIdx, int toIdx) {
        if (fromIdx == -1) {
            throw new InvalidTaskFormatException("bruh... you didn't specify the start time/date");
        } else if (toIdx == -1) {
            throw new InvalidTaskFormatException("bruh... you didn't specify the end time/date");
        }
    }

    private void validateEventDetails() {
        if (super.taskName.isEmpty() || this.from.isEmpty() || this.to.isEmpty()) {
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
