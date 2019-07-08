package ceiba.com.co.parqueadero.comando.dominio.entidad;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

	private Long id;
	private String plate;
	private Date inTime;
	private Date outTime;
	private String vehicleType;
	private Integer displacement;
	private Integer totalAPagar;

	private static final String PLACA_VACIA = "Debes ingresar la placa";
	private static final String TIPO_VEHICULO_VACIO = "Debes elegir el tipo de vehículo";
	private static final String TIPO_VEHICULO_INVALIDO = "Debes elegir un tipo de vehículo válido";
	private static final String CILINDRAJE_MOTO_VACIO = "Debes ingresar el cilindraje de la moto";
	private static final String MOTO = "MOTO";

	public Ticket(String plate, String vehicleType, int displacement) {
		RequiredValidator.validateStringRequired(plate, PLACA_VACIA);
		RequiredValidator.validateStringRequired(vehicleType, TIPO_VEHICULO_VACIO);
		RequiredValidator.validateVehicleRequired(vehicleType, TIPO_VEHICULO_INVALIDO);

		if (vehicleType.equals(MOTO)) {
			RequiredValidator.validateObjectRequired(displacement, CILINDRAJE_MOTO_VACIO);
		}
		this.plate = plate;
		this.vehicleType = vehicleType;
		this.displacement = displacement;
	}

	public void calcularPrecioAPagar() {
		
	}
}
