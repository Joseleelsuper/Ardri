// Paquete en donde se encuentra el archivo.
package tafl.control;

import java.util.List;
import java.util.Stack;

import tafl.excepcion.CoordenadasIncorrectasException;
import tafl.modelo.Celda;
import tafl.modelo.Jugada;
import tafl.modelo.Pieza;
import tafl.modelo.Tablero;
import tafl.util.TipoPieza;
import tafl.util.Color;
import tafl.util.Coordenada;
import tafl.util.TipoCelda;

/**
 * Clase ArbitroAbstracto. Clase abstracta que implementa la interfaz Arbitro.
 * Define las operaciones que debe tener un arbitro. Es el encargado de
 * controlar el juego. Se encarga de comprobar si un movimiento es legal, de
 * realizarlo, de comprobar si se ha ganado, etc.
 * 
 * @author <a href="mailto:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/11/30
 */
public abstract class ArbitroAbstracto implements Arbitro 
{
	/**
	 * Pila que almacena el historial de jugadas.
	 */
	private Stack<Registro> historial;
	/**
	 * Número de filas del tablero. No se puede modificar.
	 */
	private final int NUMERO_FILAS = 7;
	/**
	 * Número de columnas del tablero. No se puede modificar.
	 */
	private final int NUMERO_COLUMNAS = 7;
	/**
	 * Tablero del juego.
	 */
	protected Tablero tablero;
	/**
	 * Número de jugadas que se han realizado. Se inicializa a 0 y sirve para
	 * consultar el turno.
	 */
	private int jugadas;
	/**
	 * Última jugada realizada.
	 */
	private Jugada ultimaJugada;
	/**
	 * Indica si el rey ha sido eliminado.
	 */
	private boolean reyEliminado = false;

	/**
	 * Color del turno actual.
	 */
	protected Color turno;

	/**
	 * Constructor de la clase. Si el tablero es nulo lanza una excepción no
	 * comprobable IllegalArgumentException.
	 * 
	 * @param tablero Tablero del juego.
	 */
	public ArbitroAbstracto(Tablero tablero) {
		this.tablero = tablero;
		if (tablero == null) {
			throw new IllegalArgumentException("Tablero nulo");
		}
		this.jugadas = 0;
		this.historial = new Stack<Registro>();
	}

	/**
	 * Cambia el turno al otro contrincante.
	 */
	public void cambiarTurno() {
		if (turno == Color.NEGRO) {
			turno = Color.BLANCO;
		} else {
			turno = Color.NEGRO;
		}
		jugadas++;
	}

	/**
	 * Coloca piezas correspondientes a un array de tipos de pieza, con sus
	 * coordenadas correspondientes, e inicializando el turno actual al color
	 * indicado. Su uso está destinado a los tests automáticos y a las pruebas
	 * adicionales que se quieran hacer (e.g. en un método main en la propia clase),
	 * inicializando una partida con las piezas colocadas discrecionalmente. Si
	 * alguno de los argumentos es nulo, lanza una excepción no comprobable
	 * IllegalArgumentException. Si alguna de las coordenadas está fuera del tablero
	 * lanza excepción CoordenadasIncorrectasException.
	 * 
	 * @param tipo        Tipo de pieza.
	 * @param coordenadas Coordenadas de la pieza.
	 * @param turno       Turno actual..
	 * @throws CoordenadasIncorrectasException Lanza excepción de coordenadas
	 *                                         incorrectas.
	 */
	public void colocarPiezas(TipoPieza[] tipo, int[][] coordenadas, Color turno)
			throws CoordenadasIncorrectasException {
		if (tipo == null || coordenadas == null || turno == null) {
			throw new IllegalArgumentException("Argumento nulo");
		}

		this.turno = turno;

		for (int i = 0; i < tipo.length; i++) {
			for (int j = 0; j < coordenadas[i].length; j++) {
				Coordenada coordenada = new Coordenada(coordenadas[i][j], coordenadas[i][j + 1]);

				if (coordenada.fila() < 0 || coordenada.fila() > NUMERO_FILAS - 1 || coordenada.columna() < 0
						|| coordenada.columna() > NUMERO_COLUMNAS - 1) {
					throw new CoordenadasIncorrectasException("Coordenadas incorrectas");
				}
				tablero.colocar(new Pieza(tipo[i]), coordenada);
				j++;
			}
		}
	}

