package arkanian.taskmanager;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public int getTaskCount() {
        return taskList.size();
    }

    public Task getTask(int idx) {
        assert idx >= 0 && idx < taskList.size()
                : "Invalid index passed to TaskList.getTask()";
        return taskList.get(idx);
    }

    public void addTask(Task task) {
        assert task != null : "Cannot add null task";
        taskList.add(task);
    }

    public void delete(int idx) {
        assert idx >= 0 && idx < taskList.size()
                : "Invalid index passed to TaskList.delete()";
        taskList.remove(idx);
    }

    public TaskList find(String keyword) {
        TaskList filteredList = new TaskList();

        for (Task task : this.taskList) {
            if (task.getTaskName().contains(keyword)) {
                filteredList.addTask(task);
            }
        }
        return filteredList;
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
                    + "\n\n";
        }

        return str;
    }

}
