#lang scheme

;; visitar devuelve una lista con la profundidad (distancia) si el valor del nodo
;; es T y sino devuelve una lista vacia
;;
;; arbol : nodo a revisar
;; profundidad : valor de la profundidad actual en donde se llamo a la funcion
(define (visitar arbol profundidad) (
    if (equal? (car arbol) 'T)
    (list profundidad)
    '()
))

;; cada-vecino se llama a si misma pasandose el resto de la lista de vecinos,
;; para luego visitar al primer vecino de la lista. Asi dejando el calculo para cuando
;; se termina la recursion.
;;
;; Devuelve la union de las listas que se generan al visitar.
;;
;; vecinos : el resto de la lista de vecinos
;; profundidad : profundidad en donde se llamo a la funcion
(define (cada-vecino vecinos profundidad) (
    if (equal? vecinos '())
    '()
    (append (cada-vecino (cdr vecinos) profundidad) (visitar-buscar (car vecinos) profundidad))
))

;; visitar-buscar busca en cada uno de los vecinos utilizando "cada-vecino" (al visitar al vecino la profundidad aumenta) para luego
;; visitar el nodo actual. Asi dejando el calculo para cuando se termina la recursion.
;;
;; Devuelve la union de las listas que se general de visitar a todos sus vecinos y el nodo actual
;;
;; arbol : nodo del cual se quiere visitar y buscar en sus vecinos
;; profundidad : profundidad en donde se llamo a la funcion
(define (visitar-buscar arbol profundidad) (
    if (equal? arbol '())
    '()
    (append (cada-vecino (cdr arbol) (+ profundidad 1)) (visitar arbol profundidad))
))

;; profundidades ordena la lista que se genera al buscar en todo el arbol, llamando a "visitar-buscar"
;; inicia la recursion para la busqueda con una profundidad de 0.
;;
;; arbol : arbol en el cual buscar
(define (profundidades arbol) (
    sort (visitar-buscar arbol 0) <
))

;; Esto se utilizo para hacer los testings (no tiene ningun efecto)
(provide profundidades)
