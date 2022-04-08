package demogroup.myfirstgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/* A typical driver class you would see in any JavaFX application. */
/* It loads up the 'EnigmaDesign.fxml', sets a Scene object with it, sets a Stage object with the scene, and shows the Stage. */
/* Nothing too special happening here. */

public class EnigmaApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EnigmaApp.class.getResource("EnigmaDesign.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("EnigmaMachine Machine Emulator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}