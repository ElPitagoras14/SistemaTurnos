/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniqueElement;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author El Pitagoras
 */
public class Puesto implements Serializable {

    private String idPuesto;
    private boolean ocupado;
    private Medico medicoA;
    private static final long serialVersionUID = 12345643L;

    public Puesto(String idPuesto, Medico medicoA) {
        this.idPuesto = idPuesto;
        this.medicoA = medicoA;
        ocupado = false;
    }

    public Puesto(String idPuesto) {
        this.idPuesto = idPuesto;
        ocupado = false;
    }

    public String getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Medico getMedicoA() {
        return medicoA;
    }

    public void setMedicoA(Medico medicoA) {
        this.medicoA = medicoA;
    }

    public static void anadirPuesto(Puesto p) {

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

    public void eliminarPuesto() {

    }

    public void anadirMedico(String ced) {
    }
}
