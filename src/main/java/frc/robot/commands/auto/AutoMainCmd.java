package frc.robot.commands.auto;

import java.util.List;
import java.util.Map;

import javax.swing.text.Position;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
// import the commands
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.RotateTest;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Arm;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class AutoMainCmd extends SequentialCommandGroup
{   

	public AutoMainCmd()
    {  
        super(
            //This is to get the robot to stop when ir distance is lesser than 20
            //new MoveRobotSense(1, 3, 0, 0, 0.4, ()->RobotContainer.m_sensor.getIRDistance()>20)
            //
            //This code is to let robot turn when it detects something infront of it
            //new MoveRobotSense(1, 3, 0, 0, 0.4, ()->RobotContainer.m_sensor.getIRDistance()<50),
            //new MoveRobotSense(2, Math.PI/2, 0, 0, 0.4, ()->RobotContainer.m_sensor.getIRDistance()<20),
            //new MoveRobotSense(2, -Math.PI/2, 0, 0, 0.4, ()->RobotContainer.m_sensor.getIRDistance()>=50)

            //new LoopCmd(new MoveBack(), ()->RobotContainer.m_sensor.getIRDistance()>60)
            new MoveRobot(1,0.5,0,0,0.4),

            new LoopCmd(new MoveTest(), ()->RobotContainer.m_sensor.getSwitch() == true),

            new MoveBack()
            
        );
            //new MoveRobot(0, 0.5, 0, 0, 0.4),
            //new MoveRobot(2, Math.PI/2, 0, 0, Math.PI)
            //new LoopCmd(new RotateTest()),
        
    }
}
