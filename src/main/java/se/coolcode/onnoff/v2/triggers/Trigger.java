package se.coolcode.onnoff.v2.triggers;

public interface Trigger {

    boolean isTriggered(String id, Object object);
}