	/**
	 * coloca las piezas correspondientes a la configuración de inicio del juego con
	 * sus 13 piezas (ver Ilustración 1), e inicializando siempre el turno para el
	 * atacante con piezas negras.
	 */
	public void colocarPiezasConfiguracionInicial() {
	}

	/**
	 * Consulta el número de jugadas que se han realizado.
	 * 
	 * @return jugadas Número de jugadas.
	 */
	public int consultarNumeroJugada() {
		return this.jugadas;
	}

	/**
	 * Devuelve un clon en profundidad del tablero actual.
	 * 
	 * @return tablero Clon en profundidad del tablero.
	 */
	public Tablero consultarTablero() {
		return this.tablero;
	}

	/**
	 * Devuelve el turno actual, que puede realizar la siguiente jugada.
	 * 
	 * @return color Color del turno.
	 * 
	 */
	public Color consultarTurno() {
		return turno;
	}

	/**
	 * Comprueba la legalidad de la jugada, según las reglas del juego. Debe aplicar
	 * solo las reglas de los movimientos descritas en la Sec. 1., pero sin
	 * comprobar si la partida ha finalizado previamente o no. Si la jugada vale
	 * nulo, lanza una excepción no comprobable IllegalArgumentException.
	 * 
	 * @param jugada Tipo de jugada.
	 * @return esMovimientoLegal Será true o false.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 */
	public boolean esMovimientoLegal(Jugada jugada) throws CoordenadasIncorrectasException {
		if (jugada == null) {
			throw new IllegalArgumentException("Jugada nula");
		}
		boolean esMovimientoLegal = true;
		Celda origen = jugada.consultarOrigen();
		Celda destino = jugada.consultarDestino();
		Pieza pieza = destino.consultarPieza();
		// En modo grafico, me saca null en "origen", por lo que lo he puesto asi
		// para que funcione en ambos modos. IMPRESIONANTE DIOS.
		if (pieza == null) {
			pieza = origen.consultarPieza();
			if (pieza == null) {
				esMovimientoLegal = false;
				return esMovimientoLegal;
			}
		}
		Color color = pieza.consultarColor();
		// Se hacen las siguientes comprobaciones:
		// Comprobar que la celda origen no está vacía.
		// Comprobar si se está moviendo una pieza del mismo color que el turno.
		// Comprobar que no se está intentado superponer una pieza encima de otra.
		// Comprobar que el movimiento es horizontal o vertical.
		// Comprobar que el color de la pieza es el mismo que el color del turno.
		if (origen.consultarPieza() == null || pieza.consultarColor() != color
				|| jugada.consultarDestino().consultarPieza() != null || !jugada.esMovimientoHorizontalOVertical()
				|| color != turno) {
			esMovimientoLegal = false;
		}
		// Comprobar que las piezas que no son reyes se mueven hacia alguna provincia o
		// trono
		switch (destino.consultarTipoCelda()) {
		case PROVINCIA:
		case TRONO:
			if (pieza.consultarTipoPieza() != TipoPieza.REY) {
				esMovimientoLegal = false;
			}
			break;
		default:
			break;
		}
		if (esMovimientoLegal) {
			// Comprueba que se mueven de 1 en 1 o varias celdas, dependiendo del juego.
			esMovimientoLegal = consultarPiezaHorizontal(esMovimientoLegal, jugada, origen);
			esMovimientoLegal = consultarPiezaVertical(esMovimientoLegal, jugada, origen);
		}
		// Para evitar que se alarge la comprobación, comprobamos que no es false.
		if (esMovimientoLegal) {
			// Comprobar que no hay piezas entre medias del origen y el destino.
			esMovimientoLegal = consultarMoverEntreDosPiezasOtroColor(esMovimientoLegal, jugada, destino, pieza);
		}
		return esMovimientoLegal;
	}

