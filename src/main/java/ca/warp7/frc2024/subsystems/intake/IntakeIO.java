package ca.warp7.frc2024.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
    @AutoLog
    public static class IntakeIOInputs {
        public double OutputVolts = 0.0;
        public double AngularVelocityRadPerSec = 0.0;
        public double CurrentAmps = 0.0;
        public double TempCelsius = 0.0;
    }

    public abstract void updateInputs(IntakeIOInputs inputs);

    public abstract void setVolts(double Volts);
}
