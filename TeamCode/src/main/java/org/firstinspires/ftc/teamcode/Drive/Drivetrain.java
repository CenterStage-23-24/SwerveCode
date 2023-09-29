package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Drivetrain {

    private enum DTState {
        IDLE,
        DRIVING,
        TRANSLATING,
    }
    private DTState dtState = DTState.IDLE;

    SwerveModule frontRight;
    SwerveModule frontLeft;
    SwerveModule backLeft;
    SwerveModule backRight;

    private double wheelBase = 300; // Wheel base in mm
    private double trackWidth = 300; // Track Width in mm

    private double heading;
    private double translationX;
    private double translationY;
    private double rotationX;

    public Drivetrain(DcMotorEx frMotor, Servo frServo, DcMotorEx flMotor, Servo flServo, DcMotorEx blMotor, Servo blServo, DcMotorEx brMotor, Servo brServo) {

        frontRight = new SwerveModule(frMotor, frServo, trackWidth/2, wheelBase/2);
        frontLeft = new SwerveModule(flMotor, flServo, -trackWidth/2, wheelBase/2);
        backLeft = new SwerveModule(blMotor, blServo, -trackWidth/2, -wheelBase/2);
        backRight = new SwerveModule(brMotor, brServo, trackWidth/2, -wheelBase/2);

    }


    public void update(double translationJoyX, double translationJoyY, double rotationJoyX, double robotHeading) {
        heading = robotHeading;
        translationX = translationJoyX;
        translationY = translationJoyY;
        rotationX = rotationJoyX;



    }

}
