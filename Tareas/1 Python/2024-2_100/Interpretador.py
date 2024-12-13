from io import TextIOWrapper
from re import Match, fullmatch, match

class Interpretador:
#========================================================================================#
#                                       REGEX
#========================================================================================#
    _re_open_bracket = r"\{"
    _re_close_bracket = r"\}"
    _re_space = r"( )+" # 1 o mas espacios
    _re_space_0 = r"( )*" # 0 o mas espacios
    _re_var = r"\$_[A-Z]([a-z]|[A-Z])*" # variable
    _re_str = r"#(| |[a-zA-Z]|[0-9])*#" # string
    _re_int = r"(0|[1-9][0-9]*)" # entero
    _re_bool = r"(True|False)" # booleano
    _re_op_un = r"ASIG" # operador unario
    _re_op_bin = r"(\+|\*|\>|\=\=)" # operador binario

    _re_def = rf"DEFINE{_re_space}{_re_var}" # define
    _re_dp_un = rf"ASIG{_re_space}({_re_str}|{_re_int}|{_re_bool}|{_re_var})" # dp uniario
    _re_dp_bi = rf"{_re_op_bin}{_re_space}({_re_str}|{_re_int}|{_re_bool}|{_re_var}){_re_space}({_re_str}|{_re_int}|{_re_bool}|{_re_var})" # dp binario
    _re_dp = rf"DP{_re_space}{_re_var}{_re_space}({_re_dp_un}|{_re_dp_bi})" # dp
    _re_mos = rf"MOSTRAR\({_re_var}\)"

    _re_if = rf"if{_re_space_0}\({_re_space_0}{_re_var}{_re_space_0}\){_re_space_0}{_re_open_bracket}"
    _re_else = rf"{_re_close_bracket}{_re_space_0}else{_re_space_0}{_re_open_bracket}"

#========================================================================================#
#                                       INIT
#========================================================================================#
    _output:TextIOWrapper # Archivo output
    _lines:list[str] # Lineas del archivo codigo.txt
    _n_lines:int
    _var = dict()
    _is_valid = dict()
    _exec = dict()
    _it = 0
    _nested = 0
    _if_stack = []

    def __init__(self, file_name:str) -> None:
        '''
        ***
        file_name : str
        ...
        ***
        None
        ***
        Contructor que asigna las lineas del archivo a "_lines" y el numero de estas a "_n_lines",
        tambien asigna las funciones de validacion y ejecucion a los codigos correspondientes,
        por ultimo abre el archivo "output.txt"
        '''
        file = open(file_name, 'r')
        self._lines = file.readlines()
        self._n_lines = len(self._lines)
        file.close()

        self._output = open("output.txt", "w")

        self._is_valid = {
            "DE": self._is_valid_def,
            "DP": self._is_valid_dp,
            "MO": self._is_valid_mos,
            "if": self._is_valid_if,
            "}": self._is_valid_end,
            "} ": self._is_valid_els,
            "}e": self._is_valid_els
        }
        self._exec = {
            "DE": self._exec_def,
            "DP": self._exec_dp,
            "MO": self._exec_mos,
            "if": self._exec_if,
            "}": self._exec_end,
            "} ": self._exec_els,
            "}e": self._exec_els
        }

#========================================================================================#
#                                    VALID LINE
#========================================================================================#
    def _is_valid_def(self, line:str) -> None:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Revisa con regex la linea para saber si es una definicion correcta en sintaxis,
        sino arroja el error correspondiente
        '''
        if not fullmatch(self._re_def, line):
            self._error_match("Mal Sintaxis", match(self._re_def, line))


    def _is_valid_dp(self, line:str) -> None:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Revisa con regex la linea para saber si es una operacion DP correcta en sintaxis,
        sino arroja el error correspondiente
        '''
        if not fullmatch(self._re_dp, line):
            self._error_match("Mal Sintaxis", match(self._re_dp, line))

    def _is_valid_mos(self, line:str) -> None:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Revisa con regex la linea para saber si es una operacion MOSTRAR correcta en sintaxis,
        sino arroja el error correspondiente
        '''
        if not fullmatch(self._re_mos, line):
            self._error_match("Mal Sintaxis", match(self._re_mos, line))

    def _is_valid_if(self, line:str) -> None:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Revisa con regex la linea para saber si es un condicional if correcto en sintaxis,
        sino arroja el error correspondiente
        '''
        if not fullmatch(self._re_if, line):
            self._error_match("Mal Sintaxis", match(self._re_if, line))

    def _is_valid_end(self, line:str) -> None:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Revisa con regex la linea para saber si es un cierre de bloque correcto en sintaxis,
        sino arroja el error correspondiente
        '''
        if not fullmatch(self._re_close_bracket, line):
            self._error_match("Mal Sintaxis", match(self._re_close_bracket, line))

    def _is_valid_els(self, line:str) -> None:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Revisa con regex la linea para saber si es un condicional else correcto en sintaxis,
        sino arroja el error correspondiente
        '''
        if not fullmatch(self._re_else, line):
            self._error_match("Mal Sintaxis", match(self._re_else, line))

