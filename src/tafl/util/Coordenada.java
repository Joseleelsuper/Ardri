// Paquete en donde se encuentra el archivo.
package tafl.util;

/**
 * Clase Coordenada. Almacena el valor de fila y columna relativo a la posición
 * de la celda en un tablero.
 * 
 * @author <a href="mailto:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/10/25
 * 
 * @param fila    fila donde se encuentra la celda.
 * @param columna columna donde se encuentra la celda.
 */
public record Coordenada(int fila, int columna) {
}
