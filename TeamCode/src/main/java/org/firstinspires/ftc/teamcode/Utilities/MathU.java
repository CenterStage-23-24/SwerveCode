package org.firstinspires.ftc.teamcode.Utilities;

public class MathU {

    public static final double TAU = Math.PI * 2; // Mathematical constant for relating a circles radius to circumference
    public static final double ETA = Math.PI / 2; // Mathematical constant for relating a semicircles diameter to its circumference


    public static double[] normalizeVector(double x, double y){
        double magnitude = Math.hypot(x,y);
        return new double[] {x / magnitude, y / magnitude};
    }

    public static double[] normalizeVector(double[] vector){
        double magnitude = Math.hypot(vector[0],vector[1]);
        return new double[] {vector[0] / magnitude, vector[1] / magnitude};
    }

    public static double normalizeRadiansTau(double angle) {
        return (angle + TAU) % TAU;
    }

    public static double normalizeRadiansPi(double angle) {
        return TAU * Math.floor((angle + Math.PI) / TAU);
    }

}
