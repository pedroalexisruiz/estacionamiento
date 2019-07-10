package ceiba.com.co.parqueadero.comando.dominio.excepcion;

public class ExcepcionParqueaderoSinEspacio extends RuntimeException {

	public ExcepcionParqueaderoSinEspacio(String errorMessage) {
		super(errorMessage);
	}
}
