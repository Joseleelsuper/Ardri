package tafl.textui;

import java.util.Scanner;

import tafl.control.Arbitro;
import tafl.control.ArbitroArdRi;
import tafl.control.ArbitroBrandubh;
import tafl.excepcion.CoordenadasIncorrectasException;
import tafl.excepcion.TipoArbitroException;
import tafl.modelo.Celda;
import tafl.modelo.Jugada;
import tafl.modelo.Tablero;
import tafl.util.Color;
import tafl.util.Coordenada;
import tafl.util.Traductor;

/**
 * Tafl en modo texto.
 * 
 * Se abusa del uso del modificador static tanto en atributos como en métodos
 * para comprobar su similitud a variables globales y funciones globales de
 * otros lenguajes.
 * 
 * La programación en este código sigue más el paradigma de programación
 * estructurada en mayor medida que la orientación a objetos.
 *
 * @author <a href="mailto:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @since 1.0
 * @version 2.0
 * @see tafl.excepcion
 * @see tafl.modelo
 * @see tafl.control
 * @see tafl.util
 */
public class Tafl {

	/** Tamaño en caracteres de una jugada. */
	private static final int TAMAÑO_JUGADA = 4;

	/** Posición en el texto de una jugada de la coordenada destino. */
	private static final int INICIO_COORDENADA_DESTINO = 2;

	/** Texto para interrumpir la partida. */
	private static final String TEXTO_SALIR = "salir";

	/** Texto para retroceder la última jugada. */
	private static final String TEXTO_RETROCEDER = "retroceder";

	/**
	 * Árbitro.
	 */
	private static Arbitro arbitro;

	/**
	 * Lector por teclado.
	 */
	private static Scanner scanner;

	/** Oculta el constructor por defecto. */
	private Tafl() {
	}
	
	/**
	 * Método raíz.
	 * 
	 * @param args argumentos de entrada
	 * @throws TipoArbitroException si el tipo de árbitro solicitado no está entre los permitidos.
	 * @throws CoordenadasIncorrectasException si se intenta acceder a celdas con coordenadas incorrectas.
	 */
	public static void main(String[] args) throws TipoArbitroException, CoordenadasIncorrectasException {
		try {
			if (args.length > 1) {
				if (!args[0].equalsIgnoreCase("brandubh") && !args[0].equalsIgnoreCase("ardri")) {
					mostrarErrorSeleccionandoTipoArbitro();
					return;
				}
			}
			inicializarPartida(args);
			mostrarMensajeBienvenida();
			mostrarTablero();
	
			boolean salir = false;
			while (!salir) {
				String textoJugada = recogerTextoDeJugadaPorTeclado();
				salir = comprobarSalir(textoJugada);
				if (!salir) {
					if (comprobarDeshacer(textoJugada)) {
						retrocederUltimaJugada();
						mostrarTablero();
					} else if (validarFormato(textoJugada)) {
						Jugada jugada = extraerJugada(textoJugada);
						if (esLegal(jugada)) {
							realizarMovimiento(jugada);
							realizarCapturas(jugada);
							if (comprobarFinalizacionPartida()) {
								mostrarTablero();
								mostrarGanador();
								salir = true;
							} else {
								cambiarTurnoPartida();
								mostrarTablero();
							}
						} else {
							mostrarErrorPorMovimientoIlegal(textoJugada);
						}
					} else {
						mostrarErrorEnFormatoDeEntrada();
					}
				}
			}
			finalizarPartida();
		} catch (RuntimeException ex) {
			mostrarErrorInterno(ex);
		} finally {
			cerrarRecursos();
		}
	}
	

	/**
	 * Comprueba si se quiere deshacer la última jugada.
	 * 
	 * @param jugada jugada en formato texto
	 * @return true si el usuario introduce deshacer, false en caso contrario
	 * @since 2.0
	 */
	private static boolean comprobarDeshacer(String jugada) {
		return jugada.equalsIgnoreCase(TEXTO_RETROCEDER);
	}	

