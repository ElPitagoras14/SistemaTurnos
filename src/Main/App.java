/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import MediaPlayer.VideoPlayer;
import System.SistemaEspera;
import org.unique.Medico;
import org.unique.Paciente;
import org.unique.Puesto;
import org.unique.Sintoma;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.Event;
import javafx.scene.Node;

/**
 *
 * @author GeovannyRojas
 */
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("principal"));
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop() {
        VideoPlayer.getInstance().cambiarVentana();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        File f = new File("src/fxml/" + fxml + ".fxml");
        return FXMLLoader.load(f.toURI().toURL());
    }

    public static void main(String[] args) {
        launch();
    }

    public static void llamarEscena(String fxml, Event event) throws IOException {
        Parent root = loadFXML(fxml);
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }
}
