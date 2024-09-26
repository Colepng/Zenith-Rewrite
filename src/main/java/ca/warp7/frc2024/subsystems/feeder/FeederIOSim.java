package ca.warp7.frc2024.subsystems.feeder;

import ca.warp7.frc2024.Constants.Feeder;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class FeederIOSim implements FeederIO {
    private DCMotorSim topMotor = new DCMotorSim(DCMotor.getNEO(1), 1, 0.001);
    private DCMotorSim bottomMotor = new DCMotorSim(DCMotor.getNEO(1), 1, 0.001);
    private double topMotorAppliedVolts = 0;
    private double bottomMotorAppliedVolts = 0;

    @Override
    public void updateInputs(FeederIOInputs inputs) {
        topMotor.update(Feeder.LOOP_TIME);
        bottomMotor.update(Feeder.LOOP_TIME);

        inputs.OutputVolts = new double[] {topMotorAppliedVolts, bottomMotorAppliedVolts};
        inputs.CurrentAmps = new double[] {topMotor.getCurrentDrawAmps(), bottomMotor.getCurrentDrawAmps()};
        inputs.AngularVelocityRadPerSec =
                new double[] {topMotor.getAngularVelocityRadPerSec(), bottomMotor.getAngularVelocityRadPerSec()};
        inputs.TempCelsius = new double[] {0, 0};
    }

    @Override
    public void setVoltsTop(double volts) {
        topMotorAppliedVolts = volts;
        topMotor.setInput(volts);
    }

    @Override
    public void setVoltsBottom(double volts) {
        bottomMotorAppliedVolts = volts;
        bottomMotor.setInput(volts);
    }
}
