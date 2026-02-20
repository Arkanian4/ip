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

    private static final String FILE_PATH = "memory/saved.txt";
    private static final String DELIMITER = " \\| ";

    private static void writeMemory(TaskList data, FileWriter fwrite) throws IOException {
        for (int i = 0; i < data.getTaskCount(); i++) {
            fwrite.write(formatTask(data.getTask(i)));
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
            FileWriter fwrite = new FileWriter(FILE_PATH);
            writeMemory(data, fwrite);
        } catch (IOException e) {
            System.out.println("Unable to save data: " + e.getMessage());
        }
    }


    private static TaskList readMemory() throws FileNotFoundException {

        TaskList taskList = new TaskList();

        Scanner s = new Scanner(new File(FILE_PATH));
        while (s.hasNext()) {
            taskList.addTask(parseLine(s.nextLine()));
        }
        s.close();

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

        ensureFolderExists();
        ensureFileExists();

        try {
            return readMemory();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to locate memory: " + e.getMessage());
            return new TaskList();
        }
    }

    private static void ensureFolderExists() {
        File folder = new File("memory");
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    private static void ensureFileExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create memory file");
            }
        }
    }

    private static String formatTask(Task task) {

        String isDone = task.getIsDone() ? "1" : "0";
        String descriptionWithTags = task.getInputString();

        if (task instanceof ToDos) {
            return "T | " + isDone + " | " + descriptionWithTags + "\n";
        }

        if (task instanceof Deadlines) {
            return "D | " + isDone + " | "
                    + descriptionWithTags
                    + " | " + ((Deadlines) task).getDeadlineString()
                    + "\n";
        }

        if (task instanceof Events) {
            return "E | " + isDone + " | "
                    + descriptionWithTags
                    + " | " + ((Events) task).getFromString()
                    + " | " + ((Events) task).getToString()
                    + "\n";
        }

        return "";
    }

    private static Task parseLine(String line) {

        String[] tokens = line.split(DELIMITER);

        Task task = createTask(tokens);
        restoreStatus(task, tokens[1]);

        return task;
    }

    private static Task createTask(String[] tokens) {

        String type = tokens[0];
        String savedInput = tokens[2];

        switch (type) {
        case "T":
            return new ToDos(savedInput);

        case "D":
            return new Deadlines(savedInput);

        case "E":
            return new Events(savedInput);

        default:
            return null;
        }
    }

    private static void restoreStatus(Task task, String isDone) {
        if ("1".equals(isDone)) {
            assert task != null : "Attempted to call setDone() on a null Task";
            task.setDone();
        }
    }
}
