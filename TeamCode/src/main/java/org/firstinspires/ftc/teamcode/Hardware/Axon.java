package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Utilities.MathU;

// Interface class for Axons that allows easy access to encoder values with offsets and inversions as needed
// Without adding in extra encoder reads.

//This class works entirely with radians to measure angles
public class Axon {
    private CRServo servo;
    private AnalogInput encoder;

    private boolean invertPower;
    private double powerCoef = 1.0;
    private boolean invertEncoder;

    //Offset applied to the pos of the servo when read, is applied after inversion
    private double encoderOffset = 0.0;

    private double rawPos;
    private double pos;

    // Updates to be the last power actual power the servo was set to
    private double rawPower;
    // Updates to be the last externally set power to the servo
    private double power;

    public Axon(CRServo servo, AnalogInput encoder, boolean invertPower, boolean invertEncoder, double encoderOffset) {

        this.servo = servo;
        this.encoder = encoder;

        this.invertPower = invertPower;
        this.invertEncoder = invertEncoder;

        this.encoderOffset = encoderOffset;

        powerCoef = (invertPower) ? -1.0 : 1.0;
    }

    // Updates rawPos and pos
    public void updatePos() {
        rawPos = encoder.getVoltage() / 3.3 * MathU.TAU;
        pos = (invertEncoder) ? MathU.TAU - rawPos : rawPos;
        pos = MathU.normalizeRadiansTau(pos + encoderOffset);
    }

    // Returns position of servo after inversion in radians
    public double getPos() {
        return pos;
    }

    // Returns the raw position of the servo without any inversion or offset in radians
    public double getRawPos() {
        return rawPos;
    }

    // Setting power to the servo and updating power var
    public void setPower(double power) {
        this.power = power;
        rawPower = power * powerCoef;
        servo.setPower(this.rawPower);
    }

    // Returns the last externally assigned power to the servo
    public double getPower() {
        return power;
    }

    // Returns the actual power set to the servo (IE: inverted if needed)
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

    // Sets a new offset for the encoder position
    public void setEncoderOffset(double encoderOffset) {
        this.encoderOffset = encoderOffset;
    }

    // Returns the offset of the encoder
    public double getEncoderOffset() {
        return encoderOffset;
    }
}