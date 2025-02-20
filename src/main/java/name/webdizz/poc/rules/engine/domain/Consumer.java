package name.webdizz.poc.rules.engine.domain;

public record Consumer(int daysSinceRecentSample, int samplesDuringFirstCadence, int samplesDuringSecondCadence) {

    public Consumer(int daysSinceRecentSample) {
        this(daysSinceRecentSample, 0, 0);
    }

    public Consumer(int daysSinceRecentSample, int samplesDuringFirstCadence) {
        this(daysSinceRecentSample, samplesDuringFirstCadence, 0);
    }
}