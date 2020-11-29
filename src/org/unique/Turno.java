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
public class Turno implements Serializable{
    private static int codigoAnterior;
    
    private String codigo;
    private Puesto puesto;
    private Paciente paciente;
    
    private static final long serialVersionUID = 18247L;

    public Turno(Puesto puesto, Paciente paciente) {
        this.puesto = puesto;
        this.paciente = paciente;
        codigo = "A" + getCodigoAnterior();
    }
    
    public static int getCodigoAnterior() {
        return codigoAnterior++;
    }

    public String getCodigo() {
        return codigo;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    
}
