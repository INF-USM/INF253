#lang scheme
;==========================================================;
;==================== RECURSION SIMPLE ====================;
;==========================================================;

;; evaluar-funciones evalua las funciones pasandoles a cada una como parametro
;; el resultado de la funcion anterior. La lista de funciones se recorre y evalua
;; usando recursion de cola.
;;
;; funciones : lista que contiene el resto de funciones a evaluar
;; valor : calculo acumulado
(define (evaluar-funciones funciones valor) (
    if (equal? funciones '())
    valor
    (evaluar-funciones (cdr funciones) ((car funciones) valor))
))

;; rotar-una ejecuta una unica rotacion a la lista dada.
;;
;; lista : lista a rotar
(define (rotar-una lista) (
    append (cdr lista) (list (car lista))
))

;; rotar rota la lista dada la cantidad de veces indicada, utilizando
;; recursion de cola. (acumulando el resultado en el parametro lista)
;;
;; veces : veces restantes a rotar
;; lista : lista a rotar (calculo acumulado)
(define (rotar veces lista) (
    cond ((<= veces 0) lista)                   ; Caso: se terminan la iteraciones
        ((equal? lista '()) lista)              ; Caso: la lista a rotar esta vacia         (rotar no tiene ningun efecto)
        ((equal? (cdr lista) '()) lista)        ; Caso: la lista tiene un unico elemento    (rotar no tiene ningun efecto)
        (else (
            rotar (- veces 1) (rotar-una lista)
        ))
))

;; obtener-recursiva utiliza recursion de cola (acumulando el resultado en el parametro lista),
;; para rescatar el elemento de la lista en la posicion indicada.
;;
;; posicion : posicion de la lista donde se encuentra el elemento deseado
;; lista : el resto de la lista donde buscar el elemento
;; cont : contador para mantener la posicion en la lista
(define (obtener-recursiva posicion lista cont) (
    if (= posicion cont)
    (car lista)
    (obtener-recursiva posicion (cdr lista) (+ cont 1))
))

;; obtener inicia la recursion para obtener el elemento de la lista
;; en la posicion "posicion"
;;
;; posicion : posicion de la lista donde se encuentra el elemento deseado
;; lista : lista de donde obtener el elemento
(define (obtener posicion lista) (
    obtener-recursiva posicion lista 0
))

;; rotar-evaluar devuelve el resultado de evaluar las funciones contenidas
;; en la lista "funciones" rotada con el numero en la posicion "iteracion".
;;
;; en (obtener iteracion numeros) se tiene en cuenta que el primer elemento tiene index 0,
;; asi entonces (rotar iteracion funciones) para el primer elemento se rota 0 veces la lista de funciones.
;;
;; iteracion : posicion de la lista de resultados a calcular
;; funciones : lista de funciones
;; numeros : lista de numeros
(define (rotar-evaluar iteracion funciones numeros) (
    evaluar-funciones (rotar iteracion funciones) (obtener iteracion numeros)
))

;; unir une el elemento "inicio" con la lista "resto",
;; dejando al elemento al inicio de la lista.
;;
;; inicio : elemento a unir
;; resto : lista a unir
(define (unir inicio resto) (
    append (list inicio) resto
))

;; evaluador-recursiva utiliza recursion de cola (acumulando el resultado en "resultados") para calcular
;; el resultado de cada uno de los elementos que van en la lista de resultados.
;;
;; para (rotar-evaluar (- iteracion 1) funciones numeros) se tiene en cuenta que se parte de la iteracion "n",
;; por lo que al tener que realizar "n - 1" rotaciones para el primer elemento que se evalua (elemento "n") se
;; pide el resultado para la "iteracion - 1" y ese se acumula en el resultado.
;;
;; iteracion : posicion de la lista de resultados a calcular
;; funciones : lista de funciones
;; numeros : lista de numeros
;; resultados : lista acumulada de resultados
(define (evaluador-recursiva iteracion funciones numeros resultados) (
    if (= iteracion 0)
    resultados
    (evaluador-recursiva 
        (- iteracion 1)
        funciones 
        numeros 
        (unir 
            (rotar-evaluar (- iteracion 1) funciones numeros) 
            resultados
        )
    )
))

;; evaluador hace lo pedido en el enunciado, llamando a "evaluador-recursiva" inicia
;; la recursion de cola con un resultado acumulado de -> '() (0 resultados calculados)
;; y empezando desde la iteracion "n" (length numeros).
;;
;; funciones : lista de funciones
;; numeros : lista de numeros
(define (evaluador funciones numeros) (
        evaluador-recursiva (length numeros) funciones numeros '()
    )
)

;; Esto se utilizo para hacer los testings (no tiene ningun efecto)
(provide evaluador)
