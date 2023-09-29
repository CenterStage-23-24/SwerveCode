package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class SwerveModule {

    private DcMotorEx motor;
    private Servo servo;

    private double xPos; // X Offset of module from robot center in mm
    private double yPos; // Y Offset of module from robot center in mm

    private double[] rotationVector; // Rotation Vector constants normalized to magnitude of 1; positive = counter clockwise rotation
    private double[] translationVector =  new double[2]; // Translation Vector normalized to magnitude of 1; ++ = forward right
    private double[] moduleVector =  new double[2]; //Resultant vector from adding rotation and translation vectors, normalized to 1

    private double rotation; // Scale of Rotation Vector

    private double targetDirection; // Target direction that power needs to be
    private double target; // Target module heading, 0 is straight forward from robot POV; target Direction but recalculated for motor flipping
    private double position; // Actual module heading, 0 is straight forward from robot POV
    private double power; // Motor Power

    private boolean motorFlipped;

    public SwerveModule(DcMotorEx motor, Servo servo, double xPos, double yPos) {
        this.motor = motor;
        this.servo = servo;

        this.xPos = xPos;
        this.yPos = yPos;
        rotationVector = normalizeVector(-this.yPos, this.xPos);
    }

    public void update(double[] robotTranslationVector, double inputRotation, double heading) {
        translationVector[0] = robotTranslationVector[0];
        translationVector[1] = robotTranslationVector[1];
        rotation = inputRotation;
        moduleVector[0] = rotationVector[0]*rotation + translationVector[0];
        moduleVector[1] = rotationVector[1]*rotation + translationVector[1];
        targetDirection = Math.atan2(moduleVector[1],moduleVector[0]) - Math.PI/2;


    }

    public double[] normalizeVector(double x, double y){
        double magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return new double[] {x / magnitude, y / magnitude};
    }

    public double[] normalizeVector(double[] vector){
        double magnitude = Math.sqrt(Math.pow(vector[0], 2) + Math.pow(vector[1], 2));
        return new double[] {vector[0] / magnitude, vector[1] / magnitude};
    }
}
