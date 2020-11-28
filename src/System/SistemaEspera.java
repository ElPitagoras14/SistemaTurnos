/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import FileResources.Serializar;
import org.unique.Medico;
import org.unique.Paciente;
import org.unique.Puesto;
import org.unique.Turno;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author El Pitagoras
 */
public class SistemaEspera {

    private PriorityQueue<Paciente> colaPaciente;
    private LinkedList<Medico> listaMedico;
    private LinkedList<Puesto> listaPuesto;
    private LinkedList<Paciente> historicoPaciente;
    private LinkedList<Turno> listaTurno;

    private Serializar<Medico> serMedico;
    private Serializar<Puesto> serPuesto;
    private Serializar<Paciente> serPaciente;
    private Serializar<Turno> serTurno;

    private static SistemaEspera instance;

    private SistemaEspera() {
        colaPaciente = new PriorityQueue<>((Paciente p1, Paciente p2) -> p1.getSintoma().getPrioridad() - p2.getSintoma().getPrioridad());
        listaPuesto = new LinkedList<>();
        listaMedico = new LinkedList<>();
        historicoPaciente = new LinkedList<>();
        listaTurno = new LinkedList<>();

        serMedico = new Serializar<>();
        serPuesto = new Serializar<>();
        serPaciente = new Serializar<>();
        serTurno = new Serializar<>();

        listaMedico = serMedico.deserializar("Medico.ser");
        listaPuesto = serPuesto.deserializar("Puesto.ser");
        listaTurno = serTurno.deserializar("Turno.ser");
        historicoPaciente = serPaciente.deserializar("Paciente.ser");
    }

    public void actualizarDatos() {
        serMedico.serializar(listaMedico, "Medico.ser");
        serPuesto.serializar(listaPuesto, "Puesto.ser");
        serPaciente.serializar(historicoPaciente, "Paciente.ser");
        serTurno.serializar(listaTurno, "Turno.ser");
    }

    public void addPaciente(Paciente paciente) {
        colaPaciente.offer(paciente);
        historicoPaciente.add(paciente);
    }

    public void atenderPaciente() {
        listaTurno.getFirst().getPuesto().setAtencion(false);
        listaTurno.removeFirst();
        actualizarTurnos();
    }

    public void actualizarTurnos() {
        for (Puesto p : listaPuesto) {
            if (!p.isAtendiendo() && p.isOcupado() && !colaPaciente.isEmpty()) {
                p.setAtencion(true);
                listaTurno.add(new Turno(p, obtenerPaciente()));
            }
        }
    }

    public List<Turno> getListaTurno() {
        return listaTurno;
    }

    private Paciente obtenerPaciente() {
        if (!colaPaciente.isEmpty()) {
            return colaPaciente.poll();
        }
        return null;
    }

    public static SistemaEspera getInstance() {
        if (instance == null) {
            instance = new SistemaEspera();
        }
        return instance;
    }

    public List<Puesto> getListaPuesto() {
        return listaPuesto;
    }

    public List<Medico> getListaMedico() {
        return listaMedico;
    }

    public void addMedico(Medico m) {
        listaMedico.add(m);
    }

    public void addPuesto(Puesto p) {
        listaPuesto.add(p);
        listaMedico.remove(p.getMedicoA());
    }

    public void asignarMedico(String id, Medico m) {
        Puesto p = obtenerPuesto(id);
        if (p != null && !p.isOcupado() && !p.isAtendiendo() && !m.isAsignado()) {
            m.setAsignado(true);
            listaMedico.remove(m);
            p.setMedicoA(m);
        }
    }

    public void eliminarMedico(String id) {
        Puesto p = obtenerPuesto(id);
        if (p != null && p.isOcupado() && !p.isAtendiendo()) {
            p.getMedicoA().setAsignado(false);
            listaMedico.add(p.getMedicoA());
            p.eliminarMedico();
        }
    }

    public void eliminarPuesto(String id) {
        Puesto p = obtenerPuesto(id);
        if (p != null && !p.isOcupado() && !p.isAtendiendo()) {
            listaPuesto.remove(p);
        }
    }

    private Puesto obtenerPuesto(String id) {
        for (Puesto p : listaPuesto) {
            if (p.getIdPuesto().equals(id)) {
                return p;
            }
        }
        return null;
    }

}
