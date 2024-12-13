package lib.interfaz;

import java.util.List;

public abstract class Animacion {
    protected int frames;
    protected int it;
    protected List<String> asciiSet;
    protected List<List<String>> ansiSet;

    /**
     * Constructor de animacion, inicia el iterador en 0
     */
    public Animacion() {
        it = 0;
    };

    /**
     * Revisa si el iterador tiene un frame siguiente
     * 
     * @return true: existe otro frame despues del actual | false: se esta en el
     *         ultimo frame
     */
    public boolean hasNext() {
        return it < frames - 1;
    };

    /**
     * Avanza el iterador a la siguiente posicion
     */
    public void next() {
        it++;
    };

    /**
     * Getter para el frame actual segun el modo de color
     * 
     * @param colorMode : modo de color
     * @return frame actual
     */
    public String get(boolean colorMode) {
        if (frames == 0)
            return "";
        return colorMode ? StringTools.unir(ansiSet.get(it)) : asciiSet.get(it);
    };

}
