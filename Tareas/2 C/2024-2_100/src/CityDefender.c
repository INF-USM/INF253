#include "Cartas.h"
#include "Tablero.h"
#include <time.h>

void*** tablero = NULL;
Mano Cartas;
Opcion Seleccion;
int size;
int diff;
int hits = 0;
const char* difs[3] = {                     // TURNOS | 1x2 | 1x3 | 1x4 | 1x5
    "Facil      Tablero 11x11 - 5 Barcos",  //   30   |  2  |  1  |  1  |  1
    "Medio      Tablero 17x17 - 7 Barcos",  //   25   |  3  |  2  |  1  |  1
    "Dificil    Tablero 21x21 - 9 Barcos"   //   15   |  3  |  2  |  2  |  2
};
int sizesBarcos[3][4] = {
    { 2, 1, 1, 1 },
    { 3, 2, 1, 1 },
    { 3, 2, 2, 2 },
};
int totalBarcos[3] = { // Total de espacios cubierto por los barcos
    2 * 2 + 1 * 3 + 1 * 4 + 1 * 5,
    3 * 2 + 2 * 3 + 1 * 4 + 1 * 5,
    3 * 2 + 2 * 3 + 2 * 4 + 2 * 5,
};
int sizes[3] = { 11, 17, 21 };
int turnos[3] = { 30, 25, 15 };
const char* disLinealProm[2] = {
    "Horizontal",
    "Vertical"
};

/*
*   Imprime el mensaje del error por consola y termina la ejecucion del programa
*
*   @param [const_char_*]msg mensaje del error
*   @returns void
*/
void error(const char* msg) {
    printf("\nERROR: %s\n", msg);
    exit(1);
};

/*
*   Obtiene el digito ingresado por consola
*
*   @param [int*]d donde se quiere guardar el digito
*   @param [int]subLim limite inferior para el input
*   @param [int]supLim limite superior para el input
*   @throws si el digito no esta dentro de los limites se termina la ejecucion del programa
*   @returns void
*/
void getInput(int* d, int subLim, int supLim) {
    printf("> ");
    scanf(" %2d", d);
    if (*d < subLim || *d > supLim)
        error("Opcion no valida");
};

/*
*   Muestra por consola las opciones dadas
*
*   @param [const_char_**]options lista de string que representan las opciones
*   @param [int]n largo de la lista de opciones
*   @returns void
*/
void showOpt(const char** options, int n) {
    for (int i = 0; i < n; i++)
        printf("[ %d ] %s\n", i + 1, options[i]);
};

/*
*   Imprime por consola una linea de largo n
*
*   @param [int]n largo de la linea
*   @returns void
*/
void showLine(int n) {
    for (int i = 0; i < n; i++) printf("-");
    printf("\n");
};

/*
*   Muestra por consola un titulo centrado en un marco
*
*   @param [const_char_*]str string que representa el titulo
*   @param [int]n largo del string
*   @returns void
*/
void showTitle(const char* str, int n) {
    showLine(n + 3);
    printf("  %s  \n", str);
    showLine(n + 3);
};

/*
*   Muestra por consola un titulo compuesto de 2 strings centrado en un marco
*
*   @param [const_char_*]str1 string que representa la parte izquierda del titulo
*   @param [const_char_*]str2 string que representa la parte derecha del titulo
*   @param [int]n largo del titulo
*   @returns void
*/
void showTitle2(const char* str1, const char* str2, int n) {
    showLine(n + 4);
    printf("  %s %s  \n", str1, str2);
    showLine(n + 4);
};

/*
*   Muestra por consola el turno y la cantidad de hits en una linea
*
*   @param [int]turno numero del turno
*   @returns void
*/
void showTurno(int turno) {
    showLine(34);
    printf("   Turno [ %d ] Hits [ %2d / %2d ]\n", turno, hits, totalBarcos[diff - 1]);
    showLine(34);
};

/*
*   Libera la memoria alojada en el tablero y el mazo
*
*   @returns void
*/
void limpiar() {
    limpiarTablero();
    limpiarMazo();
};

/*
*   Programa principal
*
*   @param [char_const_*]argv[] lista de argumentos
*   @param [int]argc cantidad de argumentos
*   @returns codigo de salida
*/
int main(int argc, char const* argv[]) {
    // En caso de terminal la ejecucion del programa antes de lo esperado
    atexit(limpiar);
    // Cambio de la semilla de la funcion rand() para real aleatoriedad
    srand(time(NULL));

    // DIFICULTAD
    showTitle("Seleccionar dificultad:", 23);
    showOpt(difs, 3);
    getInput(&diff, 1, 3);
    if (diff > 3) error("Opcion no valida");

    // SETUP
    inicializarMazo();
    inicializarTablero(sizes[diff - 1]);

    for (int turno = 1; turno <= turnos[diff - 1] && hits < totalBarcos[diff - 1]; turno++) {
        // MOSTRAR TURNO
        showTurno(turno);
        mostrarTablero();

        // SELECCION DE CARTA
        showTitle("Seleccionar carta:", 19);
        mostrarMazo();
        getInput(&Seleccion.selec, 1, 5);
        if (Seleccion.selec > 5)
            error("Opcion no valida");

        // AVISO DE CARTA SELECCIONADA
        if (Cartas.carta[Seleccion.selec - 1] == disparoSimple)
            printf("MISIL SIMPLE SELECCIONADO\n");
        else if (Cartas.carta[Seleccion.selec - 1] == disparoGrande)
            printf("MISIL GRANDE SELECCIONADO\n");
        else if (Cartas.carta[Seleccion.selec - 1] == disparoLineal)
            printf("MISIL LINEAL SELECCIONADO\n");
        else if (Cartas.carta[Seleccion.selec - 1] == disparoRadar)
            printf("MISIL RADAR SELECCIONADO\n");
        else if (Cartas.carta[Seleccion.selec - 1] == disparo500KG) {
            printf("GRAN CAÑON SELECCIONADO\n");
            showTitle("       AVISO:\n  Perderas el cañon!!", 20);
        };

        // SELECCION DE COORDENADAS
        showTitle("Seleccionar coordenadas:", 25);
        printf("X -");
        getInput(&Seleccion.x, 1, size);
        printf("Y -");
        getInput(&Seleccion.y, 1, size);
        if (Seleccion.x > sizes[diff - 1] || Seleccion.y > sizes[diff - 1])
            error("Coordenadas fuera del mapa");

        // EJECUCION DEL DISPARO
        usarCarta();
    };

    // REVELACION FINAL
    for (int y = 0; y < size; y++)
        for (int x = 0; x < size; x++)
            revelar(x, y);

    showTitle2(
        "TABLERO FINAL",
        hits == totalBarcos[diff - 1]
        ? "DEFENSA EXITOSA"
        : "DEFENSA INSUFICIENTE",
        hits == totalBarcos[diff - 1]
        ? 14 + 16
        : 14 + 21
    );
    mostrarTablero();

    return 0;
}
