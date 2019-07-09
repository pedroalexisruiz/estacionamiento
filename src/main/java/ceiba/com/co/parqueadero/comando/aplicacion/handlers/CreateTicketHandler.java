package ceiba.com.co.parqueadero.comando.aplicacion.handlers;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.TicketCommand;
import ceiba.com.co.parqueadero.comando.aplicacion.fabrica.TicketFactory;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.servicio.ICreateTicketService;

@Component
public class CreateTicketHandler implements CommandResponseHandler<TicketCommand, CommandResponse<Long>> {

	private final ICreateTicketService createTicketService;
	private final TicketFactory ticketFactory;

	public CreateTicketHandler(TicketFactory ticketFactory, ICreateTicketService createTicketService) {
		this.ticketFactory = ticketFactory;
		this.createTicketService = createTicketService;
	}

	@Override
	public CommandResponse<Long> execute(TicketCommand ticketCommand) {
		Ticket ticket = this.ticketFactory.create(ticketCommand);
		return new CommandResponse<>(this.createTicketService.registrarEntradaDeVehiculo(ticket));
	}

}
