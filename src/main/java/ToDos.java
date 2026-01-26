public class ToDos extends Task {

    public ToDos(String todo) {
        super(todo);

        String task_name = "";

        for (int i = 1; i < super.parsed_task.length; i++) {
            String word = super.parsed_task[i];

            task_name = task_name + word + " ";
        }

        super.task_name = task_name.trim();
    }

    @Override
    public String getTaskName() {
        return this.task_name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
