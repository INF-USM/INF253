package lib.interfaz.ascii;

import java.util.Arrays;
import java.util.List;

import lib.interfaz.StringTools;

// Planeta 7x4
public interface PlanetaFrame {
    String defaultTag = "   ?   ";
    List<String> asciiFrame = Arrays.asList(
            " ,-,-. ",
            "/.( +.\\",
            "\\ {. */",
            " `-`-' ");

    /**
     * Getter para el frame ANSI del planeta con un color especifico
     * 
     * @param color : color para el planeta
     * @return frame ANSI del planeta con un color especifico
     */
    static List<List<String>> ansiFrame(String color) {
        return Arrays.asList(
                StringTools.colorL(color, StringTools.toList(" ,-,-. ")),
                StringTools.colorL(color, StringTools.toList("/.( +.\\")),
                StringTools.colorL(color, StringTools.toList("\\ {. */")),
                StringTools.colorL(color, StringTools.toList(" `-`-' ")));
    };
};
