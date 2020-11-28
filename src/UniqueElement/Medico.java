/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniqueElement;

import java.io.Serializable;

/**
 *
 * @author El Pitagoras
 */
public class Medico implements Serializable {

    private String nombre;
    private String apellido;
    private String especialidad;
    private String id;
    private boolean asignado;
    private static final long serialVersionUID = 123455L;

    public Medico(String nombre, String apellido, String especialidad, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.id = id;
    }

    public boolean isAsignado() {
        return asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " id:" + id;
    }
}
