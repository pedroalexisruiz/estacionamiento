package ceiba.com.co.parqueadero.comando.dominio.entidad;

import ceiba.com.co.parqueadero.comando.dominio.excepcion.ObjectRequiredException;
import ceiba.com.co.parqueadero.comando.dominio.excepcion.StringRequiredException;
import ceiba.com.co.parqueadero.comando.dominio.excepcion.VehicleRequiredException;

public class RequiredValidator {

	private static final String VACIO = "";
	private static final String MOTO = "MOTO";
	private static final String CARRO = "CARRO";
	
	private RequiredValidator() {
		
	}

	public static void validateStringRequired(String text, String errorMessage) {
		if (text == null || text.equals(RequiredValidator.VACIO)) {
			throw new StringRequiredException(errorMessage);
		}
	}

	public static void validateObjectRequired(Object object, String errorMessage) {
		if (object == null) {
			throw new ObjectRequiredException(errorMessage);
		}
	}

	public static void validateVehicleRequired(String text, String errorMessage) {
		if (text == null || (!text.equals(RequiredValidator.MOTO) && !text.equals(RequiredValidator.CARRO))) {
			throw new VehicleRequiredException(errorMessage);
		}
	}
}
