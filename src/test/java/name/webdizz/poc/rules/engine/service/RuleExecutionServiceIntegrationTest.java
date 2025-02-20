package name.webdizz.poc.rules.engine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import name.webdizz.poc.rules.engine.domain.Consumer;
import name.webdizz.poc.rules.engine.domain.Decision;

@SpringBootTest
public class RuleExecutionServiceIntegrationTest {

    @Autowired
    private RuleExecutionService ruleExecutionService;

    @Test
    public void testApproveConsumerSample() {
        Consumer consumer = new Consumer(5, 1, 2);
        Decision decision = ruleExecutionService.isSampleAllowed(consumer);

        assertEquals(true, decision.isApproved());
    }

    @Test
    public void testRejectConsumerSampleFirstCadence() {
        Consumer consumer = new Consumer(5, 3, 2);
        Decision decision = ruleExecutionService.isSampleAllowed(consumer);

        assertEquals(false, decision.isApproved());
        assertEquals("more then allowed during first cadence", decision.getReason());
    }

    @Test
    public void testRejectConsumerSampleSecondCadence() {
        Consumer consumer = new Consumer(15, 1, 6);
        Decision decision = ruleExecutionService.isSampleAllowed(consumer);

        assertEquals(false, decision.isApproved());
        assertEquals("more then allowed during second cadence", decision.getReason());
    }

    @Test
    public void testRejectConsumerSample() {
        Consumer consumer = new Consumer(12, 2, 5);
        Decision decision = ruleExecutionService.isSampleAllowed(consumer);

        assertEquals(false, decision.isApproved());
        assertEquals("more then allowed during second cadence", decision.getReason());
    }
}