	/**
	 * Inicializa el estado de los elementos de la partida.
	 * 
	 * @param args argumentos por teclaro para elegir el árbitro concreto a
	 *             instanciar
	 * @throws TipoArbitroException si el tipo de arbitro solicitado no está entre
	 *                              los permitidos
	 * @since 2.0
	 */
	private static void inicializarPartida(String[] args) throws TipoArbitroException {
		// Inicializaciones
		if (args.length == 0) {
			arbitro = new ArbitroBrandubh(new Tablero());
		} else if (args[0].equalsIgnoreCase("brandubh")) {
			arbitro = new ArbitroBrandubh(new Tablero());
		} else if (args[0].equalsIgnoreCase("ardri")) {
			arbitro = new ArbitroArdRi(new Tablero());
		} else {
			/*
			 * Esta solución se podría replantear con un método invocado previamente que
			 * valide el argumento en lugar de lanzar excepción. Es el típico contra-ejemplo
			 * en el que se gestiona el control de flujo con excepciones, no siendo la
			 * opción más recomendable.
			 */
			throw new TipoArbitroException(
					"Error en seleccion de tipo de árbitro para variante de Tafl con valor: " + args[0]);
		}
		// Cargar figuras...
		arbitro.colocarPiezasConfiguracionInicial();
		// Abrimos la lectura desde teclado
		scanner = new Scanner(System.in);
	}

	/**
	 * Recoge el texto de la jugada por teclado.
	 * 
	 * @return jugada jugada en formato texto
	 */
	private static String recogerTextoDeJugadaPorTeclado() {
		System.out
				.print("Introduce jugada turno con piezas de color " + arbitro.consultarTurno() + " (formato cfcf): ");
		return scanner.next();
	}

	/**
	 * Comprueba si se quiere finalizar la partida por parte de los usuarios.
	 * 
	 * @param jugada jugada en formato texto
	 * @return true si el usuario introduce salir, false en caso contrario
	 */
	private static boolean comprobarSalir(String jugada) {
		return jugada.equalsIgnoreCase(TEXTO_SALIR);
	}

	/**
	 * Valida la corrección del formato de la jugada. Solo comprueba la corrección
	 * del formato de entrada en cuanto al tablero, no la validez de la jugada en
	 * cuanto a las reglas del juego.
	 * 
	 * La jugada tiene que tener cuatro caracteres y contener letras y números de
	 * acuerdo a las reglas de la notación algebraica.
	 * 
	 * Otra mejor solución alternativa es el uso de expresiones regulares (se verán
	 * en la asignatura de 3º Procesadores del Lenguaje).
	 * 
	 * @param textoJugada a validar
	 * @return true si el formato de la jugada es correcta según las coordenadas
	 *         disponibles del tablero
	 */
	private static boolean validarFormato(String textoJugada) {
		if (textoJugada.length() == TAMAÑO_JUGADA) {
			String origen = textoJugada.substring(0, INICIO_COORDENADA_DESTINO);
			String destino = textoJugada.substring(INICIO_COORDENADA_DESTINO, TAMAÑO_JUGADA);
			// comprobar si ambos textos son correctos
			return Traductor.esTextoCorrectoParaCoordenada(origen) && Traductor.esTextoCorrectoParaCoordenada(destino);
		}
		return false;
	}

