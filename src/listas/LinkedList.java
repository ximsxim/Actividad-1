package listas;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Autor: Ximena Salazar
 * Fecha: 16/08/2025
 * Descripción: Clase que maneja diferentes tipos de listas
 * (simple, doble y circular). Permite hacer operaciones básicas
 * como insertar, borrar, buscar, actualizar e imprimir los datos.
 */
public class LinkedList<T> implements Iterable<T> {

    public enum ListKind { SIMPLE, DOBLE, CIRCULAR }

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;
    private ListKind tipo;

    public LinkedList() {
        this.tipo = ListKind.SIMPLE;
    }

    public LinkedList(ListKind tipo) {
        this.tipo = (tipo == null ? ListKind.SIMPLE : tipo);
    }

    private boolean esCircular() { return tipo == ListKind.CIRCULAR; }
    private boolean esDoble()    { return tipo == ListKind.DOBLE;    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void insertFirst(T data) {
        Node<T> n = new Node<>(data);
        if (head == null) {
            head = tail = n;
            size = 1;
            if (esCircular()) tail.setNext(head);
            return;
        }
        if (esDoble()) {
            n.setNext(head);
            head.setPrev(n);
            head = n;
        } else {
            n.setNext(head);
            head = n;
            if (esCircular()) tail.setNext(head);
        }
        size++;
    }
    public void insertLast(T data) {
        Node<T> n = new Node<>(data);
        if (head == null) {
            head = tail = n;
            size = 1;
            if (esCircular()) tail.setNext(head);
            return;
        }
        if (esDoble()) {
            tail.setNext(n);
            n.setPrev(tail);
            tail = n;
        } else {
            tail.setNext(n);
            tail = n;
            if (esCircular()) tail.setNext(head);
        }
        size++;
    }

    public T find(Predicate<T> criterio) {
        if (head == null) return null;
        if (esCircular()) {
            Node<T> cur = head;
            do {
                if (criterio.test(cur.getData())) return cur.getData();
                cur = cur.getNext();
            } while (cur != head);
            return null;
        } else {
            Node<T> cur = head;
            while (cur != null) {
                if (criterio.test(cur.getData())) return cur.getData();
                cur = cur.getNext();
            }
            return null;
        }
    }

    public boolean remove(Predicate<T> criterio) {
        if (head == null) return false;

        if (esDoble()) {
            Node<T> cur = head;
            while (cur != null) {
                if (criterio.test(cur.getData())) {
                    Node<T> p = cur.getPrev();
                    Node<T> nx = cur.getNext();
                    if (p != null) p.setNext(nx); else head = nx;
                    if (nx != null) nx.setPrev(p); else tail = p;
                    size--;
                    return true;
                }
                cur = cur.getNext();
            }
            return false;
        } else { 
            Node<T> cur = head;
            if (criterio.test(cur.getData())) {
                if (head == tail) { 
                    head = tail = null;
                    size = 0;
                    return true;
                }
                head = head.getNext();
                if (esCircular()) tail.setNext(head);
                size--;
                return true;
            }
            if (esCircular()) {
                do {
                    Node<T> nx = cur.getNext();
                    if (criterio.test(nx.getData())) {
                        if (nx == tail) tail = cur;
                        cur.setNext(nx.getNext());
                        tail.setNext(head);
                        size--;
                        return true;
                    }
                    cur = cur.getNext();
                } while (cur != head);
            } else {
                while (cur.getNext() != null) {
                    if (criterio.test(cur.getNext().getData())) {
                        if (cur.getNext() == tail) tail = cur;
                        cur.setNext(cur.getNext().getNext());
                        size--;
                        return true;
                    }
                    cur = cur.getNext();
                }
            }
            return false;
        }
    }
    public boolean update(Predicate<T> criterio, UnaryOperator<T> updater) {
        if (head == null) return false;

        if (esCircular()) {
            Node<T> cur = head;
            do {
                if (criterio.test(cur.getData())) {
                    cur.setData(updater.apply(cur.getData()));
                    return true;
                }
                cur = cur.getNext();
            } while (cur != head);
            return false;
        } else {
            Node<T> cur = head;
            while (cur != null) {
                if (criterio.test(cur.getData())) {
                    cur.setData(updater.apply(cur.getData()));
                    return true;
                }
                cur = cur.getNext();
            }
            return false;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> cur = head;
            boolean started = false;
            final boolean circular = esCircular();

            @Override public boolean hasNext() {
                if (cur == null) return false;
                if (!circular)   return cur != null;
                return !started || cur != head;
            }

            @Override public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T val = cur.getData();
                cur = cur.getNext();
                started = true;
                return val;
            }
        };
    }
    public Iterable<T> descending() {
        return () -> new Iterator<T>() {
            Node<T> cur = tail;
            @Override public boolean hasNext() { return cur != null; }
            @Override public T next() {
                if (cur == null) throw new NoSuchElementException();
                T val = cur.getData();
                cur = cur.getPrev();
                return val;
            }
        };
    }
    public void print() {
        if (head == null) { System.out.println("[lista vacía]"); return; }
        for (T x : this) System.out.println(x);
    }
    public void printReverse() {
        if (!esDoble()) { print(); return; }
        if (tail == null) { System.out.println("[lista vacía]"); return; }
        for (T x : descending()) System.out.println(x);
    }
    public void printCircularDiagram() {
        if (!esCircular()) { print(); return; }
        if (head == null) { System.out.println("[lista vacía]"); return; }

        StringBuilder sb = new StringBuilder();
        Iterator<T> it = iterator();
        if (!it.hasNext()) { System.out.println("[lista vacía]"); return; }
        sb.append(it.next());
        while (it.hasNext()) sb.append(" -> ").append(it.next());
        sb.append(" -+");
        String linea = sb.toString();
        System.out.println(linea);

        int ancho = Math.max(2, linea.length() - 1);
        StringBuilder cierre = new StringBuilder("^");
        for (int i = 0; i < ancho - 2; i++) cierre.append("-");
        cierre.append("+");
        System.out.println(cierre.toString());
    }
}
