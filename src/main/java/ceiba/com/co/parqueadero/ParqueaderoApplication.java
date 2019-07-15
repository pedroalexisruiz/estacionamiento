package ceiba.com.co.parqueadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ceiba.com.co")
public class ParqueaderoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderoApplication.class, args);
	}

}
