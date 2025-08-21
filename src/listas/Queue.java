package listas;

/**
 * Autor: Fernanda Ximena Garcia Salazar
 * Fecha: 21/08/2025
 * Descripción: La función de esta clase es implementar una cola, 
 * donde los elementos se organizan siguiendo el principio de 
 * “el primero que entra es el primero que sale”. 
 * Así se maneja un orden como si fuera una fila de espera.
 */
public class Queue<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    // Encola al final 
    public void push(T value) {
        Node<T> n = new Node<>(value);
        if (isEmpty()) {
            front = rear = n;
        } else {
            rear.setNext(n);
            rear = n;
        }
        size++;
    }

    // Desencola y devuelve el frente
    public T pop() throws Exception {
        if (isEmpty()) throw new Exception("La cola está vacía");
        T val = front.getData();
        front = front.getNext();
        if (front == null) rear = null;
        size--;
        return val;
    }

    // Consulta el frente sin quitar 
    public T peek() throws Exception {
        if (isEmpty()) throw new Exception("La cola está vacía");
        return front.getData();
    }

    public String toArrowString() {
        if (isEmpty()) return "(vacía)";
        StringBuilder sb = new StringBuilder();
        Node<T> cur = front;
        while (cur != null) {
            if (sb.length() > 0) sb.append(" -> ");
            sb.append(cur.getData());
            cur = cur.getNext();
        }
        return sb.toString();
    }
}
