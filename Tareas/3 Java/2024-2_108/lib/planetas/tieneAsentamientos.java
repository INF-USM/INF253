package lib.planetas;

import java.io.IOException;

import lib.jugable.Jugador;

public interface tieneAsentamientos {
    /**
     * Abre el menu de asentamientos del planeta, se pregunta por una confirmacion
     * antes de acceder al menu
     * 
     * @param PJ : Objeto jugador
     * @throws IOException
     * @throws InterruptedException
     */
    public void visitarAsentamientos(Jugador PJ) throws IOException, InterruptedException;
}
