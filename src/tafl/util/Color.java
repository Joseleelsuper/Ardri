// Paquete en donde se encuentra el archivo.
package tafl.util;

/**
 * Clase Color. Define los colores del turno.
 * 
 * @author <a href="jose:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/10/25
 */
public enum Color {
	/**
	 * Color Blanco.
	 */
	BLANCO('B'),
	/**
	 * Color Negro.
	 */
	NEGRO('N');

	/**
	 * Variable utilizada para saber el carácter del color dado.
	 */
	private char caracter;

	/**
	 * Constructor de la clase.
	 * 
	 * @param caracter Devuelve el carácter del color.
	 */
	private Color(char caracter) {
		this.caracter = caracter;
	}

	/**
	 * Sirve para conssultar el color contrario respecto al actual.
	 * 
	 * @return color Color a retornar dependiendo del que se sea actualmente.
	 */
	public Color consultarContrario() {
		Color color = null;
		if (caracter == 'B') {
			color = NEGRO;
		} else if (caracter == 'N') {
			color = BLANCO;
		}
		return color;
	}

	/**
	 * Devolvermos el caracter segun su color.
	 * 
	 * @return caracter Carácter del color dado.
	 */
	public char toChar() {
		return this.caracter;
	}
}
