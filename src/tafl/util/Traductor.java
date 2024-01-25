// Paquete en donde se encuentra el archivo.
package tafl.util;

/**
 * Clase Traductor. Traducción de texto en notación algebraica a coordenadas, y
 * al revés, de coordenadas a texto en notación algebraica. Siempre se considera
 * que el tablero es de tamaño 7x7 celdas.
 * 
 * @author <a href="jose:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/10/25
 */
public class Traductor {
	/**
	 * Constructor de la clase. Para evitar warning en la documentación.
	 */
	public Traductor() {
	}

	/**
	 * Función estática. Retorna la coordenada correspondiente si el texto pasado es
	 * de longitud 2 carácteres y el contenido es correcto en formato relativo al
	 * tamaño del tablero. En caso contrario retorna un nulo.
	 * 
	 * @param texto Texto en longitud de 2 carácteres.
	 * @return Coordenada Coordenada correspondiente al texto.
	 */
	public static Coordenada consultarCoordenadaParaNotacionAlgebraica(String texto) {
		if (esTextoCorrectoParaCoordenada(texto)) {
			// Se obtiene la letra y el número.
			char letra = texto.charAt(0);
			char numero = texto.charAt(1);
			int fila = 0, columna = 0;
			// Se obtiene la fila y la columna.
			fila = obtenerFila(numero);
			columna = obtenerColumna(letra);
			// Se retorna la coordenada.
			return new Coordenada(fila, columna);
		}
		return null;
	}

	/**
	 * Función estática. Retorna el texto de longitud 2 carácteres en notación
	 * algebraica, correspondiente a una coordenada. Si no se puede realizar la
	 * traducción por valor incorrecto en el texto, retorna un valor nulo.
	 * 
	 * @param coordenada texto de longitud 2 carácteres en notación algebraica.
	 * @return texto Texto de longitud 2 carácteres en notación algebraica.
	 */
	public static String consultarTextoEnNotacionAlgebraica(Coordenada coordenada) {
		if (coordenada != null && (coordenada.fila() >= 0 && coordenada.fila() <= 6)
				&& (coordenada.columna() >= 0 && coordenada.columna() <= 6)) {
			String texto = "";
			// Se obtiene la letra y el número.
			char letra = obtenerLetra(coordenada.columna(), coordenada);
			char numero = obtenerNumero(coordenada.fila(), coordenada);
			// Se concatena la letra y el número.
			texto += letra;
			texto += numero;
			// Se retorna el texto.
			return texto;
		}
		return null;
	}

	/**
	 * Función estática. Comprueba que el texto dado no sea nulo, tenga una longitud
	 * de 2 y que se corresponde en notación algebraica con una coordenada válida
	 * del juego. Por ejemplo: con "a1" retornará true, pero con valores null, "1",
	 * "a", "1a", "11" o "h1" devolvería false.
	 * 
	 * @param texto Variable en longitud de 2 carácteres.
	 * @return true Devuelve True si cumple los requerminientos.
	 */
	public static boolean esTextoCorrectoParaCoordenada(String texto) {
		if (texto != null && texto.length() == 2) {
			char letra = texto.charAt(0);
			char numero = texto.charAt(1);
			if (letra >= 'a' && letra <= 'g' && numero >= '1' && numero <= '7') {
				return true;
			}
		}
		return false;
	}

	/**
	 * FUNCIÓN PROPIA. Sirve para obtener la fila de la coordenada.
	 * 
	 * @param numero Número de la coordenada.
	 * @return fila Devuelve la fila de la coordenada.
	 */
	private static int obtenerFila(char numero) {
		// Se resta '7' al número de la coordenada para obtener la fila de la
		// coordenada.
		int fila = '7' - numero;
		return fila;
	}

	/**
	 * FUNCIÓN PROPIA. Sirve para obtener la columna de la coordenada.
	 * 
	 * @param letra Letra de la coordenada.
	 * @return columna Devuelve la columna de la coordenada.
	 */
	private static int obtenerColumna(char letra) {
		// Se resta 'a' al número de la letra para obtener la columna de la coordenada.
		int columna = letra - 'a';
		return columna;
	}

	/**
	 * FUNCIÓN PROPIA. Sirve para obtener la letra de la coordenada.
	 * 
	 * @param columna    Columna de la coordenada.
	 * @param coordenada Coordenada.
	 * @return letra Devuelve la letra de la coordenada.
	 */
	private static char obtenerLetra(int columna, Coordenada coordenada) {
		// Se suma el número de la columna a 'a' para obtener la letra de la coordenada.
		// lo de (char) es para por si acaso.
		char letra = (char) ('a' + coordenada.columna());
		return letra;
	}

	/**
	 * FUNCIÓN PROPIA. Sirve para obtener el número de la coordenada.
	 * 
	 * @param fila       Fila de la coordenada.
	 * @param coordenada Coordenada.
	 * @return numero Devuelve el número de la coordenada.
	 */
	private static char obtenerNumero(int fila, Coordenada coordenada) {
		// Se resta el número de la fila a 7 para obtener el número de la coordenada.
		// lo de (char) es para por si acaso.
		char numero = (char) ('7' - coordenada.fila());
		return numero;
	}
}