	/**
	 * Extrae la jugada a partir del texto introducido por teclado.
	 * 
	 * Se requiere que el texto haya sido validado previamente en cuanto al formato
	 * requerido en notación algebraica para un tablero de 7x7.
	 * 
	 * @param jugadaTexto texto con la jugada
	 * @return jugada
	 * @since 2.0
	 * @see #extraerCoordenada(String, int, int)
	 * @throws RuntimeException si se intenta acceder a jugada con coordenadas
	 *                          incorrectas que deberían haber sido validadas
	 *                          previamente
	 */
	private static Jugada extraerJugada(String jugadaTexto) throws RuntimeException {
		try {
			assert validarFormato(jugadaTexto) : "El texto ha debido ser validado previamente en cuanto a formato.";
			Coordenada coordenadaOrigen = extraerCoordenada(jugadaTexto, 0, INICIO_COORDENADA_DESTINO);
			Coordenada coordenadaDestino = extraerCoordenada(jugadaTexto, INICIO_COORDENADA_DESTINO, TAMAÑO_JUGADA);
			Celda origen = arbitro.consultarTablero().consultarCelda(coordenadaOrigen);
			Celda destino = arbitro.consultarTablero().consultarCelda(coordenadaDestino);
			return new Jugada(origen, destino);
		} catch (CoordenadasIncorrectasException ex) {
			throw new RuntimeException("Error en acceso a celdas con coordenadas mal obtenidas o mal validadas.", ex);
		}
	}

	/**
	 * Extrae una coordenada a partir del texto de entrada y de las posiciones
	 * [incio, fin) indicadas.
	 * 
	 * Dada una jugada en texto, extraerá la coordenada origen o destino, en función
	 * de la posición de inicio y fin dada.
	 * 
	 * @param jugada texto en formato notación algebraica (e.g. a1a3)
	 * @param inicio posición en el texto a partir del cual leer
	 * @param fin    posición final - 1, hasta donde leer el texto
	 * @return coordenada o null, si no es posible extraerla
	 */
	private static Coordenada extraerCoordenada(String jugada, int inicio, int fin) {
		if (jugada.length() != TAMAÑO_JUGADA)
			return null;
		String textoExtraido = jugada.substring(inicio, fin);
		return Traductor.consultarCoordenadaParaNotacionAlgebraica(textoExtraido);
	}

	/**
	 * Comprueba la legalidad de la jugada.
	 * 
	 * @param jugada jugada
	 * @return true si es legal, false en caso contrario
	 * @throws CoordenadasIncorrectasException si se intenta acceder a celdas con coordenadas incorrectas.
	 */
	private static boolean esLegal(Jugada jugada) throws CoordenadasIncorrectasException {
		return arbitro.esMovimientoLegal(jugada);
	}

	/**
	 * Comprueba si está finalizada la partida.
	 * 
	 * @return true si hay victoria de atacante o defensor, false en caso contrario
	 */
	private static boolean comprobarFinalizacionPartida() {
		return arbitro.haGanadoAtacante() || arbitro.haGanadoRey();
	}

	/**
	 * Cambia el turno de la partida.
	 */
	private static void cambiarTurnoPartida() {
		arbitro.cambiarTurno();
	}

	/**
	 * Finaliza la partida informando al usuario.
	 */
	private static void finalizarPartida() {
		System.out.println("Partida finalizada.");
	}

	/**
	 * Cierre de recursos abiertos en la aplicación.
	 * 
	 * En este ejemplo solo se ha abierto el scanner para leer del teclado.
	 */
	private static void cerrarRecursos() {
		if (scanner != null) {
			scanner.close();
		}
	}

	// Métodos para mostrar información en pantalla...

	/**
	 * Muestra el mensaje de bienvenida con instrucciones para finalizar la partida.
	 */
	private static void mostrarMensajeBienvenida() {
		System.out.println("Bienvenido al juego del Tafl 2.0 - Modo: " + arbitro.getClass().getSimpleName());
		System.out.println(
				"Atacan piezas de color " + Color.NEGRO + " y defienden piezas de color " + Color.BLANCO + ".");
		System.out.println("Para interrumpir partida introduzca \"salir\".");
		System.out.println("Para retroceder el estado a la anterior jugada introduzca \"retroceder\".");
		System.out.println("Disfrute de la partida...");
	}

