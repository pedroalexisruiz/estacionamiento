package ceiba.com.co.parqueadero.comando.dominio.entidad.util;

import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class GeneradorDeFecha {
	public Calendar obtenerFechaActual() {
		return Calendar.getInstance();
	}
	
	public LocalDateTime obtenerHoraLocalActual() {
		return LocalDateTime.now();
	}
}
