/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import System.SistemaEspera;
import UniqueElement.Medico;
import UniqueElement.Paciente;
import UniqueElement.Puesto;
import UniqueElement.Sintoma;
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
    public void init(){
        //inicializarDatos();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("principal"));
        stage.setScene(scene);
        stage.show();
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
    
    private void inicializarDatos() {
        SistemaEspera instancia = SistemaEspera.getInstance();
        instancia.añadirPaciente(new Paciente("Jose", "Alvarez", "25", "Masculino", new Sintoma("Fiebre", 3)));
        instancia.añadirPaciente(new Paciente("Juan", "Garcia", "30", "Masculino", new Sintoma("Sangrado Nasal", 1)));
        instancia.añadirPaciente(new Paciente("Maria", "Flores", "18", "Femenino", new Sintoma("Erupciones", 3)));
        instancia.añadirPaciente(new Paciente("Carla", "Alvarez", "25", "Femenino", new Sintoma("Calambre", 5)));
        
        instancia.añadirPuesto(new Puesto("001", new Medico("Carlos", "Arellano", "Medicina General", "001")));
        instancia.añadirPuesto(new Puesto("002", new Medico("Armando", "Paredes", "Traumatologo", "002")));
        instancia.actualizarTurnos();
        instancia.actualizarDatos();
    }
}
