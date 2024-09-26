package ca.warp7.frc2024.subsystems.feeder;

import ca.warp7.frc2024.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class FeederSubsystem extends SubsystemBase {
    private FeederIO io;
    private FeederIOInputsAutoLogged inputs = new FeederIOInputsAutoLogged();

    public FeederSubsystem() {
        switch (Constants.MODE) {
            case REAL:
                io = new FeederIOReal();
            case REPLAY:
            case SIM:
                io = new FeederIOSim();
                break;
            default:
                break;
        }
    }

    /**
     * Sets both rollers volts, rotating in the same direction
     * @param volts
     * @return
     */
    public Command setVoltsBoth(double volts) {
        return runOnce(() -> {
            io.setVoltsTop(volts);
            io.setVoltsBottom(-volts);
        });
    }

    /**
     * Sets the top roller's volts
     * @param volts
     * @return
     */
    public Command setVoltsTop(double volts) {
        return runOnce(() -> io.setVoltsTop(volts));
    }

    /**
     * Sets the bottom roller's volts
     * @param volts
     * @return
     */
    public Command setVoltsBottom(double volts) {
        return runOnce(() -> io.setVoltsBottom(volts));
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);

        Logger.processInputs("Feeder", inputs);
    }
}
