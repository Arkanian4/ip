package arkanian.userprompts;

import arkanian.arkanianexceptions.InvalidParameterException;
import arkanian.arkanianexceptions.InvalidTaskFormatException;
import arkanian.arkanianexceptions.UnknownInputException;
import arkanian.memorystorage.Save;
import arkanian.taskmanager.Deadlines;
import arkanian.taskmanager.Events;
import arkanian.taskmanager.Task;
import arkanian.taskmanager.TaskList;
import arkanian.taskmanager.ToDos;


/**
 * Handles user interaction and input processing for the Arkanian task manager bot.
 * Prints welcome messages and interprets commands to manipulate tasks in a TaskList.
 */
public class Ui {

    private final String horizontalLine = "________________________________________\n";

    private final TaskList taskList;

    /**
     * Constructs a Ui instance with a specified bot name and task list.
     * Prints a welcome message to the user.
     * <p>
     * @param botName  the name of the bot to display in messages
     * @param taskList the task list the UI will manage
     */
    public Ui(String botName, TaskList taskList) {
        this.taskList = taskList;

        System.out.println(horizontalLine
                + "Hello! I'm "
                + botName
                + ". \nWhat can I do for you?\n"
                + horizontalLine);
    }

    /**
     * Processes raw user input and executes the corresponding task management command.
     * Supports commands such as "bye", "list", "mark", "unmark", "delete", "todo",
     * "event", "deadline", and "find".
     * <p>
     * @param rawInput the raw input string entered by the user
     * @return a formatted message indicating the result of the command
     */
    public String processInput(String rawInput) {
        try {
            return wrapMessage(executeCommand(rawInput));
        } catch (InvalidTaskFormatException | UnknownInputException | InvalidParameterException e) {
            return wrapMessage(e.getMessage());
        }
    }

    private String executeCommand(String rawInput)
            throws InvalidTaskFormatException, UnknownInputException, InvalidParameterException {

        Input parsedInput = new Input(rawInput);
        String instr = parsedInput.getInstr();

        return switch (instr) {
        case "bye" -> handleBye();
        case "list" -> handleList();
        case "mark" -> handleMark(parsedInput, true);
        case "unmark" -> handleMark(parsedInput, false);
        case "delete" -> handleDelete(parsedInput);
        case "todo" -> handleAddTask(new ToDos(rawInput));
        case "event" -> handleAddTask(new Events(rawInput));
        case "deadline" -> handleAddTask(new Deadlines(rawInput));
        case "find" -> handleFind(parsedInput);
        default -> throw new UnknownInputException("Huh? That command sounds funky ^^;");
        };
    }

    private String handleBye() {
        return "Aight, see ya! Don't forget to come back with more tasks B-)\n";
    }

    private String handleList() {
        return taskList.getTaskCount() == 0
                ? "Empty, bruv. Nothing to do yet! zzz\n"
                : "Here's the lineup of your awesome tasks:\n" + taskList.toString();
    }

    private String handleFind(Input parsedInput) {
        String str = taskList.find(parsedInput.getTaskName()).toString();
        return str.isEmpty()
                ? "Hmm... couldn't find anything matching that ¯\\_(ツ)_/¯\n"
                : "Check these out, boss! Tasks that match your search:\n" + str;
    }

    private String handleMark(Input parsedInput, boolean markAsDone)
            throws InvalidTaskFormatException {

        int idx = parsedInput.getIdx() - 1;
        Task task = taskList.getTask(idx);

        if (markAsDone) {
            task.setDone();
            Save.saveData(taskList);
            return "Boom! Task completed:\n" + task + "\nYou're crushing it ^_^\n";
        } else {
            task.setNotDone();
            Save.saveData(taskList);
            return "No worries, task set back to pending:\n" + task + "\nTake your time -_-\n";
        }
    }

    private String handleDelete(Input parsedInput)
            throws InvalidTaskFormatException {

        int idx = parsedInput.getIdx() - 1;
        Task task = taskList.getTask(idx);
        taskList.delete(idx);
        Save.saveData(taskList);

        return "Gotcha! Removed this task:\n"
                + task
                + "\nNow you have "
                + taskList.getTaskCount()
                + " tasks left. Keep it up! >>>\n";
    }

    private String handleAddTask(Task task) {
        taskList.addTask(task);
        Save.saveData(taskList);

        return "Sweet! Added this gem:\n"
                + task
                + "\nYou're juggling "
                + taskList.getTaskCount()
                + " tasks now. Legendary! *_*\n";
    }

    private String wrapMessage(String message) {
        return horizontalLine + message + horizontalLine;
    }
}
