package arkanian.userprompts;

import arkanian.taskmanager.Deadlines;
import arkanian.taskmanager.Events;
import arkanian.taskmanager.Task;
import arkanian.taskmanager.TaskList;
import arkanian.taskmanager.ToDos;
import arkanian.memorystorage.Save;
import arkanian.arkanianexceptions.InvalidTaskFormatException;
import arkanian.arkanianexceptions.UnknownInputException;

public class Ui {

    private String horizontal_line = "________________________________________\n";

    private String message;

    private TaskList task_list;

    public Ui(String bot_name, TaskList task_list) {
        this.task_list = task_list;

        System.out.println(horizontal_line
                + "Hello! I'm "
                + bot_name
                + ". \nWhat can I do for you?\n"
                + horizontal_line);
    }

    public String processInput(String raw_input) {

        Input parsed_input = new Input(raw_input);
        String instr = parsed_input.getInstr();

        int idx;
        Task task;
        boolean cont_convo = true;

        try {
            switch (instr) {
            case "bye":
                message = "Bye! Hope to see you again :)\n";
                cont_convo = false;
                break;

            case "list":
                message = task_list.toString();
                break;

            case "mark":
                idx = parsed_input.getIdx() - 1;
                task = task_list.getTask(idx);
                task.setDone();
                message = "Nice! I've marked this task as done:\n"
                        + task
                        + "\n";
                Save.saveData(task_list);
                break;

            case "unmark":
                idx = parsed_input.getIdx() - 1;
                task = task_list.getTask(idx);
                task.setNotDone();
                message = "OK, I've marked this task as not done yet:\n"
                        + task
                        + "\n";
                Save.saveData(task_list);
                break;

            case "delete":
                idx = parsed_input.getIdx() - 1;
                task = task_list.getTask(idx);
                task_list.delete(idx);
                message = "Noted. I've removed this task:\n"
                        + task
                        + "\nNow you have "
                        + task_list.getTaskCount()
                        + " tasks in the list."
                        + "\n";
                Save.saveData(task_list);
                break;

            case "todo":
                task = new ToDos(raw_input);
                task_list.addTask(task);
                message = "Got it. I've added this task:\n"
                        + task
                        + "\nNow you have "
                        + task_list.getTaskCount()
                        + " tasks in the list."
                        + "\n";
                Save.saveData(task_list);
                break;

            case "event":
                task = new Events(raw_input);
                task_list.addTask(task);
                message = "Got it. I've added this task:\n"
                        + task
                        + "\nNow you have "
                        + task_list.getTaskCount()
                        + " tasks in the list."
                        + "\n";
                Save.saveData(task_list);
                break;

            case "deadline":
                task = new Deadlines(raw_input);
                task_list.addTask(task);
                message = "Got it. I've added this task:\n"
                        + task
                        + "\nNow you have "
                        + task_list.getTaskCount()
                        + " tasks in the list."
                        + "\n";
                Save.saveData(task_list);
                break;

            case "find":
                message = "Here are the tasks matching your search:\n"
                        + this.task_list.find(parsed_input.getTaskName()).toString();
                break;


            default:
                throw new UnknownInputException("what nonsense r u saying bruh");
            }
        } catch (InvalidTaskFormatException | UnknownInputException e) {
            message = e.getMessage();
        }

        message = horizontal_line
                + message
                + horizontal_line;

        return message;

    }
}