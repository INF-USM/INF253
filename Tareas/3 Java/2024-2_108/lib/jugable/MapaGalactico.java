package lib.jugable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lib.planetas.CentroGalactico;
import lib.planetas.Helado;
import lib.planetas.Oceanico;
import lib.planetas.Planeta;
import lib.planetas.Radioactivo;
import lib.planetas.Volcanico;

public class MapaGalactico {
    private final Random random;
    private List<Planeta> planetas;
    private int posicion;
    private int offset;
    private boolean centroDescubierto;
    private boolean _debug;

    /**
     * Constructor del mapa galactico, si el modo DEBUG esta activo entonces da las
     * condiciones para terminar directamente el juego
     */
    public MapaGalactico() {
        String value = System.getenv("DEBUG");
        _debug = value != null && value.contains("on");

        random = new Random();
        centroDescubierto = false;
        offset = 0;
        planetas = new ArrayList<>();
        planetas.addLast(generadorPlaneta());
        setup();
    };

    /**
     * Devuelve al jugador a la posicion inicial (planeta N° 0)
     */
    public void setup() {
        posicion = 0;
    };

    /**
     * Getter de la posicion en el mapa galactico, al poder moverse en ambas
     * direcciones se le resta la variable offset que representa cuanto se movio el
     * planeta 0 hacia la derecha
     * 
     * @return posicion en el mapa galactico
     */
    public int _posicion() {
        return posicion - offset;
    };

    /**
     * Getter para el planeta al cual se esta orbitando
     * 
     * @return planeta seleccionado (actual)
     */
    public Planeta planetaActual() {
        return planetas.get(posicion);
    };

    /**
     * Calcula si una posicion esta o no generada en el mapa
     * 
     * @param pos : posicion a revisar
     * @return true: la posicion fue generada | false: la posicion sale de los
     *         limites conocidos
     */
    public boolean enMapa(int pos) {
        return 0 <= pos && pos < planetas.size();
    };

    /**
     * Calcula la distancia de la posicion hasta el borde conocido mas cercano,
     * representa la cantidad de planetas que no se conocen hasta la posicion
     * 
     * @param pos : posicion a revisar
     * @return distancia calculada
     */
    public int distanciaAlBorde(int pos) {
        if (pos < 0)
            return Math.abs(pos);
        return pos - (planetas.size() - 1);
    };

    /**
     * Mueve al jugador de posicion y genera los planetas que no se hallan conocido
     * entre la posicion inicial y a la que se quiere viajar
     * 
     * @param salto : largo del salto hacia la derecha (negativo si es hacia la
     *              izquierda)
     */
    public void saltar(int salto) {
        if (!enMapa(posicion + salto)) {
            int dist = distanciaAlBorde(posicion + salto);
            for (int i = 0; i < dist; i++) {
                Planeta nuevo = generadorPlaneta();
                if (salto < 0) {
                    planetas.addFirst(nuevo);
                    posicion++;
                    offset++;
                } else {
                    planetas.addLast(nuevo);
                }
            }
        }
        posicion += salto;
    };

    /**
     * Getter para los 3 planetas que se mostraran en el menu de navegacion
     * 
     * @return una lista con el planeta actual y los 2 de los costados
     */
    public ArrayList<Planeta> alrededores() {
        ArrayList<Planeta> ret = new ArrayList<>();
        if (posicion != 0)
            ret.addLast(planetas.get(posicion - 1));
        else
            ret.addLast(null);
        ret.addLast(planetaActual());
        if (posicion != planetas.size() - 1)
            ret.addLast(planetas.get(posicion + 1));
        else
            ret.addLast(null);
        return ret;
    };

    /**
     * Genera un planeta con las probabilidades especificadas, si el centro fue
     * descubierto entonces esa probabilidad la ocupan los planetas volcanicos
     * 
     * @return Planeta generado
     */
    public final Planeta generadorPlaneta() {
        int res = random.nextInt(99); // numero aleatorio entre [99,0]
        Planeta nuevo;

        if (!centroDescubierto && _debug)
            res = 99;

        if (res < 30)
            nuevo = new Helado();
        else if (res < 60)
            nuevo = new Oceanico();
        else if (res < 80)
            nuevo = new Radioactivo();
        else if (res < 99)
            nuevo = new Volcanico();
        else if (!centroDescubierto)
            nuevo = new CentroGalactico();
        else
            nuevo = new Volcanico();

        if (nuevo instanceof CentroGalactico) {
            centroDescubierto = true;
        }
        return nuevo;
    };

    /**
     * Getter para saber si el centro fue descubierto
     * 
     * @return true: el centro fue generado | false: no se ha generado el centro aun
     */
    public boolean _centroDescubierto() {
        return centroDescubierto;
    };

    /**
     * Getter para la cantidad de planetas generados/descubiertos
     * 
     * @return cantidad de planetas generados/descubiertos
     */
    public int planetasDescubiertos() {
        return planetas.size();
    };

};