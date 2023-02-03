package lv.tsi;

public class Marathon {
	public static void printResults(String[] names, int[] times){
		String firstPart;
		for (int i = 0; i < names.length && i < times.length; i++) {
			firstPart = names[i];
			while (firstPart.length() < 10) {
				firstPart += " ";
			}
			System.out.println(firstPart + " |   " + times[i]);
		}
	}
	public static void main(String[] args) {
		String [] names = {"Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt", "Alex", "Emma", "John", "James", "Jane", "Emily", "Daniel", "Neda", "Aaron", "Kate"};
		int [] times = {341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 299, 343, 317, 265};
		printResults(names, times);
	}
}