	/**
	 * Comprueba la pieza se mueve de 1 en 1 celda en horizontal.
	 * 
	 * @param esMovimientoLegal Es true o false.
	 * @param jugada            Tipo de jugada.
	 * @param origen            Celda origen.
	 * @return esMovimientoLegal Será true o false.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 * @see esMovimientoLegal Ver la función esMovimientoLegal.
	 */
	protected boolean consultarPiezaHorizontal(boolean esMovimientoLegal, Jugada jugada, Celda origen)
			throws CoordenadasIncorrectasException {
		return esMovimientoLegal;
	}

	/**
	 * Comprueba que no hay piezas entre el origen y el destino en vertical.
	 * 
	 * @param esMovimientoLegal Es true o false.
	 * @param jugada            Tipo de jugada.
	 * @param origen            Celda origen.
	 * @return esMovimientoLegal Será true o false.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 * @see esMovimientoLegal Ver la función esMovimientoLegal.
	 */
	protected boolean consultarPiezaVertical(boolean esMovimientoLegal, Jugada jugada, Celda origen)
			throws CoordenadasIncorrectasException {
		return esMovimientoLegal;
	}

	/**
	 * Comprueba que no se está intentando mover una pieza entre dos piezas de otro
	 * color.
	 * 
	 * @param esMovimientoLegal Es true o false.
	 * @param jugada            Tipo de jugada.
	 * @param destino           Celda destino.
	 * @param pieza             Pieza, para el color.
	 * @return esMovimientoLegal Será true o false.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 * @see esMovimientoLegal Ver la función esMovimientoLegal.
	 */
	private boolean consultarMoverEntreDosPiezasOtroColor(boolean esMovimientoLegal, Jugada jugada, Celda destino,
			Pieza pieza) throws CoordenadasIncorrectasException {
		// Si una pieza intenta moverse al espacio entre dos piezas de otro color, no se
		// puede.
		List<Celda> celdasContiguas = tablero.consultarCeldasContiguas(destino.consultarCoordenada());
		for (int i = 0; i < celdasContiguas.size(); i++) {
			if (!sePuedeComer(celdasContiguas.get(i), pieza.consultarColor(),
					jugada.consultarDestino().consultarCoordenada())) {
				esMovimientoLegal = consultarMoverEntreDosPiezasOtroColorIguales(esMovimientoLegal, celdasContiguas,
						pieza);
				if (!esMovimientoLegal) {
					break;
				}
			} else if (sePuedeComerREY(celdasContiguas.get(i), pieza.consultarColor(),
					jugada.consultarDestino().consultarCoordenada())
					&& celdasContiguas.get(i).consultarPieza().consultarTipoPieza() != TipoPieza.REY) {
				esMovimientoLegal = false;
			}
		}
		return esMovimientoLegal;
	}

