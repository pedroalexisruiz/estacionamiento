package ceiba.com.co.parqueadero.comando.aplicacion.handlers;

public class CommandResponse<T> {

	private T value;

	public CommandResponse(T value) {
		this.value = value;
	}

	public T getValor() {
		return value;
	}
}
