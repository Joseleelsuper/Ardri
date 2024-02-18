// Paquete en donde se encuentra el archivo.
package tafl.modelo;

import java.util.Objects;
import tafl.util.Coordenada;
import tafl.util.TipoCelda;
import tafl.util.Color;

/**
 * Clase Celda, representa una celda del tablero.
 * 
 * @author <a href="mailto:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/10/25
 */
public class Celda {
	/**
	 * Variable coordenada.
	 */
	private Coordenada coordenada;
	/**
	 * Variable pieza.
	 */
	private Pieza pieza;
	/**
	 * Variable tipoCelda.
	 */
	private TipoCelda tipo;

	/**
	 * Constructor 1. Crea una celda de tipo NORMAL.
	 * 
	 * @param coordenada Coordenada.
	 */
	public Celda(Coordenada coordenada) {
		this.coordenada = coordenada;
		this.tipo = TipoCelda.NORMAL;
	}

	/**
	 * Constructor 2. Permite crear una celda, personalizando el tipo de celda.
	 * 
	 * @param coordenada Coordenada.
	 * @param tipoCelda  TipoCelda.
	 */
	public Celda(Coordenada coordenada, TipoCelda tipoCelda) {
		this.coordenada = coordenada;
		this.tipo = tipoCelda;
	}

	/**
	 * Clona la Celda. Devuelve clon en profundidad de la celda actual. Copia
	 * también la pieza que contiene, si la hay.
	 * 
	 * @return celda Clon en profundidad
	 */
	public Celda clonar() {
		Celda celda = new Celda(this.coordenada, this.tipo);
		if (this.pieza != null) {
			celda.pieza = this.pieza.clonar();
		}
		return celda;
	}

	/**
	 * Sitúa o relaciona una pieza en la celda actual.
	 * 
	 * @param pieza Pieza.
	 */
	public void colocar(Pieza pieza) {
		this.pieza = pieza;
	}

	/**
	 * Retorna el color de la pieza actual, si no está vacía, o nulo en caso
	 * contrario.
	 * 
	 * @return color Color de la pieza.
	 */
	public Color consultarColorDePieza() {
		Color color = null;
		if (pieza != null) {
			color = pieza.consultarColor();
		}
		return color;
	}

	/**
	 * Elimina la asignación actual de pieza en la celda, dejando en su lugar un
	 * valor nulo.
	 */
	public void eliminarPieza() {
		pieza = null;
	}

	/**
	 * Consulta si hay o no una pieza colocada en dicha celda.
	 * 
	 * @return vacia Valor booleano.
	 */
	public boolean estaVacia() {
		boolean vacia = false;
		if (pieza == null) {
			vacia = true;
		}
		return vacia;
	}

	/**
	 * Consultamos coordenada.
	 * 
	 * @return coordenada coordenada
	 */
	public Coordenada consultarCoordenada() {
		return this.coordenada;
	}

	/**
	 * Consultamos los parámetros de la pieza.
	 * 
	 * @return this.pieza Valor de la pieza.
	 */
	public Pieza consultarPieza() {
		return this.pieza;
	}

	/**
	 * Consultamos tipo celda.
	 * 
	 * @return celda Tipo de la celda.
	 */
	public TipoCelda consultarTipoCelda() {
		return this.tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordenada, pieza, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return Objects.equals(coordenada, other.coordenada) && Objects.equals(pieza, other.pieza) && tipo == other.tipo;
	}

	@Override
	public String toString() {
		return "Celda [coordenada=" + coordenada + ", pieza=" + pieza + ", tipo=" + tipo + "]";
	}
}