	/**
	 * Comprueba que no se está intentando mover una pieza entre dos piezas de otro
	 * color y las celdas especiales. Es para no repetir 3 veces el mismo código en
	 * la función consultarMoverEntreDosPiezasOtroColor.
	 * 
	 * @param esMovimientoLegal Es true o false.
	 * @param celdasContiguas   Celdas contiguas.
	 * @param pieza             Pieza, para el color.
	 * @return esMovimientoLegal Será true o false.
	 * @see consultarMoverEntreDosPiezasOtroColor Ver la función
	 *      consultarMoverEntreDosPiezasOtroColor.
	 */
	private boolean consultarMoverEntreDosPiezasOtroColorIguales(boolean esMovimientoLegal, List<Celda> celdasContiguas,
			Pieza pieza) {
		for (int i = 0; i < celdasContiguas.size(); i++) {
			for (int j = 0; j < celdasContiguas.size(); j++) {
				if (celdasContiguas.get(i) == null || celdasContiguas.get(j) == null) {
					esMovimientoLegal = true;
					break;
				}
				boolean piezasNoNulas = celdasContiguas.get(i).consultarPieza() != null
						&& celdasContiguas.get(j).consultarPieza() != null;
				boolean piezasIguales = celdasContiguas.get(i).consultarColorDePieza() == celdasContiguas.get(j)
						.consultarColorDePieza();
				boolean coloresDiferentes = true;
				if (celdasContiguas.get(i) != null && celdasContiguas.get(j) != null) {
					coloresDiferentes = celdasContiguas.get(i).consultarColorDePieza() != pieza.consultarColor()
							&& celdasContiguas.get(j).consultarColorDePieza() != pieza.consultarColor();
				}
				boolean celdasEspeciales = (celdasContiguas.get(i).consultarTipoCelda() == TipoCelda.PROVINCIA
						|| celdasContiguas.get(j).consultarTipoCelda() == TipoCelda.PROVINCIA)
						|| ((celdasContiguas.get(i).consultarTipoCelda() == TipoCelda.TRONO
								|| celdasContiguas.get(j).consultarTipoCelda() == TipoCelda.TRONO));
				boolean coordenadasDiferentes = celdasContiguas.get(i).consultarCoordenada() != celdasContiguas.get(j)
						.consultarCoordenada();
				boolean mismaFilaOColumna = celdasContiguas.get(i).consultarCoordenada().fila() == celdasContiguas
						.get(j).consultarCoordenada().fila()
						|| celdasContiguas.get(i).consultarCoordenada().columna() == celdasContiguas.get(j)
								.consultarCoordenada().columna();
				if (piezasNoNulas && piezasIguales && (coloresDiferentes || celdasEspeciales) && coordenadasDiferentes
						&& mismaFilaOColumna) {
					esMovimientoLegal = false;
					break;
				} else {
					esMovimientoLegal = true;
				}
			}
		}
		return esMovimientoLegal;
	}

	/**
	 * Comprueba si el rey ha sido capturado tras el último movimiento. En caso
	 * afirmativo.
	 * 
	 * @return haGanadoAtacante Será true o false.
	 */
	public boolean haGanadoAtacante() {
		return this.reyEliminado;
	}

	/**
	 * Comprueba si el rey ha alcanzado alguna de las cuatro provincias tras el
	 * último movimiento.
	 * 
	 * @return haGanadoRey Será true o false.
	 */
	public boolean haGanadoRey() {
		boolean haGanadoRey = false;
		// hacer una copia del tablero y ver si la pieza rey se encuentra en alguna de
		// las provincias
		if (this.reyEliminado == false) {
			Tablero tablero = consultarTablero();
			Coordenada coordenada;
			for (int i = 0; i < NUMERO_FILAS; i++) {
				for (int j = 0; j < NUMERO_COLUMNAS; j++) {
					coordenada = new Coordenada(i, j);
					Celda celda = null;
					try {
						celda = tablero.consultarCelda(coordenada);
					} catch (CoordenadasIncorrectasException e) {
						e.printStackTrace();
					}
					if (celda.consultarPieza() != null
							&& celda.consultarPieza().consultarTipoPieza() == TipoPieza.REY) {
						if (i == 0 || i == NUMERO_FILAS - 1 || j == 0 || j == NUMERO_COLUMNAS - 1) {
							haGanadoRey = true;
							// Salimos del bucle
							break;
						}
					}
				}
				if (haGanadoRey) {
					// Salimos del bucle
					break;
				}
			}
		}
		return haGanadoRey;
	}

	/**
	 * Realiza la jugada, asumiendo que previamente ya se habrá comprobado su
	 * legalidad (no es necesario volverlo a comprobar). Se mueve la pieza de origen
	 * a destino. Dado que es necesario posteriormente comprobar cual ha sido el
	 * último movimiento, este sería también el método adecuado para almacenar dicha
	 * información. Si la jugada vale nulo, lanza una excepción no comprobable
	 * IllegalArgumentException. Si las coordenadas de la jugada no fueran válidas
	 * lanza una excepción CoordenadasIncorrectasException
	 * 
	 * @param jugada Jugada a realizar.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 */
	public void mover(Jugada jugada) throws CoordenadasIncorrectasException {
		// Guarda el estado actual del juego antes de realizar la jugada
		Tablero tableroActual = tablero.clonar();
		Registro registro = new Registro(tableroActual, jugada);
		historial.push(registro);
	
		Celda origen = jugada.consultarOrigen();
		Celda destino = jugada.consultarDestino();
	
		try {
			Pieza pieza = origen.consultarPieza();
			tablero.eliminarPieza(origen.consultarCoordenada());
			tablero.colocar(pieza, destino.consultarCoordenada());
			this.ultimaJugada = jugada;
			jugadas++;
		} catch (CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("Coordenadas incorrectas");
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Jugada nula");
		}
	}

