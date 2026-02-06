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
        TaskList task_list = Save.initializeData();
        // TaskList task_list = new TaskList();

        System.out.println(message);

        for (; cont_convo; System.out.println(horizontal_line + message + horizontal_line)) {
            try {
                String raw_input = scanner.nextLine();
                Input parsed_input = new Input(raw_input);

                String instr = parsed_input.getInstr();

                int idx;
                Task task;

                switch (instr) {
                    case "bye":
                        message = "Bye! Hope to see you again :)\n";
                        cont_convo = false;
                        break;

                    case "list":
                        message = task_list.toString();
                        break;

                    case "mark":
                        idx = parsed_input.getIdx();
                        task = task_list.getTask(idx);
                        task.setDone();
                        message = "Nice! I've marked this task as done:\n"
                                + task
                                + "\n";
                        Save.saveData(task_list);
                        break;

                    case "unmark":
                        idx = parsed_input.getIdx();
                        task = task_list.getTask(idx);
                        task.setNotDone();
                        message = "OK, I've marked this task as not done yet:\n"
                                + task
                                + "\n";
                        Save.saveData(task_list);
                        break;

                    case "delete":
                        idx = parsed_input.getIdx();
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

                    default:
                        throw new UnknownInputException("what nonsense r u saying bruh");
                }

                //saved_data.saveData(task_list);

            } catch (InvalidTaskFormatException | UnknownInputException e) {
                message = e.getMessage();
            }
        }

    }
}

