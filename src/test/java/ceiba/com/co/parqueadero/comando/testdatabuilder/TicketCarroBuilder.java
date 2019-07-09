package ceiba.com.co.parqueadero.comando.testdatabuilder;

import java.time.LocalDateTime;

import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketCarro;

public class TicketCarroBuilder {

	private static final String PLACA = "PED123";
	private LocalDateTime HORA_DE_ENTRADA = LocalDateTime.of(2019, 07, 01, 0, 0);
	private static final String TIPO_DE_VEHICULO = "CARRO";

	private String placa;
	private LocalDateTime horaDeEntrada;
	private LocalDateTime horaDeSalida;
	private String tipoDeVehiculo;

	public TicketCarroBuilder() {
		this.placa = PLACA;
		this.horaDeEntrada = HORA_DE_ENTRADA;
		this.horaDeSalida = null;
		this.tipoDeVehiculo = TIPO_DE_VEHICULO;
	}

	public TicketCarroBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public TicketCarroBuilder conHoraDeEntrada(LocalDateTime horaDeEntrada) {
		this.horaDeEntrada = horaDeEntrada;
		return this;
	}

	public TicketCarroBuilder conHoraDeSalida(LocalDateTime horaDeSalida) {
		this.horaDeSalida = horaDeSalida;
		return this;
	}

	public TicketCarroBuilder conTipoDeVehiculo(String tipoDeVehiculo) {
		this.tipoDeVehiculo = tipoDeVehiculo;
		return this;
	}

	public TicketCarro build() {
		return new TicketCarro(null, this.placa, this.horaDeEntrada, this.horaDeSalida, this.tipoDeVehiculo, 0);
	}
}