	/**
	 * Retira del tablero las piezas capturadas tras el último movimiento.
	 * 
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 */
	public void realizarCapturasTrasMover() throws CoordenadasIncorrectasException {
		Celda origen = ultimaJugada.consultarOrigen();
		Celda destino = ultimaJugada.consultarDestino();
		Coordenada coordenadaDestino = destino.consultarCoordenada();
		Pieza pieza = destino.consultarPieza();
		// Por algun motivo, en textui me saca que la pieza es null en "destino".
		// En modo grafico, me saca null en "origen", por lo que lo he puesto asi
		// para que funcione en ambos modos. IMPRESIONANTE DIOS.
		if (pieza == null) {
			pieza = origen.consultarPieza();
		}
		Color colorPieza = pieza.consultarColor();
		List<Celda> celdasContiguas = tablero.consultarCeldasContiguas(coordenadaDestino);
		// Recorremos las celdas hasta encontrar una con una pieza de distinto color
		for (int i = 0; i < celdasContiguas.size(); i++) {
			try {
				if (sePuedeComer(celdasContiguas.get(i), colorPieza, coordenadaDestino)
						&& celdasContiguas.get(i).consultarPieza().consultarTipoPieza() != TipoPieza.REY) {
					tablero.eliminarPieza(celdasContiguas.get(i).consultarCoordenada());
				} else if (sePuedeComerREY(celdasContiguas.get(i), colorPieza, coordenadaDestino)
						&& celdasContiguas.get(i).consultarPieza().consultarTipoPieza() == TipoPieza.REY) {
					this.reyEliminado = true;
				}
			} catch (NullPointerException e) {
				tablero.eliminarPieza(celdasContiguas.get(i).consultarCoordenada());
			}
		}
	}

	/**
	 * Comprueba si, tras moverse la pieza seleccionada, se puede comer una pieza.
	 * 
	 * @param celdaContigua     Celda contigua a la pieza.
	 * @param colorPieza        Color de la pieza.
	 * @param coordenadaDestino Coordenada destino de la pieza.
	 * @return sePuedeComer Será true o false.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 * @see realizarCapturasTrasMover Ver la función realizarCapturasTrasMover.
	 */
	private boolean sePuedeComer(Celda celdaContigua, Color colorPieza, Coordenada coordenadaDestino)
			throws CoordenadasIncorrectasException {
		boolean sePuedeComer = false;
		// Comprobamos que la celda contigua no sea null
		if (celdaContigua != null) {
			if (celdaContigua.consultarPieza() != null && celdaContigua.consultarColorDePieza() != colorPieza) {
				List<Celda> celdaAmiga = tablero.consultarCeldasContiguas(celdaContigua.consultarCoordenada());
				// Comprobamos si la celda amiga es una pieza del mismo color y si se encuentra
				// en la misma fila o columna, pero distinta coordenada
				for (int i = 0; i < celdaAmiga.size(); i++) {
					boolean esColorAmigoOProvincia = celdaAmiga.get(i).consultarColorDePieza() == colorPieza
							|| celdaAmiga.get(i).consultarTipoCelda() == TipoCelda.PROVINCIA;
					boolean esTronoVacio = celdaAmiga.get(i).consultarTipoCelda() == TipoCelda.TRONO
							&& celdaAmiga.get(i).consultarPieza() == null;
					boolean mismaFilaOColumna = celdaAmiga.get(i).consultarCoordenada().fila() == coordenadaDestino
							.fila() || celdaAmiga.get(i).consultarCoordenada().columna() == coordenadaDestino.columna();
					boolean coordenadasDiferentes = !celdaAmiga.get(i).consultarCoordenada().equals(coordenadaDestino);
					if ((esColorAmigoOProvincia || esTronoVacio) && mismaFilaOColumna && coordenadasDiferentes) {
						sePuedeComer = true;
						break;
					}
				}
			}
		}
		return sePuedeComer;
	}

