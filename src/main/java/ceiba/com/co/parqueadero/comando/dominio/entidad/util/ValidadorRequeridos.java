package ceiba.com.co.parqueadero.comando.dominio.entidad.util;

import ceiba.com.co.parqueadero.comando.dominio.excepcion.ExcepcionObjetoRequerido;
import ceiba.com.co.parqueadero.comando.dominio.excepcion.ExcepcionStringRequerido;
import ceiba.com.co.parqueadero.comando.dominio.excepcion.ExcepcionTipoDeVehiculoInvalido;

public final class ValidadorRequeridos {

	private static final String VACIO = "";
	private static final String MOTO = "MOTO";
	private static final String CARRO = "CARRO";
	
	private ValidadorRequeridos() {
		
	}

	public static void validarStringNoNuloOVacio(String text, String errorMessage) {
		if (text == null || text.equals(ValidadorRequeridos.VACIO)) {
			throw new ExcepcionStringRequerido(errorMessage);
		}
	}

	public static void validarObjetoNoNulo(Object object, String errorMessage) {
		if (object == null) {
			throw new ExcepcionObjetoRequerido(errorMessage);
		}
	}

	public static void validarTipoDeVehiculo(String text, String errorMessage) {
		if (text == null || (!text.equals(ValidadorRequeridos.MOTO) && !text.equals(ValidadorRequeridos.CARRO))) {
			throw new ExcepcionTipoDeVehiculoInvalido(errorMessage);
		}
	}
}
