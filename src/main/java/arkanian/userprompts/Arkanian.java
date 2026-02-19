package arkanian.userprompts;
import java.util.Scanner;

import arkanian.memorystorage.Save;
import arkanian.taskmanager.TaskList;

/**
 * Entry point of the Arkanian chatbot application.
 * <p>
 * This class initializes the bot, loads persisted task data,
 * and delegates user input processing to the Ui class.
 */
public class Arkanian {
    private static final String BOT_NAME = "Arkanian";
    private final Scanner scanner = new Scanner(System.in);
    private final TaskList taskList = Save.initializeData();
    private final Ui ui = new Ui(BOT_NAME, taskList);

    /*
    public void Run() {

        while (cont_convo) {
            cont_convo = ui.processInput(scanner.nextLine());
        }

    }
    */

    public static void main(String[] args) {
        // new Arkanian().Run();
        System.out.println("Hi, I'm Arkanian! How can i help you");
    }

    public String getResponse(String s) {
        return ui.processInput(s);
    }
}
