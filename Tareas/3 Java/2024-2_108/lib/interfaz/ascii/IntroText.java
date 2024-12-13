package lib.interfaz.ascii;

import lib.interfaz.Colores;

public interface IntroText {
    String ascii = """
                No Java's Sky:
                Eres un simple explorador espacial buscando volver a casa.. (el centro de la galaxia)
                Chocaste en una mision y lograste reparar la nave, pero el sistema de navegacion esta
                completamente reiniciado, por lo que ahora todos los planetas son desconicidos.
                En tu camino a casa tendras que recolectar minerales para mantener el combustible de la nave
                y obtener mejoras.
            """;
    String ansi = String.format("""
                %2$sNo Java's Sky:%1$s
                Eres un simple explorador espacial buscando volver a casa.. (%3$sel centro de la galaxia%1$s)
                %4$sChocaste en una mision%1$s y lograste reparar la nave, pero el sistema de navegacion esta
                completamente reiniciado, por lo que ahora todos los %4$splanetas son desconicidos%1$s.
                En tu camino a casa tendras que recolectar minerales para %3$smantener el combustible de la nave
                y obtener mejoras%1$s.
            """,
            Colores.noColor,
            Colores.Bcyan,
            Colores.purple,
            Colores.red);

    /**
     * Getter para el texto de inicio (texto del lore) segun el modo de color
     * 
     * @param colorMode : modo de color
     * @return texto de inicio
     */
    public static String get(boolean colorMode) {
        return colorMode ? ansi : ascii;
    };
}
