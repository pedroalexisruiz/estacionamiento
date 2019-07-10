package ceiba.com.co.parqueadero.comando.aplicacion.manejadores;

import org.springframework.transaction.annotation.Transactional;

public interface ManejadorRespuestaComando<C, R> {

	@Transactional
	R ejecutar(C comando);
}