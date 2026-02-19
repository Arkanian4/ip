package arkanian.taskmanager;

import java.util.ArrayList;

/**
 * Represents a list of tasks with utility methods to manage them.
 * Supports adding, deleting, retrieving, and searching tasks.
 */
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

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to add
     * @throws AssertionError if the task is null
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add null task";
        taskList.add(task);
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param idx the index of the task to delete
     * @throws AssertionError if the index is out of bounds
     */
    public void delete(int idx) {
        assert idx >= 0 && idx < taskList.size()
                : "Invalid index passed to TaskList.delete()";
        taskList.remove(idx);
    }

    /**
     * Finds and returns a new TaskList containing tasks whose names contain
     * the specified keyword.
     *
     * @param keyword the keyword to search for in task names
     * @return a new TaskList with tasks matching the keyword
     */
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
