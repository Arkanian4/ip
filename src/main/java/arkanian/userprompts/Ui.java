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

    private final String horizontalLine = "________________________________________\n";

    private TaskList taskList;

    public Ui(String botName, TaskList taskList) {
        this.taskList = taskList;

        System.out.println(horizontalLine
                + "Hello! I'm "
                + botName
                + ". \nWhat can I do for you?\n"
                + horizontalLine);
    }

    public String processInput(String rawInput) {

        Input parsedInput = new Input(rawInput);
        String instr = parsedInput.getInstr();

        int idx;
        Task task;
        boolean isBye = false;

        String message;
        try {
            switch (instr) {
            case "bye":
                message = "Bye! Hope to see you again :)\n";
                isBye = true;
                break;

            case "list":
                message = taskList.toString();
                break;

            case "mark":
                idx = parsedInput.getIdx() - 1;
                task = taskList.getTask(idx);
                task.setDone();
                message = "Nice! I've marked this task as done:\n"
                        + task
                        + "\n";
                Save.saveData(taskList);
                break;

            case "unmark":
                idx = parsedInput.getIdx() - 1;
                task = taskList.getTask(idx);
                task.setNotDone();
                message = "OK, I've marked this task as not done yet:\n"
                        + task
                        + "\n";
                Save.saveData(taskList);
                break;

            case "delete":
                idx = parsedInput.getIdx() - 1;
                task = taskList.getTask(idx);
                taskList.delete(idx);
                message = "Noted. I've removed this task:\n"
                        + task
                        + "\nNow you have "
                        + taskList.getTaskCount()
                        + " tasks in the list."
                        + "\n";
                Save.saveData(taskList);
                break;

            case "todo":
                task = new ToDos(rawInput);
                taskList.addTask(task);
                message = "Got it. I've added this task:\n"
                        + task
                        + "\nNow you have "
                        + taskList.getTaskCount()
                        + " tasks in the list."
                        + "\n";
                Save.saveData(taskList);
                break;

            case "event":
                task = new Events(rawInput);
                taskList.addTask(task);
                message = "Got it. I've added this task:\n"
                        + task
                        + "\nNow you have "
                        + taskList.getTaskCount()
                        + " tasks in the list."
                        + "\n";
                Save.saveData(taskList);
                break;

            case "deadline":
                task = new Deadlines(rawInput);
                taskList.addTask(task);
                message = "Got it. I've added this task:\n"
                        + task
                        + "\nNow you have "
                        + taskList.getTaskCount()
                        + " tasks in the list."
                        + "\n";
                Save.saveData(taskList);
                break;

            case "find":
                message = "Here are the tasks matching your search:\n"
                        + this.taskList.find(parsedInput.getTaskName()).toString();
                break;


            default:
                throw new UnknownInputException("what nonsense r u saying bruh");
            }
        } catch (InvalidTaskFormatException | UnknownInputException e) {
            message = e.getMessage();
        }

        message = horizontalLine
                + message
                + horizontalLine;

        return message;

    }
}