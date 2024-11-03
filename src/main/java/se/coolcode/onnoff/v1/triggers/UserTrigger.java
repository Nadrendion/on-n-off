package se.coolcode.onnoff.v1.triggers;

import java.util.Set;

public final class UserTrigger implements Trigger{

    private final Set<String> userIds;

    private UserTrigger(Set<String> userIds) {
        this.userIds = userIds;
    }

    public static Trigger init(Set<String> userIds) {
        return new UserTrigger(userIds);
    }
}
