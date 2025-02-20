package name.webdizz.poc.rules.engine.service;

import org.kie.api.runtime.StatelessKieSession;
import org.springframework.stereotype.Service;

import name.webdizz.poc.rules.engine.domain.Consumer;
import name.webdizz.poc.rules.engine.domain.Decision;
import name.webdizz.poc.rules.engine.domain.DualCadenceExecutionContext;

@Service
public class RuleExecutionService {

    private StatelessKieSession kieSession;
    private final DualCadenceExecutionContext context = new DualCadenceExecutionContext(2, 10, 5, 20);

    public RuleExecutionService(StatelessKieSession kieSession) {
        this.kieSession = kieSession;
    }

    public Decision isSampleAllowed(Consumer consumer) {
        Decision decision = new Decision();
        kieSession.setGlobal("decision", decision);
        kieSession.setGlobal("context", context);
        kieSession.execute(consumer);
        return decision;
    }
}
