package se.coolcode.onnoff.v1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OnNOff {

    private static Config config = Config.create();



    public static ConfigBuilder configure() {
        return new ConfigBuilder();
    }



    public static class ConfigBuilder {

        private long daysOffset;

        public ConfigBuilder dateOffset(LocalDate newCurrentDate) {
            this.daysOffset = ChronoUnit.DAYS.between(LocalDate.now(), newCurrentDate);
            return this;
        }

        public void apply() {
            config = new Config(daysOffset);
        }
    }
}
