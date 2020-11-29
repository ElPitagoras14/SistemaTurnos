/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unique;

import java.io.Serializable;

/**
 *
 * @author El Pitagoras
 */
public class Paciente implements Serializable {

    private String nombre;
    private String apellido;
    private String edad;
    private String genero;
    private Sintoma sintoma;
    
    private String diagnostico;
    private String receta;
    
    private static final long serialVersionUID = 123L;

    public Paciente(String nombre, String apellido, String edad, String genero, Sintoma sintoma) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.sintoma = sintoma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

}
