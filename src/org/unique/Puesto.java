/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unique;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author El Pitagoras
 */
public class Puesto implements Serializable {

    private String idPuesto;
    private Medico medicoA;
    private boolean atencion;
    private static final long serialVersionUID = 12345643L;

    public Puesto(String idPuesto, Medico medicoA) {
        this.idPuesto = idPuesto;
        this.medicoA = medicoA;
    }

    public Puesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public boolean isOcupado() {
        return medicoA != null;
    }
    
    public boolean isAtendiendo() {
        return atencion;
    }

    public void setAtencion(boolean atencion) {
        this.atencion = atencion;
    }

    public Medico getMedicoA() {
        return medicoA;
    }

    public void setMedicoA(Medico medicoA) {
        this.medicoA = medicoA;
    }

    @Override
    public String toString() {
        return idPuesto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Puesto other = (Puesto) obj;
        if (!Objects.equals(this.idPuesto, other.idPuesto)) {
            return false;
        }
        return true;
    }
    
    public void eliminarMedico() {
        medicoA = null;
    }
}
