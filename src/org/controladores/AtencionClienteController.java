/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controladores;

import Main.App;
import MediaPlayer.VideoPlayer;
import System.SistemaEspera;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.unique.Paciente;
import org.unique.Turno;

/**
 * FXML Controller class
 *
 * @author GeovannyRojas
 */
public class AtencionClienteController implements Initializable {

    @FXML
    private TextArea textDiagnostico;
    @FXML
    private TextArea textReceta;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAtencion;

    @FXML
    private Text infoPaciente;

    private SistemaEspera sistema;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sistema = SistemaEspera.getInstance();
        LinkedList<Turno> pacientes = (LinkedList<Turno>) sistema.getListaTurno();
        Paciente p = pacientes.getFirst().getPaciente();
        infoPaciente.setText("Paciente:" + p.getNombre() + " " + p.getApellido() + " de " + p.getEdad() + " años.\nSintoma:" + p.getSintoma().toString());
    }

    @FXML
    public void confirmarAtencion(MouseEvent event) {
        try {
            if (!textDiagnostico.getText().equals("") && !textReceta.getText().equals("")) {
                sistema.atenderPaciente(textDiagnostico.getText(), textReceta.getText());
                sistema.actualizarDatos();
                VideoPlayer.getInstance().reproducir();
                App.llamarEscena("principal", (Event) event);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Paciente Atendido");
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Llene los campos vacios");
                a.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(AtencionClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelar(MouseEvent event) {
        Alert salida = new Alert(Alert.AlertType.CONFIRMATION);
        salida.setTitle("Confirmacion de Salida");
        salida.setContentText("Esta seguro que desea salir?");

        Optional<ButtonType> result = salida.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                VideoPlayer.getInstance().reproducir();
                App.llamarEscena("principal", (Event) event);
            } catch (IOException ex) {
                Logger.getLogger(AtencionClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
