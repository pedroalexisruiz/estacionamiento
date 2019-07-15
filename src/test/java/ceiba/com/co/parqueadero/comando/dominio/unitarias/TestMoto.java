package ceiba.com.co.parqueadero.comando.dominio.unitarias;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketMoto;
import ceiba.com.co.parqueadero.comando.testdatabuilder.TicketMotoBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan("ceiba.com.co")
public class TestMoto {

	TicketMoto moto;

	@Before
	public void setUp() {

	}

	@Test
	public void calcularPrecioPorUnDia() {
		// arrange
		LocalDateTime horaDeEntrada = LocalDateTime.of(2019, 07, 9, 0, 0);
		LocalDateTime horaDeSalida = LocalDateTime.of(2019, 07, 10, 0, 0);
		long totalAPagar = TicketMoto.VALOR_DIA;
		moto = new TicketMotoBuilder().conHoraDeEntrada(horaDeEntrada).conHoraDeSalida(horaDeSalida).build();

		// act
		moto.calcularPrecioAPagar();
		// assert
		assertEquals(totalAPagar, moto.getTotalAPagar());
	}

	@Test
	public void calcularPrecioPorHoras() {
		// arrange
		int cantidadDeHoras = 5;
		LocalDateTime horaDeEntrada = LocalDateTime.of(2019, 07, 9, 0, 0);
		LocalDateTime horaDeSalida = LocalDateTime.of(2019, 07, 9, cantidadDeHoras, 0);
		long totalAPagar = TicketMoto.VALOR_HORA * cantidadDeHoras;
		
		moto = new TicketMotoBuilder().conHoraDeEntrada(horaDeEntrada).conHoraDeSalida(horaDeSalida).build();

		// act
		moto.calcularPrecioAPagar();
		// assert
		assertEquals(totalAPagar, moto.getTotalAPagar());
	}

	@Test
	public void calcularPrecioCuandoCumpleNueveHoras() {
		// arrange
		int cantidadDeHoras = 9;
		LocalDateTime horaDeEntrada = LocalDateTime.of(2019, 07, 9, 0, 0);
		LocalDateTime horaDeSalida = LocalDateTime.of(2019, 07, 9, cantidadDeHoras, 0);
		long totalAPagar = TicketMoto.VALOR_DIA;
		
		moto = new TicketMotoBuilder().conHoraDeEntrada(horaDeEntrada).conHoraDeSalida(horaDeSalida).build();

		// act
		moto.calcularPrecioAPagar();
		// assert
		assertEquals(totalAPagar, moto.getTotalAPagar());
	}

	@Test
	public void calcularPrecioParaAltoCilindraje() {
		// arrange
		int cantidadDeHoras = 5;
		LocalDateTime horaDeEntrada = LocalDateTime.of(2019, 07, 9, 0, 0);
		LocalDateTime horaDeSalida = LocalDateTime.of(2019, 07, 9, cantidadDeHoras, 0);
		long totalAPagar = TicketMoto.VALOR_HORA * cantidadDeHoras + TicketMoto.COSTO_CILINDRAJE_ALTO;
		
		moto = new TicketMotoBuilder().conHoraDeEntrada(horaDeEntrada).conHoraDeSalida(horaDeSalida)
				.conCilindraje(TicketMoto.CILINDRAJE_ALTO).build();

		// act
		moto.calcularPrecioAPagar();
		// assert
		assertEquals(totalAPagar, moto.getTotalAPagar());
	}
}
