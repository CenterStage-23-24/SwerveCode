package org.firstinspires.ftc.teamcode.Omkar;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class DriveTrainRun extends LinearOpMode {
    public void runOpMode() {
        DriveTrain drive = new DriveTrain();
        drive.init();
        waitForStart();
        while (opModeIsActive()) {
            drive.loop();
        }
    }

}
