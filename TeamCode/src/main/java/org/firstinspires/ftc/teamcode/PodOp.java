package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "PodOp")
public class PodOp extends LinearOpMode {
    public void runOpMode(){

        RobotHardware map = new RobotHardware(telemetry, hardwareMap);
        telemetry.addLine("Module is Initializing...");
        telemetry.update();
        Pod pod = new Pod(map.servo, map.motor);
        pod.init();
        telemetry.clear();
        telemetry.addLine("Module is Initialized");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()){
            telemetry.clear();
            pod.run(gamepad1.left_stick_x, gamepad1.left_stick_y, 0.00);
            telemetry.addData("Left Stick X: ", gamepad1.left_stick_x);
            telemetry.addData("Left Stick y: ", gamepad1.left_stick_y);
            telemetry.update();
        }
    }
}
