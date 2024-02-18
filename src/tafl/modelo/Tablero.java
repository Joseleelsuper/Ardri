// Paquete en donde se encuentra el archivo.
package tafl.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tafl.excepcion.CoordenadasIncorrectasException;
import tafl.util.*;

/**
 * Clase Tablero, representa el tablero del juego en donde se colocan las
 * piezas. [0][0] las coordenadas de la esquina superior izquierda, [0][6] las
 * coordenadas de la esquina superior derecha, [6][0] las coordenadas de la
 * esquina inferior izquierda y [6][6] las coordenadas de la esquina inferior
 * derecha. Se numera de izquierda a derecha y en sentido descendente de arriba
 * a abajo.
 * 
 * @author <a href="mailto:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/10/25
 */
public class Tablero {
	/**
	 * Varaible tipo Celda. determinará cada casilla del tablero.
	 */
	private Celda[][] matriz;
	/**
	 * Número de filas del tablero. No se puede modificar.
	 */
	public final static int NUMERO_FILAS = 7;
	/**
	 * Número de columnas del tablero. No se puede modificar.
	 */
	public final static int NUMERO_COLUMNAS = 7;

	/**
	 * Constructor de la clase Tablero. Inicializa la matriz de celdas.
	 */
	public Tablero() {
		matriz = new Celda[NUMERO_FILAS][NUMERO_COLUMNAS];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				Coordenada coordenada = new Coordenada(i, j);
				if (i == 0 && j == 0 || i == 0 && j == 6 || i == 6 && j == 0 || i == 6 && j == 6) {
					Celda celda = new Celda(coordenada, TipoCelda.PROVINCIA);
					matriz[i][j] = celda;
				} else if (i == 3 && j == 3) {
					Celda celda = new Celda(coordenada, TipoCelda.TRONO);
					matriz[i][j] = celda;
				} else {
					Celda celda = new Celda(coordenada, TipoCelda.NORMAL);
					matriz[i][j] = celda;
				}
			}
		}
	}

	/**
	 * Devuelve el estado del tablero con las piezas actualmente colocadas en
	 * formato cadena de caracteres, para mostrar en pantalla. El texto generado se
	 * debe corresponder con la salida esperada en la ejecución en modo texto del
	 * programa.
	 * 
	 * @return string String con el estado del tablero.
	 */
	public String aTexto() {
		/*
		 * Hacerlo en formato, por ejemplo: 7 A - - - - - A 6 - D - - - D - 5 - - - - -
		 * - - 4 - - - R - - - 3 - - - - - - - 2 - D - - - D - 1 A - - - - - A a b c d e
		 * f g
		 * 
		 * Siendo A = Atacante, D = Defensor, R = Rey, - = Vacío
		 */
		String string = "";
		for (int i = 0; i < matriz.length; i++) {
			string += (NUMERO_FILAS - i) + " ";
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j].consultarPieza() != null) {
					if (matriz[i][j].consultarPieza().consultarTipoPieza() == TipoPieza.ATACANTE) {
						string += "A ";
					} else if (matriz[i][j].consultarPieza().consultarTipoPieza() == TipoPieza.DEFENSOR) {
						string += "D ";
					} else if (matriz[i][j].consultarPieza().consultarTipoPieza() == TipoPieza.REY) {
						string += "R ";
					}
				} else {
					string += "- ";
				}
			}
			string += "\n";
		}
		string += "  a b c d e f g";
		return string;
	}

	/**
	 * Devuelve un clon en profundidad del tablero actual.
	 * 
	 * @return tablero Clon del tablero.
	 */
	public Tablero clonar() {
		Tablero tablero = new Tablero();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				tablero.matriz[i][j] = matriz[i][j].clonar();
			}
		}
		return tablero;
	}

	/**
	 * El método colocar coloca en la coordenada indicada la pieza pasada como
	 * argumento. Si la pieza o la coordenada valieran nulo, lanza una excepción no
	 * comprobable IllegalArgumentException. Si la coordenada no está en el tablero
	 * lanza una excepción CoordenadasIncorrectasException.
	 * 
	 * @param pieza      Pieza a colocar.
	 * @param coordenada Coordenada en la que se colocará la pieza.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 */
	public void colocar(Pieza pieza, Coordenada coordenada) throws CoordenadasIncorrectasException {
		// Comprueba si no existe pieza, entonces lanza una excepción
		if (pieza == null || pieza.consultarTipoPieza() == null || pieza.consultarColor() == null
				|| coordenada == null) {
			throw new IllegalArgumentException("Pieza o coordenada nulas");
		}

		int fila = coordenada.fila();
		int columna = coordenada.columna();

		// Comprombar que la coordenada está fuera del tablero, lanza una excepción
		if (fila < 0 || fila >= NUMERO_FILAS || columna < 0 || columna >= NUMERO_COLUMNAS) {
			throw new CoordenadasIncorrectasException("Coordenada fuera del tablero");
		}

		matriz[fila][columna].colocar(pieza);
	}

	/**
	 * Devuelve un clon en profundidad de la celda con las coordenadas indicadas. Si
	 * la coordenada vale nulo, lanza una excepción no comprobable
	 * IllegalArgumentException. Si la coordenada no está en el tablero lanza una
	 * excepción CoordenadasIncorrectasException.
	 * 
	 * @param coordenada Coordenada de la celda a clonar.
	 * @return matriz[fila][columna].clonar() Clon de la celda.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 */
	public Celda consultarCelda(Coordenada coordenada) throws CoordenadasIncorrectasException {
		if (coordenada == null) {
			throw new IllegalArgumentException("Coordenada nula");
		}

		int fila = coordenada.fila();
		int columna = coordenada.columna();

		if (fila < 0 || fila >= NUMERO_FILAS || columna < 0 || columna >= NUMERO_COLUMNAS) {
			throw new CoordenadasIncorrectasException("Coordenada fuera del tablero");
		}

		return matriz[fila][columna].clonar();
	}

	/**
	 * Devuelve un array de una dimensión, con clones en profundidad de todas las
	 * celdas del tablero, recorriendo las celdas de arriba hacia abajo, y de
	 * izquierda a derecha.
	 * 
	 * @return celdas Array de celdas.
	 */
	public List<Celda> consultarCeldas() {
		List<Celda> celdas = new ArrayList<>(NUMERO_COLUMNAS*NUMERO_FILAS);
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				celdas.add(matriz[i][j].clonar());
			}
		}
		return celdas;
	}

	/**
	 * Devuelve una lista de celdas con clones en profundidad de todas las celdas
	 * contiguas a la coordenada dada. Si la coordenada vale nulo, lanza una
	 * excepción no comprobable IllegalArgumentException. Si la coordenada no está
	 * en el tablero lanza una excepción CoordenadasIncorrectasException.
	 * 
	 * @param coordenada Coordenada de la celda.
	 * @return celdas Array de celdas.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 */
	public List<Celda> consultarCeldasContiguas(Coordenada coordenada) throws CoordenadasIncorrectasException {
		if (coordenada == null) {
			throw new IllegalArgumentException("Coordenada nula");
		}

		int fila = coordenada.fila();
		int columna = coordenada.columna();

		if (fila < 0 || fila >= NUMERO_FILAS || columna < 0 || columna >= NUMERO_COLUMNAS) {
			throw new CoordenadasIncorrectasException("Coordenada fuera del tablero");
		}

		List<Celda> celdas = new ArrayList<>();
		// Si es una esquina, solo puede tener 2 celdas contiguas
		if ((fila == 0 || fila == NUMERO_FILAS - 1) && (columna == 0 || columna == NUMERO_COLUMNAS - 1)) {
			celdas = consultarCeldasContiguasEsquina(coordenada);
		}
		// Si es un borde, devuelve 3 celdas contiguas
		else if (((fila > 0 || fila < NUMERO_FILAS - 1) && (columna == 0 || columna == NUMERO_COLUMNAS - 1))
				|| (fila == 0 || fila == NUMERO_FILAS - 1) && (columna > 0 || columna < NUMERO_COLUMNAS - 1)) {
			celdas = consultarCeldasContiguasBorde(coordenada);
		}
		// si está en los centros del tablero, devuelve 4 celdas contiguas
		else {
			celdas = consultarCeldasContiguasCentro(coordenada);
		}
		return celdas;
	}

	/**
	 * Devuelve un array de una dimensión con clones en profundidad de todas las
	 * celdas contiguas a la coordenada dada.
	 * 
	 * @param coordenada Coordenada de la celda.
	 * @return celdas Array de celdas.
	 * @see consultarCeldasContiguas Enlace al método consultarCeldasContiguas.
	 */
	private List<Celda> consultarCeldasContiguasEsquina(Coordenada coordenada) {
		if (coordenada != null) {
			int fila = coordenada.fila();
			int columna = coordenada.columna();
			// Si es un borde, solo puede tener 2 celdas contiguas
			if ((fila == 0 || fila == NUMERO_FILAS - 1) && (columna == 0 || columna == NUMERO_COLUMNAS - 1)) {
				List<Celda> celdas = new ArrayList<>();
				if (fila == 0) {
					celdas.add(matriz[fila + 1][columna].clonar());
				} else if (fila == NUMERO_FILAS - 1) {
					celdas.add(matriz[fila - 1][columna].clonar());
				}
				if (columna == 0) {
					celdas.add(matriz[fila][columna + 1].clonar());
				} else if (columna == NUMERO_COLUMNAS - 1) {
					celdas.add(matriz[fila][columna - 1].clonar());
				}
				return celdas;
			}
		}
		return new ArrayList<>();
	}

	/**
	 * Devuelve un array de una dimensión con las celdas contiguas a la coordenada
	 * dada si esta está en un borde del tablero.
	 * 
	 * @param coordenada Coordenada de la celda.
	 * @return celdas Array de celdas.
	 * @see consultarCeldasContiguas Enlace al método consultarCeldasContiguas.
	 */
	private List<Celda> consultarCeldasContiguasBorde(Coordenada coordenada) {
		List<Celda> celdas = new ArrayList<>();
		int fila = coordenada.fila();
		int columna = coordenada.columna();
		if (fila == 0 || fila == NUMERO_FILAS - 1) {
			celdas.add(matriz[fila][columna + 1].clonar());
			celdas.add(matriz[fila][columna - 1].clonar());
			if (fila == 0) {
				celdas.add(matriz[fila + 1][columna].clonar());
			} else if (fila == NUMERO_FILAS - 1) {
				celdas.add(matriz[fila - 1][columna].clonar());
			}
		} else if (columna == 0 || columna == NUMERO_COLUMNAS - 1) {
			celdas.add(matriz[fila + 1][columna].clonar());
			celdas.add(matriz[fila - 1][columna].clonar());
			if (columna == 0) {
				celdas.add(matriz[fila][columna + 1].clonar());
			} else if (columna == NUMERO_COLUMNAS - 1) {
				celdas.add(matriz[fila][columna - 1].clonar());
			}
		}
		return celdas;
	}

	/**
	 * Devuelve un array de una dimensión con las celdas contiguas a la coordenada
	 * dada si esta está en el centro del tablero.
	 * 
	 * @param coordenada Coordenada de la celda.
	 * @return celdas Array de celdas.
	 * @see consultarCeldasContiguas Enlace al método consultarCeldasContiguas.
	 */
	private List<Celda> consultarCeldasContiguasCentro(Coordenada coordenada) {
		List<Celda> celdas = new ArrayList<>();
		int fila = coordenada.fila();
		int columna = coordenada.columna();
		celdas.add(matriz[fila + 1][columna].clonar());
		celdas.add(matriz[fila - 1][columna].clonar());
		celdas.add(matriz[fila][columna + 1].clonar());
		celdas.add(matriz[fila][columna - 1].clonar());
		return celdas;
	}

	/**
	 * Devuelve una lista de celdas con clones en profundidad de todas las celdas
	 * contiguas a la coordenada dada solo en horizontal. Si la coordenada vale
	 * nulo, lanza una excepción no comprobable IllegalArgumentException. Si la
	 * coordenada no está en el tablero lanza una excepción
	 * CoordenadasIncorrectasException.
	 * 
	 * @param coordenada Coordenada de la celda.
	 * @return celdas Array de celdas.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 * @see consultarCeldasContiguas Enlace al método consultarCeldasContiguas.
	 */
	public List<Celda> consultarCeldasContiguasEnHorizontal(Coordenada coordenada)
			throws CoordenadasIncorrectasException {
		if (coordenada == null) {
			throw new IllegalArgumentException("Coordenada nula");
		}

		int fila = coordenada.fila();
		int columna = coordenada.columna();

		if (fila < 0 || fila >= NUMERO_FILAS || columna < 0 || columna >= NUMERO_COLUMNAS) {
			throw new CoordenadasIncorrectasException("Coordenada fuera del tablero");
		}

		int columnaIzq = columna - 1;
		int columnaDer = columna + 1;

		List<Celda> celdas = new ArrayList<>();
		if (columnaIzq >= 0) {
			celdas.add(matriz[fila][columnaIzq].clonar());
		}
		if (columnaDer < NUMERO_COLUMNAS) {
			celdas.add(matriz[fila][columnaDer].clonar());
		}
		return celdas;
	}

	/**
	 * devuelve una lista de celdas con clones en profundidad de todas las celdas
	 * contiguas a la coordenada dada solo en vertical. Si la coordenada vale nulo,
	 * lanza una excepción no comprobable IllegalArgumentException. Si la coordenada
	 * no está en el tablero lanza una excepción CoordenadasIncorrectasException.
	 * 
	 * @param coordenada Coordenada de la celda.
	 * @return celdas Array de celdas.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 * @see consultarCeldasContiguas Enlace al método consultarCeldasContiguas.
	 */
	public List<Celda> consultarCeldasContiguasEnVertical(Coordenada coordenada)
			throws CoordenadasIncorrectasException {
		if (coordenada == null) {
			throw new IllegalArgumentException("Coordenada nula");
		}

		int fila = coordenada.fila();
		int columna = coordenada.columna();

		if (fila < 0 || fila >= NUMERO_FILAS || columna < 0 || columna >= NUMERO_COLUMNAS) {
			throw new CoordenadasIncorrectasException("Coordenada fuera del tablero");
		}

		int filaArriba = fila - 1;
		int filaAbajo = fila + 1;

		List<Celda> celdas = new ArrayList<>();
		if (filaArriba >= 0) {
			celdas.add(matriz[filaArriba][columna].clonar());
		}
		if (filaAbajo < NUMERO_FILAS) {
			celdas.add(matriz[filaAbajo][columna].clonar());
		}
		return celdas;
	}

	/**
	 * Consulta el numero de columnas del tablero.
	 * 
	 * @return NUMERO_COLUMNAS Número de columnas.
	 */
	public int consultarNumeroColumnas() {
		return NUMERO_COLUMNAS;
	}

	/**
	 * Consulta el numero de columnas del tablero.
	 * 
	 * @return NUMERO_FILAS Número de filas.
	 */
	public int consultarNumeroFilas() {
		return NUMERO_FILAS;
	}

	/**
	 * Devuelve el número de piezas en el tablero del tipo indicado. Si el tipo de
	 * pieza vale nulo, lanza una excepción no comprobable IllegalArgumentException.
	 * 
	 * @param tipoPieza Tipo de pieza.
	 * @return contador Número de piezas.
	 */
	public int consultarNumeroPiezas(TipoPieza tipoPieza) {
		if (tipoPieza == null) {
			throw new IllegalArgumentException("Tipo de pieza nulo");
		}

		int contador = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j].consultarPieza() != null
						&& matriz[i][j].consultarPieza().consultarTipoPieza() == tipoPieza) {
					contador++;
				}
			}
		}
		return contador;
	}

	/**
	 * Elimina la pieza en la celda con la coordenada indicada. Si la coordenada
	 * vale nulo, lanza una excepción no comprobable IllegalArgumentException. Si la
	 * coordenada no está en el tablero lanza una excepción
	 * CoordenadasIncorrectasException.
	 * 
	 * @param coordenada Coordenada de la celda.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 */
	public void eliminarPieza(Coordenada coordenada) throws CoordenadasIncorrectasException {
		if (coordenada == null) {
			throw new IllegalArgumentException("Coordenada nula");
		}

		int fila = coordenada.fila();
		int columna = coordenada.columna();

		if (fila < 0 || fila >= NUMERO_FILAS || columna < 0 || columna >= NUMERO_COLUMNAS) {
			throw new CoordenadasIncorrectasException("Coordenada fuera del tablero");
		}

		matriz[fila][columna].eliminarPieza();
	}

	/**
	 * Devuelve la referencia a la celda con la coordenada indicada. Si la
	 * coordenada vale nulo, lanza una excepción no comprobable
	 * IllegalArgumentException. Si la coordenada no está en el tablero lanza una
	 * excepción CoordenadasIncorrectasException.
	 * 
	 * @param coordenada Coordenada de la celda.
	 * @return matriz[fila][columna] Celda.
	 * @throws CoordenadasIncorrectasException Si las coordenadas son incorrectas.
	 */
	public Celda obtenerCelda(Coordenada coordenada) throws CoordenadasIncorrectasException {
		if (coordenada == null) {
			throw new IllegalArgumentException("Coordenada nula");
		}

		int fila = coordenada.fila();
		int columna = coordenada.columna();

		if (fila < 0 || fila >= NUMERO_FILAS || columna < 0 || columna >= NUMERO_COLUMNAS) {
			throw new CoordenadasIncorrectasException("Coordenada fuera del tablero");
		}

		return matriz[fila][columna];
	}

	/**
	 * Comprueba si la coordenada actual está dentro de los límites del tablero. Si
	 * la coordenada vale nulo, lanza una excepción no comprobable
	 * IllegalArgumentException.
	 * 
	 * @param coordenada Coordenada de la celda.
	 * @return true si la coordenada está dentro del tablero, false en caso
	 *         contrario.
	 */
	public boolean estaEnTablero(Coordenada coordenada) {
		if (coordenada == null) {
			throw new IllegalArgumentException("Coordenada nula");
		}

		int fila = coordenada.fila();
		int columna = coordenada.columna();

		if (fila < 0 || fila >= NUMERO_FILAS || columna < 0 || columna >= NUMERO_COLUMNAS) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(matriz);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		return Arrays.deepEquals(matriz, other.matriz);
	}

	@Override
	public String toString() {
		return "Tablero [matriz=" + Arrays.toString(matriz) + "]";
	}
}
