package tafl.control;

import tafl.modelo.Tablero;
import tafl.modelo.Jugada;

/**
 * Clase Registro. Registro de jugadas.
 * Almacena el tablero y la jugada aplicada sobre ese
 * tablero, para pasar al siguiente estado de la partida.
 * 
 * @author <a href="jose:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/01/14
 */
public record Registro(Tablero tablero, Jugada jugada){
}
