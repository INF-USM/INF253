package lib.interfaz.anim;

import java.util.Arrays;

import lib.interfaz.Animacion;
import lib.interfaz.ascii.Asentamientos;
/*  CASA SATELITAL
========================================================================= 73 wide
  /-/-/-/-/             |
  /-/-/-/-/         /---/---/
      |                 x         |       
   /--/--/              x     /---/---/   
      x       .       --x--       x       
     -x       |         x         x       
      x       |       __x         x       
      x_______|_______||x         x       
     /                ||x\      /   \     
    /----------------/||x \   /___ ___\   
   | [ ] [ ] ## [  ]| ||x  | ||  | |  ||  
   |         ##     | ||x  | ||  | |  ||  
-------------//--------------------------- 
*/
import lib.interfaz.ascii.FinFrames;
import lib.interfaz.ascii.Landscapes;

/* LANDSCAPE
========================================================================= 73 wide
                .                                            .
     *   .                  .              .        .   *          .
  .         .                     .       .           .      .        .
        o                             .                   .
         .              .                  .           .
          0     .
                 .          .                 ,                ,    ,
 .          \          .                         .
      .      \   ,
   .          o     .                 .                   .            .
     .         \                 ,             .                .
               #\##\#      .                              .        .
             #  #O##\###                .                        .
   .        #*#  #\##\###                       .                     ,
        .   ##*#  #\##\##               .                     .
      .      ##*#  #o##\#         .                             ,       .
          .     *#  #\#     .                    .             .         
                      \          .                         .
____^/\___^--____/\____O______________/\/\---/\___________---____________
   /\^   ^  ^    ^                  ^^ ^  '\ ^          ^       ---
         --           -            --  -      -         ---  __       ^
   --  __                      ___--  ^  ^                         --  __
*/

/* COMBINACION
========================================================================= 73 wide
     *   .                  .              .        .   *          .
        o                             .                   .
         .              .                  .           .
          0     .
                 .          .                 ,                ,    ,
 .          \          .      /-/-/-/-/             |
      .      \   ,            /-/-/-/-/         /---/---/
   .          o     .             |         .       x         |        .
     .         \               /--/--/              x     /---/---/   
               #\##\#      .      x       .       --x--       x       
             #  #O##\###         -x       |         x    ,    x       
   .        #*#  #\##\###         x       |       __x         x       ,
        .   ##*#  #\##\##         x_______|_______||x         x       
      .      ##*#  #o##\#        /                ||x\      /   \       .
          .     *#  #\#         /----------------/||x \   /___ ___\      
                      \        | [ ] [ ] ## [  ]| ||x  | ||  | |  ||  
____^/\___^--____/\____O____~  |         ##     | ||x  | ||  | |  ||~ ___
   /\^   ^  ^    ^          -------------//---------------------------
         --           -            --  -      -         ---  __       ^
   --  __                      ___--  ^  ^                         --  __
========================================================================= 73 wide
*/

public class HogarFin extends Animacion {
    public HogarFin() {
        asciiSet = Arrays.asList(
                FinFrames.TextsASCII.get(0),
                FinFrames.TextsASCII.get(1),
                "\n".repeat(5) + Landscapes.heladoASCII + "\n".repeat(4),
                FinFrames.TextsASCII.get(2),
                "\n".repeat(5) + Landscapes.oceanicoASCII + "\n".repeat(4),
                FinFrames.TextsASCII.get(3),
                "\n".repeat(5) + Landscapes.radioactivoASCII + "\n".repeat(4),
                FinFrames.TextsASCII.get(4),
                "\n".repeat(5) + Landscapes.volcanicoASCII + "\n".repeat(4),
                FinFrames.TextsASCII.get(5),
                "\n".repeat(5) + Asentamientos.heladoASCII + "\n".repeat(4),
                "\n".repeat(5) + Asentamientos.oceanicoASCII + "\n".repeat(4),
                FinFrames.TextsASCII.get(6),
                FinFrames.TextsASCII.get(7),
                FinFrames.hogarASCII);
        ansiSet = Arrays.asList(
                Arrays.asList(FinFrames.TextsANSI.get(0)),
                Arrays.asList(FinFrames.TextsANSI.get(1)),
                Arrays.asList("\n".repeat(5) + Landscapes.heladoANSI + "\n".repeat(4)),
                Arrays.asList(FinFrames.TextsANSI.get(2)),
                Arrays.asList("\n".repeat(5) + Landscapes.oceanicoANSI + "\n".repeat(4)),
                Arrays.asList(FinFrames.TextsANSI.get(3)),
                Arrays.asList("\n".repeat(5) + Landscapes.radioactivoANSI + "\n".repeat(4)),
                Arrays.asList(FinFrames.TextsANSI.get(4)),
                Arrays.asList("\n".repeat(5) + Landscapes.volcanicoANSI + "\n".repeat(4)),
                Arrays.asList(FinFrames.TextsANSI.get(5)),
                Arrays.asList("\n".repeat(5) + Asentamientos.heladoANSI + "\n".repeat(4)),
                Arrays.asList("\n".repeat(5) + Asentamientos.oceanicoANSI + "\n".repeat(4)),
                Arrays.asList(FinFrames.TextsANSI.get(6)),
                Arrays.asList(FinFrames.TextsANSI.get(7)),
                Arrays.asList(FinFrames.hogarANSI));
        frames = asciiSet.size() + 1;
    };
}
