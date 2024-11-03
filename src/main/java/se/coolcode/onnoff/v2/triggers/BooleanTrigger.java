package se.coolcode.onnoff.v2.triggers;

public class BooleanTrigger implements Trigger {

    private BooleanTrigger() {}

    public static BooleanTrigger create() {
        return new BooleanTrigger();
    }

    @Override
    public boolean isTriggered(String id, Object object) {
        String key = id + ".active";
        String value = System.getProperty(key, "false").toLowerCase();
        if ("true".equals(value) || "false".equals(value)) {
            return Boolean.parseBoolean(value);
        } else {
            throw new TriggerParsingException(String.format("Failed to parse value %s for key %s.", value, key));
        }
    }
}
