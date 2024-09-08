package ca.warp7.frc2024.subsystems.intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import ca.warp7.frc2024.Constants.Intake;
import edu.wpi.first.math.util.Units;

public class IntakeIOReal implements IntakeIO {
    CANSparkMax sparkMax = new CANSparkMax(Intake.MOTOR_CAN_ID, MotorType.kBrushless);
    RelativeEncoder encoder = sparkMax.getEncoder();
    double voltage = 0;

    @Override
    public void updateInputs(IntakeIOInputs inputs) {
        inputs.AngularVelocityRadPerSec = Units.rotationsPerMinuteToRadiansPerSecond(encoder.getVelocity());
        inputs.OutputVolts = voltage;
        inputs.CurrentAmps = sparkMax.getOutputCurrent();
        inputs.TempCelsius = sparkMax.getMotorTemperature();
    }

    @Override
    public void setVolts(double Volts) {
        sparkMax.setVoltage(Volts);
        voltage = Volts;
    }
}
