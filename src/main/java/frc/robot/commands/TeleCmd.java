package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.gamepad.OI;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Arm;

public class TeleCmd extends CommandBase
{
    /**
     * Bring in Subsystem and Gamepad code
     */
    private final OmniDrive m_omnidrive;
    private final Sensor m_sensor;
    private final OI m_oi;
    private final Arm m_arm;

    /**
     * Constructor
     */
    public TeleCmd(OmniDrive omnidrive, OI oi,Arm arm)
    {
        m_omnidrive = RobotContainer.m_omnidrive;
        m_sensor = RobotContainer.m_sensor;
        m_oi = RobotContainer.m_oi;
        m_arm = RobotContainer.m_arm;
        addRequirements(m_omnidrive); //add the drive subsystem as a requirement 
		//addRequirements(m_menu); 
    }

    /**
     * Code here will run once when the command is called for the first time
     */
    @Override
    public void initialize()
    {

    }

    /**
     * Code here will run continously every robot loop until the command is stopped
     */
    @Override
    public void execute()
    {
        /**
         * Get Joystick data
         */
        //Right stick for X-Y control
        //Left stick for W (rotational) control
        double x = m_oi.getRightDriveX();
        double y = -m_oi.getRightDriveY();//Down is positive. Need to negate
        double w = -m_oi.getLeftDriveX(); //X-positive is CW. Need to negate

        //Get other buttons?

        //Add code here to control servo motor etc.
        double s0,s1,s2;
        s0 = (-0.5*x)+(Math.cos(Math.toRadians(150))*y)+(0.18*w);
        s1 = x+0.18*w;
        s2 = (-0.5*x)+(Math.cos(Math.toRadians(30))*y)+(0.18*w);

        m_omnidrive.setMotorOut012(s0,s1,s2);
        double input_start = -1;
        double input_end = 1;
        double output_start = 0;
        double output_end = 300;
        double output = output_start+((output_end-output_start)/(input_end-input_start))*(w-input_start);
        m_arm.setServoAngle(output);

        //m_omnidrive.setRobotSpeedXYW(x*0.6, y*0.6, w*Math.PI);

    }

    /**
     * When the comamnd is stopped or interrupted this code is run
     * <p>
     * Good place to stop motors in case of an error
     */
    @Override
    public void end(boolean interrupted)
    {
        m_omnidrive.setMotorOut012(0, 0, 0);
        m_arm.setServoAngle(0);
    }

    /**
     * Check to see if command is finished
     */
    @Override
    public boolean isFinished()
    {
        return false;
    }
}