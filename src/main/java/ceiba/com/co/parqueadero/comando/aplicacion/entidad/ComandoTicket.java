package ceiba.com.co.parqueadero.comando.aplicacion.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComandoTicket {
	private String placa;
	private String tipoDeVehiculo;
	private Integer cilindraje;
}
