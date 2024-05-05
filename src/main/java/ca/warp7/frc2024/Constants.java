package ca.warp7.frc2024;

public final class Constants {
    public static final Mode MODE = Mode.SIM;

    public static enum Mode {
        // Controlling real outputs
        REAL,

        // Controlling simulated outputs
        SIM,

        // Replaying a log file on simulated outputs
        REPLAY,
    }
}
