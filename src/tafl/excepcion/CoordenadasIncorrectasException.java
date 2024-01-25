// Paquete en donde se encuentra el archivo.
package tafl.excepcion;

/**
 * Clase CoordenadasIncorrectasException. Excepción que se lanza cuando se
 * intenta acceder a una coordenada incorrecta.
 * 
 * @author <a href="jose:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/11/30
 */
public class CoordenadasIncorrectasException extends Exception {

	/**
	 * Serial version UID. Sirve para identificar la versión de la clase.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor 1. Sin parámetros.
	 */
	public CoordenadasIncorrectasException() {
		super("Coordenadas incorrectas");
	}

	/**
	 * Constructor 2. Con parámetros.
	 * 
	 * @param mensaje Mensaje de error.
	 */
	public CoordenadasIncorrectasException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Constructor 3. Con parámetros.
	 * 
	 * @param causa Causa del error.
	 */
	public CoordenadasIncorrectasException(Throwable causa) {
		super(causa);
	}

	/**
	 * Constructor 4. Con parámetros.
	 * 
	 * @param mensaje Mensaje de error.
	 * @param causa   Causa del error.
	 */
	public CoordenadasIncorrectasException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
