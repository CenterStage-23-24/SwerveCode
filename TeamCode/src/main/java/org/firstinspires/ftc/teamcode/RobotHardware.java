package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drive.SwerveModule;
import org.firstinspires.ftc.teamcode.Hardware.Axon;
import org.firstinspires.ftc.teamcode.Hardware.MotorX;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotHardware {
    // Motors
    private static DcMotorEx MotorFR;
    private static DcMotorEx MotorFL;
    private static DcMotorEx MotorBL;
    private static DcMotorEx MotorBR;

    // Servos
    private static CRServo ServoFR;
    private static CRServo ServoFL;
    private static CRServo ServoBL;
    private static CRServo ServoBR;

    // Analog Encoders
    private static AnalogInput EncoderFR;
    private static AnalogInput EncoderFL;
    private static AnalogInput EncoderBL;
    private static AnalogInput EncoderBR;

    // Axon Wrappers
    private static Axon AxonFR;
    private static Axon AxonFL;
    private static Axon AxonBL;
    private static Axon AxonBR;

    // Motor Wrappers
    private static MotorX MotorXFR;
    private static MotorX MotorXFL;
    private static MotorX MotorXBL;
    private static MotorX MotorXBR;

    // Mechanism Wrappers
    private static SwerveModule ModuleFR;
    private static SwerveModule ModuleFL;
    private static SwerveModule ModuleBL;
    private static SwerveModule ModuleBR;

    private static HardwareMap hwMap;
    private static Telemetry telemetry;

    public RobotHardware(Telemetry telem, HardwareMap map) {

        hwMap = map;
        telemetry = telem;

        //Hardware Setup
        MotorFR = map.get(DcMotorEx.class, "MotorFR");
        MotorFL = map.get(DcMotorEx.class, "MotorFL");
        MotorBL = map.get(DcMotorEx.class, "MotorBL");
        MotorBR = map.get(DcMotorEx.class, "MotorBR");

        ServoFR = map.get(CRServo.class, "ServoFR");
        ServoFL = map.get(CRServo.class, "ServoFL");
        ServoBL = map.get(CRServo.class, "ServoBL");
        ServoBR = map.get(CRServo.class, "ServoBR");

        EncoderFR = map.get(AnalogInput.class, "EncoderFR");
        EncoderFL = map.get(AnalogInput.class, "EncoderFL");
        EncoderBL = map.get(AnalogInput.class, "EncoderBL");
        EncoderBR = map.get(AnalogInput.class, "EncoderBR");

        // Hardware Wrappers Setup
        MotorXFR = new MotorX(MotorFR);
        MotorXFL = new MotorX(MotorFL);
        MotorXBL = new MotorX(MotorBL);
        MotorXBR = new MotorX(MotorBR);

        AxonFR = new Axon(ServoFR, EncoderFR);
        AxonFL = new Axon(ServoFL, EncoderFL);
        AxonBL = new Axon(ServoBL, EncoderBL);
        AxonBR = new Axon(ServoBR, EncoderBR);

        // Mechanism Construction
        ModuleFR = new SwerveModule(MotorXFR, AxonFR);
        ModuleFL = new SwerveModule(MotorXFL, AxonFL);
        ModuleBL = new SwerveModule(MotorXBL, AxonBL);
        ModuleBR = new SwerveModule(MotorXBR, AxonBR);

        }

    public static void setTelemetry(Telemetry telem) {
            telemetry = telem;
        }

    }
