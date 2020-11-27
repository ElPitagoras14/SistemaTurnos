/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

/**
 *
 * @author El Pitagoras
 */
public class CircularLinkedList<E> implements List<E> {

    private Node<E> last;
    private int current;

    public CircularLinkedList() {

    }

    private class Node<E> {

        private E data;
        private Node<E> next;

        public Node(E element) {
            this.data = element;
            this.next = this;
        }

    }

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        }
        Node<E> nodo = new Node<>(element);
        if (isEmpty()) {
            last = nodo;
            nodo.next = nodo;
        } else {
            nodo.next = last.next;
            last.next = nodo;
        }
        current++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        }
        Node<E> nodo = new Node<>(element);
        if (isEmpty()) {
            last = nodo;
            nodo.next = nodo;
        } else {
            nodo.next = last.next;
            last.next = nodo;
            last = nodo;
        }
        current++;
        return true;

    }

    public boolean insert(int index, E element) {
        if (element == null || index < 0 || index > current) {
            return false;
        }
        Node<E> nodoAdd = new Node<>(element);
        Node<E> nodo = last;
        int i = 0;
        while (i < index) {
            nodo = nodo.next;
            i++;
        }
        nodoAdd.next = nodo.next;
        nodo.next = nodoAdd;
        current++;
        return true;
    }

    @Override
    public E get(int index) {
        if (isEmpty() || index > current || index < 0) {
            return null;
        }
        return nodeIndex(index).data;
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean isEmpty() {
        return last == null;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index > current) {
            return false;
        }
        if (index == current - 1) {
            removeLast();
        } else {
            int i = 0;
            Node<E> nodo = last.next;
            while (i != index) {
                nodo = nodo.next;
                i++;
            }
            Node<E> previo = getPrevious(nodo);
            Node<E> sgte = nodo.next;
            nodo.data = null; //Help Gc
            previo.next = sgte;
            current--;
        }
        return true;

    }

    @Override
    public boolean contains(E element) {
        if (isEmpty()) {
            return false;
        }
        Node<E> nodo = last.next;
        do {
            if (nodo.data.equals(element)) {
                return true;
            }
            nodo = nodo.next;
        } while (nodo != last.next);
        return false;
    }

    @Override
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        }
        Node<E> newFirst = last.next.next;
        last.next.data = null;//help GC
        last.next = newFirst;
        return true;
    }

    @Override
    public boolean removeLast() {
        if (isEmpty()) {
            return true;
        }
        Node<E> primero = last.next;
        Node<E> previo = getPrevious(last);
        previo.next = primero;
        last.data = null;
        last = previo;
        current--;
        return true;
    }

    @Override
    public E getFirst() {
        if (last == null) {
            return null;
        }
        return last.next.data;
    }

    @Override
    public E getLast() {
        if (last == null) {
            return null;
        }
        return last.data;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (Node<E> primero = last.next; primero != last; primero = primero.next) {
            sb.append(primero.data);
            sb.append(",");
        }
        sb.append(last.data);
        sb.append("]");
        return sb.toString();
    }

    public E recorrerLlamada() {
        Node<E> tmp = last.next;
        last = tmp;
        return tmp.data;
    }

    private Node<E> nodeIndex(int index) {
        if (isEmpty() || index >= current || index < 0) {
            return null;
        }
        Node<E> viajero = last.next;
        int i = 0;
        while (i < index) {
            viajero = viajero.next;
            i++;
        }
        return viajero;
    }

    private Node<E> getPrevious(Node<E> n) {
        if (isEmpty() || n == null) {
            return null;
        }
        Node<E> node = last;
        do {
            if (node.next == n) {
                return node;
            }
            node = node.next;
        } while (node != last);
        return null;
    }

}
