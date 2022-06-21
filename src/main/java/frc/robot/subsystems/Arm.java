package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.studica.frc.Servo;

public class Arm extends SubsystemBase
{
    private final Servo servo;
    private final Servo servo2;
    private double servoAngle;
    private double servoAngle2; 
    private double q1;
    private double q2;
    private double a1 = 0.25;
    private double a2 = 0.27;
    private double[] xy = new double[2];

    //Good for debugging
    //Shuffleboard

    private final ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    private final NetworkTableEntry D_armValue = tab.add("armValue",0).getEntry();
    private final NetworkTableEntry D_armValue2 = tab.add("armValue2",0).getEntry();
 
    public Arm()
    {
        servo = new Servo(0);
        servo2 = new Servo(1);
    }

    /* Sets the servo angle
    <p>
    */
    public double[] GoToPosXY(double x,double y)
    {
        q2 = (Math.PI-(Math.acos((Math.pow(x, 2)+Math.pow(y, 2)-Math.pow(a1, 2)-Math.pow(a2, 2))/(2*a1*a2)))/(2*Math.PI))*360;
        q1 = (Math.PI-(Math.atan(y/x) - Math.atan((a2*Math.sin(q2))/(a1+(a2*Math.cos(q2)))))/(2*Math.PI))*360;
        xy[0] = q1;
        xy[1] = q2;
        return xy;
    }

    public void setAutoServoAngle(double x,double y)
    {
        double qone = GoToPosXY(x, y)[0];
        double qtwo = GoToPosXY(x, y)[1];
        servo.setAngle(qone);
        servo2.setAngle(qtwo);
    }

    public void setServoAngle(final double degrees)
    {
        servoAngle = degrees;
        servo.setAngle(degrees);
    }

    public void setServoAngle2(final double degrees)
    {
        servoAngle2 = degrees;
        servo2.setAngle(degrees);
    }

    public double getServoAngle()
    {
        return servo.getAngle();
    }

    public double getServoAngle2()
    {
        return servo2.getAngle();
    }

    public void periodic()
    {
        //Display on shuffleboard
        //These display is good for debugging but may slow system down.
        //Good to remove unnecessary display during competition
        D_armValue.setDouble(servoAngle);
        D_armValue2.setDouble(servoAngle2);
    }
}