package frc.robot.auto.AutosToSelect;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.auto.auto_commands.InitalizeShooterAutoCMD;
import frc.robot.auto.auto_commands.ShootFor3SecondsAutoCMD;
import frc.robot.auto.auto_commands.SwerveDriveAutoCMD;
import frc.robot.auto.auto_commands.SwerveDriveLLAutoCMD;

public class CenterLLMoveForwardAuto extends SequentialCommandGroup {
    
public CenterLLMoveForwardAuto(RobotContainer robot){ 
    addCommands(new SwerveDriveAutoCMD(robot.getSwerveSub(), 0.1, 0.6, 0, 0));
    addCommands(new SwerveDriveLLAutoCMD(robot.getSwerveSub(),robot.getliLimeLightSub(), 0, 0, 0));
    addCommands( new InitalizeShooterAutoCMD(robot.getShooterSub(), 2));

    addCommands(new ShootFor3SecondsAutoCMD(robot.getShooterSub(),  1.5, robot.getIndexerSub()));

    addCommands(new SwerveDriveAutoCMD(robot.getSwerveSub(), 0.3,0.6, 0, 0));


}
}
