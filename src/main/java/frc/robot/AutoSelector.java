package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auto.AutosToSelect.DoNothingAuto;
import frc.robot.auto.AutosToSelect.JustShootAuto;
import frc.robot.auto.AutosToSelect.CenterLLMoveForwardAuto;
import frc.robot.auto.AutosToSelect.CenterMoveForwardAndShootAuto;
import frc.robot.auto.AutosToSelect.CenterMoveForwardAuto;
import frc.robot.auto.AutosToSelect.SideMoveForwardAuto;
import frc.robot.auto.AutosToSelect.SideShootAndMoveForwardAuto;
import frc.robot.auto.AutosToSelect.SideShootAndMoveForwardAutoV2;
import frc.robot.auto.AutosToSelect.SideSundayMoveForwardShoot;
import frc.robot.auto.AutosToSelect.CenterMoveForwardPID;



public class AutoSelector {
    private SendableChooser<Command> autoSelector = new SendableChooser<>();

    public AutoSelector(RobotContainer container) {

        /*
         * Add all the options
         */
        autoSelector.setDefaultOption("DO nothing!!!", DoNothingAuto(container));
        autoSelector.addOption("(Center)Move Foward and shoot Swerve", MoveForwardAndShootAuto(container));
        
        autoSelector.addOption("Just Shoot", JustShootAuto(container));        
        autoSelector.addOption("(Center)Move Forward", MoveForwardAuto(container));
        autoSelector.addOption("(Center) LL Move Forward", MoveForwardLLAuto(container));
        autoSelector.addOption("(Center)  Move PID ", CenterMoveForwardPID(container));


        autoSelector.addOption("(Side) Move Forward  ", SideMoveForwardAuto(container));
        autoSelector.addOption("(Side) Shoot and Move", SideShootAndMoveForwardAuto(container));

        autoSelector.addOption("(Side) Shoot and Move V2", SideShootAndMoveForwardAutoV2(container));
        autoSelector.addOption("(Side) SUNDAY Shoot and Move ", SideSundayMoveForwardShoot(container));
        
        
    }


    public SendableChooser<Command> getAutoChooser() {
        return autoSelector;
    }

    /*
     * Allow calling the autos as a method
     */
    private SequentialCommandGroup DoNothingAuto(RobotContainer container) {
        return new DoNothingAuto(container);
    }
    private SequentialCommandGroup JustShootAuto(RobotContainer container) {
        return new JustShootAuto(container);
    }
    
    private SequentialCommandGroup MoveForwardAndShootAuto(RobotContainer container) {
        return new CenterMoveForwardAndShootAuto(container);
    }
    private SequentialCommandGroup MoveForwardAuto(RobotContainer container) {
        return new CenterMoveForwardAuto(container);
    }
    private SequentialCommandGroup SideMoveForwardAuto(RobotContainer container) {
        return new SideMoveForwardAuto(container);
    }

    private SequentialCommandGroup SideShootAndMoveForwardAuto(RobotContainer container){
        return new SideShootAndMoveForwardAuto(container);
    }
    private SequentialCommandGroup MoveForwardLLAuto(RobotContainer container) {
        return new CenterLLMoveForwardAuto(container);
    }
    private SequentialCommandGroup SideShootAndMoveForwardAutoV2(RobotContainer container) {
        return new SideShootAndMoveForwardAutoV2(container);
    }
    private SequentialCommandGroup SideSundayMoveForwardShoot(RobotContainer container) {
        return new SideSundayMoveForwardShoot(container);
    }
    private SequentialCommandGroup CenterMoveForwardPID(RobotContainer container){
        return new CenterMoveForwardPID(container);
    }
}