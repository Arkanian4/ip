import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> task_list = new ArrayList<>();

    public int getTaskCount() {
        return task_list.size();
    }

    public Task getTask(int idx) {
        return task_list.get(idx);
    }

    public void addTask(Task task) {
        task_list.add(task);
    }

    public void delete(int idx) {
        task_list.remove(idx);
    }

    @Override
    public String toString() {

        String str = "";

        for (int i = 0; i < this.getTaskCount(); i++) {
            Task task = this.getTask(i);

            str = str
                    + (i + 1)
                    + ". "
                    + task
                    + "\n";
        }

        return str;
    }

}
