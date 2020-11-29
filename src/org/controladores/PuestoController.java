/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controladores;

import Main.App;
import MediaPlayer.VideoPlayer;
import System.SistemaEspera;
import org.unique.Medico;
import org.unique.Puesto;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    private Button btnEliminarMedico;
    @FXML
    private Button btnAsignarMedico;
    @FXML
    private TextField textId;
    @FXML
    private ComboBox cmbMedico;

    @FXML
    private Button btnAtras;

    private SistemaEspera sistema;
    
    public static final String FXMLS = "principal";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sistema = SistemaEspera.getInstance();
        List<Medico> listaMedicosDisponibles = sistema.getListaMedico();
        cmbMedico.setItems(FXCollections.observableList(listaMedicosDisponibles));
    }

    @FXML
    private void crearPuesto(MouseEvent event) {
        try {
            Puesto p;
            if (!textId.getText().equals("")) {
                if (cmbMedico.getValue() != null) {
                    Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, "NO VACIO MEDICO");
                    Medico m = (Medico) cmbMedico.getValue();
                    p = new Puesto(textId.getText(), m);
                } else{ 
                    p = new Puesto(textId.getText());
                }
                sistema.addPuesto(p);
            }
            sistema.actualizarTurnos();
            sistema.actualizarDatos();
            VideoPlayer.getInstance().reproducir();
            App.llamarEscena(FXMLS, (Event) event);
        } catch (IOException ex) {
            Logger.getLogger(PuestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void asignarMedico(MouseEvent event) {
        try {
            if (!textId.getText().equals("") && cmbMedico.getValue() != null) {
                Medico m = (Medico) cmbMedico.getValue();
                sistema.asignarMedico(textId.getText(), m);
            }
            VideoPlayer.getInstance().reproducir();
            App.llamarEscena(FXMLS, (Event) event);
        } catch (IOException ex) {
            Logger.getLogger(PuestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eliminarPuesto(MouseEvent event) {
        try {
            if (!textId.getText().equals("")) {
                sistema.eliminarPuesto(textId.getText());
                sistema.actualizarDatos();
            }
            VideoPlayer.getInstance().reproducir();
            App.llamarEscena(FXMLS, (Event) event);
        } catch (IOException ex) {
            Logger.getLogger(PuestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void eliminarMedico(MouseEvent event) {
        try {
            if (!textId.getText().equals("")) {
                sistema.eliminarMedico(textId.getText());
                sistema.actualizarDatos();
            }
            VideoPlayer.getInstance().reproducir();
            App.llamarEscena(FXMLS, (Event) event);
        } catch (IOException ex) {
            Logger.getLogger(PuestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                App.llamarEscena(FXMLS, (Event) event);
            } catch (IOException ex) {
                Logger.getLogger(PuestoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
