package lib.interfaz.ascii;

import lib.interfaz.Colores;
import lib.interfaz.StringTools;

public interface Barras {
        String normal = ".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        String titleASCII = ".-=-=-=-=-=-=-=-=-=-=-=-=-=-{ No Java's Sky }-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        String titleANSI = ".-=-=-=-=-=-=-=-=-=-=-=-=-=-{" + StringTools.color(Colores.IBblue, " No Java's Sky ")
                        + "}-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        String almacenASCII = ".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-{  Almacen  }-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        String almacenANSI = ".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-{  " + StringTools.color(Colores.IBblue, "Almacen")
                        + "  }-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        String planetaASCII = ".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-{  Planeta  }-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        String planetaANSI = ".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-{  " + StringTools.color(Colores.IBblue, "Planeta")
                        + "  }-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        String caracteristicasASCII = ".-=-=-=-=-=-=-=-=-=-=-=-=-{  Caracteristicas  }-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        String caracteristicasANSI = ".-=-=-=-=-=-=-=-=-=-=-=-=-{  "
                        + StringTools.color(Colores.IBblue, "Caracteristicas")
                        + "  }-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        String asentamientosASCII = ".-=-=-=-=-=-=-=-=-=-=-=-=-{  Asentamientos  }-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        String asentamientosANSI = ".-=-=-=-=-=-=-=-=-=-=-=-=-{  " + StringTools.color(Colores.IBblue, "Asentamientos")
                        + "  }-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        String finASCII = ".-=-=-=-=-=-=-=-=-=-=-=-=-{  Fin del Juego  }-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        String finANSI = ".-=-=-=-=-=-=-=-=-=-=-=-=-{  " + StringTools.color(Colores.IBblue, "Fin del Juego")
                        + "  }-=-=-=-=-=-=-=-=-=-=-=-=-.\n";

        /**
         * Getter para las barras segun el tipo dado y el modo de color
         * 
         * @param idx       : tipo de barra
         * @param colorMode : modo de color
         * @return barra indicada
         */
        public static String getBarra(int idx, boolean colorMode) {
                switch (idx) {
                        case 1:
                                return colorMode ? titleANSI : titleASCII;
                        case 2:
                                return colorMode ? almacenANSI : almacenASCII;
                        case 3:
                                return colorMode ? planetaANSI : planetaASCII;
                        case 4:
                                return colorMode ? caracteristicasANSI : caracteristicasASCII;
                        case 5:
                                return colorMode ? asentamientosANSI : asentamientosASCII;
                        case 6:
                                return colorMode ? finANSI : finASCII;
                        default:
                                return normal;
                }
        };

}
