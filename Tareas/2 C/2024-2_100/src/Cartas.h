#ifndef H_CARTAS
#define H_CARTAS
#include <stdlib.h>
#include <stdio.h>
#include "Const.h"

typedef struct Mano {
    void** carta;
    int disponibles;
} Mano;

typedef struct Opcion {
    int selec;
    int x, y;
} Opcion;


extern Mano Cartas;
extern Opcion Seleccion;
extern void*** tablero;
extern int hits;
extern int size;
extern const char* disLinealProm[2];
extern void getInput(int* c, int subLim, int supLim);
extern void showOpt(const char** options, int n);
extern void showTitle(const char* str, int n);
void inicializarMazo();
void limpiarMazo();
void mostrarMazo();
void usarCarta();

void* elegir4(int p1, int p2, int p3, void* f1, void* f2, void* f3, void* f4);
void* elegir5(int p1, int p2, int p3, int p4, void* f1, void* f2, void* f3, void* f4, void* f5);
void revelar(int x, int y);
void revelarHint(int x, int y);
int disparar(int x, int y);
void* disparoSimple(int x, int y);
void* disparoGrande(int x, int y);
void* disparoLineal(int x, int y);
void* disparoRadar(int x, int y);
void* disparo500KG(int x, int y);

#endif