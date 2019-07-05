package ceiba.com.co.parqueadero.comando.aplicacion.handlers;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.TicketCommand;
import ceiba.com.co.parqueadero.comando.aplicacion.fabrica.TicketFactory;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.servicio.CreateTicketService;

public class CreateTicketHandler implements CommandResponseHandler<TicketCommand, CommandResponse<Long>> {

	private final CreateTicketService createTicketService;
	private final TicketFactory ticketFactory;

	public CreateTicketHandler(TicketFactory ticketFactory, CreateTicketService createTicketService) {
		this.ticketFactory = ticketFactory;
		this.createTicketService = createTicketService;
	}

	@Override
	public CommandResponse<Long> execute(TicketCommand ticketCommand) {
		Ticket ticket = this.ticketFactory.create(ticketCommand);
		return new CommandResponse<Long>(this.createTicketService.execute(ticket));
	}

}
