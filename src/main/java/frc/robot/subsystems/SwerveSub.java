package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

import com.kauailabs.navx.frc.AHRS;


import org.littletonrobotics.junction.Logger;




import edu.wpi.first.wpilibj.smartdashboard.Field2d;





public class SwerveSub extends SubsystemBase {
    public final SwerveModule frontRight = new SwerveModule(
            DriveConstants.kFrontRightDriveMotorPort, // 7
            DriveConstants.kFrontRightTurningMotorPort,
            DriveConstants.kFrontRightDriveEncoderReversed,
            DriveConstants.kFrontRightTurningEncoderReversed,
            DriveConstants.kFrontRightDriveAbsoluteEncoderPort,
            DriveConstants.kFrontRightDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kFrontRightDriveAbsoluteEncoderReversed);

    public final SwerveModule frontLeft = new SwerveModule(
            DriveConstants.kFrontLeftDriveMotorPort,
            DriveConstants.kFrontLeftTurningMotorPort,
            DriveConstants.kFrontLeftDriveEncoderReversed,
            DriveConstants.kFrontLeftTurningEncoderReversed,
            DriveConstants.kFrontLeftDriveAbsoluteEncoderPort,
            DriveConstants.kFrontLeftDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kFrontLeftDriveAbsoluteEncoderReversed);




    public final SwerveModule backRight = new SwerveModule(
            DriveConstants.kBackRightDriveMotorPort,
            DriveConstants.kBackRightTurningMotorPort,
            DriveConstants.kBackRightDriveEncoderReversed,
            DriveConstants.kBackRightTurningEncoderReversed,
            DriveConstants.kBackRightDriveAbsoluteEncoderPort,
            DriveConstants.kBackRightDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kBackRightDriveAbsoluteEncoderReversed);

    public final SwerveModule backLeft = new SwerveModule(
            DriveConstants.kBackLeftDriveMotorPort,
            DriveConstants.kBackLeftTurningMotorPort,
            DriveConstants.kBackLeftDriveEncoderReversed,
            DriveConstants.kBackLeftTurningEncoderReversed,
            DriveConstants.kBackLeftDriveAbsoluteEncoderPort,
            DriveConstants.kBackLeftDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kBackLeftDriveAbsoluteEncoderReversed);
    private final SwerveModuleState[] mySwerveStates = new SwerveModuleState[]{ // used for debugging to Adavantage Scope
        frontLeft.getState(), frontRight.getState(), backLeft.getState(), backRight.getState()
    };


    private final SwerveModule swerveModules[] = new SwerveModule[]{
        frontLeft,frontRight,
        backLeft, backRight
    };




    
    private final AHRS gyro = new AHRS(SerialPort.Port.kUSB1);
    private final SwerveDriveOdometry odometer = new SwerveDriveOdometry(DriveConstants.kDriveKinematics,
     getRotation2d(), getModulePositionsAuto());





    public SwerveSub(){



 new Thread(() -> {  /// try catch function is a fancy if else statement
        try{              // it tries to run a thread of resseting the gryo but if it exception e happens it stops 
            Thread.sleep(1000);
        }catch (Exception e){
        }
        }).start();

        zeroHeading();  
        

    
    }
    @Override
    public void periodic(){
        Logger.recordOutput("current Pose",  odometer.getPoseMeters());
        odometer.update(getRotation2d(), getModulePositionsAuto());
 

        SmartDashboard.putNumber("robot Heading", getHeading());



        SmartDashboard.putNumber("SwerveModuleTurningPostions [" + 1 + "]" ,  frontLeft.getTurningPositon());
        SmartDashboard.putNumber("SwerveModuleTurningPostions [" + 2 + "]" ,  frontRight.getTurningPositon());
        SmartDashboard.putNumber("SwerveModuleTurningPostions [" + 3 + "]" ,  backLeft.getTurningPositon());
        SmartDashboard.putNumber("SwerveModuleTurningPostions [" + 4 + "]" ,  backRight.getTurningPositon());





        Logger.recordOutput("heading",getHeading());
      




        frontLeft.sendToDashboard();
        frontRight.sendToDashboard();
        backLeft.sendToDashboard();
        backRight.sendToDashboard();
    }

     public ChassisSpeeds getSpeeds() {
         return DriveConstants.kDriveKinematics.toChassisSpeeds(getModuleStates()); 
    }


 




    public void setModuleStates(SwerveModuleState[] desiredStates){

        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
    // proportaionally decreases the change the speeds so driver always had control of robot
        frontRight.setDesiredState(desiredStates[0]);        
        frontLeft.setDesiredState(desiredStates[1]); //1 
        backRight.setDesiredState(desiredStates[2]); //2                     
        backLeft.setDesiredState(desiredStates[3]); // 3



        //ouputs to Adavantage Log

        // log desired states is an array that orders the desired states in the order 
        // Advantage Log wants ( FL,FR, BL, BR )
        SwerveModuleState[] LogDesiredStates = new SwerveModuleState[]{desiredStates[1], desiredStates[0],
         desiredStates[3], desiredStates[2]};


        Logger.recordOutput("CurrentStates", mySwerveStates);
        Logger.recordOutput("DesiredStates",LogDesiredStates);
    
    }

// get positions
    public SwerveModulePosition[] getModulePositionsAuto() {
    SwerveModulePosition[] positions = new SwerveModulePosition[swerveModules.length];
    for (int i = 0; i < swerveModules.length; i++) {
      positions[i] = swerveModules[i].getSwerveModulePosition();
    }
    return positions;
  }


    public void zeroHeading(){
        gyro.reset();
    }

    public double getHeading(){
        return Math.IEEEremainder(-gyro.getAngle(), 360); //puts the value between 0 and 360 because gryo is naturally continous
    }

    public Rotation2d getRotation2d(){
        return Rotation2d.fromDegrees(getHeading());
    } // converts into Rotation2d
    public Pose2d getPose(){

        return odometer.getPoseMeters();

    }
    public void resetOdometry(Pose2d pose){
        odometer.resetPosition(getRotation2d(), getModulePositionsAuto(), pose);
    }







    


      public SwerveModuleState[] getModuleStates() {
        SwerveModuleState[] states = new SwerveModuleState[swerveModules.length];
        for (int i = 0; i < swerveModules.length; i++) {
          states[i] = swerveModules[i].getState();
        }
        return states;
      }



    public void stopModules(){
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();

    }






}