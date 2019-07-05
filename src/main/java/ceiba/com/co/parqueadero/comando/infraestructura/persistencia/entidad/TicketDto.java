package ceiba.com.co.parqueadero.comando.infraestructura.persistencia.entidad;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

	private long id;
	private String plate;
	private LocalDateTime inTime;
	private LocalDateTime outTime;
	private String vehicleType;
	private int displacement;
}
