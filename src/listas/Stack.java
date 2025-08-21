package listas;

/**
 * Autor: Fernanda Ximena Garcia Salazar
 * Fecha: 21/08/2025
 * Descripción: La función de esta clase es implementar una pila, 
 * donde los elementos se guardan siguiendo la idea de 
 * “el último que entra es el primero que sale”. 
 * Se ocupa para apilar y desapilar datos de manera ordenada.
 */
public class Stack<T> {
    private Node<T> top;
    private int size;

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void push(T value) {
        Node<T> n = new Node<>(value);
        n.setNext(top);
        top = n;
        size++;
    }

    // Retira y devuelve el tope 
    public T pop() throws Exception {
        if (isEmpty()) throw new Exception("La pila está vacía");
        T val = top.getData();
        top = top.getNext();
        size--;
        return val;
    }

    // Consulta el tope sin quitar 
    public T peek() throws Exception {
        if (isEmpty()) throw new Exception("La pila está vacía");
        return top.getData();
    }

    public String toArrowString() {
        if (isEmpty()) return "(vacía)";
        StringBuilder sb = new StringBuilder();
        Node<T> cur = top;
        while (cur != null) {
            if (sb.length() > 0) sb.append(" -> ");
            sb.append(cur.getData());
            cur = cur.getNext();
        }
        return sb.toString();
    }
}
