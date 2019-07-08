package ceiba.com.co.parqueadero.comando.dominio.entidad;

import java.time.Duration;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Ticket {

	private Long id;
	private String placa;
	private LocalDateTime horaDeEntrada;
	private LocalDateTime horaDeSalida;
	private String tipoDeVehiculo;
	private Integer totalAPagar;

	private static final double SEGUNDOS_EN_UNA_HORA = 3600d;
	private static final int VEINTICUATRO_HORAS = 24;
	protected static final int LIMITE_COBRO_POR_HORAS = 9;
	
	public static final String MOTO = "MOTO";
	public static final String CARRO = "CARRO";

	private static final String PLACA_VACIA = "Debes ingresar la placa";
	private static final String TIPO_VEHICULO_VACIO = "Debes elegir el tipo de vehículo";
	private static final String TIPO_VEHICULO_INVALIDO = "Debes elegir un tipo de vehículo válido";

	public Ticket(String placa, String tipoDeVehiculo) {
		RequiredValidator.validateStringRequired(placa, PLACA_VACIA);
		RequiredValidator.validateStringRequired(tipoDeVehiculo, TIPO_VEHICULO_VACIO);
		RequiredValidator.validateVehicleRequired(tipoDeVehiculo, TIPO_VEHICULO_INVALIDO);
		this.placa = placa;
		this.tipoDeVehiculo = tipoDeVehiculo;
	}

	public abstract void calcularPrecioAPagar();

	int calcularHorasDeParqueo() {
		Duration duracion = Duration.between(this.getHoraDeEntrada(), this.getHoraDeSalida());
		long segundos = duracion.getSeconds();
		return (int) Math.ceil((double) segundos / SEGUNDOS_EN_UNA_HORA);
	}
	
}
