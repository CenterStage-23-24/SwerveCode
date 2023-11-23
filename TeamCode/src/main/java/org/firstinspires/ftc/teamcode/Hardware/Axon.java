package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;

public class Axon {
    private CRServo servo;
    private AnalogInput encoder;

    private boolean invertPower;
    private double powerCoef = 1.0;
    private boolean invertEncoder;

    private double rawPos;
    private double pos;

    // The power that is assigned to the servo through setPower method
    private double rawPower;
    // The power being set to the servo, if invertPower is true then it will have the opposite sign of rawPower
    private double power;

    public Axon(CRServo servo, AnalogInput encoder, boolean invertPower, boolean invertEncoder) {

        this.servo = servo;
        this.encoder = encoder;

        this.invertPower = invertPower;
        this.invertEncoder = invertEncoder;

        powerCoef = (invertPower) ? -1.0 : 1.0;
    }

    // Updates rawPos and pos
    public void updatePos() {
        rawPos = encoder.getVoltage() / 3.3 * 360;
        pos = (invertEncoder) ? 360 - rawPos : rawPos;
    }

    // Returns position of servo after inversion if needed
    public double getPos() {
        return pos;
    }

    // Returns the raw position of the servo without any inversion
    public double getRawPos() {
        return rawPos;
    }

    // Setting the power vars and setting power to the servo
    public void setPower(double power) {
        rawPower = power;
        this.power = rawPower * powerCoef;
        servo.setPower(this.power);
    }

    // Returns the power that is being set to the servo
    public double getPower() {
        return power;
    }

    // Returns the power assigned to the servo via setPower method
    public double getRawPower() {
        return rawPower;
    }

    // Returns weather or not the servos power is inverted
    public boolean isInvertPower() {
        return invertPower;
    }

    // Returns weather or not the servos encoder is inverted
    public boolean isInvertEncoder() {
        return invertEncoder;
    }
}