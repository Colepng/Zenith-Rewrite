package ca.warp7.frc2024.subsystems.intake;

import ca.warp7.frc2024.Constants.Intake;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class IntakeIOSim implements IntakeIO {
    // Math
    // Orange Compliant wheels weigh 0.12lbs * 6 = 0.72 lbs
    // 0.75in dia by 1.375in spacer:
    // Assuming spacers are made with a pla that has a density of 1.25 g/cm^3 or 0.045159115 lb/in^3
    // 0.75in pla cylinder:
    // = 0.75in^2 * 1.375in * PI * 0.04516 lb/in^3 = 0.1097287723lbs
    // 0.5in pla Hexagonal Cylinder:
    // Area of hexagon:
    // https://www.omnicalculator.com/math/hexagon?calculatorResult=H4sIAAAAAAAAA9VXS2%2FbOBD%2BKwWxh6TI2hRfEn1YoJtggRzaBTbbXooeGImR2VKSIYlBg8D%2FfYeyZEm2bECoEWANWCKH8%2FjmwRn7Fek8Nbl%2BqFWt0eoVxcrGzsIm%2BaJKox6trj6pTFdo9RVVJtHoBsWmjF3mn1b%2FoxLjKiCqUit4bXRpMl3rEtbVuijrO6PSIlcW9rbI0%2F32G%2BhZqzydsqM2Rb3WWa910uJI3XnLDXBvscgTU5sir7yrJt%2B4erTwfAuMVs3i3R%2FvcAthEQDNL1ra3taCwMF%2B154OgS0oMAwJLc8I4YJ5k0NKy3Xs94ID6zG5w7oL3EJ4uLt1c7K9QYWrD5x8RXVp0hTA52mXA58CtMqdtTfoWVkHm7p0etuHYZ7UKFDzRA%2BjeFr6sBLGeo4iPQ%2FGdApmxq5Pyxkv%2BqofSm9B3plhlQ6T2RxDVT%2FrsoKy%2FqsoM7i8TXV36vyd3jPcdyxg7%2BoqIOGSY4yv31%2BV%2BkmXOo%2F1F2%2F7%2BhoN1f7t6oGYl1iCKEgdi7WVctaqCIhcEq9mtu1OatkoOYVgol28eRRGNfnm1vtmeM70MphpF%2FhPGBx33Df3t2nwb2wVzGoQuz0zMPsrfeHRqX%2FWpbpTdXPRTPUheVYALUGrJ2UrfQOkj8WjsXpPcFVXCy6vy5fbwscLfX7wmFzpHXuB%2Fe2HuwHh4SV7LCCZ6DePAGa1U6luJXX%2BeyMMeu8zj1zZz7mpq9agD87TLpCefKc3Ok%2B8zo9qs2thtoh%2FTIQMfMtU7pS9992uubfHNymDIy%2Fld5mq19%2BBD%2F1p0k8ue2zC1zZQhKFXt%2F2zW4%2Fa4gxFZKjJb7Yd0tNp%2F9YqOOzHrRaMBeVN%2Bc3AsRPajvrsLHEcUCmigNCQhoJKEmBJqeAEywDDJmTwDTGFJeFECkk5jQLGQya5wJxTOYjDhbSd6tez%2FAopJZRFLIhCHBEWAgQiRBBJ7tGxiANOERLGGSEsCCgW8CCcUUEADAtHbl1C2S%2FVLQ6YEEyAGTAXCCYZYOEAKpQY8DAZBhJizpjEEWMRITAP4cFxBKikxKMsXULXwUSZ4wqjUjIsMcQO9LEIAsU5GKUh1AdQAg4FhClEmQMIEUZUiFBygOGxkWjoyiV0TcyqWZkho1ppt%2F0E%2Bj9Xre8rpY4LY%2Fd%2FCJWri0o9611zjgtr1abSyX3Stuu1tpt%2FYR6NetxuCHRdfsdYaatjGJPNnHiwLq26fyP%2BvYHyUqUfQiaP18PfkcMTclSK%2FWmcTd26Y71H6T9mmW5IExD7nn54CJ%2F%2FAATTmFhcDwAA
    // = 0.2165 in^2
    // Weight of Hexagonal Cylinder:
    // = 0.2165in^2 * 1.375in * 0.04516 lb/in^3 = 0.013443303lbs
    // Final spacer:
    // = 0.1097287723lbs - 0.013443303lbs
    // = 0.0962854682lbs
    // Total spacer weigh 0.0962854682lbs * 5 = 0.481427341lbs
    // Volume of Hex axle = 6.793in^3
    // Assuming the use of 7075 T6 Aluminum the Hex axle weighs 6.793in^3 * 0.102 lb/in^3 = 0.692886lbs
    // This gives a total weight of a roller = 0.481427341lbs + 0.692886lbs + 0.72lbs = 1.89431341lbs
    // This gives a moi or mass moment of inertia of 0.0024946 kg*m^2 of one roller
    // Im assuming for two rollers I can just double my moi so my final value is 0.0024946kg*m^2 * 2 = 0.0049892
    private final DCMotorSim motorSim = new DCMotorSim(DCMotor.getNEO(1), 3, 0.0049892);
    private double voltage = 0;

    @Override
    public void updateInputs(IntakeIOInputs inputs) {
        motorSim.update(Intake.LOOP_TIME);

        inputs.AngularVelocityRadPerSec = motorSim.getAngularVelocityRadPerSec();
        inputs.OutputVolts = voltage;
        inputs.CurrentAmps = motorSim.getCurrentDrawAmps();
        inputs.TempCelsius = 0;
    }

    @Override
    public void setVolts(double volts) {
        motorSim.setInputVoltage(volts);
        voltage = volts;
    }
}
