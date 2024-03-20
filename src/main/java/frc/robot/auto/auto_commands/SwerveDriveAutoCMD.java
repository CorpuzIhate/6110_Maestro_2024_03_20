package frc.robot.auto.auto_commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.SwerveSub;
 

public class SwerveDriveAutoCMD extends Command {
  /** Creates a new SwerveJoystickCmd. */
      private final Timer timer;
      private final SwerveSub swerveSubsystem;
      private double totalTime;
      private double xSpeed;
      private double ySpeed;
      private double turningSpeed;


      private final SlewRateLimiter xLimiter, yLimiter, turningLimiter; // slew rate limiter cap the the amount of change of a value
      
      public static double CurrentXSpeed;
      public static double CurrentYSpeed;
      public static double CurrentTurningSpeed;
      public static boolean CurrentOrientation;
      
  public SwerveDriveAutoCMD(
          SwerveSub swerveSubsystem, double totalTime, double xSpeed, double ySpeed, double turningSpeed) 
          { 
        this.swerveSubsystem = swerveSubsystem;
        this.totalTime = totalTime;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.turningSpeed = turningSpeed;


        this.xLimiter = new SlewRateLimiter(DriveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
        this.yLimiter = new SlewRateLimiter(DriveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
        this.turningLimiter = new SlewRateLimiter(DriveConstants.kTeleDriveMaxAngularAccelerationUnitsPerSecond);
        addRequirements(swerveSubsystem);
        timer = new Timer();
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
    SmartDashboard.putNumber("timer", timer.get());
    //now apply deband,  if joystick doesnt center back to exactly zero, it still stops
    xSpeed = Math.abs(xSpeed) > OIConstants.kDeadband ? xSpeed : 0.0;
    ySpeed = Math.abs(ySpeed) > OIConstants.kDeadband ? ySpeed : 0.0;
    turningSpeed = Math.abs(turningSpeed) > OIConstants.kDeadband ? turningSpeed : 0.0;

    // allows for violent joystick movements to be more smooth

    xSpeed = xLimiter.calculate(xSpeed) *  DriveConstants.kTeleDriveMaxSpeedMetersPerSecond;
    ySpeed = yLimiter.calculate(ySpeed) * DriveConstants.kTeleDriveMaxSpeedMetersPerSecond; 
    turningSpeed = turningLimiter.calculate(turningSpeed) *
     DriveConstants.kTeleDriveMaxAngularSpeedRadiansPerSecond;


    ChassisSpeeds chassisSpeeds;
    chassisSpeeds = new ChassisSpeeds(xSpeed,-ySpeed, -turningSpeed); //hard coded -s
    
    CurrentXSpeed = xSpeed;
    CurrentYSpeed = ySpeed;
    CurrentTurningSpeed = turningSpeed;



    // convert chassis speeds to individual module states; later to switch to velocity
    SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
    // set state to each wheel


    swerveSubsystem.setModuleStates(moduleStates);
    
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