package lv.tsi.lambda;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String name;
	private int age;
	Person(String name, int age){
		this.name = name;
		this.age = age;
	}
	public String getName(){
		 return name;
	}
	public int getAge() {
		return age;
	}
	public void print() {
		System.out.println(name + ", " + age + ";");
	}
}
class PersonEvaluator{
	private List<Person> persons = new ArrayList<Person>();
	PersonEvaluator(List<Person> list){
		this.persons = list;
	}
	public void findAndPrint(char start) {
		System.out.println("Persons with the capital '" + start + "':");
		for (Person per : persons) {
			if (per.getName().indexOf(start) == 0) {
				per.print();
			}
		}
		System.out.println();
	}
	
	public void findAndPrint(int age, char sign) {
		System.out.println("List of aged persons:");
		for (Person per : persons) {
			if (sign == '>') {
				if (per.getAge() > age) {
					per.print();
				}
			} else if (sign == '<') {
				if (per.getAge() < age) {
					per.print();
				}
			} else {
				if (per.getAge() == age) {
					per.print();
				}
			}
		}
		System.out.println();
	}
	
	public void groupAndPrint() {
		System.out.println("List of grouped by age persons:");
		for (int i = 0; i < 50; i++) {
			boolean k = true;
			for (Person per : persons) {
				if (i == per.getAge()){
					if (k == true) {
						System.out.print("Age " + i + ": ");
						k = false;
					}
					System.out.print(per.getName() + ", ");
				}
			}
			if (k == false) {
				System.out.println();
			}
		}
	}
	public void getAverageAge() {
		System.out.println();
		int average = 0;
		for (Person per : persons) {
			average += per.getAge();
		}
		average /= persons.size();
		System.out.println("The average age of all people is " + average + ".");
	}
}

class PersonProcessingWithStreams{
	public static void main(String[] args) {
		List<Person> list = new ArrayList<Person>();
		Person per1 = new Person("Max", 28);
		list.add(per1);
		Person per2 = new Person("Peter", 22);
		list.add(per2);
		Person per3 = new Person("Anna", 28);
		list.add(per3);
		Person per4 = new Person("Lilianna", 16);
		list.add(per4);
		Person per5 = new Person("Pamela", 13);
		list.add(per5);
		Person per6 = new Person("David", 22);
		list.add(per6);
		PersonEvaluator listOfPersons = new PersonEvaluator(list);
		listOfPersons.findAndPrint('P');
		listOfPersons.findAndPrint(18, '>');
		listOfPersons.groupAndPrint();
		listOfPersons.getAverageAge();
	}
}