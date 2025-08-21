package listas;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Autor: Fernanda Ximena Garcia Salazar
 * Fecha: 21/08/2025
 * Descripción: La función de esta clase es manejar una lista enlazada completa. 
 * Se encarga de organizar los nodos, conectarlos y dar las acciones necesarias 
 * para recorrer, agregar o quitar elementos de la lista.
 */
public class LinkedList<T> implements Iterable<T> {

    /** Listas compatibles */
    public enum Kind { SINGLY, DOUBLY, CIRCULAR_SINGLY, CIRCULAR_DOUBLY }

    private final Kind kind;
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /** Por defecto lista simple */
    public LinkedList() {
        this(Kind.SINGLY);
    }

    public LinkedList(Kind kind) {
        if (kind == null) throw new IllegalArgumentException("El tipo de lista no puede ser null");
        this.kind = kind;
    }

    public Kind getKind() { return kind; }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void addFirst(T value) {
        Node<T> n = new Node<>(value);
        if (isEmpty()) {
            head = tail = n;
            if (isCircular()) linkCircularEnds();
        } else {
            n.setNext(head);
            if (isDoubly()) head.setPrev(n);
            head = n;
            if (isCircular()) head.setPrev(isDoubly() ? tail : null);
            if (isCircular()) tail.setNext(head);
        }
        size++;
    }

    public void addLast(T value) {
        Node<T> n = new Node<>(value);
        if (isEmpty()) {
            head = tail = n;
            if (isCircular()) linkCircularEnds();
        } else {
            if (isDoubly()) {
                n.setPrev(tail);
            }
            tail.setNext(n);
            tail = n;
            if (!isDoubly()) n.setPrev(null); 
            if (isCircular()) linkCircularEnds();
        }
        size++;
    }

    public T findFirst(Predicate<T> predicate) {
        if (predicate == null) throw new IllegalArgumentException("predicate no puede ser null");
        if (isEmpty()) return null;
        Node<T> cur = head;
        int iterCount = 0;
        do {
            if (predicate.test(cur.getData())) return cur.getData();
            cur = cur.getNext();
            iterCount++;
        } while (cur != null && cur != head && iterCount <= size); 
        return null;
    }

    public int updateIf(Predicate<T> predicate, UnaryOperator<T> updater) {
        if (predicate == null || updater == null) throw new IllegalArgumentException("Parámetros inválidos");
        if (isEmpty()) return 0;
        int updates = 0;
        Node<T> cur = head;
        int iterCount = 0;
        do {
            if (predicate.test(cur.getData())) {
                cur.setData(updater.apply(cur.getData()));
                updates++;
            }
            cur = cur.getNext();
            iterCount++;
        } while (cur != null && cur != head && iterCount <= size);
        return updates;
    }

    public int removeIf(Predicate<T> predicate) {
        if (predicate == null) throw new IllegalArgumentException("predicate no puede ser null");
        if (isEmpty()) return 0;

        int removed = 0;
        Node<T> cur = head;
        int iterCount = 0;
        do {
            Node<T> next = cur.getNext();
            if (predicate.test(cur.getData())) {
                unlink(cur);
                removed++;
            }
            cur = next;
            iterCount++;
        } while (cur != null && cur != head && iterCount <= size + removed); 
        return removed;
    }

    private void unlink(Node<T> node) {
        if (node == null) return;
        if (size == 1) {
            head = tail = null;
            size = 0;
            return;
        }

        Node<T> prev = node.getPrev();
        if (prev == null && (!isDoubly())) {
            prev = findPrev(node);
        }
        Node<T> next = node.getNext();

        if (prev != null) prev.setNext(next);
        if (next != null && isDoubly()) next.setPrev(prev);

        if (node == head) head = next;
        if (node == tail) tail = prev;

        if (isCircular()) linkCircularEnds();

        size--;
    }

    private Node<T> findPrev(Node<T> target) {
        if (target == null || isEmpty()) return null;
        Node<T> cur = head;
        int iterCount = 0;
        Node<T> prev = null;
        do {
            if (cur == target) return prev;
            prev = cur;
            cur = cur.getNext();
            iterCount++;
        } while (cur != null && cur != head && iterCount <= size);
        return null;
    }

    private boolean isCircular() {
        return kind == Kind.CIRCULAR_SINGLY || kind == Kind.CIRCULAR_DOUBLY;
    }

    private boolean isDoubly() {
        return kind == Kind.DOUBLY || kind == Kind.CIRCULAR_DOUBLY;
    }

    private void linkCircularEnds() {
        if (!isCircular()) return;
        if (head == null || tail == null) return;
        tail.setNext(head);
        if (isDoubly()) head.setPrev(tail);
    }

    public String toArrowString() {
        if (isEmpty()) return "(vacía)";
        StringBuilder sb = new StringBuilder();
        Iterator<T> it = iterator();
        if (!it.hasNext()) return "(vacía)";
        sb.append(it.next());
        while (it.hasNext()) sb.append(" -> ").append(it.next());
        if (isCircular()) sb.append(" -+");
        return sb.toString();
    }

    public String toReverseArrowString() {
        if (isEmpty()) return "(vacía)";
        if (!isDoubly()) return "(no aplica en lista simple)";
        StringBuilder sb = new StringBuilder();
        Node<T> cur = tail;
        while (cur != null && (cur != tail || sb.length() == 0)) {
            if (sb.length() > 0) sb.append(" -> ");
            sb.append(cur.getData());
            cur = cur.getPrev();
            if (isCircular() && cur == tail) break;
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> cur = head;
            private int seen = 0;
            @Override public boolean hasNext() {
                return cur != null && seen < size;
            }
            @Override public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T val = cur.getData();
                cur = cur.getNext();
                seen++;
                return val;
            }
        };
    }
}
