 package ceiba.com.co.parqueadero.comando.infraestructura.persistencia.entidad;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Ticket")
public class TicketEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "placa", nullable = false, length = 6)
	private String placa;
	@Column(name = "horaDeEntrada", nullable = false)
	private LocalDateTime horaDeEntrada;
	@Column(name = "horaDeSalida", nullable = true)
	private LocalDateTime horaDeSalida;
	@Column(name = "tipoDeVehiculo", nullable = false, length = 5)
	private String tipoDeVehiculo;
	@Column(name = "cilindraje", nullable = true)
	private Integer cilindraje;
	@Column(name = "totalAPagar", nullable = false)
	private long totalAPagar;
}
