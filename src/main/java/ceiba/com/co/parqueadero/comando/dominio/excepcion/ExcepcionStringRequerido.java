package ceiba.com.co.parqueadero.comando.dominio.excepcion;

public class ExcepcionStringRequerido extends RuntimeException{

	public ExcepcionStringRequerido(String message) {
		super(message);
	}
}
