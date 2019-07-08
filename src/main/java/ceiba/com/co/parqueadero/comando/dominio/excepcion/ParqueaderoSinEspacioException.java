package ceiba.com.co.parqueadero.comando.dominio.excepcion;

public class ParqueaderoSinEspacioException extends RuntimeException {

	public ParqueaderoSinEspacioException(String errorMessage) {
		super(errorMessage);
	}
}
