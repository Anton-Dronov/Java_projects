package lv.tsi;

public class OneDimensionalArray {
	public static double[][] createRandomArray(int size){
		double [][] array = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				array[i][j] = Math.random() * 100;
			}
		}
		return array;
	}
	public static double mean(double[][] aray) {
		double average = 0;
		for (int i = 0; i < aray.length; i++) {
			for(int j = 0; j < aray.length; j++) {
				average += aray[i][j];
			}
		}
		average /= Math.pow(aray.length, 2);
		return average;
	}
	public static void main(String[] args) {
		int sizeOfArr = Integer.parseInt(args[0]);
		var arr = createRandomArray(sizeOfArr);
		double min = arr[0][0];
		double max = 0;
		double average = mean(arr);
		for (int i = 0; i < sizeOfArr; i++) {
			for (int j = 0; j < sizeOfArr; j++) {
				System.out.print(arr[i][j] + ", ");
				if (arr[i][j] < min) {
					min = arr[i][j];
				}
				if (arr[i][j] > max) {
					max = arr[i][j];
				}
			}
			System.out.println();
		}
		System.out.printf("Min is " + min + ", max is " + max + ", average is " + average + ".");
	}
}
