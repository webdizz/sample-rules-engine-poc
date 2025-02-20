package name.webdizz.poc.rules.engine.service;

import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import name.webdizz.poc.rules.engine.domain.Consumer;
import name.webdizz.poc.rules.engine.domain.Decision;
import name.webdizz.poc.rules.engine.domain.DualCadenceExecutionContext;

@Service
public class RuleExecutionService {

    private KieSession kieSession;
    private final DualCadenceExecutionContext context = new DualCadenceExecutionContext(2, 10, 5, 20);

    public RuleExecutionService(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    public Decision isSampleAllowed(Consumer consumer) {
        Decision decision = new Decision();
        kieSession.setGlobal("decision", decision);
        kieSession.setGlobal("context", context);
        kieSession.insert(consumer);
        kieSession.fireAllRules();
        return decision;
    }
}
