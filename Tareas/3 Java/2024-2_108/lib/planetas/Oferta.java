package lib.planetas;

import java.util.Random;

import lib.jugable.Inventario;
import lib.jugable.Jugador;

public class Oferta {
    private int uranio;
    private int platino;
    private float mejoraN;
    private float mejoraE;

    /**
     * Constructor de oferta, crea una oferta para la nave y una para el traje
     */
    public Oferta() {
        Random rand = new Random();
        float factorP = rand.nextFloat(0.9f) + 0.1f;
        float factorU = rand.nextFloat(0.9f) + 0.1f;
        uranio = (int) (factorU * 500);
        platino = (int) (factorP * 700);
        mejoraN = factorU / 100;
        mejoraE = factorP / 100;
    };

    /**
     * Getter para la cantidad de uranio que se pide para la mejora
     * 
     * @return cantidad de uranio que se pide para la mejora
     */
    public int _uranio() {
        return uranio;
    };

    /**
     * Getter para la cantidad de platino que se pide para la mejora
     * 
     * @return cantidad de platino que se pide para la mejora
     */
    public int _platino() {
        return platino;
    };

    /**
     * Getter para la cantidad de eficiencia que se mejora de la nave
     * 
     * @return eficiencia a mejorar en la nave
     */
    public float _mejoraN() {
        return mejoraN;
    };

    /**
     * Getter para la cantidad de eficiencia que se mejora del traje
     * 
     * @return eficiencia a mejorar en el traje
     */
    public float _mejoraE() {
        return mejoraE;
    };

    /**
     * Cobra la oferta al inventario del jugador y realiza las mejoras a la nave,
     * solo si se tienen los recursos pedidos
     * 
     * @param PJ : Objeto jugador
     */
    public void aplicarOfertaN(Jugador PJ) {
        Inventario inv = PJ._inv();
        int uranioPJ = inv._uranio();
        if (uranioPJ < uranio)
            return;
        inv._uranio(uranioPJ - uranio);
        PJ._refNave().mejorarEficiencia(mejoraN);
    };

    /**
     * Cobra la oferta al inventario del jugador y realiza las mejoras al traje,
     * solo si se tienen los recursos pedidos
     * 
     * @param PJ : Objeto jugador
     */
    public void aplicarOfertaE(Jugador PJ) {
        Inventario inv = PJ._inv();
        int platinoPJ = inv._platino();
        if (platinoPJ < platino)
            return;
        inv._platino(platinoPJ - platino);
        PJ.mejorarEficiencia(mejoraE);
    };

}
