package lv.tsi;

public class GravityCalculator {
	static final double GRAVITY = -9.81; // Earth's gravity in m/s^2
	static double formula (int t, double v0, double x0){
		double xt = Math.pow(t, 2) * GRAVITY * 0.5 + v0 * t + x0;
		return xt;
	}
	public static void main(String[] args) {
		int fallingTime = Integer.parseInt (args[0]);
		double initialPosition = 0;
		if (args.length >= 2) {
			initialPosition = Double.parseDouble(args[1]);
		}
		double initialVelocity = 0;
		if (args.length >= 3) {
			initialVelocity = Double.parseDouble(args[2]);
		}
		double finalPosition = formula(fallingTime, initialVelocity, initialPosition);
		System.out.println ("Position in " + fallingTime + " seconds: " + finalPosition);
	}
}
