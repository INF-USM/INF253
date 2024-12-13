package lib.interfaz.ascii;

import java.util.Arrays;
import java.util.List;

import lib.interfaz.Colores;
import lib.interfaz.StringTools;

/*
----------------
| _.-~=====~-._|        Nave
|(___/________)|        15x3
|   /  \___/   |
----------------
*/

public interface NaveFrame {
        List<String> asciiFrame = Arrays.asList(
                        " _.-~=====~-._",
                        "(___/________)",
                        "   /  \\___/   ");
        List<List<String>> ansiFrame = Arrays.asList(
                        StringTools.colorL(Colores.Iwhite, StringTools.toList(" _.-~=====~-._")),
                        StringTools.colorL(Colores.Iwhite, StringTools.toList("(___/________)")),
                        StringTools.colorL(Colores.Iwhite, StringTools.toList("   /  \\___/   ")));
        String asciiStaticFrame = """
                                                     _.-~=====~-._
                                                    (___/________)
                                                       /  \\___/
                        """;
        String ansiStaticFrame = StringTools.color(Colores.Iwhite, """
                                                     _.-~=====~-._
                                                    (___/________)
                                                       /  \\___/
                        """);
        String flameASCII = "o";
        String bigFlameASCII = "O";
        String flameANSI = StringTools.color(Colores.Icyan, flameASCII);
        String bigFlameANSI = StringTools.color(Colores.Icyan, bigFlameASCII);
};
