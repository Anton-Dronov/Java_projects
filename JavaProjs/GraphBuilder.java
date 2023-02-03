package labs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class lab_03 {

	private static String[][] LoadStrings(String[][] fromfile, int size) { // file reading
		try {
		      File filik1 = new File("asoiaf-book1-nodes.csv");
		      File filik2 = new File("asoiaf-book1-edges.csv");
		      Scanner myReader1 = new Scanner(filik1);
		      Scanner myReader2 = new Scanner(filik2);
		      for (int i = 0; i < size && (myReader1.hasNextLine() || myReader2.hasNextLine()); i++) {
		    	if (myReader1.hasNextLine()) {
		    		fromfile[0][i] = myReader1.nextLine();
		    	}
		    	if (myReader2.hasNextLine()) {
		    		fromfile[1][i] = myReader2.nextLine();
		    	}
		      }
		      myReader1.close();
		      myReader2.close();
		    } 
		catch (FileNotFoundException e) { // if one of the file can't be found
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return fromfile;
	}
	
	static class NEdge{
		private String[][] nodes; // array of nodes labels and their IDs
		private int[][] AdjWeight; // two-dimensional array of adjacency and weights
		NEdge(String[][] nodes, int[][] AdjWeight) { // simple constructor
			this.nodes = nodes;
			this.AdjWeight = AdjWeight;
		}
		public String[][] getNodes() { // getters/setters, beginning
			return nodes;
		}
		public void setNodes(String[][] nodes) {
			this.nodes = nodes;
		}
		public int[][] getAdjWeight() {
			return AdjWeight;
		}
		public void setAdjWeight(int[][] adjWeight) {
			AdjWeight = adjWeight;
		} // getters/setters, ending
	}
	
	public static NEdge deletion(NEdge graph, String label) {
		int[][] ToChange = graph.getAdjWeight(); // adjacency matrix copy
		boolean k = false;
		String[][] FromWhatDelete = graph.getNodes(); // node list copy
		for (int i = 0; i < FromWhatDelete[0].length; i++) {
			if (FromWhatDelete[1][i].equals(label)) { // if the element name is equal to the entered value
				FromWhatDelete[0][i] = ""; // element clearing
				FromWhatDelete[1][i] = "";
				for(int j = 0; j < ToChange[0].length; j++) { // all weights become equal to zero
					ToChange[i][j] = 0;
					ToChange[j][i] = 0;
				}
				k = true; // the element has been deleted
			}
		}
		if (k == false) {
			System.out.println("Sorry, element " + label + " does not exist!");
		} else {
			boolean isVert;
			for (int o = 0; o < FromWhatDelete[0].length; o++) {
				isVert = false;
				for (int q = 0; q < FromWhatDelete[0].length; q++) {
					if (ToChange[o][q] != 0) { // is exist at least 1 element in the 'o' row that isn't zero?
						isVert = true;
						break;
					}
				}
				if (FromWhatDelete[1][o] != "" && isVert == false) { // element exists but isn't adjacent to other nodes
					FromWhatDelete[0][o] = ""; // element clearing
					FromWhatDelete[1][o] = "";
				}
			}
			System.out.println("Your graph after deletion:");
			System.out.println();
			System.out.println();
			graph.setAdjWeight(ToChange);
			graph.setNodes(FromWhatDelete);
			seeGraph(graph);
			System.out.println("The element " + label + " has been succesfully deleted!");
			System.out.println();
		}
		return graph;
	}
	
	public static void seeGraph(NEdge graph) {
		int[][] adja = graph.getAdjWeight();
		String[][] obje = graph.getNodes();
		for (int i = 0; i < obje[0].length; i++) {
			if (!obje[0][i].equals("")) { // only existing elements will be displayed in the cmd
				System.out.print(obje[1][i] + ": ");
				for (int j = 0; j < obje[0].length; j++) {
					if (adja[i][j] != 0) {
						System.out.print(obje[1][j] + ", ");
					}
				}
				System.out.println();
				System.out.println();
			}
		}
		System.out.println();
		System.out.println();
	}
	
	public static void main(String[] args) {
		int cols = Integer.parseInt(args[0]);
		int vrtcs = Integer.parseInt(args[1]);
		String[][] datasets = new String[2][cols];
		LoadStrings(datasets, cols);
		String[][] AllNodes = new String[2][vrtcs];
		int[][] adjacency = new int[vrtcs][vrtcs];
		for (int fill = 0; fill < 187; fill++) {
			Arrays.fill(adjacency[fill], 0);
		}
		String[] splitNode = new String[2];
		String[] splitEdge = new String[5];
		int i = 1;
		while (datasets[0][i] != null && i < cols) { // node import
			splitNode = datasets[0][i].split(",");
			String id = splitNode[0];
			String label = splitNode[1];
			AllNodes[0][i-1] = id;
			AllNodes[1][i-1] = label;
			i++;
		}
		i = 1;
		while (datasets[1][i] != null && i < cols - 1) { // nodes linking by weight specifying
			splitEdge = datasets[1][i].split(",");
			int RowCol = 500; // some random big numbers
			int ColRow = 500;
			String source = splitEdge[0];
			String target = splitEdge[1];
			for (int j = 0; j < vrtcs; j++) {
				if (source.equals(AllNodes[0][j])) {
					RowCol = j;
					break;
				}
			}
			for (int j = 0; j < vrtcs; j++) {
				if (target.equals(AllNodes[0][j])) {
					ColRow = j;
					break;
				}
			}
			int weight = Integer.parseInt(splitEdge[3]);
			adjacency[RowCol][ColRow] = weight; // the matrix has the symmetry
			adjacency[ColRow][RowCol] = weight;
			i++;
		}
		NEdge graph = new NEdge(AllNodes, adjacency);
		seeGraph(graph);
		Scanner keyboard = new Scanner(System.in);
		while (true) {
			System.out.println("Write an ID of the element which you want to delete.");
			System.out.println("You can also enter the '/exit' command to terminate the program.");
			String phrase = keyboard.nextLine();
			if (!phrase.equals("/exit")) {
				graph = deletion(graph, phrase);
			} else break;
		}
		keyboard.close();
	}
}
