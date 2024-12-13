package lib.jugable;

public class Nave {
    private float unidadesCombustible;
    private float eficienciaPropulsor;

    /**
     * Constructor de nave, si el modo DEBUG esta activo entonces da las
     * condiciones para terminar directamente el juego
     */
    public Nave() {
        String value = System.getenv("DEBUG");
        boolean _debug = value != null && value.contains("on");
        eficienciaPropulsor = _debug ? 0.5f : 0;
        setup();
    };

    /**
     * Inicializa el combustible en su valor predeterminado
     */
    public void setup() {
        unidadesCombustible = 100;
    };

    /**
     * Getter para las unidades de combustible
     * 
     * @return unidades de combustible
     */
    public float _unidadesCombustible() {
        return unidadesCombustible;
    };

    /**
     * Getter para la eficiencia de la nave
     * 
     * @return eficiencia de propulsor
     */
    public float _eficienciaPropulsor() {
        return eficienciaPropulsor;
    };

    /**
     * Mejora la eficiencia de propulsor, hasta un maximo de 1 (100%)
     * 
     * @param ef : eficiencia a aumentar
     */
    public void mejorarEficiencia(float ef) {
        if (eficienciaPropulsor + ef >= 1)
            eficienciaPropulsor = 1;
        else
            eficienciaPropulsor += ef;
    };

    /**
     * Recarga el combustible segun el hidrogeno dado, hasta un maximo de 100
     * 
     * @param hidrogeno : hidrogeno a convertir en combustible
     */
    public void recargarPropulsores(int hidrogeno) {
        unidadesCombustible += 0.6 * hidrogeno * (1 + eficienciaPropulsor);
        if (unidadesCombustible > 100) {
            unidadesCombustible = 100;
        }
    };

    /**
     * Calcula el costo del salto con largo dado
     * 
     * @param largo : largo del salto
     * @return el costo del salto
     */
    public float costoSalto(int largo) {
        return (float) (0.75 * Math.pow(largo, 2) * (1 - eficienciaPropulsor));
    };

    /**
     * Consume el costo del salto y luego viaja a la posicion con
     * MapaGalactico.saltar( salto )
     * 
     * @see MapaGalactico.saltar
     * @param MG          : Mapa galactico
     * @param direccion   : Direccion del salto
     * @param tamanoSalto : Tamaño del salto
     * @return true: se logra viajar al planeta | false: no se logra viajar y se
     *         muere
     */
    public boolean viajarPlaneta(MapaGalactico MG, int direccion, int tamanoSalto) {
        if (tamanoSalto == 0)
            return true;
        float costo = costoSalto(tamanoSalto);
        if (unidadesCombustible < costo)
            return false;
        MG.saltar(direccion * tamanoSalto);
        unidadesCombustible -= costo;
        return true;
    };

    /**
     * Calcula el maximo salto posible, si se tiene eficiencia maxima el tamaño
     * maximo de salto es el valor maximo que puede tomar "int"
     * 
     * @return el tamaño del salto mas grande que se puede realizar
     */
    public int maximoSalto() {
        if (eficienciaPropulsor >= 1)
            return Integer.MAX_VALUE;
        return (int) Math.sqrt(unidadesCombustible / (0.75 * (1 - eficienciaPropulsor)));
    };

    /**
     * Similar a Jugador.morir() representa una falla en la nave cortando a 0 el
     * combustible
     * 
     * @see Jugador.morir
     */
    public void fallar() {
        unidadesCombustible = 0;
    };
}
