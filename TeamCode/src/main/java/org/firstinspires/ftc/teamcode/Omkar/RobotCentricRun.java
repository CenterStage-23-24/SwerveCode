package org.firstinspires.ftc.teamcode.Omkar;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class RobotCentricRun extends LinearOpMode {
    RobotCentricDriveTrain drive = new RobotCentricDriveTrain();

    @Override
    public void runOpMode() {
        while(opModeIsActive()) {
            drive.init();
            waitForStart();
            drive.loop();
        }

    }

}
