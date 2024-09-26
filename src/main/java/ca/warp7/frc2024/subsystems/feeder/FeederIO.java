package ca.warp7.frc2024.subsystems.feeder;

import org.littletonrobotics.junction.AutoLog;

public interface FeederIO {
    @AutoLog
    public static class FeederIOInputs {
        public double[] OutputVolts = new double[0];
        public double[] AngularVelocityRadPerSec = new double[0];
        public double[] CurrentAmps = new double[0];
        public double[] TempCelsius = new double[0];
    }

    public abstract void updateInputs(FeederIOInputs inputs);

    public abstract void setVoltsTop(double volts);

    public abstract void setVoltsBottom(double volts);
}
