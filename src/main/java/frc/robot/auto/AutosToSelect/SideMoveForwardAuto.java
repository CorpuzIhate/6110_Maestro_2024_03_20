package frc.robot.auto.AutosToSelect;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

import frc.robot.auto.auto_commands.SwerveDriveAutoCMD;

public class SideMoveForwardAuto extends SequentialCommandGroup{
    public SideMoveForwardAuto(RobotContainer robot){
        // addCommands(new MoveIntakeArmUpAutoCMD(robot.getIntakeOutakeSub(), IntakeConstants.kIntakeArmUpPosSetpoint));
        addCommands(new SwerveDriveAutoCMD(robot.getSwerveSub(), 0.7, 0.6, 0, 0));

    }

}
