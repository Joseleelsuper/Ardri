// Paquete en donde se encuentra el archivo.
package tafl.util;

/**
 * Clase TipoPieza. A cada pieza se le asigna un rol, que será aliado, enemigo o
 * rey.
 * 
 * @author <a href="jose:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/10/25
 */
public enum TipoPieza {
	/**
	 * Pieza defensor. Son las piezas blancas, se encargan de defender al rey.
	 */
	DEFENSOR('D', Color.BLANCO),
	/**
	 * Pieza atacante. Son las piezas negras, tienen que acabar con el rey.
	 */
	ATACANTE('A', Color.NEGRO),
	/**
	 * Pieza Rey. Su objetivo es llegar a alguna provincia. Está en el bando blanco.
	 */
	REY('R', Color.BLANCO);

	/**
	 * Carácter para identificar a la pieza.
	 */
	private char caracter;
	/**
	 * Color de la pieza a identificar.
	 */
	private Color color;

	/**
	 * Constructor de la clase.
	 * 
	 * @param caracter Carácter para identificar a la pieza.
	 * @param color    Color de la pieza a identificar.
	 */
	private TipoPieza(char caracter, Color color) {
		this.caracter = caracter;
		this.color = color;
	}

	/**
	 * Consultamos el color de la pieza, para saber si es negra o blanca.
	 * 
	 * @return this.color Devuelve el color de la pieza.
	 */
	public Color consultarColor() {
		return this.color;
	}

	/**
	 * toChar, convierte el color a carácter.
	 * 
	 * @return this.caracter Devuelve el carácter de la pieza.
	 */
	public char toChar() {
		return this.caracter;
	}

	@Override
	public String toString() {
		return String.valueOf(this.caracter);
	}
}
