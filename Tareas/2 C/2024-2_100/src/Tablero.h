#ifndef H_TABLERO
#define H_TABLERO
#include <stdlib.h>
#include <stdio.h>
#include "Const.h"

extern void *** tablero;
extern int size;
extern int diff;
extern int sizesBarcos[3][4];

void inicializarTablero(int tamano);
void mostrarTablero();
void limpiarTablero();
int crearBarco(int dir, int tamano);

#endif
