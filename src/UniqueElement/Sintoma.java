/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniqueElement;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author GeovannyRojas
 */
public class Sintoma {

    private String nombre;
    private int prioridad;

    public Sintoma(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public static ArrayList<Sintoma> llenarSintoma() {
        ArrayList<Sintoma> sintomas = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("sintomas.txt"))) {
            while (sc.hasNextLine()) {
                String[] id = sc.nextLine().split("\\|");
                Sintoma sin = new Sintoma(id[0], Integer.parseInt(id[1]));
                sintomas.add(sin);
            }
        } catch (Exception e) {
        }

        return sintomas;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
