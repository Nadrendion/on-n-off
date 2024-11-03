package se.coolcode.onnoff.v2.triggers;

public class PercentageTrigger implements Trigger {

    private final int percentage;

    private PercentageTrigger(int percentage) {
        this.percentage = percentage;
    }

    public static PercentageTrigger create(int percentage) {
        return new PercentageTrigger(percentage);
    }

    @Override
    public boolean isTriggered(String id, Object object) {
        return false;
    }
}
