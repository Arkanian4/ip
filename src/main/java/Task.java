public class Task {
    final private String task_name;
    boolean is_done = false;

    public Task(String name) {
        this.task_name = name;
    }

    public boolean isDone() {
        return is_done;
    }

    public void setdone() {
        this.is_done = true;
    }

    public void setNotDone() {
        this.is_done = false;
    }

    @Override
    public String toString() {
        String check_mark = is_done ? "X" : " ";
        return "[" + check_mark + "] " + this.task_name;
    }
}