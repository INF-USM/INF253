package lib.planetas;

import java.io.IOException;
import java.util.Random;

import lib.interfaz.Consola;
import lib.jugable.Jugador;

public class Radioactivo extends Planeta {
    private int radiacion;
    private int uranio;

    /**
     * Constructor del planeta radioactivo
     */
    public Radioactivo() {
        Random rand = new Random();
        int min = (int) Math.pow(10, 4);
        int max = (int) Math.pow(10, 5);
        radio = (int) (rand.nextInt(max - min)) + min;
        cristalesHidrogeno = (int) (0.2 * 4 * Math.PI * Math.pow(radio, 2));
        floresDeSodio = (int) (0.2 * 4 * Math.PI * Math.pow(radio, 2));
        uranio = (int) (0.25 * 4 * Math.PI * Math.pow(radio, 2));
        min = 10;
        max = 50;
        radiacion = (int) (rand.nextInt(max - min)) + min;
        consumoEnergia = (float) (0.3 * radiacion);
    }

    /**
     * Abre el menu de recoleccion del planeta
     * 
     * @param PJ : Objeto jugador
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void recolectar(Jugador PJ) throws IOException, InterruptedException {
        Consola consola = new Consola();
        while (true) {
            consola.menuRecursos(this, PJ, "¿Que recurso quieres extraer?");
            int opcion = consola.get("Cristales de H", "Flores de Na", "Uranio", "Volver");
            consola.limpiar();
            if (opcion == 4)
                return;
            consola.menuRecursos(this, PJ, "Ingresa la cantidad que quieres extraer");
            int cantidad = consola.get(0, PJ.maximoRecolectable(consumoEnergia));
            consola.limpiar();
            if (obtenerRecurso(opcion) < cantidad)
                continue;
            PJ.consumirEnergia(cantidad, consumoEnergia);
            PJ.cargarRecurso(opcion, cantidad);
            extraerMuchosRecursos(opcion, cantidad);
            if (PJ._unidadesEnergiaProteccion() < 0.0001f)
                return;
        }
    };

    /**
     * Abre el menu del planeta
     * 
     * @param PJ : Objeto jugador
     * @return true: el jugador sobrevive | false: el jugador murio
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public boolean visitar(Jugador PJ) throws IOException, InterruptedException {
        Consola consola = new Consola();
        while (true) {
            consola.menuPlaneta(this);
            int opcion = consola.get("Conocer/Recolectar", "Salir");
            consola.limpiar();
            switch (opcion) {
                case 1:
                    recolectar(PJ);
                    break;
                default:
                    salir();
                    return true;
            }
            if (PJ._unidadesEnergiaProteccion() == 0)
                return false;
        }
    }

    /**
     * Getter para el valor de la caracteristica del planeta (Temperatura,
     * Profundidad, Radiacion)
     * 
     * @return valor de la caracteristica del planeta
     */
    @Override
    public Integer _caracteristica() {
        return radiacion;
    }

    /**
     * Getter para la cantidad del mineral especial del planeta (Uranio, Plantino)
     * 
     * @return cantidad del mineral especial, null si no tiene mineral especial
     */
    @Override
    public Integer _mineralEspecial() {
        return uranio;
    }

    /**
     * Getter para el nombre de la caracteristica del planeta (Temperatura,
     * Profundidad, Radiacion)
     * 
     * @return nombre de la caracteristica del planeta
     */
    @Override
    public String nombreCaracteristica() {
        return "Radiacion";
    };

    /**
     * Getter para el nombre del mineral especial del planeta (Uranio, Platino)
     * 
     * @return nombre del mineral especial, null si no tiene mineral especial
     */
    @Override
    public String nombreMineralEspecial() {
        return "Uranio";
    }

    /**
     * Getter para el tag que es un nombre mas corto para el tipo del planeta
     * 
     * @return tag del planeta
     */
    @Override
    public String tag() {
        return " radio ";
    }

    /**
     * Extrae 1 recurso del tipo dado
     * 
     * @param tipo : tipo de recurso
     * @return 0
     */
    @Override
    public int extraerRecursos(int tipo) {
        /*
         * 1 = Cristales de H
         * 2 = Flores de Na
         * 3 = Uranio
         * 4 = Platino
         */
        switch (tipo) {
            case 1:
                cristalesHidrogeno--;
                break;
            case 2:
                floresDeSodio--;
                break;
            default:
                uranio--;
                break;
        }
        return 0;
    }

    /**
     * Getter para obtener un recurso cualquiera (puede ser tambien Uranio y
     * Platino)
     * 
     * @param tipo : tipo de recurso
     * @return cantidad del tipo de recurso en el planeta
     */
    @Override
    public int obtenerRecurso(int tipo) {
        /*
         * 1 = Cristales de H
         * 2 = Flores de Na
         * 3 = Uranio
         * 4 = Platino
         */
        switch (tipo) {
            case 1:
                return cristalesHidrogeno;
            case 2:
                return floresDeSodio;
            default:
                return uranio;
        }
    };

}
