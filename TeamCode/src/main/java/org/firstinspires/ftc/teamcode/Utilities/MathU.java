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

    // Normalizes an array of doubles to a value x, doesnt change anything if there are no values over x in the array
    // Note this method ignores the sign of a number but maintins it, EX: normalizeArrayX([-4, 2, -1], 1) returns [-1, 0.5, -0.25]
    // x Must be positive
    public static double[] normalizeArrayX(double[] array, double x) {
        double maxValue = 0.0;
        for (double value : array) {
            maxValue = Math.max(Math.abs(value), maxValue);
        }
        if (maxValue <= x) {
            return array;
        }
        double[] normalizedArray = array;
        double scale = x/maxValue;
        for (int i = 0; i< normalizedArray.length; i++) {
            normalizedArray[i] = normalizedArray[i] * scale;
        }
        return normalizedArray;
    }
}
