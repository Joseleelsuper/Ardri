// Paquete en donde se encuentra el archivo.
package tafl.util;

/**
 * Clase Sentido, marca el sentido de la pieza, servirá para validar su
 * movimiento.
 * 
 * @author <a href="mailto:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/10/25
 */
public enum Sentido {
	/**
	 * Movimiento vertical-abajo.
	 */
	VERTICAL_N(-1, 0),
	/**
	 * Movimiento vertival-arriba.
	 */
	VERTICAL_S(1, 0),
	/**
	 * Movimiento horizontal-izquierda.
	 */
	HORIZONTAL_E(0, 1),
	/**
	 * Movimiento horizontal-derecha.
	 */
	HORIZONTAL_O(0, -1);

	/**
	 * Número de celdas que se desplaza en horizontal.
	 */
	private int desplazamientoEnFilas;
	/**
	 * Número de celdas que se desplaza en vertical.
	 */
	private int desplazamientoEnColumnas;

	/**
	 * Constructor de la clase Sentido.
	 * 
	 * @param desplazamientoEnFilas    Número de celdas que se desplaza en
	 *                                 horizontal.
	 * @param desplazamientoEnColumnas Número de celdas que se desplaza en vertical.
	 */
	private Sentido(int desplazamientoEnFilas, int desplazamientoEnColumnas) {
		this.desplazamientoEnFilas = desplazamientoEnFilas;
		this.desplazamientoEnColumnas = desplazamientoEnColumnas;
	}

	/**
	 * Consulta al número de filas que se desplaza.
	 * 
	 * @return this.desplazamientoEnFilas Número de celdas que se desplaza en
	 *         horizontal.
	 */
	public int consultarDesplazamientoEnFilas() {
		return this.desplazamientoEnFilas;
	}

	/**
	 * Consulta al número de columnas que se desplaza.
	 * 
	 * @return this.desplazamientoEnColumnas Número de celdas que se desplaza en
	 *         vertical.
	 */
	public int consultarDesplazamientoEnColumnas() {
		return this.desplazamientoEnColumnas;
	}
}
