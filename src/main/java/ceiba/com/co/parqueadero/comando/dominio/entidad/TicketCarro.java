package ceiba.com.co.parqueadero.comando.dominio.entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketCarro extends Ticket {

	private static final BigDecimal VALOR_HORA = new BigDecimal("1000");
	private static final BigDecimal VALOR_DIA = new BigDecimal("8000");	

	public TicketCarro(String placa, String tipoDeVehiculo) {
		super(placa, tipoDeVehiculo);
	}
	
	public TicketCarro() {
		super();
	}

	public TicketCarro(Long id, String placa, LocalDateTime horaDeEntrada, LocalDateTime horaDeSalida,
			String tipoDeVehiculo, Integer totalAPagar) {
		super(id, placa, horaDeEntrada, horaDeSalida, tipoDeVehiculo, totalAPagar);
	}

	@Override
	public void calcularPrecioAPagar() {
		int totalAPagar = 0;
		this.setTotalAPagar(totalAPagar);
	}
	
}
