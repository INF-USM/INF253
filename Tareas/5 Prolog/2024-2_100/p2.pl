%%%%%%%%%%%%%%        %%%%%%%%%%%%%%%%
%%%%%%%%%%%%  PLANETAS  %%%%%%%%%%%%%%
%%%%%%%%%%%%%%        %%%%%%%%%%%%%%%%
c.
p1.
p2.
p3.
p4.
p5.
p6.
p7.
p8.
p9.
p10.
p11.
p12.

%%%%%%%%%%%%%%        %%%%%%%%%%%%%%%%
%%%%%%%%%%%%  PUENTES   %%%%%%%%%%%%%%
%%%%%%%%%%%%%%        %%%%%%%%%%%%%%%%

%%%%%%%%%%%%%% -> c   %%%%%%%%%%%%%%%%
puente(p1, c, 4).
puente(p4, c, 3).
puente(p3, c, 2).
%%%%%%%%%%%%%% -> p1  %%%%%%%%%%%%%%%%
puente(p5, p1, 7).
puente(p2, p1, 3).
%%%%%%%%%%%%%% -> p2  %%%%%%%%%%%%%%%%
puente(p6, p2, 2).
puente(p7, p2, 3).
%%%%%%%%%%%%%% -> p3  %%%%%%%%%%%%%%%%
puente(p10, p3, 3).
puente(p2, p3, 7).
puente(p8, p3, 8).
%%%%%%%%%%%%%% -> p4  %%%%%%%%%%%%%%%%
puente(p1, p4, 1).
puente(p11, p4, 7).
puente(p10, p4, 6).
%%%%%%%%%%%%%% -> p5  %%%%%%%%%%%%%%%%
puente(p12, p5, 2).
%%%%%%%%%%%%%% -> p6  %%%%%%%%%%%%%%%%
puente(p7, p6, 4).
%%%%%%%%%%%%%% -> p7  %%%%%%%%%%%%%%%%
% nada
%%%%%%%%%%%%%% -> p8  %%%%%%%%%%%%%%%%
% nada
%%%%%%%%%%%%%% -> p9  %%%%%%%%%%%%%%%%
puente(p8, p9, 3).
%%%%%%%%%%%%%% -> p10 %%%%%%%%%%%%%%%%
puente(p9, p10, 10).
%%%%%%%%%%%%%% -> p11 %%%%%%%%%%%%%%%%
% nada
%%%%%%%%%%%%%% -> p12 %%%%%%%%%%%%%%%%
puente(p11, p12, 3).

%%%%%%%%%%%%%%        %%%%%%%%%%%%%%%%
%%%%%%%%%%%%   CAMINO   %%%%%%%%%%%%%%
%%%%%%%%%%%%%%        %%%%%%%%%%%%%%%%

%%%
%%  camino(S, Res):
%%%
%%  Se pide que existan caminos desde algun vecino
%%  hasta "c" recursivamente, haciendo que la transitividad
%%  describa el camino hasta "c".
%%%
%%  S : Planeta de inicio
%%  Res : Lista que representa el camino desde "S" al centro galactico "c".
%%%
camino(c, [c]).%             <-  % Sabiendo que el camino para llegar a "c" es solo [c].
camino(S, Res) :-%           <-  % Existe un camino "Res" de "S" a "c" si y solo si
    puente(S, T, _),%        <-  % Existe un "T" talque hay un puente S -> T de peso cualquiera
    camino(T, TRes),%        <-  % Talque existe un camino "TRes" de "T" a "c"
    append([S], TRes, Res).% <-  % Talque el camino "Res" es simplemente "S" seguido del camino "TRes"


%%%%%%%%%%%%%%         %%%%%%%%%%%%%%%%
%%%%%%%%%%%% COMBUSTIBLE %%%%%%%%%%%%%%
%%%%%%%%%%%%%%         %%%%%%%%%%%%%%%%

%%%
%%  combustible(S, V, RES):
%%%
%%  Se utiliza la misma logica de transitividad que en camino(S, Res),
%%  con la diferencia de que se pide que la secuencia de pares del vecino
%%  parta con un combustible restante V_i - W_ij mayor que cero.
%%%
%%  S : Planeta de inicio
%%  V : Combustible restante
%%  Res : Lista de listas que representa el conjunto de pares pedido
%%%
combustible(c, V, [[c, V]]).%       <-  % Sabiendo que el combustible restante estando en "c" para llegar a "c"
                                        % dado un combustible "V" es "V".
combustible(S, V, RES) :-%          <-  % Existe una secuencia de pares (Planeta, combustible restante) "RES" que nos
                                        % llevan de "S" a "c" con un combustible inicial de "V" si y solo si
    puente(S, T, W),%               <-  % Existe un "T" talque hay un puente S -> T de peso "W"
    Vrest is V - W,%                <-  % Talque el combustible restante para el siguiente par es Vrest = V - M
    Vrest >= 0,%                    <-  % Con el combustible restante "Vrest" definido positivo
    combustible(T, Vrest, TRES),%   <-  % Tal que existe una secuencia de pares (Planeta, combustible restante) "TRES"
                                        % que nos llevan de "T" a "c"
    append([[S, V]], TRES, RES).%   <-  % Tal que la secuencia de pares (Planeta, combustible restante) "RES" no es mas que
                                        % el par (S, V) seguido de la secuencia "TRES"
