DATOS:

    Autor:
        Matias Peñaloza   202373037-8

    Sistema operativo:
        Arch linux      (Exodia OS)

    Version de JDK:
        22.0.2          (java/javac/jar)



INSTRUCCIONES:

    Comando para la compilacion del programa:
        make

    Comando para la ejecucion del programa:
        java -jar Ejecutable.jar



UTIL:

    Desactivar escapes ANSI:
        ANSI=off java -jar Ejecutable.jar

    Partir con lo necesario para probar el final (50% efi.motor y 1er planeta CentroGalactico):
        DEBUG=on java -jar Ejecutable.jar

    Las opciones ANSI y DEBUG son combinables.


CARPETAS:

    ~/lib/                          (Todos las clases que no son el programa principal)
    |
    +----interfaz/                  (Todas las interacciones con la consola)
    |    |
    |    +----anim/                 (Animaciones basadas en iteradores)
    |    |    +----...java
    |    |
    |    +----ascii/                (Todos los artes ASCII/ANSI y Textos)
    |    |    +----...java
    |    |
    |    +----...java
    |
    +----jugable/                   (Jugador, Nave, MapaGalactico, Inventario)
    |    |
    |    +----...java
    |
    +----planetas/                  (Todo lo relacionado con los planetas)
         |
         +----...java