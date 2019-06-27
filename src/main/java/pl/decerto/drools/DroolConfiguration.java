package pl.decerto.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Maciej Główka on 25.06.2019
 */
@Configuration
public class DroolConfiguration {
	private KieServices kieServices = KieServices.get();

	private KieFileSystem getKieFileSystem(int testCase) {
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

		kieFileSystem.write(ResourceFactory.newClassPathResource("rules/qx.xls"));
		kieFileSystem.write(ResourceFactory.newClassPathResource(testCase == 1 ? "rules/rate_adjustment_no_asterisk.xls" : "rules/rate_adjustment.xls"));

		return kieFileSystem;
	}

	@Bean
	public KieContainer getKieContainer(@Value("${test.case:1}") int testCase) {
		getKieRepository();
		KieBuilder kieBuilder = kieServices.newKieBuilder(getKieFileSystem(testCase));
		kieBuilder.buildAll();

		KieModule kieModule = kieBuilder.getKieModule();
		return kieServices.newKieContainer(kieModule.getReleaseId());
	}

	private void getKieRepository() {
		KieRepository kieRepository = kieServices.getRepository();
		kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
	}

	@Bean
	public KieSession getKieSession(@Value("${test.case:1}") int testCase) {
		return getKieContainer(testCase).newKieSession();
	}
}
