package se.coolcode.onnoff.v1;

import se.coolcode.onnoff.v1.triggers.DateTrigger;
import se.coolcode.onnoff.v1.triggers.Trigger;
import se.coolcode.onnoff.v1.triggers.UserTrigger;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class FeatureFlag {

    private final String id;
    private final Trigger trigger;
    private Config config;
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public FeatureFlag(String id, Trigger trigger) {
        this.id = id;
        this.trigger = trigger;
        //executor.scheduleAtFixedRate()

    }

    public boolean isActive() {
        return switch (trigger) {
            case DateTrigger dateTrigger -> dateTrigger.isActive(LocalDate.now().plusDays(config.daysOffset()));
            case UserTrigger userTrigger -> false;
        };
    }

    public static FeatureFlagBuilder builder() {
        return new FeatureFlagBuilder();
    }

    public static class FeatureFlagBuilder {

        private String id;
        private Trigger trigger;

        public FeatureFlagBuilder id(String id) {
            this.id = id;
            return this;
        }

        public FeatureFlagBuilder trigger(Trigger trigger) {
            this.trigger = trigger;
            return this;
        }

        public FeatureFlag build() {
            return new FeatureFlag(id, trigger);
        }
    }

    private class CheckForValuesTask {

    }
}
