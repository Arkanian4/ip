package arkanian.memorystorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import arkanian.taskmanager.Deadlines;
import arkanian.taskmanager.Events;
import arkanian.taskmanager.Task;
import arkanian.taskmanager.TaskList;
import arkanian.taskmanager.ToDos;

/**
 * Handles persistent storage of tasks.
 * <p>
 * This class is responsible for saving task data to disk
 * and loading previously saved tasks into memory when
 * the application starts.
 */
public class Save {

    private static void writeMemory(TaskList data, FileWriter fwrite) throws IOException {
        String taskString = "";

        for (int i = 0; i < data.getTaskCount(); i++) {
            Task task = data.getTask(i);
            String taskName = task.getTaskString();
            String isDone = task.getIsDone() ? "1" : "0";

            if (task instanceof ToDos) {
                taskString = "T | "
                        + isDone
                        + " | "
                        + taskName
                        + "\n";
            } else if (task instanceof Deadlines) {
                String deadline = ((Deadlines) task).getDeadlineString();

                taskString = "D | "
                        + isDone
                        + " | "
                        + taskName
                        + " | "
                        + deadline
                        + "\n";
            } else if (task instanceof Events) {
                String from = ((Events) task).getFromString();
                String to = ((Events) task).getToString();

                taskString = "E | "
                        + isDone
                        + " | "
                        + taskName
                        + " | "
                        + from
                        + " | "
                        + to
                        + "\n";
            }

            fwrite.write(taskString);
        }
        fwrite.close();
    }

    /**
     * Saves the provided TaskList to persistent storage.
     * <p>
     * @param data The TaskList to be saved.
     */
    public static void saveData(TaskList data) {
        try {
            FileWriter fwrite = new FileWriter("memory/saved.txt");
            writeMemory(data, fwrite);
        } catch (IOException e) {
            System.out.println("Unable to save data: " + e.getMessage());
        }
    }

    private static TaskList readMemory() throws FileNotFoundException {

        File fread = new File("memory/saved.txt");
        java.util.Scanner s = new Scanner(fread);
        TaskList taskList = new TaskList();

        while (s.hasNext()) {
            String[] taskStringArray = s.nextLine().split(" \\| ");

            String taskType = taskStringArray[0];
            String isDone = taskStringArray[1];
            String taskName = taskStringArray[2];
            String taskString;
            Task task = null;

            switch (taskType) {
            case "T":
                taskString = "todo "
                        + taskName;
                task = new ToDos(taskString);
                break;

            case "D":
                String deadline = taskStringArray[3];

                taskString = "deadline "
                        + taskName
                        + " /by "
                        + deadline;

                task = new Deadlines(taskString);
                break;

            case "E":
                String from = taskStringArray[3];
                String to = taskStringArray[4];

                taskString = "event "
                        + taskName
                        + " /from "
                        + from
                        + " /to "
                        + to;
                task = new Events(taskString);
                break;

            default:
                // throw new IllegalArgumentException("Unknown task type: " + taskType);
            }

            if (isDone.equals("1")) {
                assert task != null : "Attempted to call setDone() on a null Task";
                task.setDone();
            }

            taskList.addTask(task);

        }

        return taskList;
    }

    /**
     * Initializes the application's persistent storage.
     * <p>
     * Ensures that the required folder and file exist.
     * If they do not exist, they are created. Then previously
     * saved tasks are loaded into a TaskList.
     * <p>
     * @return A TaskList initialized from persistent storage.
     */
    public static TaskList initializeData() {

        File folder = new File("memory");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(folder, "saved.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create memory file");
            }
        }

        TaskList taskList = new TaskList();

        try {
            taskList = readMemory();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to locate memory: " + e.getMessage());
        }

        return taskList;
    }

}
