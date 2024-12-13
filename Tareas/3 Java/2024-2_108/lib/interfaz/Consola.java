package lib.interfaz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import lib.interfaz.anim.HogarFin;
import lib.interfaz.anim.NaveInicio;
import lib.interfaz.ascii.Asentamientos;
import lib.interfaz.ascii.Barras;
import lib.interfaz.ascii.IntroText;
import lib.interfaz.ascii.Landscapes;
import lib.interfaz.ascii.Monologos;
import lib.interfaz.ascii.NaveFrame;
import lib.interfaz.ascii.PlanetaFrame;
import lib.jugable.Inventario;
import lib.jugable.Jugador;
import lib.jugable.MapaGalactico;
import lib.jugable.Nave;
import lib.planetas.Oferta;
import lib.planetas.Planeta;

/*                           CONSOLA
.-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.





                            MAIN SCREEN
                              73x10




.-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.

                            DATA / STATS
                                73x3
.-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.

                          ACTIONS / OPTIONS

*/

public class Consola {

    private boolean colorMode;
    private Scanner input;

    /**
     * Constructor de consola, asigna el valor del modo color segun el valor de la
     * variable de entorno ANSI
     */
    public Consola() {
        String value = System.getenv("ANSI");
        colorMode = value == null || !value.contains("off");
        input = new Scanner(System.in);
    };

    /**
     * Getter para el modo de color
     * 
     * @return modo de color
     */
    public boolean _colorMode() {
        return colorMode;
    };

    /**
     * Alias para imprimir por pantalla
     * 
     * @param str : string a imprimir
     */
    public void log(String str) {
        System.out.print(str);
    };

    /**
     * Metodo para limpiar la consola que no implica utilizar escapes ANSI
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void limpiarNoANSI() throws IOException, InterruptedException {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    };

    /**
     * Limpia la consola segun el modo de color
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void limpiar() throws IOException, InterruptedException {
        if (colorMode)
            System.out.print("\033[H\033[2J");
        else
            limpiarNoANSI();
        System.out.flush();
    };

    /**
     * Espera cualquier entrada por consola
     * 
     * @throws IOException
     */
    public void esperar() throws IOException {
        log("    [Enter] : ");
        System.in.read();
    };

    /**
     * Obtiene un numero que va desde "low" hasta "max" por consola
     * 
     * @param low : minimo numero aceptado
     * @param max : maximo numero aceptado
     * @return numero obtenido por consola
     */
    public int get(int low, int max) {
        String str = colorMode
                ? "    " + StringTools.color(Colores.BGblack, "[" + low + "-" + max + "]") + " : "
                : "    [" + low + "-" + max + "] : ";
        int res;
        do {
            log(str);
            res = input.nextInt();
        } while (res < low || res > max);

        return res;
    };

    /**
     * Muestra las opciones indicadas por "options" y luego obtiene por consola un
     * numero que este dentro de los rangos de las opciones (empiezan desde 1)
     * 
     * @param options : lista de Strings que representan las opciones posibles
     * @return numero de la opcion selecionada por consola
     */
    public int get(String... options) {
        int i = 0;
        for (String option : options) {
            i++;
            log(String.format("    [ %d ] %s", i, option));
            log("\n");
        }
        return get(1, i);
    };

    /**
     * Imprime la barra normal
     */
    public void barra() {
        log(Barras.getBarra(0, colorMode));
    };

    /**
     * Imprime la barra titulo
     */
    public void barraTitulo() {
        log(Barras.getBarra(1, colorMode));
    };

    /**
     * Imprime la barra almacen
     */
    public void barraAlmacen() {
        log(Barras.getBarra(2, colorMode));

    };

    /**
     * Imprime la barra planeta
     */
    public void barraPlaneta() {
        log(Barras.getBarra(3, colorMode));

    };

    /**
     * Imprime la barra recursos
     */
    public void barraRecursos() {
        log(Barras.getBarra(4, colorMode));

    };

    /**
     * Imprime la barra asentamientos
     */
    public void barraAsentamientos() {
        log(Barras.getBarra(5, colorMode));

    };

    /**
     * Imprime la barra fin
     */
    public void barraFin() {
        log(Barras.getBarra(6, colorMode));

    };

