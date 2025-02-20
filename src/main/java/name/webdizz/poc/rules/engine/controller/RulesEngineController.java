package name.webdizz.poc.rules.engine.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import name.webdizz.poc.rules.engine.domain.Consumer;
import name.webdizz.poc.rules.engine.domain.Decision;
import name.webdizz.poc.rules.engine.service.RuleExecutionService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/rules")
public class RulesEngineController {

    private RuleExecutionService ruleExecutionService;

    public RulesEngineController(RuleExecutionService ruleExecutionService) {
        this.ruleExecutionService = ruleExecutionService;
    }

    @PostMapping("/validate/{consumerId}/{productId}")
    public Mono<Decision> validateConsumer(@PathVariable("consumerId") String consumerId,
            @PathVariable("productId") String productId) {

        int daysSinceRecentSample = new Random().nextInt(30);
        int firstCadenceSamples = new Random().nextInt(3);
        int secondCadenceSamples = new Random().nextInt(6);
        Consumer consumer = new Consumer(daysSinceRecentSample, firstCadenceSamples, secondCadenceSamples);
        Decision decision = ruleExecutionService.isSampleAllowed(consumer);

        return Mono.just(decision);
    }
}
