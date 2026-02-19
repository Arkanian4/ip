package arkanian.gui;

import java.io.IOException;

import arkanian.userprompts.Arkanian;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Arkanian using FXML.
 */
public class Main extends Application {

    private final Arkanian arkanian = new Arkanian();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setArkanian(arkanian);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
