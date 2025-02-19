package name.webdizz.poc.rules.engine.configuration;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RulesEngineConfiguration {

    @Bean
    public KieServices kieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    public KieFileSystem kieFileSystem() {
        KieFileSystem fs = kieServices().newKieFileSystem();
        List<String> ruleFilePaths = List.of("rules/SamplingRule.drl");
        for (String ruleFilePath : ruleFilePaths) {
            fs.write(ResourceFactory.newClassPathResource(ruleFilePath));
        }
        return fs;
    }

    @Bean
    public KieBuilder kieBuilder(KieServices kieServices, KieFileSystem kieFileSystem) {
        KieBuilder builder = kieServices.newKieBuilder(kieFileSystem);
        builder.buildAll();
        return builder;
    }

    @Bean
    public KieModule kieModule(KieBuilder kieBuilder) {
        return kieBuilder.getKieModule();
    }

    @Bean
    public KieRepository kieRepository(KieServices kieServices) {
        return kieServices.getRepository();
    }

    @Bean
    public KieContainer kieContainer(KieServices kieServices, KieRepository kieRepository, KieModule kieModule) {
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    @Bean
    public KieSession kieSession(KieContainer kieContainer) {
        return kieContainer.newKieSession();
    }
}