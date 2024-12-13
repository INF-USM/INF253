package lib.planetas;

import java.io.IOException;

import lib.interfaz.Consola;
import lib.jugable.Jugador;

public class CentroGalactico extends Planeta {

    /**
     * Constructor del centro galactico
     */
    public CentroGalactico() {
    };

    /**
     * Getter para el tag que es un nombre mas corto para el tipo del planeta
     * 
     * @return tag del planeta
     */
    @Override
    public String tag() {
        return " hogar ";
    }

    /**
     * Para este caso especial si se visita el juego termina
     * 
     * @param PJ : Objeto jugador
     * @return true: el jugador termina el juego | false: el jugador sigue
     *         explorando
     */
    @Override
    public boolean visitar(Jugador PJ) throws IOException, InterruptedException {
        Consola consola = new Consola();
        if (PJ._refNave()._eficienciaPropulsor() < 0.5f) {
            consola.log("    No puedes visitar el centro galactico a menos que tengas:\n    eficiencia >= 50.0000\n");
            consola.esperar();
            consola.limpiar();
            return false;
        }
        consola.log("    Si visitas el centro galactico (tu hogar) terminaras el juego..\n");
        int confirmacion = consola.get("Quedarme en casa", "Seguir explorando");
        consola.limpiar();
        if (confirmacion == 2)
            return false;
        consola.mostrarCinematicaFinal();
        consola.limpiar();
        return true;
    }

    /**
     * El centro galactico no posee caracteristica
     */
    @Override
    public Integer _caracteristica() {
        return null;
    }

    /**
     * El centro galactico no posee mineral especial
     */
    @Override
    public Integer _mineralEspecial() {
        return null;
    }

    /**
     * El centro galactico no posee caracteristica
     */
    @Override
    public String nombreCaracteristica() {
        return null;
    };

    /**
     * El centro galactico no posee mineral especial
     */
    @Override
    public String nombreMineralEspecial() {
        return null;
    }

    /**
     * El centro galactico no posee menu de recoleccion
     */
    @Override
    public void recolectar(Jugador PJ) {
        return;
    }

    /**
     * El centro galactico no recursos
     */
    @Override
    public int extraerRecursos(int tipo) {
        return 0;
    }

    /**
     * El centro galactico no recursos
     */
    @Override
    public int obtenerRecurso(int tipo) {
        return 0;
    };

}