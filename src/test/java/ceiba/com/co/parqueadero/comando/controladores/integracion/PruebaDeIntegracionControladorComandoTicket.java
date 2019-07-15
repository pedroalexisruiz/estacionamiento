package ceiba.com.co.parqueadero.comando.controladores.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.ComandoTicket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Vigilante;
import ceiba.com.co.parqueadero.comando.infraestructura.controladores.ControladorComandoTicket;
import ceiba.com.co.parqueadero.comando.infraestructura.persistencia.repositorios.RepositorioTicketH2;
import ceiba.com.co.parqueadero.comando.testdatabuilder.TicketCommandBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class PruebaDeIntegracionControladorComandoTicket {

	private MockMvc mvc;
	private String json;
	private static final String URL = "/tickets";

	private static final String PLACA = "PED123";
	@Autowired
	ControladorComandoTicket controlador;

	@Autowired
	RepositorioTicketH2 repositorioTicketH2;

	@Autowired
	private WebApplicationContext context;

	ComandoTicket ticketComando;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@After
	public void tearDown() {
		repositorioTicketH2.borrarTodos();
	}

	@Test
	public void ingresarCarro() {
		// arrange
		ticketComando = new TicketCommandBuilder().conPlaca(PLACA).build();
		boolean vehiculoFueGuardado = false;
		json = asJsonString(ticketComando);

		try {
			// act
			mvc.perform(
					post(URL).content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			fail();
		}

		vehiculoFueGuardado = repositorioTicketH2.existeVehiculoEnParqueadero(ticketComando.getPlaca());

		// assert
		assertTrue(vehiculoFueGuardado);
	}

	@Test
	public void ingresarMoto() {
		// arrange
		ticketComando = new TicketCommandBuilder().conPlaca(PLACA).conCilindraje(250).conTipoDeVehiculo(Ticket.MOTO)
				.build();
		boolean vehiculoFueGuardado = false;
		json = asJsonString(ticketComando);

		try {
			// act
			mvc.perform(
					post(URL).content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			fail();
		}

		vehiculoFueGuardado = repositorioTicketH2.existeVehiculoEnParqueadero(ticketComando.getPlaca());

		// assert
		assertTrue(vehiculoFueGuardado);
	}

	@Test
	public void sacarCarroNoRegistrado() {
		// arrange
		ticketComando = new TicketCommandBuilder().conPlaca(PLACA).build();
		json = asJsonString(ticketComando);
		try {
			// act
			mvc.perform(
					put(URL).content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isInternalServerError())
					.andExpect(content().string(Vigilante.EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO));
		} catch (Exception e) {
			// assert
			assertEquals(e.getCause().getMessage(), Vigilante.EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
	}

	@Test
	public void registrarSalida() {
		// arrange
		ticketComando = new TicketCommandBuilder().conPlaca(PLACA).build();
		json = asJsonString(ticketComando);
		boolean vehiculoFueGuardado = false;

		try {
			mvc.perform(
					post(URL).content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

			// act
			mvc.perform(
					put(URL).content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// assert

		vehiculoFueGuardado = repositorioTicketH2.existeVehiculoEnParqueadero(ticketComando.getPlaca());
		assertFalse(vehiculoFueGuardado);
	}

	@Test
	public void ingresarCarroYaIngresado() {
		// arrange
		ticketComando = new TicketCommandBuilder().conPlaca(PLACA).build();
		json = asJsonString(ticketComando);

		// act
		try {
			mvc.perform(
					post(URL).content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

			mvc.perform(
					post(URL).content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			// assert
			assertEquals(Vigilante.EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO, e.getCause().getMessage());
		}
	}

	@Test
	@Sql("/insertarCarros.sql")
	public void ingresarCarroSinCupo() {
		// arrange
		ticketComando = new TicketCommandBuilder().conPlaca(PLACA).build();
		json = asJsonString(ticketComando);

		try {
			// act
			mvc.perform(
					post(URL).content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
			fail();
		} catch (Exception e) {
			// assert
			assertEquals(Vigilante.NO_HAY_ESPACIOS_DISPONIBLES, e.getCause().getMessage());
		}
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
