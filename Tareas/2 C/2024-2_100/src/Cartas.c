#include "Cartas.h"

/*
*   Inicializa el mazo con 5 disparos simples alojando memoria
*
*   @returns void
*/
void inicializarMazo() {
    Cartas.carta = (void**)malloc(sizeof(void*) * 5);
    for (int i = 0; i < 5; i++) {
        Cartas.carta[i] = (void*)&disparoSimple;
    };
    Cartas.disponibles = 5;
};

/*
*   Libera la memoria alojada en el mazo
*
*   @returns void
*/
void limpiarMazo() {
    if (Cartas.carta == NULL) return;
    free(Cartas.carta);
};

/*
*   Muestra en consola las cartas disponibles en el mazo
*
*   @returns void
*/
void mostrarMazo() {
    const char** cartas = (const char**)malloc(sizeof(const char*) * Cartas.disponibles);

    for (int i = 0; i < Cartas.disponibles; i++) {
        if (Cartas.carta[i] == disparoSimple) cartas[i] = "Simple";
        else if (Cartas.carta[i] == disparoGrande) cartas[i] = "Grande";
        else if (Cartas.carta[i] == disparoLineal) cartas[i] = "Lineal";
        else if (Cartas.carta[i] == disparoRadar) cartas[i] = "Radar";
        else if (Cartas.carta[i] == disparo500KG) cartas[i] = "500KG";
    };

    showOpt(cartas, Cartas.disponibles);
    free(cartas);
};

/*
*   Selecciona la carta y ejecuta su correspondiente funcion
*
*   @returns void
*/
void usarCarta() {
    void* (*carta)(int, int) = (void* (*)(int, int)) Cartas.carta[Seleccion.selec - 1];
    void* siguiente = carta(Seleccion.x - 1, Seleccion.y - 1);
    if (carta != disparo500KG)
        Cartas.carta[Seleccion.selec - 1] = siguiente;
};

/*
*   Cambia el valor en el tablero en la posicion [y][x] segun el valor
*   anterior:
*   barco/hint -> hit   |
*   agua -> miss
*
*   @param [int]x posicion en el eje X del disparo
*   @param [int]y posicion en el eje Y del disparo
*   @returns [ int ] 0 o 1, indica si el disparo fue efectivo
*/
int disparar(int x, int y) {
    int* val = (int*)tablero[y][x];
    if (*val == Agua) *val = Miss;
    else if (*val <= Barco) {
        *val = Hit;
        hits++;
        return 1;
    };
    return 0;
};

/*
*   Revela como una pista el valor en la posicion [y][x] en el tablero,
*   cambiendo su valor dependiendo del anterior:
*   barco -> hint   |
*   agua -> miss
*
*   @param [int]x posicion en el eje X de la pista
*   @param [int]y posicion en el eje Y de la pista
*   @returns void
*/
void revelarHint(int x, int y) {
    int* val = (int*)tablero[y][x];
    if (*val == Agua) *val = Miss;
    else if (*val == Barco) *val = Hint;
};

/*
*   Revela si hay un barco en esa posicion
*
*   @param [int]x posicion en el eje X de la revelacion final
*   @param [int]y posicion ene el eje Y de la revelacion final
*   @returns void
*/
void revelar(int x, int y) {
    int* val = (int*)tablero[y][x];
    if (*val == Barco) *val = Hint;
};

/*
*   Se elige una de las opciones de forma parcialmente aleatoria (aleatoria con probabilidades desiguales)
*
*   @param [int]pi probabilidad de la opcion i
*   @param [int]fi funcion correspondiente a la opcion i
*   @return [ void* ] una de las funciones elegidas parcial-aleatoriamente
*/
void* elegir4(int p1, int p2, int p3, void* f1, void* f2, void* f3, void* f4) {
    int r = rand() % 100;
    if (r < p1) return f1;
    if (r < p2) return f2;
    if (r < p3) return f3;
    return f4;
};

/*
*   Se elige una de las opciones de forma parcialmente aleatoria (aleatoria con probabilidades desiguales),
*   en el caso de tener menos de 5 cartas disponibles, entonces la probabilidad de la ultima opcion se suma a
*   la probabilidad de la penultima opcion.
*
*   @param [int]pi probabilidad de la opcion i
*   @param [int]fi funcion correspondiente a la opcion i
*   @return [ void* ] una de las funciones elegidas parcial-aleatoriamente
*/
void* elegir5(int p1, int p2, int p3, int p4, void* f1, void* f2, void* f3, void* f4, void* f5) {
    int r = rand() % 100;
    if (Cartas.disponibles != 5)
        return elegir4(p1, p2, p3, f1, f2, f3, f4);
    if (r < p1) return f1;
    if (r < p2) return f2;
    if (r < p3) return f3;
    if (r < p4) return f4;
    return f5;
};

