package ca.warp7.frc2024.subsystems.intake;

import ca.warp7.frc2024.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class IntakeSubsystem extends SubsystemBase {
    IntakeIO io = new IntakeIOSim();
    IntakeIOInputsAutoLogged inputs = new IntakeIOInputsAutoLogged();

    public IntakeSubsystem() {
        switch (Constants.MODE) {
            case REAL:
                io = new IntakeIOReal();
                break;
            case REPLAY:
            case SIM:
                io = new IntakeIOSim();
                break;
            default:
                break;
        }
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Intake", inputs);
    }

    public Command setVolts(double volts) {
        return runOnce(() -> io.setVolts(volts));
    }
}
