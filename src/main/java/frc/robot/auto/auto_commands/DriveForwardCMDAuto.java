package frc.robot.auto.auto_commands;


import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.SwerveSub;
 

public class DriveForwardCMDAuto extends Command {
    private final Timer timer;
    private final SwerveSub swerveSubsystem;
    private double totalTime;
    private double speed;


  public DriveForwardCMDAuto(SwerveSub swerveSubsystem,double totalTime, double speed){
    this.swerveSubsystem = swerveSubsystem;
    this.totalTime = totalTime;
    this.speed = speed;

    this.timer = new Timer();

    

  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    swerveSubsystem.frontRight.setDriveMotor(speed);
    swerveSubsystem.frontLeft.setDriveMotor(speed);
    swerveSubsystem.backLeft.setDriveMotor(speed);
    swerveSubsystem.backRight.setDriveMotor(speed);

    
  }


// Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    swerveSubsystem.stopModules();
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() >= totalTime;
  }
}