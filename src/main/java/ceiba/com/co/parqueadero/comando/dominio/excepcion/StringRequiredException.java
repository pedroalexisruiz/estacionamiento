package ceiba.com.co.parqueadero.comando.dominio.excepcion;

public class StringRequiredException extends RuntimeException{

	public StringRequiredException(String message) {
		super(message);
	}
}
