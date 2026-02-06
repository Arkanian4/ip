import java.util.Scanner;

public class Arkanian {
    final String BOT_NAME = "Arkanian";

    boolean cont_convo = true;

    private Scanner scanner = new Scanner(System.in);
    private TaskList task_list = Save.initializeData();
    private Ui ui = new Ui(BOT_NAME, task_list);

    public void Run() {
        
        while (cont_convo) {
            cont_convo = ui.processInput(scanner.nextLine());
        }

    }

    public static void main(String[] args) {
        new Arkanian().Run();
    }
}

