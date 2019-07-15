package ceiba.com.co.parqueadero.comando.controladores.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.ComandoTicket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Vigilante;
import ceiba.com.co.parqueadero.comando.dominio.excepcion.ExcepcionParqueaderoSinEspacio;
import ceiba.com.co.parqueadero.comando.dominio.excepcion.ExcepcionVehiculoYaIngresado;
import ceiba.com.co.parqueadero.comando.infraestructura.controladores.ControladorComandoTicket;
import ceiba.com.co.parqueadero.comando.infraestructura.persistencia.repositorios.RepositorioTicketH2;
import ceiba.com.co.parqueadero.comando.testdatabuilder.TicketCommandBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan("ceiba.com.co")
public class PruebaDeIntegracionControladorComandoTicket {
//mockMVC
	
	private final String PLACA_BASE = "PED123";
	@Autowired
	ControladorComandoTicket controlador;

	@Autowired
	RepositorioTicketH2 repositorioTicketH2;

	ComandoTicket ticketComando;

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
		repositorioTicketH2.borrarTodos();
	}

	@Test
	public void ingresarCarro() {
		// arrange
		ticketComando = new TicketCommandBuilder().conPlaca(PLACA_BASE).build();
		boolean vehiculoFueGuardado = false;

		// act
		controlador.registrarEntrada(ticketComando);

		// assert

		vehiculoFueGuardado = repositorioTicketH2.existeVehiculoEnParqueadero(ticketComando.getPlaca());
		assertTrue(vehiculoFueGuardado);
	}

	@Test
	public void ingresarMoto() {
		// arrange
		ticketComando = new TicketCommandBuilder().conPlaca(PLACA_BASE).conCilindraje(250).conTipoDeVehiculo(Ticket.MOTO)
				.build();
		boolean vehiculoFueGuardado = false;

		// act
		controlador.registrarEntrada(ticketComando);

		// assert

		vehiculoFueGuardado = repositorioTicketH2.existeVehiculoEnParqueadero(ticketComando.getPlaca());
		assertTrue(vehiculoFueGuardado);
	}

	@Test
	public void sacarCarroNoRegistrado() {
		// arrange
		ticketComando = new TicketCommandBuilder().conPlaca(PLACA_BASE).build();
		try {
			// act
			controlador.registrarSalida(ticketComando);
		} catch (ExcepcionVehiculoYaIngresado e) {
			// assert
			assertEquals(Vigilante.EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO, e.getMessage());
		}
	}

	@Test
	public void registrarSalida() {
		// arrange
		ticketComando = new TicketCommandBuilder().conPlaca(PLACA_BASE).build();
		boolean vehiculoFueGuardado = false;
		controlador.registrarEntrada(ticketComando);

		// act
		controlador.registrarSalida(ticketComando);

		// assert

		vehiculoFueGuardado = repositorioTicketH2.existeVehiculoEnParqueadero(ticketComando.getPlaca());
		assertFalse(vehiculoFueGuardado);
	}

	@Test
	public void ingresarCarroYaIngresado() {
		// arrange
		ticketComando = new TicketCommandBuilder().conPlaca(PLACA_BASE).build();

		controlador.registrarEntrada(ticketComando);

		// act
		try {
			controlador.registrarEntrada(ticketComando);
		} catch (ExcepcionVehiculoYaIngresado e) {
			// assert
			assertEquals(Vigilante.EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO, e.getMessage());
		}
	}

	@Test
	@Sql("/insertarCarros.sql")
	public void ingresarCarroSinCupo() {
		// arrange
		ticketComando = new TicketCommandBuilder().conPlaca(PLACA_BASE).build();

		try {
			// act
			controlador.registrarEntrada(ticketComando);
			fail();
		} catch (ExcepcionParqueaderoSinEspacio e) {
			// assert
			assertEquals(Vigilante.NO_HAY_ESPACIOS_DISPONIBLES, e.getMessage());
		}
	}
}
