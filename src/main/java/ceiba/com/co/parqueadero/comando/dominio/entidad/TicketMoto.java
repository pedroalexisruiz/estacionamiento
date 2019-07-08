package ceiba.com.co.parqueadero.comando.dominio.entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketMoto extends Ticket {

	private Integer cilindraje;

	private static final String CILINDRAJE_MOTO_VACIO = "Debes ingresar el cilindraje de la moto";
	private static final BigDecimal VALOR_HORA = new BigDecimal("500");
	private static final BigDecimal VALOR_DIA = new BigDecimal("4000");

	@Override
	public void calcularPrecioAPagar() {
		// TODO Auto-generated method stub

	}

	public TicketMoto() {
		super();
	}

	public TicketMoto(String plate, String vehicleType, Integer cilindraje) {
		super(plate, vehicleType);
		RequiredValidator.validateObjectRequired(cilindraje, CILINDRAJE_MOTO_VACIO);
		this.cilindraje = cilindraje;
		// TODO Auto-generated constructor stub
	}

	public TicketMoto(Long id, String placa, LocalDateTime horaDeEntrada, LocalDateTime horaDeSalida,
			String tipoDeVehiculo, Integer totalAPagar, Integer cilindraje) {
		super(id, placa, horaDeEntrada, horaDeSalida, tipoDeVehiculo, totalAPagar);
		RequiredValidator.validateObjectRequired(cilindraje, CILINDRAJE_MOTO_VACIO);
		this.cilindraje = cilindraje;
	}

}
