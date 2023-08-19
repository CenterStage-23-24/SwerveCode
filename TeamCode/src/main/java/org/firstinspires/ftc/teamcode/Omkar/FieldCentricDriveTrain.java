
package org.firstinspires.ftc.teamcode.Omkar;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class FieldCentricDriveTrain {
    private DcMotor frontRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;
    private BNO055IMU imu; // this is the imu in the old control hub

    Gamepad gamepad;
    Telemetry telemetry;
    public FieldCentricDriveTrain(HardwareMap hardwareMap, Gamepad gamepad, Telemetry telemetry) {
        this.gamepad = gamepad;
        this.telemetry = telemetry;
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
// brakes code
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

    }
    public void loop() {
        double verticalMovement = -gamepad.left_stick_y;
        double strafe = gamepad.left_stick_x * 1.1;
        double turning = gamepad.right_stick_x;
        double heading = imu.getAngularOrientation().firstAngle;

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
