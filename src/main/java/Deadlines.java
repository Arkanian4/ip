public class Deadlines extends Task {
    private String due_date;


    public Deadlines(String deadline) {
        super(deadline);

        int due_date_idx = super.getIdxOf("/by");
        String task_name = "";
        String due_date = "";

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
    }

    @Override
    public String getTaskName() {
        return this.task_name;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.due_date
                + ")";
    }
}
