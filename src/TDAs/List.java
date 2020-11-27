/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

/**
 *
 * @author eduardo
 */
public interface List<E> {

    boolean addFirst(E e);

    boolean addLast(E e);

    E getFirst();

    E getLast();

    int size();

    boolean removeLast();

    boolean removeFirst();

    boolean isEmpty();

    E get(int index);

    boolean contains(E e);

    boolean remove(int index);
}
