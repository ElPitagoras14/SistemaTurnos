/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import Main.App;
import MediaPlayer.VideoPlayer;
import System.SistemaEspera;
import org.unique.Turno;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author GeovannyRojas
 */
public class PrincipalController implements Initializable {

    @FXML
    private Button formularioMedico;

    @FXML
    private Button formularioPaciente;

    @FXML
    private Label hora;

    @FXML
    private AnchorPane root;

    @FXML
    private VBox Vbturno;
    
    @FXML
    private VBox Vbpuesto;

    @FXML
    private Button atencionPaciente;

    @FXML
    private Button btnPuesto;

    private VideoPlayer reproductor;
    private SistemaEspera sistema;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reproductor = VideoPlayer.getInstance();
        sistema = SistemaEspera.getInstance();
        Calendar calendario = Calendar.getInstance();
        int Hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        if (minutos < 10) {
            hora.setText(Hora + ":0" + minutos);
        } else {
            hora.setText(Hora + ":" + minutos);
        }

        llenarPuesto();
        reproductor.getVentanaVideo().setX(10);
        reproductor.getVentanaVideo().setY(70);
        reproductor.getVentanaVideo().setFitHeight(200);
        reproductor.getVentanaVideo().setFitWidth(300);
        root.getChildren().add(reproductor.getVentanaVideo());
    }

    @FXML
    private void crearPaciente(MouseEvent e) {
        try {
            reproductor.cambiarVentana();
            App.llamarEscena("FormularioPaciente", (Event) e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void crearMedico(MouseEvent e) {
        try {
            reproductor.cambiarVentana();
            App.llamarEscena("FormularioMedico", (Event) e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void crearPuesto(MouseEvent e) {
        try {
            reproductor.cambiarVentana();
            App.llamarEscena("Puesto", (Event) e);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void atenderPaciente(MouseEvent e) {
        try {
            reproductor.cambiarVentana();
            App.llamarEscena("AtencionCliente", (Event) e);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void llenarPuesto() {
        for(Turno t: sistema.getListaTurno()) {
            Text turnos = new Text(t.getCodigo() + " " + t.getPaciente().getNombre());
            turnos.setFill(Color.WHITE);
            turnos.setLineSpacing(30);
            turnos.setTextAlignment(TextAlignment.CENTER);
            Vbturno.getChildren().add(turnos);
            Text puesto = new Text(t.getPuesto().getIdPuesto());
            puesto.setFill(Color.WHITE);
            puesto.setLineSpacing(20);
            puesto.setTextAlignment(TextAlignment.CENTER);
            Vbpuesto.getChildren().add(puesto);
        }
    }

}
