package ceiba.com.co.parqueadero.comando.aplicacion.manejadores;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.ComandoTicket;
import ceiba.com.co.parqueadero.comando.aplicacion.fabrica.FabricaTicket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.servicio.ServicioRegistrarEntrada;

@Component
public class ManejadorRegistrarEntrada implements ManejadorRespuestaComando<ComandoTicket, RespuestaComando<Long>> {

	private final ServicioRegistrarEntrada servicioRegistrarEntrada;
	private final FabricaTicket fabricaDeTickets;

	public ManejadorRegistrarEntrada(FabricaTicket fabricaDeTickets, ServicioRegistrarEntrada servicioRegistrarEntrada) {
		this.fabricaDeTickets = fabricaDeTickets;
		this.servicioRegistrarEntrada = servicioRegistrarEntrada;
	}

	@Override
	public RespuestaComando<Long> ejecutar(ComandoTicket ticketCommand) {
		Ticket ticket = this.fabricaDeTickets.crear(ticketCommand);
		return new RespuestaComando<>(this.servicioRegistrarEntrada.registrarEntradaDeVehiculo(ticket));
	}

}
