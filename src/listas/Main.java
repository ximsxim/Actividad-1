package listas;

import java.util.Scanner;
import listas.LinkedList.ListKind;

/**
 * Autor: Ximena Salazar
 * Fecha: 16/08/2025
 * Descripción: Clase principal del programa. Aquí aparece el menú
 * donde el usuario puede elegir el tipo de lista, hacer operaciones
 * con contactos o revisar los ejemplos de listas.
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n~~ MENÚ PRINCIPAL ~~");
            System.out.println("1) Lista simple");
            System.out.println("2) Lista doble");
            System.out.println("3) Lista circular");
            System.out.println("4) Ejemplos de lista simple");
            System.out.println("5) Ejemplos de lista doble");
            System.out.println("6) Ejemplos de lista circular");
            System.out.println("7) Salir");
            System.out.print("Elige una opción: ");

            String op = sc.nextLine().trim();
            switch (op) {
                case "1" -> submenu(ListKind.SIMPLE);
                case "2" -> submenu(ListKind.DOBLE);
                case "3" -> submenu(ListKind.CIRCULAR);
                case "4" -> DataTypeExamples.ejemplosListaSimple();
                case "5" -> DataTypeExamples.ejemplosListaDoble();
                case "6" -> DataTypeExamples.ejemplosListaCircular();
                case "7" -> { System.out.println("Saliendo..."); return; }
                default -> System.out.println("Opción inválida.");
            }
        }
    }
    private static void submenu(ListKind tipo) {
        LinkedList<Contactos> lista = new LinkedList<>(tipo);
        while (true) {
            System.out.println("\n~~ LISTA " + tipo + " ~~");
            System.out.println("1) Regresar al menú principal");
            System.out.println("2) Ingresar datos (nombre, dirección, teléfono)");
            System.out.println("3) Insertar al inicio");
            System.out.println("4) Insertar al final");
            System.out.println("5) Buscar (por nombre)");
            System.out.println("6) Borrar (por nombre)");
            System.out.println("7) Actualizar (por nombre)");
            if (tipo == ListKind.DOBLE) {
                System.out.println("8) Imprimir lista (Izq a Der)");
                System.out.println("9) Imprimir lista (Der a Izq)");
            } else if (tipo == ListKind.CIRCULAR) {
                System.out.println("8) Imprimir lista (1 vuelta, diagrama)");
            } else {
                System.out.println("8) Imprimir lista");
            }
            System.out.print("Elige una opción: ");

            String op = sc.nextLine().trim();
            switch (op) {
                case "1" -> { return; }

                case "2" -> {
                    lista.insertLast(capturarContactoSeguro());
                    System.out.println("Contacto agregado al final.");
                }

                case "3" -> {
                    lista.insertFirst(capturarContactoSeguro());
                    System.out.println("Contacto agregado al inicio.");
                }

                case "4" -> {
                    lista.insertLast(capturarContactoSeguro());
                    System.out.println("Contacto agregado al final.");
                }

                case "5" -> {
                    String nombre = pedir("Nombre a buscar: ");
                    Contactos encontrado = lista.find(ct -> ct.getNombre().equalsIgnoreCase(nombre));
                    System.out.println(encontrado != null ? encontrado : "No encontrado.");
                }

                case "6" -> {
                    String nombre = pedir("Nombre a borrar: ");
                    boolean elim = lista.remove(ct -> ct.getNombre().equalsIgnoreCase(nombre));
                    System.out.println(elim ? "Eliminado." : "No encontrado.");
                }

                case "7" -> {
                    String objetivo = pedir("Nombre a actualizar: ");
                    boolean ok = lista.update(
                        ct -> ct.getNombre().equalsIgnoreCase(objetivo),
                        ct -> new Contactos(
                                pedir("Nuevo nombre: "),
                                pedir("Nueva dirección: "),
                                leerEnteroSeguro("Nuevo teléfono: ")
                        )
                    );
                    System.out.println(ok ? "Actualizado." : "No encontrado.");
                }

                case "8" -> {
                    if (tipo == ListKind.CIRCULAR) {
                        lista.printCircularDiagram(); 
                    } else {
                        lista.print();                
                    }
                }

                case "9" -> {
                    if (tipo == ListKind.DOBLE) {
                        lista.printReverse();         
                    } else {
                        System.out.println("Opción inválida.");
                    }
                }

                default -> System.out.println("Opción inválida.");
            }
        }
    }
    private static Contactos capturarContactoSeguro() {
        String nombre = pedir("Nombre: ").trim();
        String direccion = pedir("Dirección: ").trim();
        int telefono = leerEnteroSeguro("Teléfono: ");
        return new Contactos(nombre, direccion, telefono);
    }

    private static int leerEnteroSeguro(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Intenta de nuevo.");
            }
        }
    }

    private static String pedir(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
}
