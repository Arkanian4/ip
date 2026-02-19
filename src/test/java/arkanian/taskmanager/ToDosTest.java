package arkanian.taskmanager;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import arkanian.arkanianexceptions.InvalidTaskFormatException;

public class ToDosTest {

    @Test
    public void constructor_emptyInput_throwsException() {
        InvalidTaskFormatException exception = assertThrows(
                InvalidTaskFormatException.class, () -> new ToDos("todo")
        );
    }

    @Test
    public void toString_validInput_returnsCorrectFormat() {
        ToDos todo = new ToDos("todo finish homework");

        assertEquals("[T][ ] finish homework", todo.toString());
    }

}
