package lib.planetas;

import java.io.IOException;

import lib.jugable.Jugador;

public abstract class Planeta {
    protected int radio;
    protected int cristalesHidrogeno;
    protected int floresDeSodio;
    protected float consumoEnergia;

    /**
     * Getter del radio del planeta
     * 
     * @return radio del planeta
     */
    public int _radio() {
        return radio;
    };

    /**
     * Getter de los cristales de hidrogeno del planeta
     * 
     * @return cristales de hidrogeno del planeta
     */
    public int _cristalesHidrogeno() {
        return cristalesHidrogeno;
    };

    /**
     * Getter de las flores de sodio del planeta
     * 
     * @return flores de sodio del planeta
     */
    public int _floresDeSodio() {
        return floresDeSodio;
    };

    /**
     * Getter del factor de consumo de energia del planeta
     * 
     * @return factor de consumo de energia del planeta
     */
    public float _consumoEnergia() {
        return consumoEnergia;
    };

    /**
     * Saca al jugador del planeta, siempre se puede salir del planeta..
     *
     * @return true: pudo salir del planeta
     */
    public boolean salir() {
        return true;
    }

    /**
     * Amplia Planeta.extraerRecursos(tipo) para extraer varios
     * 
     * @see Planeta.extraerRecursos
     * @param tipo     : tipo de recurso
     * @param cantidad : cantidad de recursos a extraer
     */
    public void extraerMuchosRecursos(int tipo, int cantidad) {
        for (int i = 0; i < cantidad; i++)
            extraerRecursos(tipo);
    };

    /**
     * Getter para obtener un recurso cualquiera (puede ser tambien Uranio y
     * Platino)
     * 
     * @param tipo : tipo de recurso
     * @return cantidad del tipo de recurso en el planeta
     */
    public abstract int obtenerRecurso(int tipo);

    /**
     * Extrae 1 recurso del tipo dado
     * 
     * @param tipo : tipo de recurso
     * @return 0
     */
    public abstract int extraerRecursos(int tipo);

    /**
     * Abre el menu de recoleccion del planeta
     * 
     * @param PJ : Objeto jugador
     * @throws IOException
     * @throws InterruptedException
     */
    public abstract void recolectar(Jugador PJ) throws IOException, InterruptedException;

    /**
     * Abre el menu del planeta
     * 
     * @param PJ : Objeto jugador
     * @return true: el jugador sobrevive | false: el jugador murio
     * @throws IOException
     * @throws InterruptedException
     */
    public abstract boolean visitar(Jugador PJ) throws IOException, InterruptedException;

    /**
     * Getter para el nombre de la caracteristica del planeta (Temperatura,
     * Profundidad, Radiacion)
     * 
     * @return nombre de la caracteristica del planeta
     */
    public abstract String nombreCaracteristica();

    /**
     * Getter para el valor de la caracteristica del planeta (Temperatura,
     * Profundidad, Radiacion)
     * 
     * @return valor de la caracteristica del planeta
     */
    public abstract Integer _caracteristica();

    /**
     * Getter para el nombre del mineral especial del planeta (Uranio, Platino)
     * 
     * @return nombre del mineral especial, null si no tiene mineral especial
     */
    public abstract String nombreMineralEspecial();

    /**
     * Getter para la cantidad del mineral especial del planeta (Uranio, Plantino)
     * 
     * @return cantidad del mineral especial, null si no tiene mineral especial
     */
    public abstract Integer _mineralEspecial();

    /**
     * Getter para el tag que es un nombre mas corto para el tipo del planeta
     * 
     * @return tag del planeta
     */
    public abstract String tag();
}
