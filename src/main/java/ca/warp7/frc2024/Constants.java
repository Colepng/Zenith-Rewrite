package ca.warp7.frc2024;

// CAN id convection
// - System 0x
// - Drivetrain 1x
// - Intake 2x
// - Arm 3x
// - Shooter 4x
// - Feeder 5x
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

    public static class Intake {
        public static final double LOOP_TIME = 0.020;

        public static final int MOTOR_CAN_ID = 20;
    }

    public static class Feeder {
        public static final double LOOP_TIME = 0.020;

        public static final int TOP_MOTOR_CAN_ID = 50;
        public static final int BOTTOM_MOTOR_CAN_ID = 51;
    }
}
