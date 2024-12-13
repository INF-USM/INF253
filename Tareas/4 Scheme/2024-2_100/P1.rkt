#lang scheme

;; Buscador recursiva compara el primer elemento de la lista con el elemento a buscar,
;; si son distintos se pasa a si misma el resto de la lista hasta que la lista este vacia.
;; Todo mientras se pasa un contador que mantiene la cuenta de la posicion actual en la lista.
;;
;; lista : resto de lista que queda por buscar
;; elemento : elemento a buscar en la lista
;; contador : contador para mantener la posicion actual en la lista
(define (buscadorRec lista elemento contador)
  (if (null? lista)
      -1
      (let ([firstEl (car lista)])
        (if (equal? firstEl elemento)
            contador
            (buscadorRec (cdr lista) elemento (+ contador 1))))))

;; Buscador inicia la busqueda recursiva partiendo con el index 1
;; para el primer elemento de la lista.
;;
;; lista : lista donde buscar el elemento
;; elemento : elemento a buscar en la lista
(define (buscador lista elemento)
  (buscadorRec lista elemento 1))

;; Esto se utilizo para hacer los testings (no tiene ningun efecto)
(provide buscador)
