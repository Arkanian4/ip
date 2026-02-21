package arkanian.taskmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import arkanian.arkanianexceptions.InvalidTaskFormatException;
import arkanian.userprompts.DateTimeParser;

/**
 * Represents an event task.
 * <p>
 * An Event task includes a description, a start date/time,
 * and an end date/time.
 */
public class Events extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event task from user input.
     *
     * Expected format:
     * event description /from startDateTime /to endDateTime
     */
    public Events(String event) {
        super(event);

        int fromIdx = getIdxOf("/from");
        int toIdx = getIdxOf("/to");

        validateEventIndices(fromIdx, toIdx);

        String[] parsedDetails = parseEventDetails(fromIdx, toIdx);

        super.taskName = parsedDetails[0];

        if (super.taskName.isEmpty()) {
            throw new InvalidTaskFormatException(
                    "Hmm... I need more juicy details before I can save this ^_~");
        }

        try {
            this.from = DateTimeParser.convert(parsedDetails[1]);
            this.to = DateTimeParser.convert(parsedDetails[2]);
        } catch (Exception e) {
            throw e; // Let Ui handle the InvalidParameterException
        }
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    public String getFromString() {
        return formatDateTime(this.from);
    }

    public String getToString() {
        return formatDateTime(this.to);
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + formatDateTime(this.from)
                + " to: "
                + formatDateTime(this.to)
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
            throw new InvalidTaskFormatException(
                    "Oi! You forgot the start time/date ^^;");
        }
        if (toIdx == -1) {
            throw new InvalidTaskFormatException(
                    "Whoa! You also need to tell me the end time/date *gulp*");
        }
    }

    private String formatDateTime(LocalDateTime dt) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dt.format(formatter);
    }
}
