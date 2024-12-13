package lib.interfaz.anim;

import java.util.ArrayList;
import java.util.List;

import lib.interfaz.Animacion;
import lib.interfaz.StringTools;
import lib.interfaz.ascii.NaveFrame;

public class NaveInicio extends Animacion implements NaveFrame {

    /**
     * Constructor de la animacion NaveInicio, genera todos los frames de la
     * cinematica incial del juego
     */
    public NaveInicio() {
        frames = 44;
        asciiSet = new ArrayList<>();
        ansiSet = new ArrayList<>();
        int size = asciiFrame.size();
        for (int i = 0; i < frames; i++) {
            List<String> ascii = new ArrayList<>();
            List<List<String>> ansi = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                String modAscii = StringTools.modificarLength(i, asciiFrame.get(j)) + "\n";
                List<String> modAnsi = StringTools.modificarLengthL(i, ansiFrame.get(j));
                modAnsi.addLast("\n");
                if (j == 1 && i > 16) {
                    modAnsi.set(i - 16, (i - 1) % 3 == 0 ? flameANSI : bigFlameANSI);
                }
                if (j == 1 && i > 18) {
                    modAnsi.set(i - 18, (i - 1) % 3 == 0 ? bigFlameANSI : flameANSI);
                }
                ascii.add(modAscii);
                ansi.add(modAnsi);
            }
            asciiSet.add(StringTools.unir(ascii));
            ansiSet.add(StringTools.unirL(ansi));
        }

    };

}
