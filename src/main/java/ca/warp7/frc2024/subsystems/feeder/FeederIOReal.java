package ca.warp7.frc2024.subsystems.feeder;

import ca.warp7.frc2024.Constants.Feeder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.util.Units;

public class FeederIOReal implements FeederIO {
    private CANSparkMax topMotorController = new CANSparkMax(Feeder.TOP_MOTOR_CAN_ID, MotorType.kBrushless);
    private CANSparkMax bottomMotorController = new CANSparkMax(Feeder.BOTTOM_MOTOR_CAN_ID, MotorType.kBrushless);

    private RelativeEncoder topMotorEncoder = topMotorController.getEncoder();
    private RelativeEncoder bottomMotorEncoder = bottomMotorController.getEncoder();

    @Override
    public void updateInputs(FeederIOInputs inputs) {
        inputs.OutputVolts =
                new double[] {topMotorController.getAppliedOutput(), bottomMotorController.getAppliedOutput()};
        inputs.CurrentAmps =
                new double[] {topMotorController.getOutputCurrent(), bottomMotorController.getOutputCurrent()};
        inputs.AngularVelocityRadPerSec = new double[] {
            Units.rotationsPerMinuteToRadiansPerSecond(topMotorEncoder.getVelocity()),
            Units.rotationsPerMinuteToRadiansPerSecond(bottomMotorEncoder.getVelocity())
        };
        inputs.TempCelsius =
                new double[] {topMotorController.getMotorTemperature(), bottomMotorController.getMotorTemperature()};
    }

    @Override
    public void setVoltsTop(double volts) {
        topMotorController.set(volts);
    }

    @Override
    public void setVoltsBottom(double volts) {
        bottomMotorController.set(volts);
    }
}
