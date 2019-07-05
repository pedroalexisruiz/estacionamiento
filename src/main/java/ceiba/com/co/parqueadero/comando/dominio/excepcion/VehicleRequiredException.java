package ceiba.com.co.parqueadero.comando.dominio.excepcion;

public class VehicleRequiredException extends RuntimeException{

	public VehicleRequiredException(String message) {
		super(message);
	}
}
