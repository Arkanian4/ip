package arkanian.taskmanager;

import java.time.LocalDateTime;

import arkanian.arkanianexceptions.InvalidTaskFormatException;
import arkanian.userprompts.DateTimeParser;

public class Events extends Task {
    private final String from;
    private final String to;

    public Events(String event) {
        super(event);

        int fromIdx = getIdxOf("/from");
        int toIdx = getIdxOf("/to");

        if (fromIdx == -1) {
            throw new InvalidTaskFormatException("bruh... you didn't specify the start time/date");
        } else if (toIdx == -1) {
            throw new InvalidTaskFormatException("bruh... you didn't specify the end time/date");
        }

        String taskName = "";
        String from = "";
        String to = "";

        for (int i = 1; i < super.parsedTask.length; i++) {
            String word = super.parsedTask[i];

            if (i < fromIdx) {
                taskName = taskName + word + " ";
            } else if (fromIdx < i && i < toIdx) {
                from = from + word + " ";
            } else if (toIdx < i) {
                to = to + word + " ";
            }
        }

        super.taskName = taskName.trim();
        this.from = from.trim();
        this.to = to.trim();

        if (super.taskName.isEmpty() || this.from.isEmpty() || this.to.isEmpty()) {
            throw new InvalidTaskFormatException("bruh... I need more deets");
        }
    }

    public LocalDateTime getFrom() {
        return DateTimeParser.convert(this.from);
    }

    public String getFromString() {
        String dateTime = this.getFrom().toString();

        return DateTimeParser.getDateString(dateTime)
                + " "
                + DateTimeParser.getTimeString(dateTime);
    }

    public LocalDateTime getTo() {
        return DateTimeParser.convert(this.to);
    }

    public String getToString() {
        String dateTime = this.getTo().toString();

        return DateTimeParser.getDateString(dateTime)
                + " "
                + DateTimeParser.getTimeString(dateTime);
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + this.getFromString()
                + " to: "
                + this.getToString()
                + ")";
    }

}
