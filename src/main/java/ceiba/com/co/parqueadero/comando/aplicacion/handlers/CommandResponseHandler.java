package ceiba.com.co.parqueadero.comando.aplicacion.handlers;

import org.springframework.transaction.annotation.Transactional;

public interface CommandResponseHandler<C, R> {

	@Transactional
	R execute(C command);
}