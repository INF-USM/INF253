#lang scheme
;==========================================================;
;========================== SENO ==========================;
;==========================================================;

;; factorial-simple calcula el factorial del numero "n" utilizando
;; recursion simple, dejando el calculo para cuando ya se ha terminado la
;; recursion.
;;
;; n : numero al cual calcular el factorial
(define (factorial-simple n) (
    if (= n 0)
        1
        (* n (factorial-simple (- n 1)))
    )
)

;; calculo-simple calcula el termino "i" correspondiente para el seno,
;; para una iteracion "i" y un numero "x" dado. Esto utilizando funciones que
;; ocupan recursion simple.
;;
;; i : numero de la iteracion de la sumatoria
;; x : valor del cual se quiere calcular el seno
(define (calculo-simple i x) (
    *
        (/
            (expt -1 i)
            (factorial-simple (+ (* 2 i) 1))
        )
        (expt x (+ (* 2 i) 1))
    )
)

;; taylorSenoSimple calcula lo pedido dejando el calculo de suma de la sumatoria
;; para cuando se terminen las recursiones. (cuando se encuentra el valor para el ultimo termino
;; se empiezan a sumar).
;;
;; n : numero de iteraciones para la aproximacion del seno
;; x : numero al cual calcular la aproximacion de su seno
(define (taylorSenoSimple n x) (
    if (< n 0)
        0
        (+ (calculo-simple n x) (taylorSenoSimple (- n 1) x))
    )
)

;==========================================================;
;========================= COSENO =========================;
;==========================================================;

;; factorial-cola-recursiva calcula el factorial del termino "n", acumulando
;; el resultado para la siguiente recursion en el parametro "m", asi dejando la
;; recursion para cuando se termina el calculo.
;;
;; n : el siguiente termino del cual calcular el factorial
;; m : calculo acumulado de las llamadas anteriores
(define (factorial-cola-recursiva n m) (
    if (= n 0)
        m
        (factorial-cola-recursiva (- n 1) (* n m))
    )
)

;; factorial-cola empieza la recursion para el caclulo del numero "n"
;; utilizando factorial-cola-recursiva.
;;
;; n : numero del cual calcular el factorial (termino inicial)
(define (factorial-cola n) (
    factorial-cola-recursiva n 1
))

;; calculo-cola calcula el termino "i" correspondiente para el coseno,
;; para una iteracion "i" y un numero "x" dado. Esto utilizando funciones que
;; ocupan recursion de cola.
;;
;; i : numero de la iteracion de la sumatoria
;; x : valor del cual se quiere calcular el coseno
(define (calculo-cola i x) (
    *
        (/
            (expt -1 i)
            (factorial-cola (* 2 i))
        )
        (expt x (* 2 i))
    )
)

;; taylorCosenoCola-recursiva calcula la sumatoria de de los terminos para la
;; iteracion "n", pasandose a si misma el calculo acumulado a traves del parametro "m".
;; (se empieza a calcular al reves, desde el termino "n" hasta el termino 0)
;;
;; n : numero de iteraciones para la aproximacion del coseno
;; x : numero con el cual calcular la aproximacion de su coseno
;; m : calculo acumulado de llamadas anteriores
(define (taylorCosenoCola-recursiva n x m) (
    if (< n 0)
    m
    (taylorCosenoCola-recursiva (- n 1) x (+ m (calculo-cola n x)))
))

;; taylorCosenoCola inicia la recursion para el calculo del coseno de "x",
;; partiendo con un calculo acumulado de 0.
;;
;; n : numero de iteraciones para la aproximacion del coseno
;; x : numero al cual calcular la aproximacion de su coseno
(define (taylorCosenoCola n x) (
    taylorCosenoCola-recursiva n x 0
))

;; Esto se utilizo para hacer los testings (no tiene ningun efecto)
(provide taylorCosenoCola)
(provide taylorSenoSimple)
