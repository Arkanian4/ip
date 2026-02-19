package arkanian.userprompts;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

//import arkanian.arkanianexceptions.UnknownInputException;
import arkanian.taskmanager.TaskList;

public class UiTest {

    @Test
    public void processInput_byeInput_returnFalse() {

        Ui ui = new Ui("test_bot", new TaskList());

        assertEquals("Bye! Hope to see you again :)\n", ui.processInput("bye"));
    }

    @Test
    public void processInput_nonByeInput_returnTrue() {

        Ui ui = new Ui("test_bot", new TaskList());

        assertEquals(true, ui.processInput("lolololol"));

    }
}
