package frc.robot.auto.AutosToSelect;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.auto.auto_commands.DriveForwardCMDAuto;
import frc.robot.auto.auto_commands.InitalizeShooterAutoCMD;
import frc.robot.auto.auto_commands.ShootFor3SecondsAutoCMD;

public class SideSundayMoveForwardShoot extends SequentialCommandGroup{
    public SideSundayMoveForwardShoot(RobotContainer robot){

        // addCommands(new MoveIntakeArmUpAutoCMD(robot.getIntakeOutakeSub(),
        //  IntakeConstants.kIntakeArmUpPosSetpoint));


        addCommands(new InitalizeShooterAutoCMD(robot.getShooterSub(),2));

        addCommands(new ShootFor3SecondsAutoCMD(robot.getShooterSub(),
          1.5, robot.getIndexerSub()));
         
        addCommands(new DriveForwardCMDAuto(robot.getSwerveSub(),1,0.5));
    }
}