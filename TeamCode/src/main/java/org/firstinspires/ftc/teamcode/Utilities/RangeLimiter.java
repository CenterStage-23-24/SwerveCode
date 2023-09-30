package org.firstinspires.ftc.teamcode.Utilities;

public class RangeLimiter {

    private double min, max;

    public RangeLimiter(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double clip(double value) {
        double output = value;
        output = Math.min(output, max);
        output = Math.max(output, min);
        return output;
    }
}
