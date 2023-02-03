package labs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class lab_01 {	
	private static String[] LoadStrings(String[] fromfile) { // file reading
		try {
		      File filik = new File("dataset-titanic.csv");
		      Scanner myReader = new Scanner(filik);
		      for (int i = 0; i < fromfile.length && myReader.hasNextLine(); i++) {
		        fromfile[i] = myReader.nextLine();
		      }
		      myReader.close();
		    } 
		catch (FileNotFoundException e) { // if file can't be found
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return fromfile;
	}
	
	public static void BingoSort(String[] whatToSort) { // realization of bingo sort algorithm
		String ismin = "";
		long perm = 0;
		   for (int i = 0; i < whatToSort.length; i++){
			   int k = i;
			   ismin = whatToSort[i];
			   for (int j = i+1; j < whatToSort.length; j++){
				   if (whatToSort[j].compareTo(ismin) < 0){
					   ismin = whatToSort[j];
					   k = j;
					   whatToSort[k] = whatToSort[i];
					   whatToSort[i] = ismin;
					   perm++;
		           } else if (whatToSort[j].compareTo(ismin) == 0) {
		        	   ismin = whatToSort[j];
		        	   whatToSort[j] = whatToSort[i+1];
		        	   whatToSort[i+1] = ismin;
		        	   perm++;
		           }
		       }
			   System.out.println(whatToSort[i] + ", ");
		   }
		System.out.println();
		System.out.println("Number of perms in bingo sort: " + perm + ". ");
	}
	
	public static void ShellSort(String[] whatToSort) { // realization of shell sort algorithm
		String ismin = "";
		long perm = 0;
		int ShellStep = 1;
		int k;
		while (3 * ShellStep < whatToSort.length) {
			ShellStep = 3 * ShellStep + 1; // shell step counting 
		}
		while (ShellStep >= 1) {
			for (int i = ShellStep; i < whatToSort.length; i++) {
				k = i;
				ismin = whatToSort[i];
					while ((k > ShellStep - 1) && (whatToSort[k - ShellStep].compareTo(ismin)) > 0) {
						whatToSort[k] = whatToSort[k - ShellStep];
						k = k - ShellStep;
						perm++;
					}
				whatToSort[k] = ismin;
			}
			ShellStep = (ShellStep - 1) / 3; // shell step editing
		}
		for (int i = 0; i < whatToSort.length; i++) {
			System.out.println(whatToSort[i] + ", ");
		}
		System.out.println();
		System.out.println("Number of perms in shell sort: "+ perm + ". ");
	}
	
	public static void main(String[] args) {
		String[] lines = new String[887];
		LoadStrings(lines);
		BingoSort(lines); // here you can choose between shell and bingo
		ShellSort(lines);   // sort algorithms simply by removing "//".
	}
}
