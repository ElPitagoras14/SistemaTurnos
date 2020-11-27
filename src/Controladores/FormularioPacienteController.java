/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import FileResources.Serializar;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Main.App;
import UniqueElement.Paciente;
import UniqueElement.Sintoma;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;

/**
 *
 * @author GeovannyRojas
 */
public class FormularioPacienteController  implements Initializable{

    @FXML
    private TextField textNombre;
    
    @FXML
    private TextField textApellido;
    
    @FXML
    private TextField textEdad;
    
    @FXML
    private ComboBox cmbSintoma;
    @FXML
    private ComboBox cmbGenero;
    
    Serializar<Paciente> serializar = new Serializar();
    LinkedList<Paciente> listaPaciente;
    /**
     * textNombre = el campo de Nombre del paciente.
     * textApellido = el campo de Apellido del paciente.
     * textEdad = la edad del paciente ----verificar que sea un número.
     * textSintoma = sintoma del paciente, supuse que solo era uno. ---- tmb verificar.
     * cmbGenero  = comboBox para el genero.
     */
    
    @FXML
    private Button btnCreaPaciente, btnCancelar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ArrayList<String> generos = new ArrayList<>();
        generos.add("Masculino"); generos.add("Femenino");generos.add("Sin Especificar");
        cmbGenero.setItems(FXCollections.observableArrayList(generos));
        listaPaciente = serializar.deserializar("Paciente.ser");
        cmbSintoma.setItems(FXCollections.observableArrayList(Sintoma.llenarSintoma()));
    }
    
    @FXML
    private void creaPaciente(MouseEvent e)
    {
        try{
            if (textNombre.getText().equals("") || textApellido.getText().equals("")
               || textEdad.getText().equals("") || cmbSintoma.getValue().toString().equals("")
               || cmbGenero.getValue().toString().equals("")) {
                throw new fieldException("Es necesario llenas los espacios vacios");
                
            } else if (!verificarTexto(textNombre.getText())
                    || !verificarTexto(textApellido.getText())
                    || verificarNumero(textEdad.getText())) {
                throw new fieldException("Por favor ingrese datos válidos");
            }
            
            /*
            Paciente paciente = new Paciente(textNombre.getText(),textApellido.getText(),textEdad.getText(),cmbGenero.getValue().toString(),cmbSintoma.getValue().toString());
            listaPaciente.add(paciente);
            serializar.serializar(listaPaciente, "Paciente.ser");
            */
            Alert a = new Alert(Alert.AlertType.INFORMATION,"Se ha registrado un paciente");
            a.show();
            App.llamarEscena("principal", (Event)e);
        }catch(fieldException f)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION,f.getMessage());
            a.show();
        } catch (IOException ex) {
            Logger.getLogger(FormularioPacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void atras(MouseEvent e)
    {
         Alert salida = new Alert(Alert.AlertType.CONFIRMATION);
        salida.setTitle("Confirmacion de Salida");
        salida.setContentText("Esta seguro que desea salir?");

        Optional<ButtonType> result = salida.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try{
                App.llamarEscena("principal", (Event)e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
   
    class fieldException extends Exception
    {
        public fieldException(String msg)
        {
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
     
    public static boolean verificarNumero (String msg)
    {
        try {
            int numero = Integer.parseInt(msg);
            return numero >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
