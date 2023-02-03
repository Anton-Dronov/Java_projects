package SimplexAlgorithm;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

public class Simplex {
	static DecimalFormat format = new DecimalFormat("#.###");
	public static void Print(double[][] mat, int rows, int columns, FileWriter writeIn) {
		String res = "";
		try {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					res += format.format(mat[i][j]);
					while (res.length() < 5) {
						res += " ";
					}
					writeIn.append(res + "\t");
					res = "";
				}
				writeIn.append("\n");
			}
			writeIn.append("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> info = new ArrayList<String>();
		try {
			File inFile = new File(args[0]);
			Scanner gotoscan = new Scanner(inFile);
			while (gotoscan.hasNextLine()) {
				info.add(gotoscan.nextLine());
			}
			gotoscan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Sorry, file couldn't be found!");
		    e.printStackTrace();
		    System.exit(0);
		}
		String[] count = {};
		count = info.get(0).split("\t");
		int constraints = info.size() - 4;
		int variables = count.length;
		int slacks = constraints;
		int rows = constraints + 1;
		int columns = variables + slacks + 1;
		double[][] matrix = new double[rows][columns];
		try {
			File outFile = new File(args[1]);
			FileWriter errorWriter = new FileWriter(args[1]);
			if (outFile.createNewFile()) {
				System.out.println("Check the file " + outFile.getName() + ".");
			} else {
				System.out.println("The file " + outFile.getName() + " has been modified.");
			}
			if (info.size()-4 != info.get(info.size()-1).split("\t").length) {
				errorWriter.append("ERROR: incorrect input data");
				errorWriter.close();
				System.exit(0);
			}
		for (int i = 0; i < constraints; i++) {
			for (int j = 0; j < variables; j++) {
				matrix[i][j] = Double.parseDouble(info.get(i+2).split("\t")[j]);
				if (info.get(2).split("\t").length != info.get(i+2).split("\t").length) {
					errorWriter.append("ERROR: incorrect input data");
					errorWriter.close();
					System.exit(0);
				}
			}
			for (int j = 0; j < slacks; ++j) {
				if (j == i) {
					matrix[i][variables + j] = 1;
				} else {
					matrix[i][variables + j] = 0;
				}
			}
			matrix[i][columns-1] = Double.parseDouble(info.get(info.size()-1).split("\t")[i]);
			if (matrix[i][columns-1] < 0) {
				errorWriter.append("ERROR: incorrect problem statement");
				errorWriter.close();
				System.exit(0);
			}
		}
		for (int i = 0; i < columns; i++) {
			if (i < variables) {
				matrix[constraints][i] = Double.parseDouble(info.get(0).split("\t")[i]);
			} else {
				matrix[constraints][i] = 0;
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileWriter gotowrite = new FileWriter(args[1]);
			Print(matrix, rows, columns, gotowrite);
		boolean ifMultiple = false;
		double FirstOne = 0;
		double TheNext = 0;
		for (int iks = 0; iks < rows-1; iks++) {
			boolean b = true;
			for (int pere = 0; pere < variables; pere++) {
				if (pere == 0) {
					FirstOne = matrix[rows-1][0]/matrix[iks][0];
				} else {
					TheNext = matrix[rows-1][pere]/matrix[iks][pere];
					if (TheNext != FirstOne) {
						b = false;
						break;
					}
				}
			}
			if (b == true) {
				ifMultiple = true;
				break;
			}
		}
		while (true) {
			int maxcol = -1;
			double max = 0;
			for (int n = 0; n < columns; n++) {			
				if (max < matrix[rows-1][n]) {
					max = matrix[rows-1][n];
					maxcol = n;
				}
			}
			if (maxcol == -1) {
					gotowrite.append("SOLUTION FOUND: ");
					if (ifMultiple == true) {
						gotowrite.append("multiple solution\n");
					} else {
						gotowrite.append("unique solution\n");
					}
					gotowrite.append("Objective: z = " + format.format(matrix[rows-1][columns-1]*-1) + '\n');
					for (int stolb = 0; stolb < columns-1; stolb++) {
						boolean k = false;
						double result = 0;
						for (int ryad = 0; ryad < rows; ryad++) {
							if (matrix[ryad][stolb] != 0) {
								if (k == false) {
									result = matrix[ryad][columns-1];
									k = true;
								} else {
									result = 0;
									break;
								}
							}
						}
						gotowrite.append(format.format(result) + "\t");
					}
					gotowrite.close();
				System.out.println("Success!");
				System.exit(0);
			}
			double min = 0;
			int minrow = 0;
			for (int q = 0; q < rows; q++) {
				if (matrix[q][maxcol] != 0 && matrix[q][columns-1]/matrix[q][maxcol] > 0) {
					min = matrix[q][columns-1]/matrix[q][maxcol];
					break;
				}
			}
			if (min == 0) {
				try {			
					gotowrite.append("NO SOLUTION: unbounded problem");
					gotowrite.close();
				} catch (IOException e){
					System.out.println("Ann error is occured...");
					e.printStackTrace();
				}
				System.exit(0);
			}
			for (int o = 1; o < rows-1; o++) {
				if (matrix[o][columns-1]/matrix[o][maxcol] > 0 && matrix[o][columns-1]/matrix[o][maxcol] <= min) {
					minrow = o;
				}
			}
			double delimeter = matrix[minrow][maxcol];
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (i != minrow && j != maxcol) {
						matrix[i][j] -= matrix[i][maxcol]/matrix[minrow][maxcol]*matrix[minrow][j];
					}
				}
			}
			for (int i = 0; i < rows; i++) {
				if (i != minrow) {
					matrix[i][maxcol] = 0;
				}
			}
			for (int j = 0; j < columns; j++) {
				matrix[minrow][j] /= delimeter;
			}
			Print(matrix, rows, columns, gotowrite);
		}
		} finally {
			System.out.println();
		}
	}
}