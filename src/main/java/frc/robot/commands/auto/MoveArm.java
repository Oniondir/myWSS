package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import the commands
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;;

/**
 * Servo Class
 * <p>
 * This class creates the inline auto command to move the servo
 */
public class MoveArm extends SequentialCommandGroup
{
    private final static Arm m_arm = RobotContainer.m_arm;
    public MoveArm(double x,double y)
    {
        super(
        new MoveServo1(m_arm.GoToPosXY(x, y)[0], 50),
        new MoveServo2(m_arm.GoToPosXY(x, y)[1], 50)
        );
    }
    
}
