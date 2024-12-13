package lib.jugable;

public class Jugador {
    private Nave refNV;
    private Inventario inv;
    private float unidadesEnergiaProteccion;
    private float eficienciaEnergiaProteccion;

    /**
     * Constructor de jugador
     * 
     * @param NV : Nave para guardar en referencia
     */
    public Jugador(Nave NV) {
        inv = new Inventario();
        refNV = NV;
        eficienciaEnergiaProteccion = 0;
        setup();
    };

    /**
     * Inicializa las variables del inventario y la energia en sus valores
     * predeterminados
     */
    public void setup() {
        inv.setup();
        unidadesEnergiaProteccion = 100;
    };

    /**
     * Getter de la referencia a la nave
     * 
     * @return referencia al objeto nave
     */
    public Nave _refNave() {
        return refNV;
    };

    /**
     * Getter del inventario del jugador
     * 
     * @return objeto inventario del jugador
     */
    public Inventario _inv() {
        return inv;
    };

    /**
     * Getter de las unidades de energia del traje
     * 
     * @return unidades de energia del traje
     */
    public float _unidadesEnergiaProteccion() {
        return unidadesEnergiaProteccion;
    };

    /**
     * Getter del nivel de eficiencia del traje
     * 
     * @return nivel de eficiencia del traje
     */
    public float _eficienciaEnergiaProteccion() {
        return eficienciaEnergiaProteccion;
    };

    /**
     * Calcula cuanta energia se consumiria con los parametros dados
     * 
     * @param n_recursos      : Cantidad de recursos
     * @param consumo_planeta : Factor de consumo del planeta
     * @return energia que se consumiria
     */
    public float calcularEnergiaConsumida(int n_recursos, float consumo_planeta) {
        return (float) (0.5 * n_recursos * (consumo_planeta / 100) * (1 - eficienciaEnergiaProteccion));
    }

    /**
     * Consume energia del traje, si consume mas del total disponible el jugador
     * muere
     * 
     * @param n_recursos      : Cantidad de recursos que se recolectaron
     * @param consumo_planeta : Factor de consumo del planeta
     * @return true: el jugador sigue vivo | false: el jugador murio
     */
    public boolean consumirEnergia(int n_recursos, float consumo_planeta) {
        if (n_recursos == 0)
            return true;
        float consumo = calcularEnergiaConsumida(n_recursos, consumo_planeta);
        if (unidadesEnergiaProteccion < consumo) {
            morir();
            return false;
        }
        unidadesEnergiaProteccion -= consumo;
        return true;
    };

    /**
     * Recarga la energia segun el sodio dado
     * 
     * @param sodio : Cantidad de sodio transformado en energia
     */
    public void recargarEnergiaProteccion(int sodio) {
        unidadesEnergiaProteccion += 0.65 * sodio * (1 + eficienciaEnergiaProteccion);
        if (unidadesEnergiaProteccion > 100) {
            unidadesEnergiaProteccion = 100;
        }
    };

    /**
     * Mejora la eficiencia del traje, hasta un maximo de 1 (100%)
     * 
     * @param ef : % de eficiencia a aumentar
     */
    public void mejorarEficiencia(float ef) {
        if (eficienciaEnergiaProteccion + ef >= 1)
            eficienciaEnergiaProteccion = 1;
        else
            eficienciaEnergiaProteccion += ef;
    };

    /**
     * Calcula la cantidad maxima de recursos que se pueden recolectar
     * 
     * @param consumo_planeta : Factor de consumo del planeta
     * @return cantidad maxima de recursos que se pueden recolectar
     */
    public int maximoRecolectable(float consumo_planeta) {
        return (int) (unidadesEnergiaProteccion / (0.5 * (consumo_planeta / 100) * (1 - eficienciaEnergiaProteccion)));
    }

    /**
     * Aumenta el recurso segun el tipo y la cantidad
     * 
     * @param tipo     : Tipo de recurso
     * @param cantidad : Cantidad a aumentar
     */
    public void cargarRecurso(int tipo, int cantidad) {
        /*
         * 1 = Cristales de H
         * 2 = Flores de Na
         * 3 = Uranio
         * 4 = Platino
         */
        switch (tipo) {
            case 1:
                inv._cristalesHidrogeno(inv._cristalesHidrogeno() + cantidad);
                break;
            case 2:
                inv._floresDeSodio(inv._floresDeSodio() + cantidad);
                break;
            case 3:
                inv._uranio(inv._uranio() + cantidad);
                break;
            case 4:
                inv._platino(inv._platino() + cantidad);
                break;
        }
    };

    /**
     * Representa la muerte del jugador cortando a 0 la energia del traje
     */
    public void morir() {
        unidadesEnergiaProteccion = 0;
    };
}
