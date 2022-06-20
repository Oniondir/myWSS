package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.studica.frc.Servo;

public class Arm extends SubsystemBase
{
    private final Servo servo;
    private double servoAngle;

    //Good for debugging
    //Shuffleboard

    private final ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    private final NetworkTableEntry D_armValue = tab.add("armValue",0).getEntry();

    public Arm()
    {
        servo = new Servo(0);
    }

    /* Sets the servo angle
    <p>
    */
    public void setServoAngle(final double degrees)
    {
        servoAngle = degrees;
        servo.setAngle(degrees);
    }

    public double getServoAngle()
    {
        return servo.getAngle();
    }

    public void periodic()
    {
        //Display on shuffleboard
        //These display is good for debugging but may slow system down.
        //Good to remove unnecessary display during competition
        D_armValue.setDouble(servoAngle);
    }
}