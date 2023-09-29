package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Test")
public class Main extends LinearOpMode {

    public void runOpMode(){
        HWMap map = new HWMap(telemetry, hardwareMap);
    }
}

