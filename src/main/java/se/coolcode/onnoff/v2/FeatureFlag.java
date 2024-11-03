package se.coolcode.onnoff.v2;

import se.coolcode.onnoff.v2.triggers.Trigger;

public class FeatureFlag {

    private final String id;
    private final Trigger trigger;

    private FeatureFlag(String id, Trigger trigger) {
        this.id = id;
        this.trigger = trigger;
    }

    public static FeatureFlagBuilder builder() {
        return new FeatureFlagBuilder();
    }

    public boolean isActive() {
        return trigger.isTriggered(id, null);
    }

    public boolean isActive(Object object) {
        return trigger.isTriggered(id, object);
    }

    public static class FeatureFlagBuilder {

        private String id;
        private Trigger trigger;

        private FeatureFlagBuilder() {}

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
}
