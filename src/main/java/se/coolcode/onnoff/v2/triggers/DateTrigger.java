package se.coolcode.onnoff.v2.triggers;

import java.time.LocalDate;

public class DateTrigger implements Trigger {

    private final LocalDate date;

    private DateTrigger(LocalDate date) {
        this.date = date;
    }

    public static DateTrigger create(String date) {
        return create(LocalDate.parse(date));
    }

    public static DateTrigger create(LocalDate date) {
        return new DateTrigger(date);
    }

    @Override
    public boolean isTriggered(String id, Object object) {
        return false;
    }
}