    /**
     * Muestra por consola la cinematica inicial del juego
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void mostrarCinematicaInicio() throws InterruptedException, IOException {
        NaveInicio anim = new NaveInicio();
        do {
            limpiar();
            log("\n");
            log(anim.get(colorMode));
            Thread.sleep(40);
            anim.next();
        } while (anim.hasNext());
        log(IntroText.get(colorMode));
    };

    /**
     * Muestra por consola la cinematica final del juego
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void mostrarCinematicaFinal() throws InterruptedException, IOException {
        HogarFin anim = new HogarFin();
        do {
            limpiar();
            barraTitulo();
            log(anim.get(colorMode));
            barra();
            esperar();
            anim.next();
        } while (anim.hasNext());
    };

    /**
     * Muestra los planetas del alrededor por consola
     */
    public void mostrarAlrededores() {
        for (int i = 0; i < 4; i++) {
            List<String> line = Arrays.asList(
                    PlanetaFrame.asciiFrame.get(i),
                    PlanetaFrame.asciiFrame.get(i),
                    PlanetaFrame.asciiFrame.get(i));
            log("    ");
            log(StringTools.unirConSeparador(22, line));
            log("\n");

        }
    };

    /**
     * Muestra los planetas del alrededor con sus respectivos colores por consola
     * 
     * @param colores : colores respectivos de los planetas
     */
    public void mostrarAlrededoresColor(List<String> colores) {
        int size = colores.size();
        List<List<String>> steps = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            steps.add(new ArrayList<>());
        }

        for (int i = 0; i < size; i++) {
            String col = colores.get(i);
            List<String> planeta = StringTools.unirL(PlanetaFrame.ansiFrame(col));
            for (int j = 0; j < 4; j++) {
                steps.get(j).add(planeta.get(j));
            }
        }

