package lib.jugable;

public class Inventario {
    private int platino;
    private int uranio;
    private int cristalesHidrogeno;
    private int floresDeSodio;

    /**
     * Constructor de inventario
     */
    public Inventario() {
        setup();
    };

    /**
     * Inicializa las variables con sus valores predeterminados
     */
    public void setup() {
        platino = 0;
        uranio = 0;
        cristalesHidrogeno = 0;
        floresDeSodio = 0;
    };

    /**
     * Getter del platino del jugador
     *
     * @return platino guardado en inventario
     */
    public int _platino() {
        return platino;
    };

    /**
     * Setter del platino del jugador
     */
    public void _platino(int p) {
        platino = p;
    };

    /**
     * Getter del uranio del jugador
     *
     * @return uranio guardado en inventario
     */
    public int _uranio() {
        return uranio;
    };

    /**
     * Setter del uranio del jugador
     * 
     * @param u : nuevo uranio
     */
    public void _uranio(int u) {
        uranio = u;
    };

    /**
     * Getter de los cristales de hidrogeno
     * 
     * @return Cristales de hidrogeno guardados en inventario
     */
    public int _cristalesHidrogeno() {
        return cristalesHidrogeno;
    };

    /**
     * Setter de los cristales de hidrogeno
     * 
     * @param h : nuevos cristales de hidrogeno
     */
    public void _cristalesHidrogeno(int h) {
        cristalesHidrogeno = h;
    };

    /**
     * Getter de las flores de sodio
     * 
     * @return Flores de sodio guardados en inventario
     */
    public int _floresDeSodio() {
        return floresDeSodio;
    };

    /**
     * Setter de las flores de sodio
     * 
     * @param Na : nuevas flores de sodio
     */
    public void _floresDeSodio(int Na) {
        floresDeSodio = Na;
    };
}
