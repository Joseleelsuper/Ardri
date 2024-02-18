// Paquete en donde se encuentra el archivo.
package tafl.modelo;

import java.util.Objects;
import tafl.util.TipoPieza;
import tafl.util.Color;

/**
 * Clase Pieza Representa una pieza del juego. Una pieza tiene un tipo y un
 * color. El tipo de la pieza puede ser: Rey, Defensor o Atacante.
 * 
 * @author <a href="mailto:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2023/10/25
 */
public class Pieza {
	/**
	 * Tipo de la pieza. (Rey, Defensor o Atacante)
	 */
	private TipoPieza tipo;
	/**
	 * Color de la pieza. (Blanco o Negro)
	 */
	private Color color;

	/**
	 * Constructor de la clase. Inicializa la pieza con el tipo de pieza pasado.
	 * 
	 * @param tipoPieza tipo de la pieza.
	 */
	public Pieza(TipoPieza tipoPieza) {
		this.tipo = tipoPieza;
		this.color = tipoPieza.consultarColor();
	}

	/**
	 * Clona la pieza. Se crea una nueva pieza con el mismo tipo y color.
	 * 
	 * @return pieza Características de la pieza.
	 */
	public Pieza clonar() {
		Pieza pieza = new Pieza(this.tipo);
		return pieza;
	}

	/**
	 * Devuelve el color asociado a la pieza.
	 * 
	 * @return color Color de la Pieza.
	 */
	public Color consultarColor() {
		Color color = null;
		if (tipo == TipoPieza.REY || tipo == TipoPieza.DEFENSOR) {
			color = Color.BLANCO;
		} else if (tipo == TipoPieza.ATACANTE) {
			color = Color.NEGRO;
		}
		return color;
	}

	/**
	 * Devuelve el tipo de la pieza.
	 * 
	 * @return tipo Tipo de la Pieza.
	 */
	public TipoPieza consultarTipoPieza() {
		return tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pieza other = (Pieza) obj;
		return color == other.color && tipo == other.tipo;
	}

	@Override
	public String toString() {
		return "Pieza [tipo=" + tipo + ", color=" + color + "]";
	}
}
