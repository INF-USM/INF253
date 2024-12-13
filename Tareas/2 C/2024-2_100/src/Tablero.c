#include "Tablero.h"

/*
*   Crea posiciones aleatorias para las piezas del barco, luego si no existes intersecciones con otros barcos
*   las posiciones se ocupan en el tablero, en caso contrario no se hacen cambios y se retorna 0 (fallo)
*
*   @param [int]dir horientacion del barco 1 -> horizontal | 0 -> vertical
*   @param [int]tamano largo del barco
*   @returns [ int ] 0 o 1, describe si el barco se creo correctamente o fallo al intentarlo
*/
int crearBarco(int dir, int tamano) {
    int baderror = 0;
    int* listx = (int*)malloc(sizeof(int) * tamano);
    int* listy = (int*)malloc(sizeof(int) * tamano);
    int x = rand() % size;
    int y = rand() % size;
    for (int dx = 0, dy = 0, it = 0; dx < tamano && dy < tamano; dx += dir, dy += !dir, it++) {
        if (x + dx >= size || y + dy >= size) {
            baderror = 1;
            break;
        };

        if ((*(int*)tablero[y + dy][x + dx]) < Agua) {
            baderror = 1;
            break;
        };
        listx[it] = x + dx;
        listy[it] = y + dy;
    };

    if (baderror) {
        free(listx);
        free(listy);
        return 0;
    };

    for (int i = 0; i < tamano; i++) {
        int xx = listx[i];
        int yy = listy[i];
        (*(int*)tablero[yy][xx]) = Barco;
    };

    free(listx);
    free(listy);
    return 1;
};

/*
*   Inicializa tablero solo con agua alojando memoria, luego se crean todos los barcos por tipo (tamaño)
*
*   @param [int]tamano tamaño del tablero
*   @returns void
*/
void inicializarTablero(int tamano) {
    size = tamano;
    tablero = (void***)malloc(tamano * sizeof(int**));
    for (int i = 0; i < tamano; i++) {
        tablero[i] = (void**)malloc(tamano * sizeof(int*));
        for (int j = 0; j < tamano; j++) {
            tablero[i][j] = (void*)malloc(tamano * sizeof(int));
            (*(int*)tablero[i][j]) = Agua;
        };
    };

    for (int tipo = 0; tipo < 4; tipo++)
        for (int cantidad = 0; cantidad < sizesBarcos[diff - 1][tipo]; cantidad++)
            while (1) {
                int success = crearBarco(
                    rand() % 2, // direccion 1=Horizontal 0=Vertical
                    tipo + 2    // largo del barco
                );
                if (success) break;
            };
};

/*
*   Libera la memoria alojada en el tablero
*
*   @returns void
*/
void limpiarTablero() {
    if (tablero == NULL) return;

    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++)
            free(tablero[i][j]);
        free(tablero[i]);
    };
    free(tablero);
};

/*
*   Muestra el tablero por consola en un formato enumerado
*
*   @returns void
*/
void mostrarTablero() {
    int val;
    printf("   ");
    for (int x = 0; x < size; x++) {
        printf(" %2d ", x + 1);
    };
    printf("\n");
    for (int y = 0; y < size; y++) {
        printf("%2d ", y + 1);
        for (int x = 0; x < size; x++) {
            val = *((int*)tablero[y][x]);
            if (val == Hit)
                printf("| %-2s", "X");
            else if (val == Hint)
                printf("| %-2s", "@");
            else if (val == Miss)
                printf("| %-2s", "~");
            else
                printf("| %-2s", " ");
        };
        printf("|\n");
    };
    printf("\n");
};