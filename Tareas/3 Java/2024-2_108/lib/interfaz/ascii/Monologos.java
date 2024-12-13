package lib.interfaz.ascii;

import java.util.Arrays;
import java.util.List;

import lib.interfaz.StringTools;
import lib.planetas.Planeta;

public interface Monologos {
    List<String> almacen = Arrays.asList("Mmmmm..", "No hay cafe en este almacen..", "Nada tiene sentido..");
    List<String> helado = Arrays.asList("Hace frio aqui..", "Nieveeeeeeee!!");
    List<String> oceanico = Arrays.asList("Que bonito el mar..", "Solo agua y nada mas que agua..",
            "Quizas este habitado..");
    List<String> radioactivo = Arrays.asList("Los restos de una ciudad..", "Estoy rodeado por nubes radioactivas..",
            "Parece santiago centro..", "Muy sucio aqui..");
    List<String> volcanico = Arrays.asList("Hace mucho calor aqui..", "¡¡VOLCANES!!", "Todo muy rojo aqui..");

    /**
     * Getter para un monologo aleatorio propio del menu del almacen
     * 
     * @return monologo aleatorio apropiado
     */
    public static String _almacen() {
        return StringTools.getAleatorio(almacen);
    };

    /**
     * Getter para un monologo aleatorio propio del menu del planeta helado
     * 
     * @return monologo aleatorio apropiado
     */
    public static String _helado() {
        return StringTools.getAleatorio(helado);
    };

    /**
     * Getter para un monologo aleatorio propio del menu del planeta oceanico
     * 
     * @return monologo aleatorio apropiado
     */
    public static String _oceanico() {
        return StringTools.getAleatorio(oceanico);
    };

    /**
     * Getter para un monologo aleatorio propio del menu del planeta radioactivo
     * 
     * @return monologo aleatorio apropiado
     */
    public static String _radioactivo() {
        return StringTools.getAleatorio(radioactivo);
    };

    /**
     * Getter para un monologo aleatorio propio del menu del planeta volcanico
     * 
     * @return monologo aleatorio apropiado
     */
    public static String _volcanico() {
        return StringTools.getAleatorio(volcanico);
    };

    /**
     * Getter para obtener un monologo aleatorio segun el planeta
     * 
     * @param planeta : planeta
     * @return monologo aleatorio
     */
    public static String obtenerMonologoPlaneta(Planeta planeta) {
        switch (planeta.getClass().getSimpleName()) {
            case "Helado":
                return _helado();
            case "Oceanico":
                return _oceanico();
            case "Radioactivo":
                return _radioactivo();
            default:
                return _volcanico();
        }
    };
}
