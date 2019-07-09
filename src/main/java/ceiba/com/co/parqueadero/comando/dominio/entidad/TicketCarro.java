package ceiba.com.co.parqueadero.comando.dominio.entidad;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketCarro extends Ticket {

	public static final long VALOR_HORA = 1000;
	public static final long VALOR_DIA = 8000;

	public TicketCarro(String placa, String tipoDeVehiculo) {
		super(placa, tipoDeVehiculo);
	}

	public TicketCarro(Long id, String placa, LocalDateTime horaDeEntrada, LocalDateTime horaDeSalida,
			String tipoDeVehiculo, long totalAPagar) {
		super(id, placa, horaDeEntrada, horaDeSalida, tipoDeVehiculo, totalAPagar);
	}

	@Override
	public void calcularPrecioAPagar() {
		long totalAPagar = 0;
		int horasDeUso = super.calcularHorasDeParqueo();

		if (horasDeUso >= LIMITE_COBRO_POR_HORAS) {
			totalAPagar = super.obtenerValorPorDias(horasDeUso, VALOR_DIA, VALOR_HORA);
		} else {
			totalAPagar = super.obtenerValorPorHoras(horasDeUso, VALOR_HORA);
		}
		this.setTotalAPagar(totalAPagar);
	}

}
