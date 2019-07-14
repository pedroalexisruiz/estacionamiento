package ceiba.com.co.parqueadero.comando.aplicacion.manejadores;

public class RespuestaComando<T> {

	private T datos;

	public RespuestaComando(T datos) {
		this.datos = datos;
	}

	public T getDatos() {
		return datos;
	}
}
