package lv.tsi;

public class TwoDimensionalArray {
	public static int[][] createRandomArray(int x){
		int [][] array = new int[x][x];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				array[i][j] = (int)(Math.random() * 100);
			}
		}
		return array;
	}
	public static int[] mean(int[][] aray) {
		int[] average = new int[aray.length];
		for (int i = 0; i < aray.length; i++) {
			for(int j = 0; j < aray.length; j++) {
				average[i] += aray[j][i];
			}
			average[i] /= aray.length;
		}
		return average;
	}
	public static void print(int[][] array, int[] average) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("Averages: ");
		for (int i = 0; i < average.length; i++) {
			System.out.print(average[i] + ", ");
		}
	}
	public static void main(String[] args) {
		int sizeOfArr = Integer.parseInt(args[0]);
		var arr = createRandomArray(sizeOfArr);
		int[] average = mean(arr);
		print(arr, average);
	}
}
