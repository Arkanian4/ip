package arkanian.gui;

import java.io.IOException;

import arkanian.userprompts.Arkanian;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Arkanian using FXML.
 */
public class Main extends Application {

    private final Arkanian arkanian = new Arkanian();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));

            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);

            // 🔥 Attach dark theme
            scene.getStylesheets().add(
                    Main.class.getResource("/view/DarkTheme.css").toExternalForm()
            );

            stage.setTitle("Arkanian");
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setArkanian(arkanian);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
