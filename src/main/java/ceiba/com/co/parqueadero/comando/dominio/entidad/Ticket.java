package ceiba.com.co.parqueadero.comando.dominio.entidad;

import java.time.Duration;
import java.time.LocalDateTime;

import ceiba.com.co.parqueadero.comando.dominio.entidad.util.TiempoTranscurrido;
import ceiba.com.co.parqueadero.comando.dominio.entidad.util.ValidadorRequeridos;
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
	private long totalAPagar;

	private static final double SEGUNDOS_EN_UNA_HORA = 3600d;
	private static final int VEINTICUATRO_HORAS = 24;
	protected static final int LIMITE_COBRO_POR_HORAS = 9;

	public static final String MOTO = "MOTO";
	public static final String CARRO = "CARRO";

	private static final String PLACA_VACIA = "Debes ingresar la placa";
	private static final String TIPO_VEHICULO_VACIO = "Debes elegir el tipo de vehículo";
	private static final String TIPO_VEHICULO_INVALIDO = "Debes elegir un tipo de vehículo válido";

	public Ticket(String placa, String tipoDeVehiculo) {
		ValidadorRequeridos.validarStringNoNuloOVacio(placa, PLACA_VACIA);
		ValidadorRequeridos.validarStringNoNuloOVacio(tipoDeVehiculo, TIPO_VEHICULO_VACIO);
		ValidadorRequeridos.validarTipoDeVehiculo(tipoDeVehiculo, TIPO_VEHICULO_INVALIDO);
		this.placa = placa;
		this.tipoDeVehiculo = tipoDeVehiculo;
		this.totalAPagar = 0;
	}

	public abstract void calcularPrecioAPagar();

	int calcularHorasDeParqueo() {
		Duration duracion = Duration.between(this.getHoraDeEntrada(), this.getHoraDeSalida());
		long segundos = duracion.getSeconds();
		return (int) Math.ceil((double) segundos / SEGUNDOS_EN_UNA_HORA);
	}

	long obtenerValorPorHoras(int horas, long precioPorHora) {
		return horas * precioPorHora;
	}
	
	protected long obtenerValorPorDias(int horasDeParqueo, long valorDia, long valorHora) {
		long totalPagarPorDias;
		long totalPagarPorHoras;
		TiempoTranscurrido tiempoDeParqueo = obtenerTiempoDeParqueo(horasDeParqueo);
		totalPagarPorDias = valorDia * tiempoDeParqueo.getDias();
		totalPagarPorHoras = valorHora * tiempoDeParqueo.getHoras();
		return totalPagarPorDias + totalPagarPorHoras;
	}
	
	public TiempoTranscurrido obtenerTiempoDeParqueo(int horasDeParqueo) {
		int dias;
		int horas = 0;
		TiempoTranscurrido tiempoTranscurrido = new TiempoTranscurrido();

		if(horasDeParqueo<VEINTICUATRO_HORAS) {			
			dias = 1;			
		} else {
			horas = horasDeParqueo % VEINTICUATRO_HORAS;
			
			if(horas==0) {
				dias = horasDeParqueo / VEINTICUATRO_HORAS;
				
			} else {				
				dias = (horasDeParqueo - horas) / VEINTICUATRO_HORAS;	
				
				if(horas>=LIMITE_COBRO_POR_HORAS) {
					dias++;
					horas = 0;
				} 
			}
		}
		tiempoTranscurrido.setDias(dias);
		tiempoTranscurrido.setHoras(horas);
		return tiempoTranscurrido;
	}
}
