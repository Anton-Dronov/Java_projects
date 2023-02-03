package labs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class lab_02 {	
	
	private static String[] LoadStrings(String[] fromfile) { // file reading
		try {
		      File filik = new File("dataset.csv");
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
	
	public static void ShellSort(ArrayList<DataPerson> whatToSort, byte choice) { // realization of shell sort algorithm
		DataPerson ismin;
		int ShellStep = 1;
		int k;
		while (3 * ShellStep < whatToSort.size()) {
			ShellStep = 3 * ShellStep + 1; // shell step counting 
		}
		while (ShellStep >= 1) {
			for (int i = ShellStep; i < whatToSort.size(); i++) {
				k = i;
				ismin = whatToSort.get(i);
				if (choice == 0) {
					while ((k > ShellStep - 1) && (whatToSort.get(k - ShellStep).TicketCost() > ismin.TicketCost())) {
						whatToSort.set(k, whatToSort.get(k - ShellStep));
						k = k - ShellStep;
					}
				} else if (choice == 1) {
					while ((k > ShellStep - 1) && (whatToSort.get(k - ShellStep).Age() > ismin.Age())) {
						whatToSort.set(k, whatToSort.get(k - ShellStep));
						k = k - ShellStep;
					}
				} else if (choice == 2) {
					while ((k > ShellStep - 1) && (whatToSort.get(k - ShellStep).Name().compareTo(ismin.Name()) > 0)) {
						whatToSort.set(k, whatToSort.get(k - ShellStep));
						k = k - ShellStep;
					}
				}
				whatToSort.set(k, ismin);
			}
			ShellStep = (ShellStep - 1) / 3; // shell step editing
		}
		for (int i = 0; i < whatToSort.size(); i++) {
			if (choice == 0) {
				System.out.print(whatToSort.get(i).TicketCost() + ", ");
			} else if (choice == 1) {
				System.out.print(whatToSort.get(i).Age() + ", ");
			}
			System.out.println(whatToSort.get(i).Name());
		}
		System.out.println();
	}
	
	static class DataPerson{
		private boolean isSurvived;
		private byte whichClass;
		private String name;
		private char sex;
		private float age;
		private short siblingsAndSpouses;
		private short parentsAndChildren;
		private float ticketCost;
		DataPerson(boolean IS, byte WC, String N, char S, float A, short SAS, short PAC, float TC){
			this.isSurvived = IS;
			this.whichClass = WC;
			this.name = N;
			this.sex = S;
			this.age = A;
			this.siblingsAndSpouses = SAS;
			this.parentsAndChildren = PAC;
			this.ticketCost = TC;
		}
		public boolean IsSurvived(){
			return isSurvived;
		}
		public byte WhichClass() {
			return whichClass;
		}
		public String Name() {
			return name;
		}
		public char Sex() {
			return sex;
		}
		public float Age() {
			return age;
		}
		public short SiblingsAndSpouses() {
			return siblingsAndSpouses;
		}
		public short ParentsAndChildren() {
			return parentsAndChildren;
		}
		public float TicketCost() {
			return ticketCost;
		}
	}
	
	public static void FindingElements(ArrayList<DataPerson> list, byte choice, String find) {
		int sum = 0;
		int subtr = 0;
		while (sum < list.size() - subtr) {
			int elem = (int)Math.floor((list.size() - subtr - sum)/2) + sum;
			if ((choice == 0 && list.get(elem).TicketCost() == Float.parseFloat(find)) ||
				(choice == 1 && list.get(elem).Age() == Float.parseFloat(find))	||
				(choice == 2 && list.get(elem).Name().compareTo(find) == 0)) {
				System.out.println(list.get(elem).Name() + ", " + list.get(elem).Age() + ", " + list.get(elem).TicketCost());
				for (int i = elem - 1;
						(choice == 0 && list.get(i).TicketCost() == list.get(elem).TicketCost()) ||
						(choice == 1 && list.get(i).Age() == list.get(elem).Age()) ||
						(choice == 2 && list.get(i).Name().compareTo(list.get(elem).Name()) == 0); i--) {
					System.out.println(list.get(i).Name() + ", " + list.get(elem).Age() + ", " + list.get(elem).TicketCost());
				}
				for (int i = elem + 1; 
						(choice == 0 && list.get(i).TicketCost() == list.get(elem).TicketCost()) ||
						(choice == 1 && list.get(i).Age() == list.get(elem).Age()) ||
						(choice == 2 && list.get(i).Name().compareTo(list.get(elem).Name()) == 0); i++) {
					System.out.println(list.get(i).Name() + ", " + list.get(elem).Age() + ", " + list.get(elem).TicketCost());
				}
				break;
			}
			else if ((choice == 0 && list.get(elem).TicketCost() < Float.parseFloat(find)) ||
					(choice == 1 && list.get(elem).Age() < Float.parseFloat(find))	||
					(choice == 2 && list.get(elem).Name().compareTo(find) < 0)) {
				sum += Math.round((double)(list.size()-subtr-sum)/2);
			} else {
				subtr += Math.round((double)(list.size()-subtr-sum)/2);
			}
		}
	}
	
	public static void main(String[] args) {
		String[] lines = new String[888];
		LoadStrings(lines);
		ArrayList<DataPerson> cheloveki = new ArrayList<DataPerson>();
		for (int i = 1; i < lines.length; i++) {
			String[] allParams = lines[i].split(",");
			boolean is;	// is passenger survived the disaster
			if (allParams[0].equals("1")) {
				is = true;
			} else is = false;
			byte wc = Byte.parseByte(allParams[1]);	// passenger's ticket class
			String n = allParams[2];	// passenger's name
			char s;	// passenger's sex
			if (allParams[3].equals("male")) {
				s = 'm';
			} else s = 'f';
			float a = Float.parseFloat(allParams[4]);	// age of a passenger
			short sas = Short.parseShort(allParams[5]);	// siblings and spouses
			short pac = Short.parseShort(allParams[6]);	// parents and children
			float tc = Float.parseFloat(allParams[7]);	// the ticket cost
			DataPerson newPerson = new DataPerson(is, wc, n, s, a, sas, pac, tc);
			cheloveki.add(newPerson);	// adding a new person in the array
		}
		System.out.println("Your element #54 name is: " + cheloveki.get(54).Name());
		DataPerson CurrentPerson1 = cheloveki.get(54);
		cheloveki.remove(54);
		System.out.println("Your element #54 NOW name is: " + cheloveki.get(54).Name());
		DataPerson CurrentPerson2 = cheloveki.get(54);
		cheloveki.set(54, CurrentPerson1);
		cheloveki.add(CurrentPerson2);
		System.out.println("Your element #54 NOW name is: " + cheloveki.get(54).Name());
		System.out.println("Here was " + cheloveki.size() + " people on a Titanic.");
		byte choice;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("\nEnter 0 to find elements by ticket cost,\nenter 1 to find elements by age,\nenter 2 to find elements by name.");
		choice = keyboard.nextByte();	// by which parameter you want to sort?
		ShellSort(cheloveki, choice);	// sorting by Shell algorithm
		String space = keyboard.nextLine(); // to clear the "Enter" symbol
		System.out.print("Enter the value of chosen parameter: ");
		String toFind = keyboard.nextLine();	// your choice reading
		keyboard.close();
		FindingElements(cheloveki, choice, toFind);
	}
}
