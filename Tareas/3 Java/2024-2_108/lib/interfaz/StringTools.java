package lib.interfaz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public interface StringTools {
    public static int max = 73;
    public static int maxClaveValor = 28;
    public static int maxWfull = 73;
    public static int maxHfull = 20;

    /**
     * concatena una lista de strings en un unico string
     * 
     * @param set : lista de strings
     * @return el string unificado
     */
    public static String unir(List<String> set) {
        String str = "";
        for (String el : set) {
            str += el;
        }
        return str;
    };

    /**
     * concatena cada una de las listas de strings dentro de "set"
     * 
     * @param set : lista de "listas de strings a unificar"
     * @return lista de strings unificados
     */
    public static List<String> unirL(List<List<String>> set) {
        List<String> str = new ArrayList<>();
        for (List<String> el : set) {
            str.add(unir(el));
        }
        return str;
    };

    /**
     * Corta el string para que tenga un maximo largo dado
     * 
     * @param str : string a modificar
     * @param n   : maximo larggo para el string
     * @return string truncado
     */
    public static String truncar(String str, int n) {
        return str.length() > n ? str.substring(0, n) : str;
    };

    /**
     * Corta el substring que empieza de la posicion "i" para que tenga largo maximo
     * "n"
     * 
     * @param str : string dado
     * @param i   : indice de donde empieza el substring
     * @param n   : largo maximo para el substring
     * @return substring truncado
     */
    public static String truncar(String str, int i, int n) {
        int size = str.length();
        if (size - i < 0)
            return "";
        return size - i > n ? str.substring(i, i + n) : str.substring(i);
    };

    /**
     * Corta la lista para que tenga largo maximo "n"
     * 
     * @param str : lista de strings
     * @param n   : largo maximo
     * @return lista truncada
     */
    public static List<String> truncarL(List<String> str, int n) {
        return str.size() > n ? str.subList(0, n) : str;
    };

    /**
     * Corta la sublista que empieza de la posicion "i" para que tenga largo maximo
     * "n"
     * 
     * @param str : lista de strings
     * @param i   : indice de donde empieza la sublista
     * @param n   : largo maximo
     * @return sublista truncada
     */
    public static List<String> truncarL(List<String> str, int i, int n) {
        int size = str.size();
        if (size - i < 0)
            return Arrays.asList();
        return size - i > n ? str.subList(i, i + n) : str.subList(i, size);
    };

    /**
     * Modifica el string dado para que tenga largo "n", se agregan " " si falta
     * 
     * @param n   : largo deseado
     * @param str : string a modificar
     * @return string modificado
     */
    public static String modificarLength(int n, String str) {
        int size = str.length();
        if (n <= size)
            return truncar(str.substring(size - n, size), max);
        return truncar(new String(" ").repeat(n - size) + str, max);
    };

    /**
     * Modifica la lista de strings para que tenga largo "n", se agregan " " si
     * falta
     * 
     * @param n   : largo deseado
     * @param str : lista de strings a modificar
     * @return lista de strings modificada
     */
    public static List<String> modificarLengthL(int n, List<String> str) {
        int size = str.size();
        if (n <= size) {
            return truncarL(new ArrayList<String>(str.subList(size - n, size)), max);
        }
        List<String> ret = new ArrayList<>(str);
        for (int i = 0; i < n - size; i++)
            ret.addFirst(" ");
        return truncarL(ret, max);
    };

    /**
     * "Pinta" el string con escapes ANSI al inicio y al final vuelve al color
     * neutro
     * 
     * @param col : color
     * @param str : string a pintar
     * @return string con color
     */
    public static String color(String col, String str) {
        return col + str + Colores.noColor;
    };

    /**
     * "Pinta" cada uno de los strings de la lista con el color dado
     * 
     * @param col : color
     * @param str : lista de strings
     * @return lista de strings con color
     */
    public static List<String> colorL(String col, List<String> str) {
        List<String> ret = new ArrayList<>();
        for (String sub : str) {
            ret.addLast(color(col, sub));
        }
        return ret;
    };

    /**
     * Separa el string en una lista de strings que contienen un caracter del
     * original
     * 
     * @param str : string a separar
     * @return string separado
     */
    public static List<String> toList(String str) {
        List<String> ret = new ArrayList<>();
        int size = str.length();
        for (int i = 0; i < size; i++) {
            ret.addLast(str.substring(i, i + 1));
        }
        return ret;
    };

    /**
     * Une los elementos de la lista de strings con un separador de largo "n" en un
     * unico string unificado
     * 
     * @param n   : largo del separador
     * @param str : lista de strings a unir
     * @return string unificado
     */
    public static String unirConSeparador(int n, List<String> str) {
        String ret = new String(str.get(0));
        for (int i = 1; i < str.size(); i++) {
            ret += " ".repeat(n);
            ret += str.get(i);
        }
        return ret;
    };

    /**
     * Une las listas contenidas en "str" en una unica lista que las separa con el
     * elemento " " n veces
     * 
     * @param n   : largo del separador
     * @param str : lista que contiene las listas a unir
     * @return lista unificada
     */
    public static List<String> unirConSeparadorL(int n, List<List<String>> str) {
        List<String> ret = new ArrayList<String>();
        int size = str.size();
        ret.addAll(str.get(0));
        for (int i = 1; i < size; i++) {
            ret.addAll(StringTools.toList(" ".repeat(n)));
            ret.addAll(str.get(i));
        }
        return ret;
    };

    /**
     * Da un formato donde la clave esta a la izquierda y el valor a la derecha en
     * un string de largo 28, ademas incluye un color para los digitos enteros
     * 
     * @param color : color para los digitos
     * @param clave : nombre de la clave
     * @param valor : digitos del valor
     * @return string en el formato especificado
     */
    public static String formatoClaveValor(String color, String clave, int valor) {
        int size = clave.length();
        Integer delta = 28 - size;
        if (delta < 0)
            return "";
        return clave + String.format("%s%" + delta.toString() + "d%s", color, valor, Colores.noColor);
    };

    /**
     * Da un formato donde la clave esta a la izquierda y el valor a la derecha en
     * un string de largo 28, ademas incluye un color para los digitos float
     * 
     * @param color : color para los digitos
     * @param clave : nombre de la clave
     * @param valor : digitos del valor
     * @return string en el formato especificado
     */
    public static String formatoClaveValor(String color, String clave, float valor) {
        int size = clave.length();
        Integer delta = 28 - size;
        if (delta < 0)
            return "";
        return clave + String.format("%s%" + delta.toString() + ".4f%s", color, valor, Colores.noColor);
    };

    /**
     * Da un formato donde la clave esta a la izquierda y el valor a la derecha en
     * un string de largo 28, ademas incluye un color para el valor
     * 
     * @param color : color para el valor
     * @param clave : nombre de la clave
     * @param valor : valor
     * @return string en el formato especificado
     */
    public static String formatoClaveValor(String color, String clave, String valor) {
        int size = clave.length();
        Integer delta = 28 - size;
        if (delta < 0)
            return "";
        return clave + String.format("%s%" + delta.toString() + "s%s", color, valor, Colores.noColor);
    };

    /**
     * Da un formato donde la clave esta a la izquierda y el valor a la derecha en
     * un string de largo 28, donde el valor es entero
     * 
     * @param clave : nombre de la clave
     * @param valor : digitos del valor
     * @return string en el formato especificado
     */
    public static String formatoClaveValor(String clave, int valor) {
        int size = clave.length();
        Integer delta = 28 - size;
        if (delta < 0)
            return "";
        return clave + String.format("%" + delta.toString() + "d", valor);
    };

    /**
     * Da un formato donde la clave esta a la izquierda y el valor a la derecha en
     * un string de largo 28, donde el valor es float
     * 
     * @param clave : nombre de la clave
     * @param valor : digitos del valor
     * @return string en el formato especificado
     */
    public static String formatoClaveValor(String clave, float valor) {
        int size = clave.length();
        Integer delta = 28 - size;
        if (delta < 0)
            return "";
        return clave + String.format("%" + delta.toString() + "f", valor);
    };

    /**
     * Da un formato donde la clave esta a la izquierda y el valor a la derecha en
     * un string de largo 28
     * 
     * @param clave : nombre de la clave
     * @param valor : valor
     * @return string en el formato especificado
     */
    public static String formatoClaveValor(String clave, String valor) {
        int size = clave.length();
        Integer delta = 28 - size;
        if (delta < 0)
            return "";
        return clave + String.format("%" + delta.toString() + "s", valor);
    };

    /**
     * Devuelve un string de largo "maxWfull" y de alto "maxHfull" que contiene al
     * texto dado en el centro
     * 
     * @param str : texto dado
     * @return string en el formato especificado
     */
    public static String formatoTextoFullScreen(String str) {
        String[] lineas = str.split("\n");
        String ret = "";
        int centerH = (maxHfull - lineas.length) / 2;
        ret += "\n".repeat(centerH);
        for (int i = 0; i < lineas.length; i++) {
            int size = lineas[i].length();
            // Los caracteres de los escapes ANSI tambien cuentan en el largo
            if (lineas[i].contains(Colores.noColor))
                size -= 12;
            int centerW = (maxWfull - size) / 2;
            ret += " ".repeat(centerW) + lineas[i] + "\n";
        }
        ret += "\n".repeat(maxHfull - (centerH + lineas.length));
        return ret;
    };

    /**
     * Obtiene un string aleatorio de la lista de strings dada
     * 
     * @param str : lista de strings
     * @return string aleatorio
     */
    public static String getAleatorio(List<String> str) {
        Random rand = new Random();
        int i = rand.nextInt(str.size());
        return str.get(i);
    };

}
