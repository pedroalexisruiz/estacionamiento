package ceiba.com.co.parqueadero.comando.dominio.excepcion;

public class ExcepcionVehiculoYaIngresado extends RuntimeException {

	public ExcepcionVehiculoYaIngresado(String errorMessage) {
		super(errorMessage);
	}
}
