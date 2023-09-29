package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;




public class HWMap {
    //Motor
    public DcMotorEx motor;
    //servo
    public Servo servo;

    public HardwareMap hwMap;
    public Telemetry telemetry;

    public HWMap(Telemetry telem, HardwareMap map){

        hwMap = map;
        telemetry = telem;

        motor = map.get(DcMotorEx.class, "Motor");
        servo = map.get(Servo.class, "Servo");
    }

    public void init(){
        servo.setPosition(0.5);
    }

    public void loop(double power){
        motor.setPower(power);
    }

    public void servo(double input){
        servo.setPosition(input);
    }
}
