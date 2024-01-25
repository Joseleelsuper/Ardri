// Paquete en donde se encuentra el archivo.
package tafl.modelo;

import tafl.util.Sentido;

/**
 * Clase Jugada, representa si una jugada es válida o no.
 * 
 * @author <a href="jose:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/10/25
 * 
 * @param origen  Coordenada origen de la jugada.
 * @param destino Coordenada destino de la jugada.
 */
public record Jugada(Celda origen, Celda destino) {
	/**
	 * Constructor de la clase.
	 * 
	 * @param origen  Coordenada origen de la jugada.
	 * @param destino Coordenada destino de la jugada.
	 */
	public Jugada(Celda origen, Celda destino) {
		this.origen = origen;
		this.destino = destino;
	}

	/**
	 * Devuelve el sentido correspondiente a sus coordenadas origen y destino, o
	 * nulo si el sentido no es ninguno de los cuatro permitido en este juego (i.e.
	 * horizontal este, horizontal oeste, vertical norte y vertical sur).
	 * 
	 * @return sentido Sentido de la jugada.
	 */
	public Sentido consultarSentido() {
		Sentido sentido = null;
		if (origen.consultarCoordenada().fila() == destino.consultarCoordenada().fila()) {
			if (origen.consultarCoordenada().columna() < destino.consultarCoordenada().columna()) {
				sentido = Sentido.HORIZONTAL_E;
			} else {
				sentido = Sentido.HORIZONTAL_O;
			}
		} else if (origen.consultarCoordenada().columna() == destino.consultarCoordenada().columna()) {
			if (origen.consultarCoordenada().fila() < destino.consultarCoordenada().fila()) {
				sentido = Sentido.VERTICAL_S;
			} else {
				sentido = Sentido.VERTICAL_N;
			}
		}
		return sentido;
	}

	/**
	 * Devuelve true si el movimiento es en horizontal o vertical (en uno de los
	 * cuatros sentidos legales), y false en caso contrario.
	 * 
	 * @return esMovimientoHorizontalOVertical True si el movimiento es en
	 *         horizontal o vertical.
	 */
	public boolean esMovimientoHorizontalOVertical() {
		boolean esMovimientoHorizontalOVertical = false;
		if (consultarSentido() == Sentido.HORIZONTAL_E || consultarSentido() == Sentido.HORIZONTAL_O
				|| consultarSentido() == Sentido.VERTICAL_N || consultarSentido() == Sentido.VERTICAL_S) {
			esMovimientoHorizontalOVertical = true;
		}
		return esMovimientoHorizontalOVertical;
	}

	/**
	 * Devuelve la coordenada origen de la jugada.
	 * 
	 * @return origen Coordenada origen de la jugada.
	 */
	public Celda consultarOrigen() {
		return origen;
	}

	/**
	 * Devuelve la coordenada destino de la jugada.
	 * 
	 * @return destino Coordenada destino de la jugada.
	 */
	public Celda consultarDestino() {
		return destino;
	}
}
