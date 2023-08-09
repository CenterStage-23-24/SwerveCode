package org.firstinspires.ftc.teamcode.Omkar;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class RobotCentricRun extends LinearOpMode {

    RobotCentricDriveTrain drive;

    @Override
    public void runOpMode() {
        drive = new RobotCentricDriveTrain(hardwareMap,gamepad1, telemetry);
        waitForStart();
        while(opModeIsActive()) {
            drive.loop();
        }

    }

}
