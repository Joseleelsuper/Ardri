// Paquete en donde se encuentra el archivo.
package tafl.util;

/**
 * TipoCelda nos crea qué tipo de celdas hay en el juego, las cuales tendrán
 * distintas propiedades.
 * 
 * @author <a href="jose:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/10/25
 */
public enum TipoCelda {
	/**
	 * Celda Trono, donde comienza la partida el Rey. Se encuentra en el centro del
	 * tablero.
	 */
	TRONO,
	/**
	 * Celda Provincia, el rey debe de llegar hasta aquí para ganar la partida. Se
	 * encuentra en las esquinas.
	 */
	PROVINCIA,
	/**
	 * Celda Normal, cualquier pieza puede quedarse sobre ellas.
	 */
	NORMAL;
}