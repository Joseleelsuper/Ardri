// Paquete en donde se encuentra el archivo.
package tafl.control;

import tafl.excepcion.CoordenadasIncorrectasException;
import tafl.modelo.Celda;
import tafl.modelo.Jugada;
import tafl.modelo.Pieza;
import tafl.modelo.Tablero;
import tafl.util.Color;
import tafl.util.Coordenada;
import tafl.util.TipoPieza;

/**
 * Clase ArbitroBrandubh. Árbitro del juego Brandubh. Se encarga de controlar el
 * juego. Funciones específicas del juego Brandubh.
 * 
 * @author <a href="mailto:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/11/30
 */
public class ArbitroBrandubh extends ArbitroAbstracto {
	/**
	 * Tablero del juego.
	 */
	protected Tablero tablero;

	/**
	 * Número de jugadas realizadas.
	 */
	protected int jugadas;

	/**
	 * Crea un nuevo árbitro para el juego Brandubh.
	 *
	 * @param tablero Tablero del juego.
	 * @throws IllegalArgumentException Si el tablero es nulo.
	 */
	public ArbitroBrandubh(Tablero tablero) {
		super(tablero);
		this.tablero = tablero;
		if (tablero == null) {
			throw new IllegalArgumentException("Tablero nulo");
		}
		this.jugadas = 0;
	}

	@Override
	public void colocarPiezasConfiguracionInicial() {
		/*
		 * Piezas en coordenadas: 7 - - - A - - - 6 - - - A - - - 5 - - - D - - - 4 A A
		 * D R D A A 3 - - - D - - - 2 - - - A - - - 1 - - - A - - - a b c d e f g
		 * Siendo A = Atacante, D = Defensor y R = Rey.
		 */
		super.turno = Color.NEGRO;
		try {
			// Atacantes
			atacantesConfiguracionInicial();
			// Defensores
			defensoresConfiguracionInicial();
			// Rey
			Pieza pieza = new Pieza(TipoPieza.REY);
			Coordenada coordenada = new Coordenada(3, 3);
			tablero.colocar(pieza, coordenada);
		} catch (CoordenadasIncorrectasException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Coloca las piezas correspondientes a la configuración de inicio del juego de
	 * los atacantes.
	 * 
	 * @throws CoordenadasIncorrectasException Lanza excepción de coordenadas
	 *                                         incorrectas.
	 * @see colocarPiezasConfiguracionInicial Ver método
	 *      colocarPiezasConfiguracionInicial.
	 */
	private void atacantesConfiguracionInicial() throws CoordenadasIncorrectasException {
		Pieza pieza = new Pieza(TipoPieza.ATACANTE);
		Coordenada coordenada;

		// Posiciones de los atacantes
		int[][] atacantesPosiciones = { { 0, 3 }, { 1, 3 }, { 3, 0 }, { 3, 1 }, { 3, 5 }, { 3, 6 }, { 5, 3 },
				{ 6, 3 } };

		// Bucle for each
		for (int[] posicion : atacantesPosiciones) {
			coordenada = new Coordenada(posicion[0], posicion[1]);
			tablero.colocar(pieza, coordenada);
		}
	}

	/**
	 * Coloca las piezas correspondientes a la configuración de inicio del juego de
	 * los defensores.
	 * 
	 * @throws CoordenadasIncorrectasException Lanza excepción de coordenadas
	 *                                         incorrectas.
	 * @see colocarPiezasConfiguracionInicial Ver método
	 *      colocarPiezasConfiguracionInicial.
	 */
	private void defensoresConfiguracionInicial() throws CoordenadasIncorrectasException {
		Pieza pieza = new Pieza(TipoPieza.DEFENSOR);
		Coordenada coordenada;

		// Posiciones de los defensores
		int[][] defensoresPosiciones = { { 4, 3 }, { 3, 2 }, { 3, 4 }, { 2, 3 } };

		for (int[] posicion : defensoresPosiciones) {
			coordenada = new Coordenada(posicion[0], posicion[1]);
			tablero.colocar(pieza, coordenada);
		}
	}

	@Override
	protected boolean consultarPiezaHorizontal(boolean esMovimientoLegal, Jugada jugada, Celda origen)
			throws CoordenadasIncorrectasException {
		switch (jugada.consultarSentido()) {
		case HORIZONTAL_E:
			for (int i = origen.consultarCoordenada().columna() + 1; i < jugada.consultarDestino().consultarCoordenada()
					.columna(); i++) {
				Coordenada coordenada = new Coordenada(origen.consultarCoordenada().fila(), i);
				if (tablero.consultarCelda(coordenada).consultarPieza() != null) {
					esMovimientoLegal = false;
				}
			}
			break;
		case HORIZONTAL_O:
			for (int i = origen.consultarCoordenada().columna() - 1; i > jugada.consultarDestino().consultarCoordenada()
					.columna(); i--) {
				Coordenada coordenada = new Coordenada(origen.consultarCoordenada().fila(), i);
				if (tablero.consultarCelda(coordenada).consultarPieza() != null) {
					esMovimientoLegal = false;
				}
			}
			break;
		default:
			break;
		}
		return esMovimientoLegal;
	}

	@Override
	protected boolean consultarPiezaVertical(boolean esMovimientoLegal, Jugada jugada, Celda origen)
			throws CoordenadasIncorrectasException {
		switch (jugada.consultarSentido()) {
		case VERTICAL_N:
			for (int i = origen.consultarCoordenada().fila() - 1; i > jugada.consultarDestino().consultarCoordenada()
					.fila(); i--) {
				Coordenada coordenada = new Coordenada(i, origen.consultarCoordenada().columna());
				if (tablero.consultarCelda(coordenada).consultarPieza() != null) {
					esMovimientoLegal = false;
				}
			}
			break;
		case VERTICAL_S:
			for (int i = origen.consultarCoordenada().fila() + 1; i < jugada.consultarDestino().consultarCoordenada()
					.fila(); i++) {
				Coordenada coordenada = new Coordenada(i, origen.consultarCoordenada().columna());
				if (tablero.consultarCelda(coordenada).consultarPieza() != null) {
					esMovimientoLegal = false;
				}
			}
			break;
		default:
			break;
		}
		return esMovimientoLegal;
	}
}