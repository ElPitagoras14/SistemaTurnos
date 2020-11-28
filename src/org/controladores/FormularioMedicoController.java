/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Main.App;
import MediaPlayer.VideoPlayer;
import System.SistemaEspera;
import org.unique.Medico;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;

/**
 *
 * @author GeovannyRojas
 */
public class FormularioMedicoController implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnAgregar;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textApellido;

    @FXML
    private TextField textEspecialidad;

    @FXML
    private TextField textId;

    private SistemaEspera sistema;

    /**
     *
     * btnCancelar = Boton para cancelar formulario. btnAgregar = Boton para
     * agregar médico. textNombre = el campo de texto del nombre del médico.
     * textApellido = el campo de texto del apellido del médico.
     * textEspecialidad = el campo de texto de la expecialidad del médico.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sistema = SistemaEspera.getInstance();
    }

    @FXML
    private void agregarMedico(MouseEvent e) {
        Medico doc;
        try {
            if (verificarCampos()) {
                if (verificarTexto(textNombre.getText()) && verificarTexto(textApellido.getText()) && verificarTexto(textEspecialidad.getText())) {
                    doc = new Medico(textNombre.getText(), textApellido.getText(), textEspecialidad.getText(), textId.getText());
                    sistema.addMedico(doc);
                    sistema.actualizarDatos();
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Médico Registrado");
                    a.show();
                    VideoPlayer.getInstance().reproducir();
                    App.llamarEscena("principal", (Event) e);
                } else {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Ingrese correctamente los campos");
                    a.show();
                }

            }

        } catch (FieldException campo) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, campo.getMessage());
            a.show();
        } catch (IOException ex) {
            Logger.getLogger(FormularioMedicoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void atras(MouseEvent e) {
        Alert salida = new Alert(Alert.AlertType.CONFIRMATION);
        salida.setTitle("Confirmacion de Salida");
        salida.setContentText("Esta seguro que desea salir?");

        Optional<ButtonType> result = salida.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                VideoPlayer.getInstance().reproducir();
                App.llamarEscena("principal", (Event) e);
            } catch (IOException ex) {
                Logger.getLogger(FormularioMedicoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean verificarCampos() throws FieldException {

        if (textNombre.getText().equals("") || textApellido.getText().equals("") || textEspecialidad.getText().equals("")) {
            throw new FieldException("Por favor llene los espacios vacios.");
        } else {
            return true;
        }
    }

    class FieldException extends Exception {

        public FieldException(String msg) {
            super(msg);
        }
    }

    public static boolean verificarTexto(String texto) {
        try {
            int numero = Integer.parseInt(texto);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
