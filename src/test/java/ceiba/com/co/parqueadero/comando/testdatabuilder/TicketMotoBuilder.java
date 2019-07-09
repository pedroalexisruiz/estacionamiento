package ceiba.com.co.parqueadero.comando.testdatabuilder;

import java.time.LocalDateTime;

import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketMoto;

public class TicketMotoBuilder {

	private static final String PLACA_BASE = "PED123";
	private static final Integer CILINDRAJE_BASE = 250;
	private LocalDateTime HORA_DE_ENTRADA_BASE = LocalDateTime.of(2019, 07, 01, 0, 0);
	private static final String TIPO_DE_VEHICULO = "MOTO";

	private String placa;
	private LocalDateTime horaDeEntrada;
	private LocalDateTime horaDeSalida;
	private String tipoDeVehiculo;
	private Integer cilindraje;

	public TicketMotoBuilder() {
		this.placa = PLACA_BASE;
		this.horaDeEntrada = HORA_DE_ENTRADA_BASE;
		this.horaDeSalida = null;
		this.cilindraje = CILINDRAJE_BASE;
		this.tipoDeVehiculo = TIPO_DE_VEHICULO;
	}

	public TicketMotoBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public TicketMotoBuilder conHoraDeEntrada(LocalDateTime horaDeEntrada) {
		this.horaDeEntrada = horaDeEntrada;
		return this;
	}

	public TicketMotoBuilder conHoraDeSalida(LocalDateTime horaDeSalida) {
		this.horaDeSalida = horaDeSalida;
		return this;
	}

	public TicketMotoBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public TicketMotoBuilder conTipoDeVehiculo(String tipoDeVehiculo) {
		this.tipoDeVehiculo = tipoDeVehiculo;
		return this;
	}

	public TicketMoto build() {
		return new TicketMoto(this.placa, this.horaDeEntrada, this.horaDeSalida, this.tipoDeVehiculo,
				this.cilindraje);
	}
}
