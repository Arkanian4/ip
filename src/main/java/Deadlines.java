import java.time.LocalDateTime;

public class Deadlines extends Task {
    private String due_date;


    public Deadlines(String deadline) {
        super(deadline);

        int due_date_idx = super.getIdxOf("/by");
        String task_name = "";
        String due_date = "";

        if (due_date_idx == -1) {
            throw new InvalidTaskFormatException("bruh... you didn't give me a deadline");
        }

        for (int i = 1; i < super.parsed_task.length; i++) {
            String word = super.parsed_task[i];

            if (i < due_date_idx) {
                task_name = task_name + word + " ";
            } else if (due_date_idx < i) {
                due_date = due_date + word + " ";
            }
        }

        super.task_name = task_name.trim();
        this.due_date = due_date.trim();

        if (super.task_name == "" || this.due_date == "") {
            throw new InvalidTaskFormatException("bruh... I need more deets");
        }
    }

    @Override
    public String getTaskName() {
        return this.task_name;
    }

    public LocalDateTime getDeadline() {
	    return DateTimeParser.convert(this.due_date);
    }

    public String getDeadlineString() {
	    return this.getDeadline().toString();
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
