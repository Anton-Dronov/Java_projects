package lv.tsi;

public class FactorialCalculator {
	static int factorialLoop(int whatToFact) {
		int result = 1;
		for (int i = 1; i <= whatToFact; i++) {
			 result *= i;
		}
		return result;
	}
	static int factorialRecur(int bb) {
		int result;
		if (bb == 0) return 1;
		if (bb == 1) return 1;
		result = bb * factorialRecur(bb-1);
		return result;
	}
	public static void main(String[] args) {
		int factor = Integer.parseInt(args[0]);
		System.out.println(factor + "! = " + factorialLoop(factor) + " (loop method used);");
		System.out.println(factor + "! = " + factorialRecur(factor) + " (recursive call method used);");
	}
}
