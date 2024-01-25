package tafl.control;
import java.util.ArrayList;
import java.util.List;

public class Historial {
    private List<Registro> historial;
    private int indice;
    /**
     * El constructor inicializa el almacenamiento necesario, en este caso utilizando listas dinámicas con
        genericidad para su implementación (utilizando java.util.List y java.util.ArrayList). No se
        puede resolver con arrays de Java. Por simplificación, se asume que nunca se agotará la memoria
        necesaria.
     */
    public Historial() {
        this.historial = new ArrayList<Registro>();
        this.indice = 0;
    }
    /**
     * El método añadirUltimoRegistro permite añadir un registro con la información del tablero
        actual y la jugada aplicada sobre dicho tablero. Si el registro o cualquiera de sus valores de
        tablero o registro, vale nulo, lanza una excepción no comprobable
        IllegalArgumentException.
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
        disponibles en el historial. Inicialmente el valor retornado será cero. Se incrementa cada vez que
        se añade un nuevo registro y se decrementará cada vez que se extrae uno.
     */
    public int consultarNumeroRegistros() {
        return this.historial.size();
    }

    /**
     * El método extraerUltimoRegistro, extrae el último registro añadido en el historial, eliminándolo
        del mismo. Sucesivas invocaciones al método, vaciarán el historial.
     */
    public Registro extraerUltimoRegistro() {
        if (this.historial.size() == 0) {
            return null;
        }
        this.indice--;
        return this.historial.remove(this.indice);
    }
}
