DATOS:

    Autor:
        Matias Peñaloza   202373037-8

    Sistema operativo:
        Arch linux      (Exodia OS)

    Version de Racket:
        8.14


OPCIONES PARA EJECUTAR TESTS:

    1 - Default:

        Agregar los tests al final de cada archivo y ejecutar el programa con:

            racket ./archivo.rkt

    2 - Opcional:

        Ponga todos los tests en el archivo correspondiente (EJ: para P1.rkt los tests estan en tests/P1.rkt.test)
        en el siguiente formato:

        (funcion y parametros a evaluar);resultado

        luego ejecute los tests para cada archivo con:

            bash run_tests.sh <numero de enunciado>

        EJs:
            Ejecuta los tests para P1:

                bash run_tests.sh 1

            Ejecuta los tests para P2:

                bash run_tests.sh 2

            Ejecuta los tests para el seno de P2:

                bash run_tests.sh 2.1

            Ejecuta los tests para el coseno de P2:

                bash run_tests.sh 2.2


EN CASO DE USAR "Opcional":

    Es necesario dejar la linea "(provide funcion)" para que
    el script pueda ejecutar los tests.

    En caso de no soportar escapes ANSI utilice:

        ANSI=off bash run_tests.sh <numero de enunciado>


CARPETAS:

    ~/LP_TAREA_4/
    |
    +----lib/                   (Contiene 2 scripts en python)
    |    |
    |    +----...py
    |
    +----tests/                 (Contiene tests que se utilizaron anteriormente, usar a conveniencia)
    |    |
    |    +----...rkt.test
    |
    +----...rkt
    +----README.txt
    +----run_tests.sh
