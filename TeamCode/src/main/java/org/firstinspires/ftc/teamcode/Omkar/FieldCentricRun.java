package org.firstinspires.ftc.teamcode.Omkar;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class FieldCentricRun extends LinearOpMode {

    FieldCentricOldIMU drive = new FieldCentricOldIMU();
    @Override
    public void runOpMode() {
        drive.init();
        waitForStart();
        while(opModeIsActive()) {
            drive.loop();
        }

    }

}
