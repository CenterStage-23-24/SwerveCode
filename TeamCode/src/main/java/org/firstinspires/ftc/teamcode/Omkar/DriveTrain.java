package org.firstinspires.ftc.teamcode.Omkar;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class DriveTrain extends OpMode {

  private DcMotor frontRightMotor;
  private DcMotor frontLeftMotor;
  private DcMotor backRightMotor;
  private DcMotor backLeftMotor;
@Override
    public void init() {
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double verticalMovement = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x * 1.1;
        double turning = gamepad1.right_stick_x;
        double maintainRatio = Math.max(Math.abs(verticalMovement) + Math.abs(strafe) + Math.abs(turning),1);
        frontRightMotor.setPower((verticalMovement - strafe - turning)/maintainRatio);
        backRightMotor.setPower((verticalMovement + strafe - turning)/maintainRatio);
        frontLeftMotor.setPower((verticalMovement + strafe + turning)/maintainRatio);
        backLeftMotor.setPower((verticalMovement - strafe + turning)/maintainRatio);

    }
}

