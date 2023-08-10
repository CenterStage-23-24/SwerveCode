package org.firstinspires.ftc.teamcode.Omkar;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RobotCentricDriveTrain {

    private DcMotor frontRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;


    Gamepad gamepad;
    Telemetry telemetry;
    // constructor maps the hardware
    public RobotCentricDriveTrain(HardwareMap hardwareMap, Gamepad gamepad, Telemetry telemetry) {
        this.gamepad = gamepad;
        this.telemetry = telemetry;
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {
        double verticalMovement = -gamepad.left_stick_y;
        double strafe = gamepad.left_stick_x * 1.1;
        double turning = gamepad.right_stick_x;
        double maintainRatio = Math.max(Math.abs(verticalMovement) + Math.abs(strafe) + Math.abs(turning), 1);
        frontRightMotor.setPower((verticalMovement - strafe - turning) / maintainRatio);
        backRightMotor.setPower((verticalMovement + strafe - turning) / maintainRatio);
        frontLeftMotor.setPower((verticalMovement + strafe + turning) / maintainRatio);
        backLeftMotor.setPower((verticalMovement - strafe + turning) / maintainRatio);
        telemetry.addData("frontRightMotor power: ", frontRightMotor.getPower());
        telemetry.addData("frontLeftMotor power: ", frontLeftMotor.getPower());
        telemetry.addData("backRightMotor power: ", backRightMotor.getPower());
        telemetry.addData("backLeftMotor power: ", backLeftMotor.getPower());
        telemetry.update();

    }
}

