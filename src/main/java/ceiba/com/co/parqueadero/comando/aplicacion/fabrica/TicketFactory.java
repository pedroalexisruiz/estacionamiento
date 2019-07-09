package ceiba.com.co.parqueadero.comando.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.TicketCommand;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketCarro;
import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketMoto;

@Component
public class TicketFactory {

	public Ticket create(TicketCommand ticketCommand) {
		if (ticketCommand.getTipoDeVehiculo().equals(Ticket.MOTO)) {
			return new TicketMoto(ticketCommand.getPlaca(), ticketCommand.getTipoDeVehiculo(),
					ticketCommand.getCilindraje());
		}
		return new TicketCarro(ticketCommand.getPlaca(), ticketCommand.getTipoDeVehiculo());
	}
}
