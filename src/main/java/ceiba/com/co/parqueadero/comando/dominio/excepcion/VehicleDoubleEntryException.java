package ceiba.com.co.parqueadero.comando.dominio.excepcion;

public class VehicleDoubleEntryException extends RuntimeException {

	public VehicleDoubleEntryException(String errorMessage) {
		super(errorMessage);
	}
}
