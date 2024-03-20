package frc.robot.auto.auto_commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeOutakeSub;

public class MoveIntakeArmUpAutoCMD extends Command {
    private final IntakeOutakeSub intakeOutakeSub;
    private final PIDController intakeArmPidController;
    private double intakeArmSetpoint;



    public MoveIntakeArmUpAutoCMD(IntakeOutakeSub intakeOutakeSub, double intakeArmSetpoint){
        this.intakeArmSetpoint = intakeArmSetpoint;
        this.intakeOutakeSub = intakeOutakeSub;
        this.intakeArmPidController = intakeOutakeSub.intakeArmPidController;
       // intakeArmPidController.setSetpoint(intakeArmSetpoint);

        addRequirements(intakeOutakeSub);
    }

    @Override
    public void initialize(){
        intakeArmPidController.reset();


    }

    @Override
    public void execute() {
        intakeOutakeSub.setIntakeArmMotorSetpoint(intakeArmSetpoint); 
        //input a RAW setpoint into IntakeArmSetpoint

    }
    @Override
    public void end(boolean interrupted) {
        intakeOutakeSub.setIntakeArmMotorSpeed0();
    }
    @Override
    public boolean isFinished(){
        return intakeOutakeSub.intakeArmEncoder.getAbsolutePosition() >= intakeArmSetpoint - 0.05 
        &&   intakeOutakeSub.intakeArmEncoder.getAbsolutePosition() <= intakeArmSetpoint + 0.05 ;
    }

    
}