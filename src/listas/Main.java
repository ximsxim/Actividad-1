package listas;

import java.util.Locale;
import java.util.Scanner;

/**
 * Autor: Fernanda Ximena Garcia Salazar
 * Fecha: 21/08/2025
 * Descripción: La función de esta clase es servir como punto de inicio del programa. 
 * Desde aquí se muestra el menú principal y se permite al usuario elegir entre 
 * las opciones para probar la lista, la pila y la cola.
 */
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Stack<String> pilaComandos = new Stack<>();
    private static final Queue<String> colaProcesos = new Queue<>();

    public static void main(String[] args) {
        Locale.setDefault(new Locale("es", "MX"));
        boolean seguir = true;
        while (seguir) {
            System.out.println("\n~ SIMULADOR DE SISTEMA OPERATIVO ~");
            System.out.println("1) Agregar comandos a la pila");
            System.out.println("2) Agregar procesos a la cola");
            System.out.println("3) Ver estado actual");
            System.out.println("4) Ejecutar comandos ");
            System.out.println("5) Realizar procesos ");
            System.out.println("6) Salir");
            int opcion = pedirOpcion(1, 6);
            switch (opcion) {
                case 1 -> agregarComandos();
                case 2 -> agregarProcesos();
                case 3 -> mostrarEstado();
                case 4 -> ejecutarComandos();
                case 5 -> realizarProcesos();
                case 6 -> { System.out.println("Saliendo..."); seguir = false; }
            }
        }
    }

    private static void agregarComandos() {
        System.out.println("\n** Agregando comandos **");
        boolean agregarMas;
        do {
            String cmd = pedirNoVacio("Escribe un comando y presiona ENTER: ");
            pilaComandos.push(cmd);
            agregarMas = pedirSiNo("¿Deseas agregar otro comando? (si/no): ");
        } while (agregarMas);
    }

    private static void agregarProcesos() {
        System.out.println("\n** Agregando procesos **");
        boolean agregarMas;
        do {
            String proc = pedirNoVacio("Escribe un proceso y presiona ENTER: ");
            colaProcesos.push(proc);
            agregarMas = pedirSiNo("¿Deseas agregar otro proceso? (si/no): ");
        } while (agregarMas);
    }

    private static void mostrarEstado() {
        System.out.println("\n** ESTADO DEL SISTEMA **");
        System.out.println("Los comandos se están manejando en una pila (Stack):");
        System.out.println("Pila: " + pilaComandos.toArrowString());

        System.out.println("");

        System.out.println("Los procesos se están manejando en una fila (Queue):");
        System.out.println("Fila: " + colaProcesos.toArrowString());
    }

    private static void ejecutarComandos() {
        System.out.println("\n** EJECUTAR COMANDOS **");
        if (pilaComandos.isEmpty()) {
            System.out.println("No hay comandos en la pila.");
            return;
        }
        boolean continuar = true;
        while (continuar && !pilaComandos.isEmpty()) {
            try {
                String siguiente = pilaComandos.peek();
                System.out.println("Último comando: " + siguiente);
                String ejecutado = pilaComandos.pop();
                System.out.println("Ejecutando: " + ejecutado);
                System.out.println("");
                System.out.println("Pila ahora: " + pilaComandos.toArrowString());
                if (!pilaComandos.isEmpty()) {
                    continuar = pedirSiNo("¿Ejecutar el siguiente? (si/no): ");
                    System.out.println("");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }
    }

    private static void realizarProcesos() {
        System.out.println("\n** REALIZAR PROCESOS **");
        if (colaProcesos.isEmpty()) {
            System.out.println("No hay procesos en la fila.");
            return;
        }
        boolean continuar = true;
        while (continuar && !colaProcesos.isEmpty()) {
            try {
                String siguiente = colaProcesos.peek();
                System.out.println("Siguiente proceso: " + siguiente);
                String ejecutado = colaProcesos.pop();
                System.out.println("Realizando: " + ejecutado);
                System.out.println("");
                System.out.println("Fila ahora: " + colaProcesos.toArrowString());
                if (!colaProcesos.isEmpty()) {
                    continuar = pedirSiNo("¿Realizar el siguiente? (si/no): ");
                    System.out.println("");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }
    }

    // Utilidades de validación 

    private static int pedirOpcion(int min, int max) {
        while (true) {
            System.out.print("Elige una opción: ");
            String s = sc.nextLine();
            try {
                int val = Integer.parseInt(s.trim());
                if (val >= min && val <= max) return val;
                System.out.println("Valor fuera de rango.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un número.");
            }
        }
    }

    private static boolean pedirSiNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            if (s == null) continue;
            s = s.trim().toLowerCase();
            if (s.isEmpty()) {
                System.out.println("No dejes la respuesta vacía.");
                continue;
            }
            if (s.equals("si") || s.equals("s")) return true;
            if (s.equals("no") || s.equals("n")) return false;
            System.out.println("Responde 'si' o 'no' por favor.");
        }
    }

    private static String pedirNoVacio(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            if (s == null) continue;
            s = s.trim();
            if (!s.isEmpty()) return s;
            System.out.println("El texto no puede estar vacío.");
        }
    }
}
