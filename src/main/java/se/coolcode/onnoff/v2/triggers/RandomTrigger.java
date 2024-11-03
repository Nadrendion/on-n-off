package se.coolcode.onnoff.v2.triggers;

public class RandomTrigger implements Trigger {

    public static RandomTrigger create() {
        return new RandomTrigger();
    }

    @Override
    public boolean isTriggered(String id, Object object) {
        return false;
    }
}
