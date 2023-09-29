package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Pod {
    public Servo servo;
    public DcMotorEx motor;
    public double direction;

    public Pod(Servo s, DcMotorEx m){
        servo = s;
        motor = m;
    }

    public void init(){
        servo.setPosition(0.5);
        direction = 0.5;
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void run(float x, float y, double heading) {
        double rawTarget = (Math.toDegrees(Math.atan2(-y,x)) + 180 + 90) % 360;
        double target = rawTarget / 360;
        direction = nearest(target % 0.5, (target % 0.5) + 0.5, servo.getPosition());
        servo.setPosition(direction);
        double sign = ((target - 0.5 < 0 ? -1: 1) * (direction - 0.5 < 0 ? -1: 1) * -1);
        motor.setPower(Math.sqrt(Math.pow(x,2) + Math.pow(y,2)) * sign);
    }

    public double nearest(double a, double b, double t){
        if (Math.abs(t - a) < Math.abs(t - b)) {
            return a;
        } else {
            return b;
            }
        }
    }
