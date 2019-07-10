package ceiba.com.co.parqueadero.consulta.infraestructura.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceiba.com.co.parqueadero.consulta.aplicacion.manejadores.ManejadorListarVehiculosParqueados;
import ceiba.com.co.parqueadero.consulta.dominio.dto.Ticket;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorConsultaVehiculosIngresados {

	private final ManejadorListarVehiculosParqueados manejadorListarVehiculosParqueados;

	@Autowired
	public ControladorConsultaVehiculosIngresados(ManejadorListarVehiculosParqueados manejadorListarVehiculosParqueados) {
		this.manejadorListarVehiculosParqueados = manejadorListarVehiculosParqueados;
	}

	@GetMapping
	public List<Ticket> listarVehiculosParqueados() {
		return manejadorListarVehiculosParqueados.ejecutar();
	}

}