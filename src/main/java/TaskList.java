public class TaskList {
    private Task[] task_list = new Task[100];
    private static int task_count = 0;

    public int getTaskCount() {
        return this.task_count;
    }

    public Task getTask(int idx) {
        return task_list[idx];
    }

    public void addTask(Task task) {
        task_list[task_count++] = task;
    }

}
