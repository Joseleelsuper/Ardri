// Paquete en donde se encuentra el archivo.
package tafl.control;

import tafl.excepcion.CoordenadasIncorrectasException;
import tafl.modelo.Jugada;
import tafl.modelo.Tablero;
import tafl.util.TipoPieza;
import tafl.util.Color;

/**
 * Interfaz Arbitro. Define las operaciones que debe tener un arbitro. Es el
 * encargado de controlar el juego. Se encarga de comprobar si un movimiento es
 * legal, de realizarlo, de comprobar si se ha ganado, etc.
 * 
 * @author <a href="jose:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/11/30
 */
public interface Arbitro {
	/**
	 * Método cambiarTurno. Cambia el turno.
	 */
	public void cambiarTurno();

	/**
	 * Método colocarPiezas. Coloca las piezas en el tablero.
	 * 
	 * @param tipo        Tipo de pieza.
	 * @param coordenadas Coordenadas de la pieza.
	 * @param turnoActual Turno actual.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 */
	public void colocarPiezas(TipoPieza[] tipo, int[][] coordenadas, Color turnoActual)
			throws CoordenadasIncorrectasException;

	/**
	 * Método colocarPiezasConfiguracionInicial. Coloca las piezas en el tablero en
	 * la configuración inicial.
	 */
	public void colocarPiezasConfiguracionInicial();

	/**
	 * Método consultarNumeroJugada. Consulta el número de jugada.
	 * 
	 * @return Número de jugada.
	 */
	public int consultarNumeroJugada();

	/**
	 * Método consultarTablero. Consulta el tablero.
	 * 
	 * @return Tablero.
	 */
	public Tablero consultarTablero();

	/**
	 * Método consultarTurno. Consulta el turno.
	 * 
	 * @return Turno.
	 */
	public Color consultarTurno();

	/**
	 * Método esMovimientoLegal. Comprueba si un movimiento es legal.
	 * 
	 * @param jugada Jugada a comprobar.
	 * @return Si el movimiento es legal.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 */
	public boolean esMovimientoLegal(Jugada jugada) throws CoordenadasIncorrectasException;

	/**
	 * Método haGanadoAtacante. Comprueba si ha ganado el atacante.
	 * 
	 * @return Si ha ganado el atacante.
	 */
	public boolean haGanadoAtacante();

	/**
	 * Método haGanadoRey. Comprueba si ha ganado el rey.
	 * 
	 * @return Si ha ganado el rey.
	 */
	public boolean haGanadoRey();

	/**
	 * Método mover. Mueve una pieza.
	 * 
	 * @param jugada Jugada a realizar.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 */
	public void mover(Jugada jugada) throws CoordenadasIncorrectasException;

	/**
	 * Método realizarCapturasTrasMover. Realiza las capturas tras mover.
	 * 
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 */
	public void realizarCapturasTrasMover() throws CoordenadasIncorrectasException;

	/**
	 * Método retroceder. Retrocede una jugada.
	 */
	public void retroceder();
}