/*
*   Ejecuta unicamente 1 disparo en la posicion [y][x] del tablero
*
*   @param [int]x posicion en el eje X del disparo
*   @param [int]y posicion en el eje Y del disparo
*   @returns [ void* ] algun otro tipo de disparo elejido de forma parcialmente aleatoria
*/
void* disparoSimple(int x, int y) {
    if (disparar(x, y)) printf("HIT!\n");
    else printf("MISS\n");
    return elegir4(
        65, 85, 90,
        (void*)&disparoSimple,
        (void*)&disparoGrande,
        (void*)&disparoLineal,
        (void*)&disparoRadar
    );
};

/*
*   Ejecuta 9 disparos correspondientes al area que afecta el disparo con centro
*   en la posicion [y][x] del tablero
*
*   @param [int]x posicion en el eje X del disparo
*   @param [int]y posicion en el eje Y del disparo
*   @returns [ void* ] algun otro tipo de disparo elejido de forma parcialmente aleatoria
*/
void* disparoGrande(int x, int y) {
    int hit = 0;

    for (int dy = -1; dy <= 1; dy++)
        if (!(y + dy < 0 || y + dy >= size))
            for (int dx = -1; dx <= 1; dx++)
                if (!(x + dx < 0 || x + dx >= size))
                    if (disparar(x + dx, y + dy)) {
                        printf("HIT!\n");
                        hit = 1;
                    };

    if (!hit) printf("MISS\n");

    return elegir5(
        80, 83, 93, 98,
        (void*)&disparoSimple,
        (void*)&disparoGrande,
        (void*)&disparoLineal,
        (void*)&disparoRadar,
        (void*)&disparo500KG
    );
};

/*
*   Pregunta por consola el tipo de disparo lineal que se quiere ejecutar y luego
*   ejecuta 5 disparos correspondientes a la linea donde afecta el disparo con centro
*   en la posicion [y][x] del tablero
*
*   @param [int]x posicion en el eje X del disparo
*   @param [int]y posicion en el eje Y del disparo
*   @returns [ void* ] algun otro tipo de disparo elejido de forma parcialmente aleatoria
*/
void* disparoLineal(int x, int y) {
    int vert;
    showTitle("Elige una direccion:", 21);
    showOpt(disLinealProm, 2);
    getInput(&vert, 1, 2);
    vert -= 1;

    int hit = 0;

    for (int dx = vert ? 0 : -2, dy = vert ? -2 : 0; dx <= 2 && dy <= 2; dy += vert, dx += !vert)
        if (!(x + dx < 0 || x + dx >= size || y + dy < 0 || y + dy >= size))
            if (disparar(x + dx, y + dy)) {
                printf("HIT!\n");
                hit = 1;
            };

    if (!hit) printf("MISS\n");

    return elegir5(
        85, 90, 92, 98,
        (void*)&disparoSimple,
        (void*)&disparoGrande,
        (void*)&disparoLineal,
        (void*)&disparoRadar,
        (void*)&disparo500KG
    );
};

/*
*   Ejecuta 25 revelaciones (pistas) correspondientes al area que afecta el disparo con centro
*   en la posicion [y][x] del tablero
*
*   @param [int]x posicion en el eje X del disparo
*   @param [int]y posicion en el eje Y del disparo
*   @returns [ void* ] algun otro tipo de disparo elejido de forma parcialmente aleatoria
*/
void* disparoRadar(int x, int y) {
    for (int dy = -2; dy <= 2; dy++)
        if (!(y + dy < 0 || y + dy >= size))
            for (int dx = -2; dx <= 2; dx++)
                if (!(x + dx < 0 || x + dx >= size))
                    revelarHint(x + dx, y + dy);

    return elegir5(
        75, 90, 95, 97,
        (void*)&disparoSimple,
        (void*)&disparoGrande,
        (void*)&disparoLineal,
        (void*)&disparoRadar,
        (void*)&disparo500KG
    );
};

/*
*   Ejecuta 121 disparos correspondientes al area que afecta el disparo con centro
*   en la posicion [y][x] del tablero
*
*   @param [int]x posicion en el eje X del disparo
*   @param [int]y posicion en el eje Y del disparo
*   @returns [ void* ] NULL, ningun disparo
*/
void* disparo500KG(int x, int y) {
    int hit = 0;

    for (int dy = -10; dy <= 10; dy++)
        if (!(y + dy < 0 || y + dy >= size))
            for (int dx = -10; dx <= 10; dx++)
                if (!(x + dx < 0 || x + dx >= size))
                    if (disparar(x + dx, y + dy))
                        hit = 1;

    if (hit) printf("MEGA ULTRA NUKE HIT!!\n");
    else printf("MISS...\n");

    Cartas.disponibles -= 1;
    void** disponibles = (void**)malloc(sizeof(void*) * Cartas.disponibles);
    for (int i = 0, index = 0; i < 5; i++) {
        if (Cartas.carta[i] == disparo500KG) continue;
        disponibles[index] = Cartas.carta[i];
        index++;
    };
    free(Cartas.carta);
    Cartas.carta = disponibles;
    return (void*)NULL;
};
