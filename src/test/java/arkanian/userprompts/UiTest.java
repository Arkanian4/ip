package arkanian.userprompts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arkanian.taskmanager.Deadlines;
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
                + "Bye! Hope to see you again :)\n"
                + "________________________________________\n";
        assertEquals(expected, ui.processInput("bye"));
    }

    @Test
    void processInput_unknownInput_returnsErrorMessage() {
        String expected = "________________________________________\n"
                + "what nonsense r u saying bruh\n"
                + "________________________________________\n";
        assertEquals(expected, ui.processInput("wat"));
    }

    @Test
    void processInput_todoInput_addsToDoCorrectly() {
        String input = "todo buy milk";
        String response = ui.processInput(input);

        assertEquals(1, taskList.getTaskCount());
        assertTrue(response.contains("Got it. I've added this task:"));
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
    void processInput_findTask_noMatches_returnsNoMatchMessage() {
        taskList.addTask(new ToDos("read book"));
        String response = ui.processInput("find flight");
        assertTrue(response.contains("No tasks matched your search"));
    }

    @Test
    void processInput_invalidTaskFormat_returnsErrorMessage() {
        String response = ui.processInput("todo "); // empty description
        assertTrue(response.contains("bruh... I need more deets"));
    }
}