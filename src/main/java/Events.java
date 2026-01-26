import java.util.Arrays;

public class Events extends Task {
    private String from;
    private String to;

    public Events(String event) {
        super(event);

        int from_idx = getIdxOf("/from");
        int to_idx = getIdxOf("/to");

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
    }

    @Override
    public String getTaskName() {
        return super.task_name;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + this.from
                + "; to: "
                + this.to
                + ")";
    }

}
