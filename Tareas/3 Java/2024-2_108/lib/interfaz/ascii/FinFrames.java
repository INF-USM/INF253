package lib.interfaz.ascii;

import java.util.Arrays;
import java.util.List;

import lib.interfaz.Colores;
import lib.interfaz.StringTools;

public interface FinFrames {
    String hogarASCII = """
                             *   .                  .              .        .   *
                    o                             .                   .
                     .              .                  .           .
                      0     .
                             .          .                 ,                ,    ,
             .          \\          .      /-/-/-/-/             |
                  .      \\   ,            /-/-/-/-/         /---/---/
               .          o     .             |         .       x         |        .
                 .         \\               /--/--/              x     /---/---/
                           #\\##\\#      .      x       .       --x--       x
                         #  #O##\\###          x       |         x    ,    x
               .        #*#  #\\##\\###         x       |       __x         x       ,
                    .   ##*#  #\\##\\##         x_______|_______||x         x
                  .      ##*#  #o##\\#        /                ||x\\      /   \\       .
                      .     *#  #\\#         /----------------/||x \\   /___ ___\\
                                  \\        | [ ] [ ] ## [  ]| ||x  | ||  | |  ||
            ____^/\\___^--____/\\____O____~  |         ##     | ||x  | ||  | |  ||~ ___
               /\\^   ^  ^    ^          -------------//---------------------------
                     --           -            --  -      -         ---  __       ^
               --  __                      ___--  ^  ^                         --  __
                        """;
    String hogarANSI = String.format("""
            %3$s                 *   .                  .              .        .   *
                    %5$so                             %3$s.                   .
                     %5$s.              %3$s.                  .           .
                      %5$s0     %3$s.
                             .          .                 ,                ,    ,
             .          %5$s\\          %3$s.      %2$s/-/-/-/-/             |
                  .      %5$s\\   %3$s,            %2$s/-/-/-/-/         /---/---/
               .          %5$so     %3$s.             %2$s|         %3$s.       %2$sx         |        %3$s.
                 .         %5$s\\               %2$s/--/--/              x     /---/---/
                           %5$s#\\##\\#      %3$s.      %2$sx       %3$s.       %2$s--x--       x
                         %5$s#  #O##\\###          %2$sx       |         x    %3$s,    %2$sx
               .        %5$s#*#  #\\##\\###         %2$sx       |       __x         x       %3$s,
                    .   %5$s##*#  #\\##\\##         %2$sx_______|_______||x         x
                  .      %5$s##*#  #o##\\#        %2$s/                ||x\\      /   \\       %3$s.
                      .     %5$s*#  #\\#         %2$s/----------------/||x \\   /___ ___\\
                                  %5$s\\        %2$s| [ ] [ ] ## [  ]| ||x  | ||  | |  ||
            %4$s____^/\\___^--____/\\____%5$sO%4$s____~  %2$s|         ##     | ||x  | ||  | |  ||%4$s~ ___
               %4$s/\\^   ^  ^    ^          %2$s-------------//---------------------------
                     %4$s--           -            --  -      -         ---  __       ^
               --  __                      ___--  ^  ^                         --  __%1$s
                        """,
            Colores.noColor,
            Colores.noColor,
            Colores.IBwhite,
            Colores.Iyellow,
            Colores.Icyan);
    List<String> TextsASCII = Arrays.asList(
            StringTools.formatoTextoFullScreen("Despues de recorrer tantos planetas"),
            StringTools.formatoTextoFullScreen("Helados.."),
            StringTools.formatoTextoFullScreen("Oceanicos.."),
            StringTools.formatoTextoFullScreen("Radioactivos.."),
            StringTools.formatoTextoFullScreen("Volcanicos.."),
            StringTools.formatoTextoFullScreen("Y a tantos aliens"),
            StringTools.formatoTextoFullScreen("Por fin he llegado a casa :)"),
            StringTools.formatoTextoFullScreen("Finalmente soy feliz.."));
    List<String> TextsANSI = Arrays.asList(
            StringTools
                    .formatoTextoFullScreen(StringTools.color(Colores.Iwhite, "Despues de recorrer tantos planetas")),
            StringTools.formatoTextoFullScreen(StringTools.color(Colores.Icyan, "Helados..")),
            StringTools.formatoTextoFullScreen(StringTools.color(Colores.Iblue, "Oceanicos..")),
            StringTools.formatoTextoFullScreen(StringTools.color(Colores.Igreen, "Radioactivos..")),
            StringTools.formatoTextoFullScreen(StringTools.color(Colores.Ired, "Volcanicos..")),
            StringTools.formatoTextoFullScreen(StringTools.color(Colores.purple, "Y a tantos aliens")),
            StringTools.formatoTextoFullScreen(StringTools.color(Colores.Iwhite, "Por fin he llegado a casa :)")),
            StringTools.formatoTextoFullScreen(StringTools.color(Colores.Iyellow, "Finalmente soy feliz..")));
}
