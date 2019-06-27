package pl.decerto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.decerto.drools.DroolsCalculation;
import pl.decerto.hyperon.HyperonCalculation;

@SpringBootApplication
public class HyperonVsDroolsApplication implements CommandLineRunner {
	private final ConfigurableApplicationContext context;
	private final HyperonCalculation hyperonCalculation;
	private final DroolsCalculation droolsCalculation;

	public HyperonVsDroolsApplication(ConfigurableApplicationContext context, HyperonCalculation hyperonCalculation, DroolsCalculation droolsCalculation) {
		this.context = context;
		this.hyperonCalculation = hyperonCalculation;
		this.droolsCalculation = droolsCalculation;
	}

	public static void main(String[] args) {
		SpringApplication.run(HyperonVsDroolsApplication.class, args);
	}

	@Override
	public void run(String... args) {
		hyperonCalculation.runMassiveCalculations();

		droolsCalculation.runMassiveCalculations();

		SpringApplication.exit(context);
	}
}
