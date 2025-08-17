package listas;

import java.util.Iterator;

/**
 * Autor: Ximena Salazar
 * Fecha: 16/08/2025
 * Descripción: Clase que muestra ejemplos de cómo funcionan
 * las listas con distintos tipos de datos (cadenas, enteros,
 * dobles). Sirve para ver de manera sencilla cómo se ven
 * las estructuras ya armadas.
 */
public class DataTypeExamples {

    private static <T> String join(Iterable<T> it, String arrow) {
        Iterator<T> itr = it.iterator();
        if (!itr.hasNext()) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(itr.next());
        while (itr.hasNext()) sb.append(" ").append(arrow).append(" ").append(itr.next());
        return sb.toString();
    }

    private static String cierre(char punta, char relleno, char fin, int ancho) {
        StringBuilder sb = new StringBuilder().append(punta);
        for (int i = 0; i < Math.max(0, ancho - 2); i++) sb.append(relleno);
        return sb.append(fin).toString();
    }

    public static void ejemplosListaSimple() {

        LinkedList<String> lc = new LinkedList<>(LinkedList.ListKind.SIMPLE);
        lc.insertLast("C"); lc.insertLast("A"); lc.insertLast("B"); lc.insertLast("D");
        System.out.println("Lista simple de cadenas : ");
        System.out.println(join(lc, "->") + " -> null\n");

        LinkedList<Integer> li = new LinkedList<>(LinkedList.ListKind.SIMPLE);
        li.insertLast(3); li.insertLast(1); li.insertLast(2); li.insertLast(4);
        System.out.println("Lista simple de enteros : ");
        System.out.println(join(li, "->") + " -> null\n");

        LinkedList<Double> ld = new LinkedList<>(LinkedList.ListKind.SIMPLE);
        ld.insertLast(33.33); ld.insertLast(11.11); ld.insertLast(22.22); ld.insertLast(44.44);
        System.out.println("Lista simple de dobles: ");
        System.out.println(join(ld, "->") + " -> null");
    }
    public static void ejemplosListaDoble() {
        LinkedList<String> lc = new LinkedList<>(LinkedList.ListKind.DOBLE);
        lc.insertLast("C"); lc.insertLast("A"); lc.insertLast("B"); lc.insertLast("D");
        System.out.println("Lista doble de cadenas (Izq a Der): ");
        System.out.println(join(lc, "->") + "\n");
        System.out.println("Lista doble de cadenas (Der a Izq): ");
        System.out.println(join(lc.descending(), "->") + "\n");

        LinkedList<Integer> li = new LinkedList<>(LinkedList.ListKind.DOBLE);
        li.insertLast(3); li.insertLast(1); li.insertLast(2); li.insertLast(4);
        System.out.println("Lista doble de enteros (Izq a Der): ");
        System.out.println(join(li, "->") + "\n");
        System.out.println("Lista doble de enteros (Der a Izq): ");
        System.out.println(join(li.descending(), "->") + "\n");

        LinkedList<Double> ld = new LinkedList<>(LinkedList.ListKind.DOBLE);
        ld.insertLast(33.33); ld.insertLast(11.11); ld.insertLast(22.22); ld.insertLast(44.44);
        System.out.println("Lista doble de dobles (Izq a Der): ");
        System.out.println(join(ld, "->") + "\n");
        System.out.println("Lista doble de dobles (Der a Izq):");
        System.out.println(join(ld.descending(), "->"));
    }
    public static void ejemplosListaCircular() {
        LinkedList<String> cs1 = new LinkedList<>(LinkedList.ListKind.CIRCULAR);
        cs1.insertLast("C"); cs1.insertLast("A"); cs1.insertLast("B"); cs1.insertLast("D");
        String l1 = join(cs1, "->") + " -+";
        System.out.println("Lista circular simple de cadenas:");
        System.out.println(l1);
        System.out.println(cierre('^','-','+', l1.length()-1) + "\n");

        LinkedList<Integer> cs2 = new LinkedList<>(LinkedList.ListKind.CIRCULAR);
        cs2.insertLast(3); cs2.insertLast(1); cs2.insertLast(2); cs2.insertLast(4);
        l1 = join(cs2, "->") + " -+";
        System.out.println("Lista circular simple de enteros:");
        System.out.println(l1);
        System.out.println(cierre('^','-','+', l1.length()-1) + "\n");

        LinkedList<Double> cs3 = new LinkedList<>(LinkedList.ListKind.CIRCULAR);
        cs3.insertLast(3.3); cs3.insertLast(1.1); cs3.insertLast(2.2); cs3.insertLast(4.4);
        l1 = join(cs3, "->") + " -+";
        System.out.println("Lista circular simple de dobles:");
        System.out.println(l1);
        System.out.println(cierre('^','-','+', l1.length()-1) + "\n");

        LinkedList<String> cdCad = new LinkedList<>(LinkedList.ListKind.DOBLE);
        cdCad.insertLast("C"); cdCad.insertLast("A"); cdCad.insertLast("B"); cdCad.insertLast("D");
        String l = join(cdCad, "<->");
        System.out.println("Lista circular doble de cadenas (Izq a Der):");
        System.out.println(l);
        System.out.println(cierre('^','-','|', l.length()-1) + "\n");

        l = join(cdCad.descending(), "<->");
        System.out.println("Lista circular doble de cadenas (IDer a Izq):");
        System.out.println(l);
        System.out.println(cierre('^','-','|', l.length()-1) + "\n");

        LinkedList<Integer> cdInt = new LinkedList<>(LinkedList.ListKind.DOBLE);
        cdInt.insertLast(3); cdInt.insertLast(1); cdInt.insertLast(2); cdInt.insertLast(4);
        l = join(cdInt, "<->");
        System.out.println("Lista circular doble de enteros (Izq a Der):");
        System.out.println(l);
        System.out.println(cierre('^','-','|', l.length()-1) + "\n");

        l = join(cdInt.descending(), "<->");
        System.out.println("Lista circular doble de enteros (Der a Izq):");
        System.out.println(l);
        System.out.println(cierre('^','-','|', l.length()-1) + "\n");

        LinkedList<Double> cdDob = new LinkedList<>(LinkedList.ListKind.DOBLE);
        cdDob.insertLast(33.33); cdDob.insertLast(11.11); cdDob.insertLast(22.22); cdDob.insertLast(44.44);
        l = join(cdDob, "<->");
        System.out.println("Lista circular doble de dobles (Izq a Der)");
        System.out.println(l);
        System.out.println(cierre('^','-','|', l.length()-1) + "\n");

        l = join(cdDob.descending(), "<->");
        System.out.println("Lista circular doble de dobles (Der a Izq)");
        System.out.println(l);
        System.out.println(cierre('^','-','|', l.length()-1));
    }
}
