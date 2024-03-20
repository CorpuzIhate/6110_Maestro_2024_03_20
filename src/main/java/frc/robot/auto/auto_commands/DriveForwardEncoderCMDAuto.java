package frc.robot.auto.auto_commands;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ModuleConstants;
import frc.robot.subsystems.SwerveSub;
 

public class DriveForwardEncoderCMDAuto extends Command {
    private final SwerveSub swerveSubsystem;
    private double driveSpeed;
    private double turningSpeed;
    private double distance;
    private double angle;

    private double driveFrontLeftSetSpeed;
    private double driveFrontRightSetSpeed;
    private double driveBackLeftSetSpeed;
    private double driveBackRightSetSpeed;
  
    private double turningFrontLeftSetSpeed;
    private double turningFrontRightSetSpeed;
    private double turningBackLeftSetSpeed;
    private double turningBackRightSetSpeed;

    private PIDController frontLeftDriveContoller; 

    private PIDController frontRightDriveController;

    private PIDController backLeftDriveController;

    private PIDController backRightDriveController;
    

    private PIDController frontLeftTurningContoller;

    private PIDController frontRightTurningController;

    private PIDController backLeftTurningController;

    private PIDController backRightTurningController;






  public DriveForwardEncoderCMDAuto(SwerveSub swerveSubsystem,double distance, double angle, double driveSpeed, 
  double turningSpeed){
    
    this.swerveSubsystem = swerveSubsystem;
    this.driveSpeed = driveSpeed;
    this.turningSpeed = turningSpeed;
    this.distance = distance;
    this.angle = angle;


    frontLeftDriveContoller = new PIDController(driveSpeed,  
    AutoConstants.kDriveAutoI,  AutoConstants.kDriveAutoD );

    frontRightDriveController = new PIDController(driveSpeed,  
    AutoConstants.kDriveAutoI,  AutoConstants.kDriveAutoD );

    backLeftDriveController = new PIDController(driveSpeed,  
    AutoConstants.kDriveAutoI,  AutoConstants.kDriveAutoD );

    backRightDriveController = new PIDController(driveSpeed,  
    AutoConstants.kDriveAutoI,  AutoConstants.kDriveAutoD );
    

    frontLeftTurningContoller = new PIDController(turningSpeed,  
    AutoConstants.kTurningAutoI,  AutoConstants.kTurningAutoD );

    frontRightTurningController = new PIDController(turningSpeed,  
    AutoConstants.kTurningAutoI,  AutoConstants.kTurningAutoD );

    backLeftTurningController = new PIDController(turningSpeed,  
    AutoConstants.kTurningAutoI,  AutoConstants.kTurningAutoD );

    backRightTurningController = new PIDController(turningSpeed,  
    AutoConstants.kTurningAutoI,  AutoConstants.kTurningAutoD );


    

  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {




    
    driveFrontLeftSetSpeed = frontLeftDriveContoller.calculate(swerveSubsystem.frontLeft.getDrivePostion(), distance);
    driveFrontRightSetSpeed = frontRightDriveController.calculate(swerveSubsystem.frontRight.getDrivePostion(), distance);
    driveBackLeftSetSpeed = backLeftDriveController.calculate(swerveSubsystem.backLeft.getDrivePostion(), distance);
    driveBackRightSetSpeed = backRightDriveController.calculate(swerveSubsystem.backRight.getDrivePostion(), distance);

    double desiredAngleRad = Units.degreesToRadians(angle);


    turningFrontLeftSetSpeed = frontLeftTurningContoller.calculate(swerveSubsystem.frontLeft.getTurningPositon(), 
      desiredAngleRad + DriveConstants.kFrontLeftDriveAbsoluteEncoderOffsetRad);
      
    turningFrontRightSetSpeed = frontRightTurningController.calculate(swerveSubsystem.frontRight.getTurningPositon(), 
      desiredAngleRad + DriveConstants.kFrontRightDriveAbsoluteEncoderOffsetRad);

      turningBackLeftSetSpeed = backLeftTurningController.calculate(swerveSubsystem.backLeft.getTurningPositon(),
      desiredAngleRad + DriveConstants.kBackLeftDriveAbsoluteEncoderOffsetRad);

      turningBackRightSetSpeed = backRightTurningController.calculate(swerveSubsystem.backRight.getTurningPositon(), 
      desiredAngleRad + DriveConstants.kBackRightDriveAbsoluteEncoderOffsetRad);







    swerveSubsystem.frontRight.setMotor(driveFrontRightSetSpeed, turningFrontRightSetSpeed);
    swerveSubsystem.frontLeft.setMotor(driveFrontLeftSetSpeed, turningFrontLeftSetSpeed);
    swerveSubsystem.backLeft.setMotor(driveBackLeftSetSpeed, turningBackLeftSetSpeed);
    swerveSubsystem.backRight.setMotor(driveBackRightSetSpeed, turningBackRightSetSpeed);

    





    
  }


// Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    swerveSubsystem.stopModules();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return swerveSubsystem.frontLeft.getDrivePostion() >= driveFrontLeftSetSpeed &&
    swerveSubsystem.frontRight.getDrivePostion() >= driveFrontRightSetSpeed &&
    swerveSubsystem.backLeft.getDrivePostion() >= driveBackLeftSetSpeed &&
    swerveSubsystem.backRight.getDrivePostion() >= driveBackRightSetSpeed;
    
  }
}