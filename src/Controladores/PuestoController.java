/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import FileResources.Serializar;
import Main.App;
import MediaPlayer.VideoPlayer;
import UniqueElement.Medico;
import UniqueElement.Puesto;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author GeovannyRojas
 */
public class PuestoController implements Initializable {

    @FXML
    private Button btnCrear;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField textId;
    @FXML
    private ComboBox cmbMedico;

    @FXML
    private Button btnAtras;

    Serializar<Puesto> puestos = new Serializar();
    Serializar<Medico> medicos = new Serializar();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LinkedList<Medico> listamedicos = medicos.deserializar("Medicos.ser");
        cmbMedico.setItems(FXCollections.observableList(listamedicos));
    }

    @FXML
    private void crearPuesto(MouseEvent event) {
        try {
            Puesto p;
            if (!textId.getText().equals("") && !cmbMedico.getValue().toString().equals("")) {
                p = new Puesto(textId.getText(), (Medico) cmbMedico.getValue());
                LinkedList<Puesto> listap = puestos.deserializar("Puestos.ser");
                listap.add(p);
                puestos.serializar(listap, "Puestos.ser");
            }
            VideoPlayer.getInstance().reproducir();
            App.llamarEscena("principal", (Event) event);
        } catch (IOException ex) {
            Logger.getLogger(PuestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eliminarPuesto(MouseEvent event) {
    }

    @FXML
    private void volver(MouseEvent event) {
        Alert salida = new Alert(Alert.AlertType.CONFIRMATION);
        salida.setTitle("Confirmacion de Salida");
        salida.setContentText("Esta seguro que desea salir?");

        Optional<ButtonType> result = salida.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                VideoPlayer.getInstance().reproducir();
                App.llamarEscena("principal", (Event) event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
