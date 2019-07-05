package ceiba.com.co.parqueadero.comando.infraestructura.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.TicketCommand;
import ceiba.com.co.parqueadero.comando.aplicacion.handlers.CommandResponse;
import ceiba.com.co.parqueadero.comando.aplicacion.handlers.CreateTicketHandler;

@RestController
@RequestMapping("/tickets")
// @Api(tags = { "Ticket command controller"})
public class TicketCommandController {

	private final CreateTicketHandler createTicketHandler;

	@Autowired
	public TicketCommandController(CreateTicketHandler createTicketHandler) {
		this.createTicketHandler = createTicketHandler;
	}

	@PostMapping
	// @ApiOperation("Crear Usuario")
	public CommandResponse<Long> crear(@RequestBody TicketCommand ticketCommand) {
		return createTicketHandler.execute(ticketCommand);
	}
}