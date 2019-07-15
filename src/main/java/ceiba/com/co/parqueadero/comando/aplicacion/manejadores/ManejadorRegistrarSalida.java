package ceiba.com.co.parqueadero.comando.aplicacion.manejadores;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.dominio.servicio.ServicioRegistrarSalida;

@Component
public class ManejadorRegistrarSalida implements ManejadorRespuestaComando<String, RespuestaComando<LocalDateTime>> {

	private final ServicioRegistrarSalida servicioDeRegistroDeSalida;

	public ManejadorRegistrarSalida(ServicioRegistrarSalida servicioDeRegistroDeSalida) {
		this.servicioDeRegistroDeSalida = servicioDeRegistroDeSalida;
	}

	@Override
	public RespuestaComando<LocalDateTime> ejecutar(String placa) {
		return new RespuestaComando<>(this.servicioDeRegistroDeSalida.registrarSalidaDelVehiculo(placa).getHoraDeSalida());
	}

}
