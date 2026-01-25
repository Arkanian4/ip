import java.util.Scanner;

public class Arkanian {
    public Arkanian() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String horizontal_line = "________________________________________\n";
        String bot_name = "Arkanian";
        String message = horizontal_line + "Hello! I'm " + bot_name + ". \nWhat can I do for you?\n" + horizontal_line;
        boolean cont_convo = true;
        TaskList task_list = new TaskList();

        System.out.println(message);

        for (; cont_convo; System.out.println(horizontal_line + message + horizontal_line)) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                message = "Bye! Hope to see you again :)\n";
                cont_convo = false;
            } else if (input.equals("list")) {
                message = "";
                for (int i = 0; i < task_list.getTaskCount(); i++) {
                    message = message
                            + (i + 1)
                            + ". "
                            + task_list.getTask(i)
                            + "\n";
                }
            } else {
                message = "added: " + input + "\n";
                task_list.addTask(new Task(input));
            }
        }

    }
}

