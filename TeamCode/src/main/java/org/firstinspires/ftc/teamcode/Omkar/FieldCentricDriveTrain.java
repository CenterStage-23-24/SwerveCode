package org.firstinspires.ftc.teamcode.Omkar;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class FieldCentricDriveTrain extends OpMode {
    private DcMotor frontRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;
    private IMU imu;

    @Override
    public void init() {
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters params = new IMU.Parameters(
                new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.UP));
        imu.initialize(params);
    }
    @Override
    public void loop() {
        double verticalMovement = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x * 1.1;
        double turning = gamepad1.right_stick_x;
        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        if (gamepad1.right_bumper) {
            imu.resetYaw();
        }

        double rotatedStrafe = (strafe * Math.cos(-heading)) - (verticalMovement * Math.sin(-heading));
        double rotatedVerticalMovement = (strafe * Math.sin(-heading)) + (verticalMovement * Math.cos(-heading));

        double maintainRatio = Math.max(Math.abs(rotatedVerticalMovement) + Math.abs(rotatedStrafe) + Math.abs(turning), 1);
        frontRightMotor.setPower((rotatedVerticalMovement - rotatedStrafe - turning) / maintainRatio);
        backRightMotor.setPower((rotatedVerticalMovement + rotatedStrafe - turning) / maintainRatio);
        frontLeftMotor.setPower((rotatedVerticalMovement + rotatedStrafe + turning) / maintainRatio);
        backLeftMotor.setPower((rotatedVerticalMovement - rotatedStrafe + turning) / maintainRatio);
        telemetry.addData("frontRightMotor power: ", frontRightMotor.getPower());
        telemetry.addData("frontLeftMotor power: ", frontLeftMotor.getPower());
        telemetry.addData("backRightMotor power: ", backRightMotor.getPower());
        telemetry.addData("backLeftMotor power: ", backLeftMotor.getPower());
        telemetry.update();
    }

}
