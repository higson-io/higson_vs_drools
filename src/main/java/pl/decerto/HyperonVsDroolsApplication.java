package pl.decerto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.decerto.hyperon.HyperonCalculation;

@SpringBootApplication
public class HyperonVsDroolsApplication implements CommandLineRunner {

	private final ConfigurableApplicationContext context;
	private final HyperonCalculation calculation;

	public HyperonVsDroolsApplication(ConfigurableApplicationContext context, HyperonCalculation calculation) {
		this.context = context;
		this.calculation = calculation;
	}

	public static void main(String[] args) {
		SpringApplication.run(HyperonVsDroolsApplication.class, args);
	}

	@Override
	public void run(String... args) {
		calculation.massiveCalculations();

		SpringApplication.exit(context);
	}
}
