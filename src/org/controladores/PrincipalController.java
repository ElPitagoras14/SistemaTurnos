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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
        Vbturno.setAlignment(Pos.TOP_CENTER);
        Vbturno.setSpacing(15);
        Vbpuesto.setAlignment(Pos.TOP_CENTER);
        Vbpuesto.setSpacing(15);
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
        reproductor.getVentanaVideo().setY(120);
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
            if(sistema.getListaTurno().isEmpty())
                throw new NullPointerException("No hay pacientes en cola");
            reproductor.cambiarVentana();
            App.llamarEscena("AtencionCliente", (Event) e);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException ale){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION,ale.getMessage());
            alerta.show();
        }
    }

    private void llenarPuesto() {
        for(Turno t: sistema.getListaTurno()) {
            
            Label turnos = new Label(t.getCodigo() + " " + t.getPaciente().getNombre());
            turnos.setMinWidth(100);
            turnos.setMinHeight(20);
            turnos.setAlignment(Pos.CENTER);
            turnos.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, new CornerRadii(5.0), new Insets(-5.0))));
            Vbturno.getChildren().add(turnos);
            Label puesto = new Label(t.getPuesto().getIdPuesto());
            puesto.setMinWidth(100);
            puesto.setAlignment(Pos.CENTER);
            puesto.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, new CornerRadii(5.0), new Insets(-5.0))));
            puesto.setTextFill(Color.BLACK);
            puesto.setMinHeight(20);
            puesto.setLineSpacing(20);
            Vbpuesto.getChildren().add(puesto);
        }
    }
    
}
