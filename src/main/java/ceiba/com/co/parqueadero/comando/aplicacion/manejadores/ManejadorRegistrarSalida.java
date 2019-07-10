package ceiba.com.co.parqueadero.comando.aplicacion.manejadores;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.ComandoTicket;
import ceiba.com.co.parqueadero.comando.aplicacion.fabrica.FabricaTicket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.servicio.ServicioRegistrarSalida;

@Component
public class ManejadorRegistrarSalida implements ManejadorRespuestaComando<ComandoTicket, RespuestaComando<LocalDateTime>> {

	private final ServicioRegistrarSalida servicioDeRegistroDeSalida;
	private final FabricaTicket fabricaDeTickets;

	public ManejadorRegistrarSalida(FabricaTicket fabricaDeTickets, ServicioRegistrarSalida servicioDeRegistroDeSalida) {
		this.fabricaDeTickets = fabricaDeTickets;
		this.servicioDeRegistroDeSalida = servicioDeRegistroDeSalida;
	}

	@Override
	public RespuestaComando<LocalDateTime> ejecutar(ComandoTicket ticketComando) {
		Ticket ticket = this.fabricaDeTickets.crear(ticketComando);
		return new RespuestaComando<>(this.servicioDeRegistroDeSalida.registrarSalidaDelVehiculo(ticket.getPlaca()).getHoraDeSalida());
	}

}
