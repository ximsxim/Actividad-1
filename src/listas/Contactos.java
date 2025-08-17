package listas;

/**
 * Autor: Ximena Salazar
 * Fecha: 16/08/2025
 * Descripción: Clase que define un contacto con su nombre,
 * dirección y teléfono. Es el tipo de dato que usamos
 * dentro de las listas para los ejemplos.
 */
public class Contactos {
    private String nombre;
    private String direccion;
    private int telefono;

    public Contactos() { }

    public Contactos(String nombre, String direccion, int telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Contacto{nombre='" + nombre + "', direccion='" + direccion + "', telefono=" + telefono + "}";
    }
}
