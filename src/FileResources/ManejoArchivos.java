/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileResources;

import java.util.LinkedList;

/**
 *
 * @author El Pitagoras
 * @param <E>
 */
public interface ManejoArchivos<E> {

    void serializar(LinkedList<E> lista, String archivo);

    LinkedList<E> deserializar(String archivo);
}
