package ceiba.com.co.parqueadero.comando.dominio.entidad.util;

import java.time.LocalDateTime;
import java.util.Calendar;

public class GeneradorDeFecha {
	
	private static GeneradorDeFecha instancia;
	
	public static GeneradorDeFecha obtenerInstancia() {
		if(instancia == null) {
			instancia = new GeneradorDeFecha();
		}
		
		return instancia;
	}
	
	public Calendar obtenerFechaActual() {
		return Calendar.getInstance();
	}
	
	public LocalDateTime obtenerHoraLocalActual() {
		return LocalDateTime.now();
	}
}
