package arkanian.userprompts;


import arkanian.arkanianexceptions.UnknownInputException;
import arkanian.taskmanager.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UiTest {

    @Test
    public void processInput_byeInput_returnFalse() {

        Ui ui = new Ui("test_bot", new TaskList());

        assertEquals(false, ui.processInput("bye"));
    }

    @Test
    public void processInput_nonByeInput_returnTrue() {

        Ui ui = new Ui("test_bot", new TaskList());

        assertEquals(true, ui.processInput("lolololol"));

    }
}
