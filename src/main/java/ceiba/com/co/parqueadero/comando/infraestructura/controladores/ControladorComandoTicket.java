package ceiba.com.co.parqueadero.comando.infraestructura.controladores;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.ComandoTicket;
import ceiba.com.co.parqueadero.comando.aplicacion.manejadores.RespuestaComando;
import ceiba.com.co.parqueadero.comando.aplicacion.manejadores.ManejadorRegistrarEntrada;
import ceiba.com.co.parqueadero.comando.aplicacion.manejadores.ManejadorRegistrarSalida;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorComandoTicket {

	private final ManejadorRegistrarEntrada manejadorDeRegistroDeEntrada;
	private final ManejadorRegistrarSalida manejadorDeRegistroDeSalida;

	@Autowired
	public ControladorComandoTicket(ManejadorRegistrarEntrada manejadorDeRegistroDeEntrada,
			ManejadorRegistrarSalida manejadorDeRegistroDeSalida) {
		this.manejadorDeRegistroDeEntrada = manejadorDeRegistroDeEntrada;
		this.manejadorDeRegistroDeSalida = manejadorDeRegistroDeSalida;
	}

	@PostMapping
	public RespuestaComando<LocalDateTime> registrarEntrada(@RequestBody ComandoTicket ticketComando) {
		return manejadorDeRegistroDeEntrada.ejecutar(ticketComando);
	}

	@PutMapping
	public RespuestaComando<LocalDateTime> registrarSalida(@RequestBody ComandoTicket ticketComando) {
		return manejadorDeRegistroDeSalida.ejecutar(ticketComando);
	}
}