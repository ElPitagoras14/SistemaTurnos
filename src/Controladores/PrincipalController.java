/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import Main.App;
import MediaPlayer.VideoPlayer;
import javafx.event.Event;
import javafx.scene.layout.AnchorPane;


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
   private AnchorPane root;
   
   VideoPlayer reproductor = VideoPlayer.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        reproductor.getVentanaVideo().setFitHeight(200);
        reproductor.getVentanaVideo().setFitWidth(300);

        root.getChildren().add(reproductor.getVentanaVideo());
    }
    
    @FXML
    private void crearPaciente(MouseEvent e) 
    {
       try {
           App.llamarEscena("FormularioPaciente", (Event) e);
       } catch (IOException ex) {
           ex.printStackTrace();
       }
    }
    
    @FXML
    private void crearMedico(MouseEvent e) 
    {
       try {
            App.llamarEscena("FormularioMedico", (Event)e);
       } catch (IOException ex) {
           ex.printStackTrace();
       }
    }
    

   
}
