package org.firstinspires.ftc.teamcode.Drive;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import org.firstinspires.ftc.teamcode.Hardware.Axon;
import org.firstinspires.ftc.teamcode.Utilities.MathU;

import com.arcrobotics.ftclib.controller.PIDController;

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

    private double heading; // Read from IMU
    private double translationX; // Read from controller
    private double translationY; // Read from controller
    private double rotationX; // Read from controller

    private double rotation; // Rotation from controller + heading PID input
    private double[] translationVector;

    private PIDController headingController;
    private double p, i, d;
    private double headingPID;

    public Drivetrain(MotorEx frMotor, Axon frServo, MotorEx flMotor, Axon flServo, MotorEx blMotor, Axon blServo, MotorEx brMotor, Axon brServo) {

        frontRight = new SwerveModule(frMotor, frServo, trackWidth/2, wheelBase/2);
        frontLeft = new SwerveModule(flMotor, flServo, -trackWidth/2, wheelBase/2);
        backLeft = new SwerveModule(blMotor, blServo, -trackWidth/2, -wheelBase/2);
        backRight = new SwerveModule(brMotor, brServo, trackWidth/2, -wheelBase/2);

    }

    public void update() {

        translationVector[0] = translationX;
        translationVector[1] = translationY;

        switch (dtState) {
            case IDLE:
                // Calc heading PID
                // Motor PID, will be implemented later
            case DRIVING:
                // Nothing special happens here, just a empty state for now
            case TRANSLATING:
                //Calc Heading PID
        }
        drive();
    }


    public void drive() {
        frontRight.update(translationVector, rotation, heading);
        frontLeft.update(translationVector, rotation, heading);
        backLeft.update(translationVector, rotation, heading);
        backRight.update(translationVector, rotation, heading);

        double[] motorPowers = new double[]{frontRight.getMotorPower(), frontLeft.getMotorPower(), backLeft.getMotorPower(), backRight.getMotorPower()};
        motorPowers = MathU.normalizeArrayX(motorPowers, 1.0);

        frontRight.setMotorPower(motorPowers[0]);
        frontLeft.setMotorPower(motorPowers[1]);
        backLeft.setMotorPower(motorPowers[2]);
        backRight.setMotorPower(motorPowers[3]);
    }

    private double calcHeadingPID() {
        //  Need to write, need to add some utils methods for this
        return 0.0; // just here as placeholder
    }
}
