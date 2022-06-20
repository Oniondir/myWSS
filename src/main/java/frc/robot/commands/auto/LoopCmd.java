package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;



/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class LoopCmd extends CommandBase
{
    //private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    static boolean cmdEndFlag;
    private int state;
    private boolean scheduleFlag;
    private boolean m_endFlag;
    private SequentialCommandGroup cmd;
    private final end_func fn_ptr;

    interface end_func
    {
        public boolean endCondition();
    }

	public LoopCmd(SequentialCommandGroup cmdToRun, end_func fn)
    {
        cmd = cmdToRun;
        fn_ptr = fn;
        //addRequirements(m_drive);
    }
    @Override
    public void initialize()
    {
        state=0;
        //scheduleFlag = true;
        m_endFlag = false;
        //cmdEndFlag = false;
        // Globals.debug[0]=Globals.debug[1]=Globals.debug[2]=0;
    }
    @Override
    public void execute()
    {
        if (cmd.isScheduled() == false) {
            //End condition for loopCmd
            if (fn_ptr.endCondition()) {
                m_endFlag = true;
            }
            else {
                //schedule command
                cmd.schedule(false);
            }
        }
        // Globals.debug[0] = state;
    }

    @Override
    public boolean isFinished()
    {
        // if (m_endFlag)
        //     Globals.debug[2] = -1;
        return m_endFlag;
    }
    @Override
    public void end(boolean interrupted)
    {
        // Globals.debug[1] = -1;
    }
}
