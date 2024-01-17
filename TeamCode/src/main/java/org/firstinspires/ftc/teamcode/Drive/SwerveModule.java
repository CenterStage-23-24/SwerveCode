package org.firstinspires.ftc.teamcode.Drive;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Hardware.MotorX;
import org.firstinspires.ftc.teamcode.Utilities.MathU;
import org.firstinspires.ftc.teamcode.Utilities.RangeLimiter;
import com.arcrobotics.ftclib.controller.PIDFController;
import org.firstinspires.ftc.teamcode.Hardware.Axon;

public class SwerveModule {

    private MotorX motor;
    private Axon servo;

    private PIDFController servoController;
    private double p = 0, i = 0, d = 0;
    private double f = 0;
    private final double TPR = 0; //Needs to be set

    private RangeLimiter servoPowerRange = new RangeLimiter(-1, 1);

    private double xPos; // X Offset of module from robot center in mm
    private double yPos; // Y Offset of module from robot center in mm

    private double[] rotationVector; // Rotation Vector constants normalized to magnitude of 1; positive = counter clockwise rotation
    private double[] translationVector =  new double[2]; // Translation Vector normalized to magnitude of 1; ++ = forward right
    private double[] moduleVector =  new double[2]; //Resultant vector from adding rotation and translation vectors, normalized to 1

    private double rotation; // Scale of Rotation Vector

    // Servo Vars
    private double error;
    private double targetDirection; // Target direction that power needs to be
    private double target; // Target module heading, 0 is straight forward from robot POV; target Direction but recalculated for motor flipping
    private double position; // Actual module heading, 0 is straight forward from robot POV
    private double servoPower; // Servo Power

    // Motor Vars
    private boolean motorFlipped;
    private double motorPower;
    private double targetVelocity;

    public SwerveModule(MotorX motor, Axon servo, double xPos, double yPos) {
        this.motor = motor;
        this.servo = servo;

        this.xPos = xPos;
        this.yPos = yPos;
        rotationVector = MathU.normalizeVector(-this.yPos, this.xPos);

        servoController = new PIDFController(p, i, d, f);
    }

    public void update(double[] robotTranslationVector, double inputRotation, double heading) {
        translationVector[0] = robotTranslationVector[0];
        translationVector[1] = robotTranslationVector[1];
        rotation = inputRotation;

        position = servo.getPos();

        moduleVector[0] = rotationVector[0]*rotation + translationVector[0];
        moduleVector[1] = rotationVector[1]*rotation + translationVector[1];

        targetDirection = MathU.normalizeRadiansTau(Math.atan2(moduleVector[1],moduleVector[0] - MathU.ETA));

        error = MathU.normalizeRadiansTau(targetDirection - position);

        if (error > (MathU.ETA)) {
            target = MathU.normalizeRadiansTau(target - Math.PI);
            error = MathU.normalizeRadiansTau(target - position);
            motorFlipped = true;
        } else {
            target = targetDirection;
            motorFlipped = false;
        }

        motorPower = Math.hypot(translationVector[0], translationVector[1]);

        servoPower = servoPowerRange.clip(servoController.calculate(0, MathU.normalizeRadiansPi(error)));
        servo.setPower(servoPower);
    }

    public double getMotorPower() {
        return motorPower;
    }

    public void setMotorPower(double power) {
        power = (motorFlipped) ? -power : power;
        //motor.set(power);
    }
}
