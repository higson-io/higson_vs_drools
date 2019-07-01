package pl.decerto.jmh.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

/**
 * @author Maciej Główka on 01.07.2019
 */
class ConfigurationUtils {

	private ConfigurationUtils() {
	}

	static KieSession createSession() {
		KieServices kieServices = KieServices.get();
		KieRepository kieRepository = kieServices.getRepository();
		kieRepository.addKieModule(kieRepository::getDefaultReleaseId);

		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

		kieFileSystem.write(ResourceFactory.newFileResource("./jmh/src/main/resources/rules/qx.xls"));
//				kieFileSystem.write(ResourceFactory.newFileResource("./jmh/src/main/resources/rules/rate_adjustment_no_asterisk.xls"));
//				uncomment for testCase 2
		kieFileSystem.write(ResourceFactory.newFileResource("./jmh/src/main/resources/rules/rate_adjustment.xls"));

		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		kieBuilder.buildAll();

		KieModule kieModule = kieBuilder.getKieModule();
		KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

		return kieContainer.newKieSession();
	}

}
