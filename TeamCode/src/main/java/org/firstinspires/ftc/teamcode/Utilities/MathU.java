package org.firstinspires.ftc.teamcode.Utilities;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.normalizeRadians;
public class MathU {

    public static final double TAU = Math.PI * 2; // Mathematical constant for relating a circles radius to circumference
    public static final double ETA = Math.PI / 2; // Mathematical constant for relating a semicircles diameter to its circumference

    // Normalize vector components so that magnitude is 1
    // Pass in x and y as separate params
    public static double[] normalizeVector(double x, double y){
        double magnitude = Math.hypot(x,y);
        return new double[] {x / magnitude, y / magnitude};
    }
    // Normalize vector components so that magnitude is 1
    // Pass in a array of doubles as param; x,y
    public static double[] normalizeVector(double[] vector){
        double magnitude = Math.hypot(vector[0],vector[1]);
        return new double[] {vector[0] / magnitude, vector[1] / magnitude};
    }
    // normalize angle in radians to be in the range of [0 - Tau]
    public static double normalizeRadiansTau(double angle) {
        return (angle + TAU) % TAU;
    }

    // Normalize angle in radians to be in the range of [-pi - pi]
    public static double normalizeRadiansPi(double angle) {
       return normalizeRadians(angle);
    }
}
