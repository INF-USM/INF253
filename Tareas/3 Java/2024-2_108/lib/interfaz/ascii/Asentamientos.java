package lib.interfaz.ascii;

import lib.interfaz.Colores;
import lib.planetas.Planeta;

public interface Asentamientos {
    String heladoASCII = """
            *           *       *           *       *   *    *       *   *   *
                    *               *                   *       *       *       * *
                 o            o          *      *           *
                  \\          /     *               *               *       *
              *    \\        /                           *      *
                    :-'""'-:         _______________
                 .-'  ____  `-.     /               \\ *    *        *       *   *
                ( (  (_()_)  ) )    |  Ta bueno el  |
                 `-.   ^^   .-'    /|      dia      |    *      *       *
                    `._==_.'     _/ \\________________/                     *      *
            .        __)(___    *       *       *  *        *   *               *
                           """;
    String heladoANSI = String.format("""
            %2$s*           *       *           *       *   *    *       *   *   *
                    *               *                   *       *       *       * *
                 o            o          *      *           *
                  %1$s\\          /     %2$s*               *               *       *
              *    %1$s\\        /                           %2$s*      *
                    %1$s:-'""'-:         %3$s_______________
                 %1$s.-'  ____  `-.     %3$s/               \\ %2$s*    *        *       *   *
                %1$s( (  (_%2$s()%1$s_)  ) )    %3$s|  Ta bueno el  |
                 %1$s`-.   ^^   .-'    %3$s/|      dia      |    %2$s*      *       *
                    %1$s`._==_.'     %3$s_/ \\_______________/                     %2$s*      *
            *        %1$s__)(___    %2$s*       *       *  *        *   *               *%3$s
                           """,
            Colores.Iblue,
            Colores.Iwhite,
            Colores.noColor);
    String oceanicoASCII = """
               O        o o          O            O            o
                  o     | |       0             .   o           ,                 0
               .       _L_L_           ____________________      .          0
                    }\\/__-__\\/{        |                  |
                    }(|~o.o~|){      O |    Hoy no hace   |
                    }/ \\`-'/ \\{  o     |     tanta sal    |                       0
                      _/`U'\\_         /|    en el agua    |            O
               0     ( .   . )      _/ |__________________|                 O
                    / /     \\ \\     0         o   O                           o
            o       \\ |  ,  | /     .            .             O            o
            .        \\|=====|/             o                 O               ,
                     """;
    String oceanicoANSI = String.format("""
               %1$sO        %2$so o          %1$sO            O            o
                  o     %2$s| |       %1$s0             .   o           ,                 0
               .       %2$s_L_L_           %3$s____________________      %1$s.          0
                    %2$s}\\/__-__\\/{        %3$s|                  |
                    %2$s}(|~o.o~|){      %1$sO %3$s|    Hoy no hace   |
                    %2$s}/ \\`-'/ \\{  %1$so     %3$s|     tanta sal    |                       %1$s0
                      %2$s_/`U'\\_         %3$s/|    en el agua    |            %1$sO
               0     %2$s( .   . )      %3$s_/ |__________________|                 %1$sO
                    %2$s/ /     \\ \\     %1$s0         o   O                           o
            o       %2$s\\ |  ,  | /     %1$s.            .             O            o
            .        %2$s\\|=====|/             %1$so                 O               ,%3$s
                     """,
            Colores.Iblue,
            Colores.IBgreen,
            Colores.noColor);

    /**
     * Getter para el ASCII/ANSI art de los asentamientos segun el planeta y el modo
     * de color
     * 
     * @param planeta   : Planeta con asentamientos
     * @param colorMode : modo de color
     * @return ASCII/ANSI art de los asentamientos
     */
    public static String obtenerAsentamientoPlaneta(Planeta planeta, boolean colorMode) {
        switch (planeta.getClass().getSimpleName()) {
            case "Helado":
                return colorMode ? heladoANSI : heladoASCII;
            case "Oceanico":
                return colorMode ? oceanicoANSI : oceanicoASCII;
            default:
                return "Sin asentamientos";
        }
    };
}
