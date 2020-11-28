/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import FileResources.Serializar;
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
import UniqueElement.Puesto;
import UniqueElement.Turno;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    private VBox Vbturno, Vbpuesto;

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
        reproductor.getVentanaVideo().setY(10);
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
        System.out.println("Paciente Atendido");
    }

    private void llenarPuesto() {
        System.out.println("LLENANDO");
        for(Turno t: sistema.getListaTurno()) {
            Label turnos = new Label(t.getCodigo() + " " + t.getPaciente().getNombre());
            Vbturno.getChildren().add(turnos);
            Label puesto = new Label(t.getPuesto().getIdPuesto());
            Vbpuesto.getChildren().add(puesto);
        }
        /*
        Serializar<Puesto> p = new Serializar();
        LinkedList<Puesto> listap = p.deserializar(Archivo);
        for (int i = 0; i < listap.size(); i++) {
            Label turnos = new Label("A" + i);
            Puesto cola = listap.get(i);
            Vbturno.getChildren().add(turnos);
            Label puesto = new Label(cola.getIdPuesto());
            Vbpuesto.getChildren().add(puesto);
        }
         */
    }

}
