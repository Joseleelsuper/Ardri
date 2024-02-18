// Paquete en donde se encuentra el archivo.
package tafl.excepcion;

/**
 * Clase TipoArbitroException. Excepción que se lanza cuando se intenta crear un
 * arbitro con un tipo incorrecto.
 * 
 * @author <a href="mailto:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/11/30
 */
public class TipoArbitroException extends Exception {
	/**
	 * Serial version UID. Sirve para identificar la versión de la clase.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor 1. Sin parámetros.
	 */
	public TipoArbitroException() {
		super("Tipo de arbitro incorrecto");
	}

	/**
	 * Constructor 2. Con parámetros.
	 * 
	 * @param mensaje Mensaje de error.
	 */
	public TipoArbitroException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Constructor 3. Con parámetros.
	 * 
	 * @param causa Causa del error.
	 */
	public TipoArbitroException(Throwable causa) {
		super(causa);
	}

	/**
	 * Constructor 4. Con parámetros.
	 * 
	 * @param mensaje Mensaje de error.
	 * @param causa   Causa del error.
	 */
	public TipoArbitroException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

}