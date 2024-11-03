package se.coolcode.onnoff.v1.triggers;

import java.time.LocalDate;

public final class DateTrigger implements Trigger {

    private final LocalDate initialTrigger;

    private DateTrigger(LocalDate initialTrigger) {
        this.initialTrigger = initialTrigger;
    }

    public static DateTrigger init(LocalDate initialTrigger) {
        return new DateTrigger(initialTrigger);
    }

    public boolean isActive(LocalDate currentDate) {
        return ! currentDate.isBefore(initialTrigger);
    }
}
