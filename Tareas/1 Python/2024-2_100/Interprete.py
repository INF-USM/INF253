from Interpretador import Interpretador

def throw(ex:Exception) -> None:
    '''
    ***
    ex : Exception
    ...
    ***
    None
    ***
    Imprime la descripcion del error "ex" por la pantalla
    '''
    print(ex.args[0])

def main() -> None:
    '''
    ***
    None
    ***
    Crea una intancia del interpretador y si arroja un error lo muestra por la pantalla
    '''
    inter = Interpretador('codigo.txt')
    try:
        inter.run()
    except Exception as ex:
        throw(ex)

if __name__ == '__main__':
    main()