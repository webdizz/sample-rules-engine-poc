package name.webdizz.poc.rules.engine.domain;

public record DualCadenceExecutionContext(int allowedSamplesDuringFirstCadence,
                int firstCadenceDays,
                int allowedSamplesDuringSecondCadence,
                int secondCadenceDays) {
}
