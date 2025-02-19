package name.webdizz.poc.rules.engine.controller;

import java.util.List;
import java.util.Random;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import name.webdizz.poc.rules.engine.domain.Consumer;
import name.webdizz.poc.rules.engine.domain.Decision;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/rules")
public class RulesEngineController {

    private KieSession kieSession;

    public RulesEngineController(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    @GetMapping("")
    public Flux<String> getRules() {
        return Flux.fromIterable(List.of("Rule 1"));
    }

    @PostMapping("/validate/{consumerId}/{productId}")
    public Flux<String> validateConsumer(@PathVariable("consumerId") String consumerId,
            @PathVariable("productId") String productId) {

        int sampled = new Random().nextInt(15);
        Consumer consumer = new Consumer(sampled);
        kieSession.insert(consumer);

        Decision decision = new Decision();
        kieSession.setGlobal("decision", decision);

        Agenda agenda = kieSession.getAgenda();
        agenda.getAgendaGroup("sampling").setFocus();

        kieSession.fireAllRules();

        return Flux.fromIterable(List.of("Consumer validated " + decision + "  for sampled " + sampled));
    }
}
