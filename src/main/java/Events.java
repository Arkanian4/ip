import java.time.LocalDateTime;

public class Events extends Task {
    private String from;
    private String to;

    public Events(String event) {
        super(event);

        int from_idx = getIdxOf("/from");
        int to_idx = getIdxOf("/to");

        if (from_idx == -1) {
            throw new InvalidTaskFormatException("bruh... you didn't specify the start time/date");
        } else if (to_idx == -1) {
            throw new InvalidTaskFormatException("bruh... you didn't specify the end time/date");
        }

        String task_name = "";
        String from = "";
        String to = "";

        for (int i = 1; i < super.parsed_task.length; i++) {
            String word = super.parsed_task[i];

            if (i < from_idx) {
                task_name = task_name + word + " ";
            } else if (from_idx < i && i < to_idx) {
                from = from + word + " ";
            } else if (to_idx < i) {
                to = to + word + " ";
            }
        }

        super.task_name = task_name.trim();
        this.from = from.trim();
        this.to = to.trim();

        if (super.task_name == "" || this.from == "" || this.to == "") {
            throw new InvalidTaskFormatException("bruh... I need more deets");
        }
    }

    @Override
    public String getTaskName() {
        return super.task_name;
    }

    public LocalDateTime getFrom() {
	    return DateTimeParser.convert(this.from);
    }

    public String getFromString() {
	    return this.getFrom().toString();
    }

    public LocalDateTime getTo() {
	    return DateTimeParser.convert(this.to);
    }

    public String getToString() {
	    return this.getTo().toString();
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + this.getFromString()
                + "; to: "
                + this.getToString()
                + ")";
    }

}
