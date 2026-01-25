public class Task {
    final private String task_name;

    public Task(String name) {
        this.task_name = name;
    }

    @Override
    public String toString() {
        return this.task_name;
    }
}