// Paquete en donde se encuentra el archivo.
package tafl.control;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Historial. Almacena los registros de las jugadas realizadas.
 * 
 * @author <a href="mailto:jgc1031@alu.ubu.es">José Gallardo Caballero</a>
 * @version 1.0
 * @serial 2024/02/18
 */
public class Historial {
    /**
     * Variable historial.
     */
    private List<Registro> historial;
    /**
     * Variable indice.
     */
    private int indice;
    
    /**
     * Constructor de la clase Historial.
     */
    public Historial() {
        this.historial = new ArrayList<Registro>();
        this.indice = 0;
    }
    /**
     * El método añadirUltimoRegistro permite añadir un registro con la información del tablero
     * actual y la jugada aplicada sobre dicho tablero. Si el registro o cualquiera de sus valores de
     * tablero o registro, vale nulo, lanza una excepción no comprobable
     * IllegalArgumentException.
     * 
     * @param registro Registro.
     */
    public void añadirUltimoRegistro(Registro registro) {
        if (registro == null) {
            throw new IllegalArgumentException("Registro nulo");
        }
        this.historial.add(registro);
        this.indice++;
    }

    /**
     * El método consultarNumeroRegistros, permite consultar el número de registros actualmente
     * disponibles en el historial. Inicialmente el valor retornado será cero. Se incrementa cada vez que
     * se añade un nuevo registro y se decrementará cada vez que se extrae uno.
     * 
     * @return int Número de registros.
     */
    public int consultarNumeroRegistros() {
        return this.historial.size();
    }

    /**
     * El método extraerUltimoRegistro, extrae el último registro añadido en el historial, eliminándolo
     * del mismo. Sucesivas invocaciones al método, vaciarán el historial.
     * 
     * @return Registro Registro extraído.
     */
    public Registro extraerUltimoRegistro() {
        if (this.historial.size() == 0) {
            return null;
        }
        this.indice--;
        return this.historial.remove(this.indice);
    }
}
