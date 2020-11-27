/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import UniqueElement.Paciente;
import UniqueElement.Puesto;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author El Pitagoras
 */
public class SistemaEspera {
    private PriorityQueue<Paciente> colaPaciente;
    private Queue<Puesto> colaPuestoDisponible;
    private static SistemaEspera instance;
    
    private SistemaEspera() {
        colaPaciente = new PriorityQueue<>((Paciente p1, Paciente p2) -> p2.getSintoma().getPrioridad() - p1.getSintoma().getPrioridad());
        colaPuestoDisponible = new LinkedList<>();
    }
    
    public void añadirPaciente(Paciente paciente) {
        colaPaciente.offer(paciente);
    }
    
    public Paciente obtenerPaciente() {
        if (!colaPaciente.isEmpty()) {
            return colaPaciente.poll();
        }
        return null;
    }
    
    public void añadirPuesto(Puesto disponible) {
        colaPuestoDisponible.offer(disponible);
    }
    
    public Puesto obtenerPuestoDisponible() {
        if (!colaPuestoDisponible.isEmpty()) {
            return colaPuestoDisponible.poll();
        }
        return null;
    }

    public static SistemaEspera getInstance() {
        if (instance == null) {
            instance = new SistemaEspera();
        }
        return instance;
    }
    
    
    
}
