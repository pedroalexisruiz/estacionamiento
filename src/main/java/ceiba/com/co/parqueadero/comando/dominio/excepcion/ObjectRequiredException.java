package ceiba.com.co.parqueadero.comando.dominio.excepcion;

public class ObjectRequiredException extends RuntimeException{

	public ObjectRequiredException(String message) {
		super(message);
	}
}