#========================================================================================#
#                                    EXEC LINE
#========================================================================================#
    def _goto_end(self) -> None:
        '''
        ...
        ***
        None
        ***
        Busca el siguiente cierre de bloque que tenga la misma profundidad de anidado que la linea donde esta leyendo el programa principal
        '''
        sub_nested = self._nested
        for idx in range(self._it + 1, self._n_lines):
            line = self._lines[idx].strip()
            tok = line[0:2]
            if sub_nested == self._nested and tok == '}':
                self._it = idx - 1
                return
            if tok == "if":
                sub_nested += 1
            elif tok == "} " or tok == "}e" or tok == "}":
                sub_nested -= 1
        self._error("Mal sintaxis, condicional else sin respectivo \"}\"")

    def _goto_else(self) -> None:
        '''
        ...
        ***
        None
        ***
        Busca el siguiente condicional else que tenga la misma profundidad de anidado que la linea donde esta leyendo el programa principal
        '''
        sub_nested = self._nested
        for idx in range(self._it, self._n_lines):
            line = self._lines[idx].strip()
            tok = line[0:2]
            if tok == "if":
                sub_nested += 1
            elif tok == "} " or tok == "}e" or tok == "}":
                sub_nested -= 1
            if sub_nested == self._nested and (tok == '} ' or tok == '}e'):
                self._it = idx - 1
                return
        self._error("Mal sintaxis, condicional if sin respectivo else")

    def _write(self, val:str | int | bool | None) -> None:
        '''
        ***
        val : str | int | bool | None
        ...
        ***
        None
        ***
        Escribe el output del programa en el archivo "output.txt"
        '''
        out = str(val).replace('#', '') + '\n'
        self._output.write(out)

    def _find_defined_variables_out(self, var_name:str) -> str | bool:
        '''
        ***
        val : str | bool
        ...
        ***
        None
        ***
        Busca el nombre de la variable en los ambitos alcanzables por el actual y retorna el nombre ej:<1>$_Test,
        si no se encuentra retorna False
        '''
        all_variables = self._var.keys()
        for nested in range(self._nested, -1, -1):
            variable = f"<{nested}>{var_name}"
            if variable in all_variables:
                return variable
        return False

    def _get_typename(self, val:str | int | bool | None) -> str:
        '''
        ***
        val : str | int | bool | None
        ...
        ***
        str
        ***
        rescata solo el nombre del tipo del valor dado y lo retorna como un string
        Ej: "NoneType" | "int" | "str" | "bool"
        '''
        string = str(type(val))
        left = string.index("'")
        right = string.index("'", left + 1)
        return string[left + 1:right]

    def _get_value_from(self, var_name:str) -> str | int | bool | None:
        '''
        ***
        var_name : str
        ...
        ***
        str | int | bool | None
        ***
        Accede al valor de una variable y retorna su valor, si es None o
        sino esta definida o si el nivel de anidado es mayor a
        donde se esta leyendo actualmente en el programa principal arroja el error correspondiente
        '''
        name = var_name
        if ">$" in var_name: name = var_name[3:]
        variable = self._find_defined_variables_out(name)
        if not variable: self._error(f"La variable {name} no ha sido definida dentro del rango")
        val = self._var[variable]
        tipo = self._get_typename(val)
        if tipo == "NoneType": self._error(f"La variable {name} no se le a asignado un valor")
        return val

    def _transform_type(self, val:str) -> str | int | bool | None:
        '''
        ***
        val : str
        ...
        ***
        str | int | bool | None
        ***
        Transforma el tipo del valor dado en string al correspondiente y lo retorna
        '''
        return val if "#" in val else eval(val)

    def _save_var(self, name:str, val:str | int | bool | None) -> None:
        '''
        ***
        name : str
        val : str | int | bool | None
        ...
        ***
        None
        ***
        Guarda el valor y el nivel de anidado de la variable dada segun su nombre en un diccionario de variables
        '''
        self._var[name] = val

    def _get_args(self, line:str) -> list[str]:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Obtiene cada una de las palabras en la linea dada, y las devuelve en una lista
        '''
        args = []
        arg = ""
        on_string = False
        for c in line:
            if c == "#":
                on_string = not on_string
            if arg != "" and not on_string and c == " ":
                args.append(arg)
                arg = ""
            if (on_string) or (c != " " and not on_string):
                arg += c
        args.append(arg)
        return args
    
    def _exec_def(self, line:str) -> None:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Obtiene el nombre de la variable, revisa si se declaro, y por utilmo la guarda inicializada con valor None
        '''
        _, defi = self._get_args(line)
        variable = self._find_defined_variables_out(defi)
        if variable and int(variable[1]) == self._nested: self._error(f'La variable {defi} ya ha sido definida')
        self._save_var(f"<{self._nested}>{defi}", None)

    def _exec_dp_asig(self, save_in:str, value:str) -> None:
        '''
        ***
        save_in : str
        value : str
        ...
        ***
        None
        ***
        revisa si el nombre de la variable existe y si es accesible desde el ambito donde se esta leyendo, luego
        si el valor a asignar es una variable se utiliza el metodo "_get_value_from" sino solo se tranforma el tipo para finalmente
        guardar el valor de la variable con "_save_var"
        '''
        variable = self._find_defined_variables_out(save_in)
        if not variable: self._error(f"La variable {save_in} no ha sido definida dentro del rango")
        val = self._get_value_from(value) if "$" in value else self._transform_type(value)

        self._save_var(variable, val)

    def _exec_dp_mul(self, save_in:str, arg_1:str, arg_2:str) -> None:
        '''
        ***
        save_in : str
        arg_1 : str
        arg_2 : str
        ...
        ***
        None
        ***
        revisa si las variables existes y si son accesibles desde el ambito actual, luego se chequean si los tipos son compatibles con la operacion,
        finalmente se guarda en la variable con nombre (save_in) el resultado
        '''
        save_variable = self._find_defined_variables_out(save_in)
        if not save_variable: self._error(f"La variable {save_in} no ha sido definida dentro del rango")

        val_1 = self._get_value_from(arg_1) if "$" in arg_1 else self._transform_type(arg_1)
        val_2 = self._get_value_from(arg_2) if "$" in arg_2 else self._transform_type(arg_2)

        type_1 = self._get_typename(val_1)
        type_2 = self._get_typename(val_2)

        if type_1 == "NoneType": self._error(f"La variable {arg_1} no se le a asignado un valor")
        if type_2 == "NoneType": self._error(f"La variable {arg_2} no se le a asignado un valor")

        if type_1 != "int": self._error(f"La operacion DP * no es compatible con el tipo {type_1}")
        if type_2 != "int": self._error(f"La operacion DP * no es compatible con el tipo {type_2}")
        
        self._save_var(save_variable, val_1 * val_2)

    def _exec_dp_sum(self, save_in:str, arg_1:str, arg_2:str) -> None:
        '''
        ***
        save_in : str
        arg_1 : str
        arg_2 : str
        ...
        ***
        None
        ***
        revisa si las variables existes y si son accesibles desde el ambito actual, luego se chequean si los tipos son compatibles con la operacion,
        finalmente se guarda en la variable con nombre (save_in) el resultado
        '''
        save_variable = self._find_defined_variables_out(save_in)
        if not save_variable: self._error(f"La variable {save_in} no ha sido definida dentro del rango")

        val_1 = self._get_value_from(arg_1) if "$" in arg_1 else self._transform_type(arg_1)
        val_2 = self._get_value_from(arg_2) if "$" in arg_2 else self._transform_type(arg_2)

        type_1 = self._get_typename(val_1)
        type_2 = self._get_typename(val_2)

        if type_1 == "NoneType": self._error(f"La variable {arg_1} no se le a asignado un valor")
        if type_2 == "NoneType": self._error(f"La variable {arg_2} no se le a asignado un valor")

        if type_1 != "int" and type_1 != "str": self._error(f"La operacion DP + no es compatible con el tipo {type_1}")
        if type_2 != "int" and type_2 != "str": self._error(f"La operacion DP + no es compatible con el tipo {type_2}")

        if type_1 == type_2: self._save_var(save_variable, val_1 + val_2)     # los 2 string o los 2 integer
        else: self._save_var(save_variable, str(val_1) + str(val_2))          # string y integer se concatenan en un string

    def _exec_dp_may(self, save_in:str, arg_1:str, arg_2:str) -> None:
        '''
        ***
        save_in : str
        arg_1 : str
        arg_2 : str
        ...
        ***
        None
        ***
        revisa si las variables existes y si son accesibles desde el ambito actual, luego se chequean si los tipos son compatibles con la operacion,
        finalmente se guarda en la variable con nombre (save_in) el resultado
        '''
        save_variable = self._find_defined_variables_out(save_in)
        if not save_variable: self._error(f"La variable {save_in} no ha sido definida dentro del rango")

        val_1 = self._get_value_from(arg_1) if "$" in arg_1 else self._transform_type(arg_1)
        val_2 = self._get_value_from(arg_2) if "$" in arg_2 else self._transform_type(arg_2)

        type_1 = self._get_typename(val_1)
        type_2 = self._get_typename(val_2)

        if type_1 == "NoneType": self._error(f"La variable {arg_1} no se le a asignado un valor")
        if type_2 == "NoneType": self._error(f"La variable {arg_2} no se le a asignado un valor")

        if type_1 != "int": self._error(f"La operacion DP > no es compatible con el tipo {type_1}")
        if type_2 != "int": self._error(f"La operacion DP > no es compatible con el tipo {type_2}")

        self._save_var(save_variable, val_1 > val_2)

    def _exec_dp_igu(self, save_in:str, arg_1:str, arg_2:str) -> None:
        '''
        ***
        save_in : str
        arg_1 : str
        arg_2 : str
        ...
        ***
        None
        ***
        revisa si las variables existes y si son accesibles desde el ambito actual, luego se chequean si los tipos son compatibles con la operacion,
        finalmente se guarda en la variable con nombre (save_in) el resultado
        '''
        save_variable = self._find_defined_variables_out(save_in)
        if not save_variable: self._error(f"La variable {save_in} no ha sido definida dentro del rango")

        val_1 = self._get_value_from(arg_1) if "$" in arg_1 else self._transform_type(arg_1)
        val_2 = self._get_value_from(arg_2) if "$" in arg_2 else self._transform_type(arg_2)

        type_1 = self._get_typename(val_1)
        type_2 = self._get_typename(val_2)

        if type_1 == "NoneType": self._error(f"La variable {arg_1} no se le a asignado un valor")
        if type_2 == "NoneType": self._error(f"La variable {arg_2} no se le a asignado un valor")

        if type_1 != "int" and type_1 != "str": self._error(f"La operacion DP == no es compatible con el tipo {type_1}")
        if type_2 != "int" and type_2 != "str": self._error(f"La operacion DP == no es compatible con el tipo {type_2}")

        self._save_var(save_variable, val_1 == val_2)

    def _exec_dp(self, line:str) -> None:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Obtiene las palabras contenidas en la linea y realiza las operaciones DP correspondientes,
        pasando los parametros necesarios a cada operacion
        '''
        args = self._get_args(line)

        if "ASIG" in args:
            _, arg_var, _, arg_val = args                      # DP, VAR, ASIG, VAL
            self._exec_dp_asig(arg_var, arg_val)
        elif "*" in args:
            _, arg_var, _, arg_val, arg_val2 = args            # DP, VAR, *, VAL1, VAL2
            self._exec_dp_mul(arg_var, arg_val, arg_val2)
        elif "+" in args:
            _, arg_var, _, arg_val, arg_val2 = args            # DP, VAR, +, VAL1, VAL2
            self._exec_dp_sum(arg_var, arg_val, arg_val2)
        elif ">" in args:
            _, arg_var, _, arg_val, arg_val2 = args            # DP, VAR, >, VAL1, VAL2
            self._exec_dp_may(arg_var, arg_val, arg_val2)
        elif "==" in args:
            _, arg_var, _, arg_val, arg_val2 = args            # DP, VAR, ==, VAL1, VAL2
            self._exec_dp_igu(arg_var, arg_val, arg_val2)
        else:
            self._error("Operacion DP desconocida")

    def _exec_mos(self, line:str) -> None:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Obtiene el valor de la variable, accede a su contenido y luego lo escribe en el output
        '''
        left = line.index("(") + 1
        right = line.index(")")
        name = line[left:right].strip()

        variable = self._find_defined_variables_out(name)
        if not variable: self._error(f"La variable {name} no ha sido definida dentro del rango")
        value = self._get_value_from(variable)
        self._write(value)

    def _exec_if(self, line:str) -> None:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Obtiene el valor de la variable, accede a su contenido, chequea si el tipo es el correcto,
        el valor se guarda en un stack y si el valor es false el codigo empieza a leer en el else correspondiente,
        finalmente el nivel de anidado del programa principal aumenta (_nested)
        '''
        left = line.index("(") + 1
        right = line.index(")")
        name = line[left:right].strip()

        variable = self._find_defined_variables_out(name)
        if not variable: self._error(f"La variable {name} no ha sido definida dentro del rango")
        value = self._get_value_from(name)
        typ = self._get_typename(value)
        if typ != "bool": self._error(f"Condicional imcompatible con el tipo {typ}")

        self._if_stack.append(value)
        if not value:
            self._goto_else()
        self._nested += 1

    def _delete_scope(self, nested:int) -> None:
        '''
        ***
        nested : int
        ...
        ***
        None
        ***
        Borra las variables que estan en el ambito (nested) dado
        '''
        delete = []
        for key in self._var.keys():
            if int(key[1]) == nested:
                delete.append(key)
        for variable in delete:
            self._var.pop(variable)

    def _exec_end(self, line:str) -> None:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Al terminal el bloque de codigo se eliminan las variables declaradas en ese ambito,
        tambien el nivel de anidado del programa principal disminuye (_nested)
        '''
        self._delete_scope(self._nested)
        self._nested -= 1

    def _exec_els(self, line:str) -> None:
        '''
        ***
        line : str
        ...
        ***
        None
        ***
        Al llegar al else se eliminan las variables que se hallan creado en el bloque if asociado,
        luego se saca del stack el valor del if asociado y si es verdadero, se salta hasta el final del bloque else
        '''
        self._delete_scope(self._nested)
        
        last_if = self._if_stack.pop()
        if last_if: self._goto_end()

