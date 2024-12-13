package lib.interfaz.ascii;

import lib.interfaz.Colores;
import lib.planetas.Planeta;

public interface Landscapes {
        String heladoASCII = """
                             .             *              .     +:`|!             . ||||  :.||`
                         +                      .                ..!|*          . | :`||+ |||`
                             .                         +      : |||`        .| :| | | |.| ||`
                               *     +   '               +  :|| |`     :.+. || || | |:`|| `
                                                    .      .||` .    ..|| | |: '` `| | |`  +
                          .       +++                      ||        !|!: `       :| |
                                      +         .      .    | .      `|||.:      .||    .      .
                                  '                           `|.   .  `:|||   + ||'     `
                          __    +      *                         `'       `'|.    `:
                        "'  `---\"\"\"----....____,..^---`^``----.,.___          `.    `.  .    _
                            ___,--'""`---"'   ^  ^ ^        ^       \"\"\"\'---,..___ __,..---\"\"\'
                                                """;
        String heladoANSI = String.format("""
                        %2$s     .             *              .     +%1$s:`|!             %1$s. ||||  :.||`
                         %2$s+                      .                %1$s..!|%2$s*          %1$s. | :`||%2$s+ %1$s|||`
                             %2$s.                         +      %1$s: |||`        .| :| | | |.| ||`
                               %2$s*     +   '               +  %1$s:|| |`     :.%2$s+%1$s. || || | |:`|| `
                                                    %2$s.      %1$s.||` .    ..|| | |: '` `| | |`  %2$s+
                          .       +++                      %1$s||        !|!: `       :| |
                                      %2$s+         .      .    %1$s| .      `|||.:      .||    %2$s.      .
                                  '                           %1$s`|.   .  `:|||   %2$s+ %1$s||'     %2$s`
                          __    +      *                         %1$s`'       `'|.    `:
                        %2$s"'  `---\"\"\"----....____,..^---`^``----.,.___          %1$s`.    `.  .    %2$s_
                            ___,--'""`---"'   ^  ^ ^        ^       \"\"\"\'---,..___ __,..---\"\"\'%3$s
                                                """,
                        Colores.Icyan,
                        Colores.IBwhite,
                        Colores.noColor);
        String oceanicoASCII = """

                                    ^^                   @@@@@@@@@
                               ^^       ^^            @@@@@@@@@@@@@@@
                                                    @@@@@@@@@@@@@@@@@@              ^^
                                                   @@@@@@@@@@@@@@@@@@@@
                        ~~~~~ ~~ ~~~~~ ~~~~~~~~ ~~ &&&&&&&&&&&&&&&&&&&& ~~~~~~~ ~~~~~~~~~~~ ~~~~
                        ~         ~~   ~  ~       ~~~~~~~~~~~~~~~~~~~~ ~       ~~     ~~ ~
                        ~      ~~      ~~ ~~ ~~  ~~~~~~~~~~~~~ ~~~~  ~     ~~~    ~ ~~~  ~ ~~
                        ~  ~~     ~         ~      ~~~~~~  ~~ ~~~       ~~ ~ ~~  ~~ ~
                        ~  ~       ~ ~      ~           ~~ ~~~~~~  ~      ~~  ~             ~~
                        ~             ~        ~      ~      ~~   ~             ~
                               """;
        String oceanicoANSI = String.format("""

                                    %2$s^^                   %7$s@@@@@@@@@
                               %2$s^^       ^^            %7$s@@@@@@@@@@@@@@@
                                                    %3$s@@@@@@@@@@@@@@@@@@              %2$s^^
                                                   %5$s@@@@@@@@@@@@@@@@@@@@
                        %4$s~~~~~ ~~ ~~~~~ ~~~~~~~~ ~~ %6$s&&&&&&&&&&&&&&&&&&&& %4$s~~~~~~~ ~~~~~~~~~~~ ~~~~
                        ~         ~~   ~  ~       %6$s~~~~~~~~~~~~~~~~~~~~%4$s ~       ~~     ~~ ~
                        ~      ~~      ~~ ~~ ~~  %6$s~~~~~~~~~~~~~ ~~~~%4$s  ~     ~~~    ~ ~~~  ~ ~~
                        ~  ~~     ~         ~      %6$s~~~~~~  ~~ ~~~%4$s       ~~ ~ ~~  ~~ ~
                        ~  ~       ~ ~      ~           %6$s~~ ~~~~~~%4$s  ~      ~~  ~             ~~
                        ~             ~        ~      %6$s~%4$s      ~~   ~             ~                    %1$s
                               """,
                        Colores.noColor,
                        Colores.Iwhite,
                        Colores.yellow,
                        Colores.Iblue,
                        Colores.red,
                        Colores.Ired,
                        Colores.IByellow);
        String volcanicoASCII = """
                                                   .          .           .     .
                          .      .      *           .       .          .                       .
                                 ____         .        .               .
                         .   .  /WWWI; \\  .       .    .  ____               .         .     .
                          *    /WWWWII; \\=====;    .     /WI; \\   *    .        /\\_
                          .   /WWWWWII;..      \\_  . ___/WI;:. \\     .        _/M; \\    .   .
                            _/WWWWWIIIIi;..      \\__/WWWIIII:.. \\____ .   .  /MMI:  \\   * .
                          /WWWWWIWIiii;;;.:.. :   ;\\WWWWWIII;;;::     \\___/MMWIIII;   \\
                         /WWWWWIIIIiii;;::.... :   ;|WWWWWWII;;::.:      :;IMWIIIII;:   \\___
                        /WWWWWWWWWIIIIIWIIii;;::;..;\\WWWWWWIII;;;:::...    ;IMIII;;     ::  \\
                        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXX
                                        """;
        String volcanicoANSI = String.format(
                        """
                                                                   %2$s.          .           .     .
                                          .      .      *           .       .          .                       .
                                                 %3$s____         %2$s.        .               .
                                         .   .  %3$s/%4$sWWWI; %3$s\\  %2$s.       .    .  %3$s____               %2$s.         .     .
                                          *    %3$s/%4$sWWWWII; %3$s\\=====;    %2$s.     %3$s/%4$sWI; %3$s\\   %2$s*    .        %3$s/\\_
                                          .   %3$s/%4$sWWWWWII;%5$s..      %3$s\\_  %2$s. %3$s___/%4$sWI;%5$s:. %3$s\\     %2$s.        %3$s_/%4$sM; %3$s\\    %2$s.   .
                                            %3$s_/%4$sWWWWWIIIIi;%5$s..      %3$s\\__/%4$sWWWIIII:%5$s.. %3$s\\____ %2$s.   .  %3$s/%4$sMMI:  %3$s\\   %2$s* .
                                          %3$s/%4$sWWWWWIWIiii;;;%5$s.:.. :   %3$s;\\%4$sWWWWWIII;;;%5$s::     %3$s\\___/%4$sMMWIIII;   %3$s\\
                                         /%4$sWWWWWIIIIiii;;:%5$s:.... :   %3$s;|%4$sWWWWWWII;;%5$s::.:      %3$s:;%4$sIMWIIIII;:   %3$s\\___
                                        /%4$sWWWWWWWWWIIIIIWIIii;;:%5$s:;..%3$s;\\%4$sWWWWWWIII;;%5$s;:::%3$s...    %4$s;IMIII;;     %5$s::  %3$s\\
                                        %3$sxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXX%1$s
                                                        """,
                        Colores.noColor,
                        Colores.Iwhite,
                        Colores.red,
                        Colores.Ired,
                        Colores.IByellow);
        String radioactivoASCII = """

                                                        _._
                                   ___/\\_._/~~\\_..   .-~ | ~-.    ..__/\\__.._._/~\\
                          /~~\\/~\\                    |   |   |                     `-/~\\_
                                           .         |  _:_  |                  ¨
                            ~~^~    ``             .-"~~ | ~~"-.      ¨ ~            ,._^^_.
                                  _.-~:.    +  .   |     |     |        ~~^~      _~    __~.
                                 |    | `.         |     |     |                _.¨, ~^^   }
                           --~:-.|    |  |         |     |     |                |   /      |
                          /   |  ~.   |  |         |  __.:.__  |      .~~^~     |   |      | ~~^
                        ~^    |   |   |  |       .-"~~   |   ~~"-.              |   |      |
                                                          """;
        String radioactivoANSI = String.format(
                        """
                                        %2$s                                                                         %3$s
                                        %2$s                                 %1$s_._%2$s                                     %3$s
                                        %2$s            ___/\\_._/~~\\_..   %1$s.-~ | ~-.%2$s    ..__/\\__.._._/~\\              %3$s
                                        %2$s   /~~\\/~\\                    %1$s|   |   |%2$s                     `-/~\\_       %3$s
                                        %2$s                    .         %1$s|  _:_  |%2$s                  ¨               %3$s
                                        %2$s     ~~^~    ``             %1$s.-"~~ | ~~"-.%2$s      ¨ ~            %1$s,._^^_.%2$s    %3$s
                                        %2$s           %1$s_.-~:.%2$s    +  .   %1$s|     |     |%2$s        ~~^~      %1$s_~    __~.%2$s    %3$s
                                        %2$s          %1$s|    | `.%2$s         %1$s|     |     |%2$s                %1$s_.¨, ~^^   }%2$s    %3$s
                                        %2$s    %1$s--~:-.|    |  |%2$s         %1$s|     |     |%2$s                %1$s|   /      |%2$s    %3$s
                                        %2$s   %1$s/   |  ~.   |  |%2$s         %1$s|  __.:.__  |%2$s      .~~^~     %1$s|   |      |%2$s ~~^%3$s
                                        %2$s %1$s~^    |   |   |  |%2$s       %1$s.-"~~   |   ~~"-.%2$s              %1$s|   |      |%2$s    %3$s
                                                                           """,
                        Colores.purple,
                        Colores.IBgreen,
                        Colores.noColor);

        /**
         * Getter para el landscape segun el planeta y el modo de color
         * 
         * @param planeta   : planeta
         * @param colorMode : modo de color
         * @return landscape
         */
        public static String obtenerLandscapePlaneta(Planeta planeta, boolean colorMode) {
                switch (planeta.getClass().getSimpleName()) {
                        case "Helado":
                                return colorMode ? heladoANSI : heladoASCII;
                        case "Oceanico":
                                return colorMode ? oceanicoANSI : oceanicoASCII;
                        case "Radioactivo":
                                return colorMode ? radioactivoANSI : radioactivoASCII;
                        case "Volcanico":
                                return colorMode ? volcanicoANSI : volcanicoASCII;
                        default:
                                return "";
                }
        };
}
