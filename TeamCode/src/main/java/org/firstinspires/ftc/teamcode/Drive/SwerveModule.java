package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.teamcode.Utilities.MathU;
import org.firstinspires.ftc.teamcode.Utilities.RangeLimiter;
import com.arcrobotics.ftclib.controller.PIDFController;

public class SwerveModule {

    private DcMotorEx motor;
    private CRServo servo;

    private PIDFController servoController;
    private double p = 0, i = 0, d = 0;
    private double f = 0;
    private final double TPR = 10;

    private RangeLimiter servoRange = new RangeLimiter(-1, 1);

    private double xPos; // X Offset of module from robot center in mm
    private double yPos; // Y Offset of module from robot center in mm

    private double[] rotationVector; // Rotation Vector constants normalized to magnitude of 1; positive = counter clockwise rotation
    private double[] translationVector =  new double[2]; // Translation Vector normalized to magnitude of 1; ++ = forward right
    private double[] moduleVector =  new double[2]; //Resultant vector from adding rotation and translation vectors, normalized to 1

    private double rotation; // Scale of Rotation Vector

    private double error;
    private double targetDirection; // Target direction that power needs to be
    private double target; // Target module heading, 0 is straight forward from robot POV; target Direction but recalculated for motor flipping
    private double position; // Actual module heading, 0 is straight forward from robot POV
    private double servoPower; // Servo Power

    private boolean motorFlipped;

    public SwerveModule(DcMotorEx motor, CRServo servo, double xPos, double yPos) {
        this.motor = motor;
        this.servo = servo;

        this.xPos = xPos;
        this.yPos = yPos;
        rotationVector = MathU.normalizeVector(-this.yPos, this.xPos);
    }

    public void update(double[] robotTranslationVector, double inputRotation, double heading, double servoPos) {
        translationVector[0] = robotTranslationVector[0];
        translationVector[1] = robotTranslationVector[1];
        rotation = inputRotation;
        position = servoPos;

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

        servoPower = servoRange.clip(servoController.calculate(0, MathU.normalizeRadiansPi(error)));
        servo.setPower(servoPower);
    }

}