	/**
	 * Comprueba si la pieza en la celda Contigua es la pieza tipo REY, y si se
	 * puede comer.
	 * 
	 * @param celdaContigua     Celda Contigua.
	 * @param colorPieza        Color de la Pieza.
	 * @param coordenadaDestino Coordenada de destino.
	 * @return sePuedeComer Si se puede comer al rey.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 * @see realizarCapturasTrasMover Ver la función realizarCapturasTrasMover.
	 */
	private boolean sePuedeComerREY(Celda celdaContigua, Color colorPieza, Coordenada coordenadaDestino)
			throws CoordenadasIncorrectasException {
		boolean sePuedeComer = false;
		int fila = celdaContigua.consultarCoordenada().fila();
		int columna = celdaContigua.consultarCoordenada().columna();
		// Comprobar que se enuentra en el trono
		if (celdaContigua.consultarTipoCelda() == TipoCelda.TRONO) {
			sePuedeComer = sePuedeComerREYTrono(celdaContigua, colorPieza, coordenadaDestino);
		} else if ((fila == 2 && columna == 3) || (fila == 3 && (columna == 2 || columna == 4))
				|| (fila == 4 && columna == 3)) {
			sePuedeComer = sePuedeComerREYAyacenteTrono(celdaContigua, coordenadaDestino);
		} else {
			sePuedeComer = sePuedeComer(celdaContigua, colorPieza, coordenadaDestino);
		}
		return sePuedeComer;
	}

	/**
	 * Comprueba si se puede comer al rey en el trono.
	 * 
	 * @param celdaContigua     Celda contigua.
	 * @param colorPieza        Color de la pieza.
	 * @param coordenadaDestino Coordenada de destino.
	 * @return sePuedeComer Si se puede comer al rey.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 * @see sePuedeComerREY Ver la función sePuedeComerREY.
	 */
	private boolean sePuedeComerREYTrono(Celda celdaContigua, Color colorPieza, Coordenada coordenadaDestino)
			throws CoordenadasIncorrectasException {
		boolean sePuedeComer = false;
		List<Celda> celdasContiguasTrono = tablero.consultarCeldasContiguas(celdaContigua.consultarCoordenada());
		int contador = 0;
		for (int i = 0; i < celdasContiguasTrono.size(); i++) {
			if (celdasContiguasTrono.get(i).consultarColorDePieza() == Color.NEGRO) {
				contador++;
			}
			if (contador == celdasContiguasTrono.size()) {
				sePuedeComer = true;
			}
		}
		return sePuedeComer;
	}

	/**
	 * Comprueba si se puede comer al rey en alguna celda ayacente al trono.
	 * 
	 * @param celdaContigua     Celda Contigua.
	 * @param coordenadaDestino Coordenada de destino.
	 * @return sePuedeComer Si se puede comer al rey o no.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 * @see sePuedeComerREY Ver la función sePuedeComerREY.
	 */
	private boolean sePuedeComerREYAyacenteTrono(Celda celdaContigua, Coordenada coordenadaDestino)
			throws CoordenadasIncorrectasException {
		boolean sePuedeComer = false;
		List<Celda> celdasContiguasTrono = tablero.consultarCeldasContiguas(celdaContigua.consultarCoordenada());
		int contador = 0;
		for (int i = 0; i < celdasContiguasTrono.size(); i++) {
			if (celdasContiguasTrono.get(i).consultarColorDePieza() == Color.NEGRO) {
				contador++;
			}
			if (contador == celdasContiguasTrono.size() - 1) {
				sePuedeComer = true;
			}
		}
		return sePuedeComer;
	}

	/**
	 * El método retroceder permite volver al estado previo de la partida, deshaciendo el efecto de la
	 * última jugada, controlando el correcto número de jugadas, turno, estado del tablero, etc.
	 */
	public void retroceder() {
		if (historial.size() > 0) {
			Registro registro = historial.pop();
			// Restaura el estado del tablero y la jugada
			cambiarTurno();
			tablero = registro.tablero();
			ultimaJugada = registro.jugada();
		}
	}
}