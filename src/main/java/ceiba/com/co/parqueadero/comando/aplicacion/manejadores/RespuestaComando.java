package ceiba.com.co.parqueadero.comando.aplicacion.manejadores;

public class RespuestaComando<T> {

	private T valor;

	public RespuestaComando(T valor) {
		this.valor = valor;
	}

	public T getValor() {
		return valor;
	}
}
