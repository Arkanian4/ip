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
                + "Woof woof! I'm "
                + botName
                + "! \nWanna play... I mean, manage some tasks with me? ;w;\n"
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
        default -> throw new UnknownInputException("Arf arf! I don't understand that *sniff*... "
                + ";w; Can you try another command, hooman? ^^;");
        };
    }

    private String handleBye() {
        return "Woof woof! Bye bye, hooman! Come back soon with more bones to fetch! ;P\n";
    }

    private String handleList() {
        return taskList.getTaskCount() == 0
                ? "Sniff sniff... nothing to chase yet! Your task yard is empty! zzz\n"
                : "Woof! Here's the lineup of your pawsome tasks:\n" + taskList.toString();
    }

    private String handleFind(Input parsedInput) {
        String str;

        try {
            str = taskList.find(parsedInput.getTaskName()).toString();
        } catch (Exception e) {
            return "grrrr... you need to give me something to sniff out\n";
        }

        return str.isEmpty()
                ? "Sniff sniff... I can't find any bones that match ¯\\_(ツ)_/¯\n"
                : "Woof! ^_^ Lookie here, hooman! These bones match your sniff:\n" + str;
    }

    private String handleMark(Input parsedInput, boolean markAsDone)
            throws InvalidTaskFormatException {

        int idx = parsedInput.getIdx() - 1;
        Task task = taskList.getTask(idx);

        if (markAsDone) {
            task.setDone();
            Save.saveData(taskList);
            return "Woof woof! You fetched this bone:\n"
                    + task
                    + "\nTail wag! You did pawsome, hooman! ^_^\n";
        } else {
            task.setNotDone();
            Save.saveData(taskList);
            return "Arf! I've put this bone back in the yard:\n"
                    + task
                    + "\nTake your time, hooman :)\n";
        }
    }

    private String handleDelete(Input parsedInput)
            throws InvalidTaskFormatException {

        int idx = parsedInput.getIdx() - 1;
        Task task = taskList.getTask(idx);
        taskList.delete(idx);
        Save.saveData(taskList);

        return "Woof! Snatched this bone away:\n"
                + task
                + "\nNow you have "
                + taskList.getTaskCount()
                + " bones left to find! Keep wagging! ;w;\n";
    }

    private String handleAddTask(Task task) {
        taskList.addTask(task);
        Save.saveData(taskList);

        return "Yip yip! Added this bone:\n"
                + task
                + "\nYou've got "
                + taskList.getTaskCount()
                + " bones to play with now! Tail wags! ;w;\n";
    }

    private String wrapMessage(String message) {
        return horizontalLine + message + horizontalLine;
    }
}
