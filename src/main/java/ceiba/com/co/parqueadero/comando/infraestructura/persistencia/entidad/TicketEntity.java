 package ceiba.com.co.parqueadero.comando.infraestructura.persistencia.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ticket")
public class TicketEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "plate", nullable = false, length = 6)
	private String plate;
	@Column(name = "inTime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date inTime;
	@Column(name = "outTime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date outTime;
	@Column(name = "vehicleType", nullable = false, length = 5)
	private String vehicleType;
	@Column(name = "displacement", nullable = true)
	private int displacement;
	@Column(name = "totalAPagar", nullable = false)
	private int totalAPagar;
}
