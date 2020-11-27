/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileResources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 *
 * @author GeovannyRojas
 * @param <E>
 */
public class Serializar<E> implements ManejoArchivos<E> {

    @Override
    public void serializar(LinkedList<E> lista, String archivo) {
        try (FileOutputStream out = new FileOutputStream(archivo);
                ObjectOutputStream serializa = new ObjectOutputStream(out);) {
            serializa.writeObject(lista);

        } catch (Exception ex) {
            System.out.println("No se encontro el archivo");
        }
    }

    @Override
    public LinkedList<E> deserializar(String archivo) {
        LinkedList<E> lista = new LinkedList<>();
        try (FileInputStream file = new FileInputStream(archivo);
                ObjectInputStream in = new ObjectInputStream(file);) {
            lista = (LinkedList<E>) in.readObject();
        } catch (IOException ex) {
            System.out.println("No hay archivo");
        } catch (ClassNotFoundException ex) {
            System.out.println("No hay dicha clase");
        }

        return lista;
    }
}
