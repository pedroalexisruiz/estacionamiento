package ceiba.com.co.parqueadero.comando.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.ComandoTicket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketCarro;
import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketMoto;

@Component
public class FabricaTicket {

	public Ticket crear(ComandoTicket ticketComando) {
		if (ticketComando.getTipoDeVehiculo().equals(Ticket.MOTO)) {
			return new TicketMoto(ticketComando.getPlaca(), ticketComando.getTipoDeVehiculo(),
					ticketComando.getCilindraje());
		}
		return new TicketCarro(ticketComando.getPlaca(), ticketComando.getTipoDeVehiculo());
	}
}
