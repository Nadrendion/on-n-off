package se.coolcode.onnoff.v1;

public class Config {

    private final long daysOffset;

    private Config() {
        this(0);
    }

    Config(long daysOffset) {
        this.daysOffset = daysOffset;
    }

    public static Config create() {
        return new Config();
    }

    public long daysOffset() {
        return daysOffset;
    }
}
