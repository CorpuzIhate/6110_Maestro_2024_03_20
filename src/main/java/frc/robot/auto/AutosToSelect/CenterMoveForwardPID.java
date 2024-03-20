package frc.robot.auto.AutosToSelect;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.auto.auto_commands.DriveForwardCMDAuto;
import frc.robot.auto.auto_commands.DriveForwardEncoderCMDAuto;
import frc.robot.auto.auto_commands.InitalizeShooterAutoCMD;
import frc.robot.auto.auto_commands.ShootFor3SecondsAutoCMD;

public class CenterMoveForwardPID extends SequentialCommandGroup{
    public CenterMoveForwardPID(RobotContainer robot){

        // addCommands(new MoveIntakeArmUpAutoCMD(robot.getIntakeOutakeSub(),
        //  IntakeConstants.kIntakeArmUpPosSetpoint));


        addCommands(new DriveForwardEncoderCMDAuto(robot.getSwerveSub(), 20, 0, 0.3,0.3 ));
    }
}