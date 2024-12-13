package lib.interfaz;

import java.util.ArrayList;
import java.util.List;

import lib.planetas.Planeta;

public interface Colores {
    public static String noColor = "\033[0m";
    public static String purple = "\033[0;35m";
    public static String red = "\033[0;31m";
    public static String yellow = "\033[0;33m";
    public static String Bcyan = "\033[1;36m";
    public static String Icyan = "\033[0;96m";
    public static String Ired = "\033[0;91m";
    public static String Iblue = "\033[0;94m";
    public static String Iyellow = "\033[0;93m";
    public static String Iwhite = "\033[0;97m";
    public static String Igreen = "\033[0;92m";
    public static String Iblack = "\033[0;90m";
    public static String IBred = "\033[1;91m";
    public static String IByellow = "\033[1;93m";
    public static String IBwhite = "\033[1;97m";
    public static String IBcyan = "\033[1;96m";
    public static String IBblue = "\033[1;94m";
    public static String IBgreen = "\033[1;92m";
    public static String BGblue = "\033[0;104m";
    public static String BGblack = "\033[0;100m";

    /**
     * Getter para el color de un planeta especifico
     * 
     * @param planeta : planeta especificado
     * @return color del planeta
     */
    public static String obtenerColorPlaneta(Planeta planeta) {
        if (planeta == null)
            return noColor;
        switch (planeta.getClass().getSimpleName()) {
            case "Helado":
                return IBcyan;
            case "Oceanico":
                return IBblue;
            case "Radioactivo":
                return IBgreen;
            case "Volcanico":
                return IBred;
            default:
                return IByellow;
        }
    };

    /**
     * Getter para los colores respectivos de los planetas indicados
     * 
     * @param planetas : lista de planetas indicados
     * @return colores respectivos
     */
    public static List<String> obtenerColorPlanetaL(List<Planeta> planetas) {
        List<String> ret = new ArrayList<>();
        for (Planeta planeta : planetas) {
            ret.addLast(obtenerColorPlaneta(planeta));
        }
        return ret;
    };
}