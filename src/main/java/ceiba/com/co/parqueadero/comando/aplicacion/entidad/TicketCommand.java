package ceiba.com.co.parqueadero.comando.aplicacion.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketCommand {
	private String plate;
	private String vehicleType;
	private int displacement;
}
