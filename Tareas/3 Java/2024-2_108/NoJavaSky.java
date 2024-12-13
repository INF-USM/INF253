import java.io.IOException;

import lib.interfaz.Consola;
import lib.jugable.Jugador;
import lib.jugable.Nave;
import lib.planetas.CentroGalactico;
import lib.planetas.Planeta;
import lib.jugable.MapaGalactico;

public class NoJavaSky {
    private Consola consola;
    private Jugador PJ;
    private Nave NV;
    private MapaGalactico MP;

    /**
     * Punto de entrada
     * 
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        new NoJavaSky().run();
    }

    /**
     * Inicializa los objetos mas importantes
     */
    public void setup() {
        MP = new MapaGalactico();
        NV = new Nave();
        PJ = new Jugador(NV);
    };

    /**
     * Inicia la partida con los valores predeterminados
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void startGame() throws IOException, InterruptedException {
        NV.setup();
        PJ.setup();
        MP.setup();
        mainLoop();
        finalizarJuego();
    };

    /**
     * Inicia la interfaz, anuncia los valores de la configuracion y
     * muestra la cinematica de inicio
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void run() throws IOException, InterruptedException {
        consola = new Consola();
        aviso();
        consola.esperar();
        consola.limpiar();
        setup();
        consola.mostrarCinematicaInicio();
        consola.esperar();
        consola.limpiar();

        startGame();
    };

    /**
     * Anuncia los valores del modo DEBUG y el modo ANSI
     */
    public void aviso() {
        String value_debug = System.getenv("DEBUG");
        boolean debugMode = value_debug != null && value_debug.contains("on");
        String value_ansi = System.getenv("ANSI");
        boolean colorMode = value_ansi == null || !value_ansi.contains("off");
        consola.log(String.format("    Iniciando\n    Debug mode : %b\n    Color mode : %b\n", debugMode, colorMode));
    };

    /**
     * Pantalla final que muestra los valores finales del jugador, la nave y el mapa galactico.
     * Ademas muestra un mensaje dependiendo de que forma se murio o si se gano la partida.
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void finalizarJuego() throws IOException, InterruptedException {
        consola.barraFin();
        consola.mostrarInventario(NV, PJ);
        consola.barra();
        consola.mostrarStatsMP(MP);
        consola.barra();
        consola.log("\n");
        if (!condicionPJ()) {
            consola.log("    Muerte por falta de energia en tu exotraje..");
        } else if (!condicionNV()) {
            consola.log("    Muerte por imposibilidad de conseguir combustible..");
        } else if (MP._centroDescubierto()) {
            consola.log("    Felicidades!, llegaste a tu hogar..");
        } else {
            consola.log("    Has terminado la partida..");
        }
        consola.log("\n\n");

        if (condicionPJ() && condicionNV())
            return;

        consola.log("    ¿Continuar?\n");
        int confirmacion = consola.get("Si", "No");
        if (confirmacion == 2)
            return;

        consola.limpiar();
        startGame();
    };

    /**
     * Verifica si la nave permite seguir jugando
     * 
     * @return true: la nave puede seguir viajando | false: no existe manera de conseguir combustible
     */
    public boolean condicionNV() {
        // Se puede viajar
        boolean cond_salto = NV.maximoSalto() > 0;
        // Existe hidrogeno que puede ser convertido en combustible
        boolean cond_inv = PJ._inv()._cristalesHidrogeno() > 0;
        Planeta planeta = MP.planetaActual();
        // El planeta tiene hidrogeno recolectable
        boolean cond_planeta = planeta._cristalesHidrogeno() > 0;
        // Es posible recolectar el hidrogeno
        boolean cond_recoleccion = PJ.maximoRecolectable(planeta._consumoEnergia()) > 0;

        return cond_salto || cond_inv || (cond_planeta && cond_recoleccion);
    };

    /**
     * Verifica si el jugador cumple con las condiciones para vivir
     * 
     * @return true: La energia es suficiente para sobrevivir | false: El jugador morira por falta de energia
     */
    public boolean condicionPJ() {
        // Energia minima mostrada en consola 0.0001
        return PJ._unidadesEnergiaProteccion() >= 0.0001f;
    };

    /**
     * Ciclo principal del juego
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void mainLoop() throws IOException, InterruptedException {
        while (condicionNV() && condicionPJ()) {
            consola.menuMapa(MP, NV, PJ);
            int opcion = consola.get(
                    "Visitar planeta",
                    "Navegar",
                    "Almacen",
                    "Salir del juego");
            consola.limpiar();
            switch (opcion) {
                case 1:
                    boolean finalizar = visitar();
                    if (finalizar)
                        return;
                    break;
                case 2:
                    navegar();
                    break;
                case 3:
                    almacen();
                    break;
                case 4:
                    return;
            }
        }
    };

    /**
     * Obtiene el planeta en la posicion actual y abre el menu de visita del planeta
     * 
     * @return true: se sobrevive y se visita el centro o se muere y no se alcanza a visitar el centro | false: se sigue jugando
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean visitar() throws IOException, InterruptedException {
        Planeta actual = MP.planetaActual();
        boolean res = actual.visitar(PJ);
        return res && actual instanceof CentroGalactico;
    };

    /**
     * Menu de navegacion
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void navegar() throws IOException, InterruptedException {
        consola.menuMapa(MP, NV, PJ);
        int opcion = consola.get("Izquierda", "Derecha", "Cancelar");
        int dir = 0;
        consola.limpiar();
        switch (opcion) {
            case 1:
                dir = -1;
                break;
            case 2:
                dir = 1;
                break;
            case 3:
                return;
        }
        consola.menuMapa(MP, NV, PJ);
        consola.log("    Ingrese el tamaño del salto\n");
        int tamanoSalto = consola.get(0, NV.maximoSalto());
        NV.viajarPlaneta(MP, dir, tamanoSalto);
        consola.limpiar();
    };

    /**
     * Menu de inventario del jugador
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void almacen() throws IOException, InterruptedException {
        while (true) {
            consola.almacen(NV, PJ);
            int opcion = consola.get(
                    "Convertir H en combustible",
                    "Convertir Na en energia",
                    "Volver");
            consola.limpiar();
            switch (opcion) {
                case 1:
                    consola.almacen(NV, PJ);
                    consola.log("    Ingrese la cantidad a convertir\n");
                    int max_h = PJ._inv()._cristalesHidrogeno();
                    int cantidad_h = consola.get(0, max_h);
                    if (cantidad_h <= max_h) {
                        PJ._inv()._cristalesHidrogeno(max_h - cantidad_h);
                        NV.recargarPropulsores(cantidad_h);
                    }
                    consola.limpiar();
                    break;
                case 2:
                    consola.almacen(NV, PJ);
                    consola.log("    Ingrese la cantidad a convertir\n");
                    int max_Na = PJ._inv()._floresDeSodio();
                    int cantidad_Na = consola.get(0, max_Na);
                    if (cantidad_Na <= max_Na) {
                        PJ._inv()._floresDeSodio(max_Na - cantidad_Na);
                        PJ.recargarEnergiaProteccion(cantidad_Na);
                    }
                    consola.limpiar();
                    break;
                case 3:
                    return;
            }
        }
    };

}