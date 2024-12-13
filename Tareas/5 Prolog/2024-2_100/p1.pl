%%%
%%  esPalindroma(Lista, Rango):
%%%
%%  En esPalindroma se rescata el segmento central indicando
%%  que la lista se puede componer de 3 segmentos, en donde se conoce
%%  el largo de los 2 de los extremos.
%%
%%  finalmente solo se comprueba si ese segmento es su propio
%%  reverso. (es palindromo)
%%%
%%  Lista : Lista de la cual se verificara el sub-segmento.
%%  Rango : Rango en el cual se encuentra el sub-segmento.
%%%
esPalindroma(Lista, Rango) :-
    [RangoL|RangoR] = Rango,%           <-  % Siendo "RangoL" y "RangoR" los limites del Rango.
                                            % Es palindroma si y solo si

    LargoLeft is RangoL - 1,%           <-  % Existe un "LargoLeft" = L - 1
    length(Lista, LargoLista),              % y un
    LargoRight is LargoLista - RangoR,      % "LargoRight" = Largo(Lista) - R

    length(Left, LargoLeft),%           <-  % Talque El largo de los segmentos "Left" y "Right"
    length(Right, LargoRight),              % sean "LargoLeft" y "LargoRight" respectivamente

    append(Left, CenterRight, Lista),%  <-  % Talque Aquellos segmentos unidos a un segmento central "Center"
    append(Center, Right, CenterRight),     % Sean la lista "Lista"

    reverse(Center, Center).%           <-  % Y Ese segmento Center igual a su reverso, osea palindromo.


%%%%%%%%%%%%%%%%% VISUALIZACION %%%%%%%%%%%%%%%%%
%                                               %
%      Rango:    RangoL   ->  RangoR            %
%                   |           |               %
%                   v           v               %
% Lista = [ L e f t C e n t e r R i g h t ]     %
%                                               %
% LargoLeft = Largo(Left)                       %
% LargoRight = Largo(Right)                     %
%                                               %
% Center = retneC                               %
%                                               %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
