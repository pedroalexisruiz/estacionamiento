package ceiba.com.co.parqueadero.comando.infraestructura.controladores;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.TicketCommand;
import ceiba.com.co.parqueadero.comando.aplicacion.handlers.CommandResponse;
import ceiba.com.co.parqueadero.comando.aplicacion.handlers.CreateTicketHandler;
import ceiba.com.co.parqueadero.comando.aplicacion.handlers.UpdateVehicleExitHandler;

@RestController
@RequestMapping("/tickets")
// @Api(tags = { "Ticket command controller"})
public class TicketCommandController {

	private final CreateTicketHandler createTicketHandler;
	private final UpdateVehicleExitHandler updateVehicleExitHandler;

	@Autowired
	public TicketCommandController(CreateTicketHandler createTicketHandler,
			UpdateVehicleExitHandler updateVehicleExitHandler) {
		this.createTicketHandler = createTicketHandler;
		this.updateVehicleExitHandler = updateVehicleExitHandler;
	}

	@PostMapping
	// @ApiOperation("Crear Usuario")
	public CommandResponse<Long> crear(@RequestBody TicketCommand ticketCommand) {
		return createTicketHandler.execute(ticketCommand);
	}

	@PutMapping
	// @ApiOperation("Crear Usuario")
	public CommandResponse<Date> registrarSalida(@RequestBody TicketCommand ticketCommand) {
		return updateVehicleExitHandler.execute(ticketCommand);
	}
}