        for (int i = 0; i < 4; i++) {
            log("    ");
            log(StringTools.unirConSeparador(22, steps.get(i)));
            log("\n");
        }
    };

    /**
     * Muestra los tags de los planetas del alrededor en la respectiva posicion de
     * los planetas
     * 
     * @param alrededor : planetas del alrededor
     */
    public void mostrarTag(List<Planeta> alrededor) {
        log("    ");
        for (Planeta planeta : alrededor) {
            if (planeta == null)
                log(PlanetaFrame.defaultTag);
            else
                log(planeta.tag());
            log(" ".repeat(22));
        }
        log("\n");
    };

    /**
     * Muestra la posicion de los planetas del alrededor segun la posicion actual
     * del jugador
     * 
     * @param pos : posicion actual
     */
    public void mostrarIndex(int pos) {
        log("    ");
        for (int i = -1; i < 2; i++) {
            log(String.format(" N°%3s ", i + pos));
            if (i != 2)
                log(" ".repeat(22));
        }
        log("\n");
    };

    /**
     * Muestra por consola los titulos de la seccion de stats
     */
    public void mostrarStatsTitle() {
        String titleNave = colorMode ? StringTools.color(Colores.BGblue, "[Nave]") : "[Nave]";
        String titleTraje = colorMode ? StringTools.color(Colores.BGblue, "[Traje]") : "[Traje]";
        log("    " + titleNave + " ".repeat(26));
        log(" ");
        log("    " + titleTraje);
        log("\n");
    };

    /**
     * Muestra por consola los valores de energia/combustible y eficiencia de la
     * nave y el traje, en la seccion de stats
     * 
     * @param NV : Objeto nave
     * @param PJ : Objeto jugador
     */
    public void mostrarStatsNVPJ(Nave NV, Jugador PJ) {
        String efiNave = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Eficiencia", NV._eficienciaPropulsor())
                : StringTools.formatoClaveValor("Eficiencia", NV._eficienciaPropulsor());
        String efiTraje = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Eficiencia", PJ._eficienciaEnergiaProteccion())
                : StringTools.formatoClaveValor("Eficiencia", PJ._eficienciaEnergiaProteccion());
        String uniNave = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Combustible", NV._unidadesCombustible())
                : StringTools.formatoClaveValor("Combustible", NV._unidadesCombustible());
        String uniTraje = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Energia", PJ._unidadesEnergiaProteccion())
                : StringTools.formatoClaveValor("Energia", PJ._unidadesEnergiaProteccion());
        log("    " + efiNave + "         " + efiTraje + "\n");
        log("    " + uniNave + "         " + uniTraje + "\n");
    };

    /**
     * Muestra la seccion de stats del mapa galactico para la pantalla final del
     * juego
     * 
     * @param MP : Objeto mapa galactico
     */
    public void mostrarStatsMP(MapaGalactico MP) {
        String nPlanetas = colorMode
                ? StringTools.formatoClaveValor(Colores.IBblue, "Planetas descubiertos", MP.planetasDescubiertos())
                : StringTools.formatoClaveValor("Planetas descubiertos", MP.planetasDescubiertos());
        String centroDescubierto = colorMode
                ? StringTools.formatoClaveValor(Colores.IBblue, "Centro descubierto",
                        MP._centroDescubierto() ? 1 : 0)
                : StringTools.formatoClaveValor("Centro descubierto", MP._centroDescubierto() ? 1 : 0);
        log("\n    " + nPlanetas + "         " + centroDescubierto + "\n\n");
    };

    /**
     * Muestra por consola la interfaz del menu de navegacion (menu mapa)
     * 
     * @param MP : Objeto mapa galactico
     * @param NV : Objeto nave
     * @param PJ : Objeto jugador
     */
    public void menuMapa(MapaGalactico MP, Nave NV, Jugador PJ) {
        barraTitulo();
        String nave = colorMode
                ? NaveFrame.ansiStaticFrame
                : NaveFrame.asciiStaticFrame;
        log(nave);
        log("\n");
        List<Planeta> alrededor = MP.alrededores();
        if (colorMode)
            mostrarAlrededoresColor(Colores.obtenerColorPlanetaL(alrededor));
        else
            mostrarAlrededores();
        mostrarTag(alrededor);
        mostrarIndex(MP._posicion());
        barra();
        mostrarStatsTitle();
        mostrarStatsNVPJ(NV, PJ);
        barra();
    };

    /**
     * Muestra el inventario y las stats de la nave y el juegador
     * 
     * @param NV : Objeto nave
     * @param PJ : Objeto jugador
     */
    public void mostrarInventario(Nave NV, Jugador PJ) {
        log("\n");
        String titleMineral = colorMode ? StringTools.color(Colores.BGblue, "[Minerales]") : "[Minerales]";
        log("    " + titleMineral + "\n");
        Inventario inv = PJ._inv();
        String uranio = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Uranio", inv._uranio())
                : StringTools.formatoClaveValor("Uranio", inv._uranio());
        String platino = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Platino", inv._platino())
                : StringTools.formatoClaveValor("Platino", inv._platino());
        log("    " + uranio + "         " + platino + "\n");
        String hidrogeno = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Cristales de H", inv._cristalesHidrogeno())
                : StringTools.formatoClaveValor("Cristales de H", inv._cristalesHidrogeno());
        String sodio = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Flores de Na", inv._floresDeSodio())
                : StringTools.formatoClaveValor("Flores de Na", inv._floresDeSodio());
        log("    " + hidrogeno + "         " + sodio + "\n");
        log("\n\n");
        mostrarStatsTitle();
        mostrarStatsNVPJ(NV, PJ);
        log("\n");
    };

    /**
     * Muestra por consola el monologo dado en el formato especifico
     * 
     * @param mono : monologo
     */
    public void mostrarMonologo(String mono) {
        log("\n");
        String monologo = colorMode
                ? "    [ " + StringTools.color(Colores.Igreen, "Tu") + " ] : "
                        + StringTools.color(Colores.Igreen, mono) + "\n"
                : "    [ Tu ] : " + mono + "\n";
        log(monologo);
        log("\n");
    };

    /**
     * Muestra por consola el menu del almacen (inventario)
     * 
     * @param NV : Objeto nave
     * @param PJ : Objeto jugador
     */
    public void almacen(Nave NV, Jugador PJ) {
        barraAlmacen();
        mostrarInventario(NV, PJ);
        barra();
        mostrarMonologo(Monologos._almacen());
        barra();
    };

    /**
     * Muestra por consola el landscape y un monologo aleatorio segun el planeta
     * 
     * @param planeta : planeta actual
     */
    public void menuPlaneta(Planeta planeta) {
        barraPlaneta();
        log(Landscapes.obtenerLandscapePlaneta(planeta, colorMode));
        barra();
        mostrarMonologo(Monologos.obtenerMonologoPlaneta(planeta));
        barra();
    }

    /**
     * Muestra por consola el landscape y un monologo especifico segun el planeta
     * 
     * @param planeta  : planeta actual
     * @param monologo : monologo especifico
     */
    public void menuPlaneta(Planeta planeta, String monologo) {
        barraPlaneta();
        log(Landscapes.obtenerLandscapePlaneta(planeta, colorMode));
        barra();
        mostrarMonologo(monologo);
        barra();
    }

    /**
     * Muestra por consola las caracteristicas y recursos segun el planeta
     * 
     * @param planeta : planeta indicado
     * @param PJ      : Objeto jugador
     */
    public void mostrarRecursos(Planeta planeta, Jugador PJ) {
        log("\n");
        String titlePlaneta = colorMode ? StringTools.color(Colores.BGblue, "[Planeta]") : "[Planeta]";
        log("    " + titlePlaneta + "\n");
        String radio = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Radio", planeta._radio())
                : StringTools.formatoClaveValor("Radio", planeta._radio());
        String caracName = planeta.nombreCaracteristica();
        Integer caracValue = planeta._caracteristica();
        String carac;
        if (caracValue == null) {
            carac = "";
        } else {
            carac = colorMode
                    ? StringTools.formatoClaveValor(Colores.Iblue, caracName, caracValue)
                    : StringTools.formatoClaveValor(caracName, caracValue);
        }
        log("    " + radio + "         " + carac + "\n");
        log("\n");
        String titleRec = colorMode ? StringTools.color(Colores.BGblue, "[Recursos]") : "[Recursos]";
        log("    " + titleRec + "\n");
        String hidrogeno = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Cristales de H", planeta._cristalesHidrogeno())
                : StringTools.formatoClaveValor("Cristales de H", planeta._cristalesHidrogeno());
        String sodio = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Flores de Na", planeta._floresDeSodio())
                : StringTools.formatoClaveValor("Flores de Na", planeta._floresDeSodio());
        String especialName = planeta.nombreMineralEspecial();
        Integer especialValue = planeta._mineralEspecial();
        log("    " + hidrogeno + "         " + sodio + "\n");
        String especial;
        if (especialValue == null) {
            especial = "";
        } else {
            especial = colorMode
                    ? StringTools.formatoClaveValor(Colores.Iblue, especialName, especialValue)
                    : StringTools.formatoClaveValor(especialName, especialValue);
        }
        log("    " + especial + "\n");
        log("\n");
        String titleExo = colorMode ? StringTools.color(Colores.BGblue, "[Exotraje]") : "[Exotraje]";
        log("    " + titleExo + "\n");
        String uniTraje = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Energia", PJ._unidadesEnergiaProteccion())
                : StringTools.formatoClaveValor("Energia", PJ._unidadesEnergiaProteccion());
        String efiTraje = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Eficiencia", PJ._eficienciaEnergiaProteccion())
                : StringTools.formatoClaveValor("Eficiencia", PJ._eficienciaEnergiaProteccion());
        log("    " + uniTraje + "         " + efiTraje + "\n");
        log("\n");
    };

    /**
     * Muestra por consola el menu de caracteristicas y recursos del planeta dado,
     * ademas tambien un mensaje con formato
     * 
     * @param planeta : planeta indicado
     * @param PJ      : Objeto jugador
     * @param msg     : mensaje con formato
     */
    public void menuRecursos(Planeta planeta, Jugador PJ, String msg) {
        barraRecursos();
        mostrarRecursos(planeta, PJ);
        barra();
        log("\n");
        String msgLog = colorMode
                ? StringTools.color(Colores.Igreen, msg)
                : msg;
        log("    " + msgLog + "\n");
        log("\n");
        barra();
    }

    /**
     * Muestra por consola el menu de asentamientos del planeta dado. (Landscape de
     * asentamiento, Oferta y minerales disponibles)
     * 
     * @param planeta : planeta indicado
     * @param PJ      : Objeto jugador
     * @param oferta  : Oferta del planeta
     */
    public void menuAsentamientos(Planeta planeta, Jugador PJ, Oferta oferta) {
        barraAsentamientos();
        log(Asentamientos.obtenerAsentamientoPlaneta(planeta, colorMode));
        barra();
        String mejoraNave = colorMode
                ? StringTools.formatoClaveValor(Colores.Igreen, "Mejora de Nave",
                        String.format("+%.4f", oferta._mejoraN()))
                : StringTools.formatoClaveValor("Mejora de Nave",
                        String.format("+%.4f", oferta._mejoraN()));
        String mejoraTraje = colorMode
                ? StringTools.formatoClaveValor(Colores.Igreen, "Mejora de Traje",
                        String.format("+%.4f", oferta._mejoraE()))
                : StringTools.formatoClaveValor("Mejora de Traje",
                        String.format("+%.4f", oferta._mejoraE()));
        log("    " + mejoraNave + "         " + mejoraTraje + "\n");
        String uranioNecesario = colorMode
                ? StringTools.formatoClaveValor(Colores.Iyellow, "Uranio necesario", oferta._uranio())
                : StringTools.formatoClaveValor("Uranio necesario", oferta._uranio());
        String platinoNecesario = colorMode
                ? StringTools.formatoClaveValor(Colores.Iyellow, "Platino necesario", oferta._platino())
                : StringTools.formatoClaveValor("Platino necesario", oferta._platino());
        log("    " + uranioNecesario + "         " + platinoNecesario + "\n");
        String uranioDisponible = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Uranio disponible", PJ._inv()._uranio())
                : StringTools.formatoClaveValor("Uranio disponible", PJ._inv()._uranio());
        String platinoDisponible = colorMode
                ? StringTools.formatoClaveValor(Colores.Iblue, "Platino disponible", PJ._inv()._platino())
                : StringTools.formatoClaveValor("Platino disponible", PJ._inv()._platino());
        log("    " + uranioDisponible + "         " + platinoDisponible + "\n");
        barra();
    };
}