#========================================================================================#
#                                    GENERAL
#========================================================================================#

    def _error(self, tipo:str) -> None:
        '''
        ***
        tipo : str
        ...
        ***
        None
        ***
        Se cierra el archivo "output.txt" y luego se arroja el error correspondiente
        en un formato simple
        '''
        self._output.close()
        raise Exception(
            "#======================================#\n" \
            f"\t\tERROR\n" \
            "#======================================#\n"\
            f"linea {self._it + 1}:\n\t-> {self._lines[self._it].strip()}\n\n" \
            f"{tipo}\n"
        )

    def _error_match(self, tipo:str, match:Match[str] | None) -> None:
        '''
        ***
        tipo : str
        match : Match[str]
        ...
        ***
        None
        ***
        Se cierra el archivo "output.txt" y luego se arroja el error correspondiente
        en un formato que indica donde falla el match de la regex
        '''
        self._output.close()
        line = self._lines[self._it].strip()
        where = [ "^" for i in range(len(line)) ]
        if match:
            start = match.start()
            end = match.end()
            for i in range(start, end):
                where[i] = " "
        raise Exception(
            "#======================================#\n" \
            f"\t\tERROR\n" \
            "#======================================#\n"\
            f"linea {self._it + 1}:\n\t-> {self._lines[self._it].strip()}\n" \
            + "\t   " + "".join(where) + "\n"\
            f"{tipo}"
        )

    def run(self) -> None:
        '''
        ***
        None
        ***
        Se recorren las lineas del archivo y se obtiene un codigo de los 2 primeros caracteres de la primera palabra de la linea,
        con eso se ejecutan las funciones de validacion y ejecucion correspondientes.
        '''
        keys = self._is_valid.keys()                    # todos los codigos
        while self._it < self._n_lines:
            line = self._lines[self._it].strip()        # linea sin espacios adicionales al final ni al inicio
            tok = line[0:2]                             # codigo (tok), los 2 primeros caracteres
            
            if tok in keys: self._is_valid[tok](line)   # llamado a la funcion de validacion segun codigo (tok)
            else: self._error('Mal Sintaxis')

            self._exec[tok](line)                       # llamado a la funcion de ejecucion segun codigo (tok)
            self._it += 1