	/**
	 * Muestra el ganador de la partida en pantalla.
	 */
	private static void mostrarGanador() {
		if (arbitro.haGanadoAtacante()) {
			System.out.printf("%nHa ganado la partida el jugador atacante con piezas de color %s.%n",
					arbitro.consultarTurno());
		} else if (arbitro.haGanadoRey()) {
			System.out.printf("%nHa ganado la partida el jugador defensor con piezas de color %s.%n",
					arbitro.consultarTurno());
		} else {
			System.out.println("\nNo hay ganador.");
		}
	}

	/**
	 * Muestra la información de error en el formato de entrada, mostrando ejemplos.
	 */
	private static void mostrarErrorEnFormatoDeEntrada() {
		System.out.println();
		System.out.println("Error en el formato de entrada.");
		System.out.println(
				"El formato debe ser letranumeroletranumero, por ejemplo a7a5 o g2e2, o bien introducir la cadena \"salir\" para finalizar la partida.");
		System.out.println("Las letras deben estar en el rango [a,g] y los números en el rango [1,7].");
	}

	/**
	 * Informa de la ilegalidad de la jugada intentada.
	 * 
	 * @param textoJugada texto de la jugada introducido por teclado
	 */
	private static void mostrarErrorPorMovimientoIlegal(String textoJugada) {
		System.out.printf("%nLa jugada %s es ilegal.%nRevise las reglas del juego.%n", textoJugada);
	}

	/**
	 * Muestra el estado del tablero con sus piezas actuales en pantalla.
	 * 
	 * @since 2.0
	 */
	private static void mostrarTablero() {
		System.out.println();
		System.out.println(arbitro.consultarTablero().aTexto());
	}

	/**
	 * Muestra mensaje de error grave si el tipo de árbitro no es ninguno de los dos
	 * disponibles.
	 */
	private static void mostrarErrorSeleccionandoTipoArbitro() {
		System.err
				.println("El tipo de árbitro seleccionado no se corresponde con ninguna de las dos opciones válidas.");
		System.err.println("Debe introducir \"brandubh\" o \"ardri\".");
	}

	/**
	 * Muestra mensaje de error grave por error en el código del que no podemos
	 * recuperarnos.
	 * 
	 * @param ex excepción generada
	 */
	private static void mostrarErrorInterno(RuntimeException ex) {
		System.err.println("Error interno en código a corregir por el equipo informático.");
		System.err.println("Mensaje asociado de error: " + ex.getMessage());
		System.err.println("Traza detallada del error a reportar:");
		ex.printStackTrace();
		// sería mejor solución mandar dicha informacion de la traza a un fichero de log
		// en lugar de a la consola, pero esta solución se verá en otras asignaturas
	}

	/**
	 * Deshacer la última jugada.
	 * 
	 * @since 2.0
	 */
	private static void retrocederUltimaJugada() {
		arbitro.retroceder();
	}

	/**
	 * Realizar la jugada completando el movimientos.
	 * 
	 * @param jugada jugada
	 * @throws IllegalArgumentException si se detecta un intento de realizar el
	 *                                  movimiento con jugada incorrecta previamente
	 *                                  validada
	 * @since 2.0
	 */
	private static void realizarMovimiento(Jugada jugada) {
		// la jugada debería haber sido validada previamente
		try {
			arbitro.mover(jugada);
		} catch (CoordenadasIncorrectasException ex) {
			throw new IllegalArgumentException(
					"No debería intentar realizarse una jugada como " + jugada + " incorrecta.", ex);
		}
	}

	/**
	 * Realizar las capturas correspondienes al último movimiento.
	 * 
	 * @param jugada jugada
	 * @throws CoordenadasIncorrectasException si se intenta acceder a celdas con coordenadas incorrectas. 
	 * @since 2.0
	 */
	private static void realizarCapturas(Jugada jugada) throws CoordenadasIncorrectasException {
		arbitro.realizarCapturasTrasMover();
	}
}
