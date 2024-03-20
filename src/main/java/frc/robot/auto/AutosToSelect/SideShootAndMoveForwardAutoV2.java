package frc.robot.auto.AutosToSelect;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.auto.auto_commands.InitalizeShooterAutoCMD;
import frc.robot.auto.auto_commands.ShootFor3SecondsAutoCMD;
import frc.robot.auto.auto_commands.SwerveDriveAutoCMD;

public class SideShootAndMoveForwardAutoV2 extends SequentialCommandGroup{
    public SideShootAndMoveForwardAutoV2(RobotContainer robot){
        // addCommands(new MoveIntakeArmUpAutoCMD(robot.getIntakeOutakeSub(),
        //  IntakeConstants.kIntakeArmUpPosSetpoint));

        addCommands(new InitalizeShooterAutoCMD(robot.getShooterSub(),2));

        addCommands(new ShootFor3SecondsAutoCMD(robot.getShooterSub(),
          1.5, robot.getIndexerSub()));
         
        addCommands(new SwerveDriveAutoCMD(robot.getSwerveSub(), 
        0.7, 0.55, -0.55, 0));

    }
}