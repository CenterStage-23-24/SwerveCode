package org.firstinspires.ftc.teamcode.Omkar;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class FieldCentricRun extends OpMode {
    FieldCentricDriveTrain drive = new FieldCentricDriveTrain();
    @Override
    public void init() {
        drive.init();
    }
    @Override
    public void loop() {
        drive.loop();
    }

}
