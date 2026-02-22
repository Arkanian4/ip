package arkanian.userprompts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arkanian.taskmanager.TaskList;
import arkanian.taskmanager.ToDos;

public class UiTest {

    private TaskList taskList;
    private Ui ui;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        ui = new Ui("test_bot", taskList);
    }

    @Test
    void processInput_byeInput_returnsByeMessage() {
        String expected = "________________________________________\n"
                + "Woof woof! Bye bye, hooman! Come back soon with more bones to fetch! ;P\n"
                + "________________________________________\n";
        assertEquals(expected, ui.processInput("bye"));
    }

    @Test
    void processInput_unknownInput_returnsErrorMessage() {
        String expected = "________________________________________\n"
                + "Arf arf! I don't understand that *sniff*... ;w; Can you try another command, hooman? ^^;\n"
                + "________________________________________\n";
        assertEquals(expected, ui.processInput("wat"));
    }

    @Test
    void processInput_todoInput_addsToDoCorrectly() {
        String input = "todo buy milk";
        String response = ui.processInput(input);

        assertEquals(1, taskList.getTaskCount());
        assertTrue(response.contains("Yip yip! Added this bone:"));
        assertTrue(response.contains("buy milk"));
    }

    @Test
    void processInput_deadlineInput_addsDeadlineCorrectly() {
        String input = "deadline submit report /by 2026-02-21 23:59";
        String response = ui.processInput(input);

        assertEquals(1, taskList.getTaskCount());
        assertTrue(response.contains("submit report"));
        assertTrue(response.contains("2026-02-21 23:59"));
    }

    @Test
    void processInput_eventInput_addsEventCorrectly() {
        String input = "event meeting /from 2026-02-21 10:00 /to 2026-02-21 12:00";
        String response = ui.processInput(input);

        assertEquals(1, taskList.getTaskCount());
        assertTrue(response.contains("meeting"));
        assertTrue(response.contains("2026-02-21 10:00"));
        assertTrue(response.contains("2026-02-21 12:00"));
    }

    @Test
    void processInput_findTaskNoMatches_returnsNoMatchMessage() {
        taskList.addTask(new ToDos("todo read book"));
        String response = ui.processInput("find flight");

        assertTrue(response.contains("Sniff sniff... I can't find any bones that match ¯\\_(ツ)_/¯\n"));
    }

    @Test
    void processInput_invalidTaskFormat_returnsErrorMessage() {
        String response = ui.processInput("todo ");

        assertTrue(response.contains("Arf arf! You didn't give me any bones... I mean tasks! ^^; "
                + "Try again, hooman!\n"));
    }
}
