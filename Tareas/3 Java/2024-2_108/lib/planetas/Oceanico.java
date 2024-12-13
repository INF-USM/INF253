package lib.planetas;

import java.io.IOException;
import java.util.Random;

import lib.interfaz.Consola;
import lib.jugable.Jugador;

public class Oceanico extends Planeta implements tieneAsentamientos {
    private int profundidad;
    private boolean habitado;
    private Oferta oferta;

    /**
     * Constructor del planeta oceanico
     */
    public Oceanico() {
        Random rand = new Random();
        int min = (int) Math.pow(10, 4);
        int max = (int) Math.pow(10, 6);
        radio = (int) (rand.nextInt(max - min)) + min;
        cristalesHidrogeno = (int) (0.2 * 4 * Math.PI * Math.pow(radio, 2));
        floresDeSodio = (int) (0.65 * 4 * Math.PI * Math.pow(radio, 2));
        min = 30;
        max = (int) Math.pow(10, 2);
        profundidad = (int) (rand.nextInt(max - min)) + min;
        consumoEnergia = (float) (0.002 * Math.pow(profundidad, 2));
        habitado = rand.nextBoolean();
        oferta = new Oferta();
    }

    /**
     * Abre el menu de asentamientos del planeta, se pregunta por una confirmacion
     * antes de acceder al menu
     * 
     * @param PJ : Objeto jugador
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void visitarAsentamientos(Jugador PJ) throws IOException, InterruptedException {
        float consumo = PJ.calcularEnergiaConsumida(100, consumoEnergia);
        float energia = PJ._unidadesEnergiaProteccion();
        Consola consola = new Consola();
        consola.menuPlaneta(this, String.format("Si lo intento gastare %.4f [energia] de mi exotraje..", consumo));
        consola.log(String.format("    Aun tengo %.4f [energia] en mi exotraje, ¿Estoy seguro?\n", energia));
        int confirmacion = consola.get("Si", "No");
        consola.limpiar();
        if (confirmacion == 2)
            return;
        PJ.consumirEnergia(100, consumoEnergia);
        if (PJ._unidadesEnergiaProteccion() < 0.0001f)
            return;
        if (!habitado) {
            consola.menuPlaneta(this, "Al parecer el planeta esta deshabitado..");
            consola.esperar();
            consola.limpiar();
            return;
        }
        while (true) {
            consola.menuAsentamientos(this, PJ, oferta);
            int opcion = consola.get("Mejorar Nave", "Mejorar Exotraje", "Volver");
            consola.limpiar();
            switch (opcion) {
                case 1:
                    oferta.aplicarOfertaN(PJ);
                    break;
                case 2:
                    oferta.aplicarOfertaE(PJ);
                    break;
                case 3:
                    return;
            }
        }
    };

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
            int opcion = consola.get("Cristales de H", "Flores de Na", "Volver");
            consola.limpiar();
            if (opcion == 3)
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
            int opcion = consola.get("Buscar asentamientos", "Conocer/Recolectar", "Salir");
            consola.limpiar();
            switch (opcion) {
                case 1:
                    visitarAsentamientos(PJ);
                    break;
                case 2:
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
     * Getter para el tag que es un nombre mas corto para el tipo del planeta
     * 
     * @return tag del planeta
     */
    @Override
    public String tag() {
        return " ocean ";
    }

    /**
     * Getter para el valor de la caracteristica del planeta (Temperatura,
     * Profundidad, Radiacion)
     * 
     * @return valor de la caracteristica del planeta
     */
    @Override
    public Integer _caracteristica() {
        return profundidad;
    }

    /**
     * Getter para la cantidad del mineral especial del planeta (Uranio, Plantino)
     * 
     * @return cantidad del mineral especial, null si no tiene mineral especial
     */
    @Override
    public Integer _mineralEspecial() {
        return null;
    }

    /**
     * Getter para el nombre de la caracteristica del planeta (Temperatura,
     * Profundidad, Radiacion)
     * 
     * @return nombre de la caracteristica del planeta
     */
    @Override
    public String nombreCaracteristica() {
        return "Profundidad";
    };

    /**
     * Getter para el nombre del mineral especial del planeta (Uranio, Platino)
     * 
     * @return nombre del mineral especial, null si no tiene mineral especial
     */
    @Override
    public String nombreMineralEspecial() {
        return null;
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
            default:
                floresDeSodio--;
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
            default:
                return floresDeSodio;
        }
    };
}
