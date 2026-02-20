package arkanian.gui;

import arkanian.userprompts.Arkanian;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Arkanian arkanian;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/dog1.jpg"));
    private final Image arkanianImage = new Image(this.getClass().getResourceAsStream("/images/dog2.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Arkanian instance */
    public void setArkanian(Arkanian a) {
        arkanian = a;
        printWelcomeMessage();
    }

    private void printWelcomeMessage() {
        String asciiArt = """
                    ,──.  ,──.,──.    ,──. \s
                    │  '──'  ││  │    │  | \s
                    │  .──.  ││  │    `──' \s
                    │  │  │  ││  │    .──. \s
                    `──'  `──'`──'    '──' \s
            """;

        String message = "I'm ARKANIAN :)\n"
                + "What mischief or tasks are we tackling today?\n";

        TextFlow flow = new TextFlow(new Text(asciiArt + "\n" + message));
        dialogContainer.getChildren().add(flow);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Arkanian's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = arkanian.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getArkanianDialog(response, arkanianImage)
        );
        userInput.clear();
    }
}
