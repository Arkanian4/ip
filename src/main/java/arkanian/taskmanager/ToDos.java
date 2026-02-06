package arkanian.taskmanager;

import arkanian.arkanianexceptions.InvalidTaskFormatException;

public class ToDos extends Task {

    public ToDos(String todo) {
        super(todo);

        String task_name = "";

        for (int i = 1; i < super.parsed_task.length; i++) {
            String word = super.parsed_task[i];

            task_name = task_name + word + " ";
        }

        super.task_name = task_name.trim();

        if (super.task_name == "") {
            throw new InvalidTaskFormatException("bruh... I need more deets");
        }
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
