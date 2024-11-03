package se.coolcode.onnoff.v2.triggers;

import java.util.Set;

public class ValueTrigger implements Trigger {

    private final Set<?> values;

    public ValueTrigger(Set<?> values) {
        this.values = values;
    }

    public static Trigger create(Set<?> values) {
        return new ValueTrigger(values);
    }

    @Override
    public boolean isTriggered(String id, Object object) {
        return false;
    }
}
