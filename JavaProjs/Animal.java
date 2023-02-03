package lv.tsi.animals;

public interface Animal {
	public static void sound() { }
}

interface Wild extends Domestic{} 

class Cat implements Animal, Domestic{
	String name;
	void sound(){
		System.out.println("Meow");
	}
	Cat(){ }
	Cat(String name){
		this.name = name;
	}
	void printName(){
		System.out.println("Cat's name is " + name);
	}
}
class Dog implements Animal, Domestic{
	String name;
	void sound(){
		System.out.println("Bark");
	}
	Dog(){ }
	Dog(String name){
		this.name = name;
	}
	void printName(){
		System.out.println("Dog's name is " + name);
	}
}
class Fox implements Animal, Wild{
	String name;
	void sound(){
		System.out.println("Wa-pow");
	}
	Fox(){ }
	Fox(String name){
		this.name = name;
	}
	void printName() {
		System.out.println("Fox's name is " + name);
	}
}
class Animals{
	public static void main(String[] args) {
		Cat cat = new Cat("Felix");
		Dog dog = new Dog("Barashka");
		Fox fox = new Fox("Orbitrator");
		cat.printName();
		cat.sound();
		System.out.println();
		dog.printName();
		dog.sound();
		System.out.println();
		fox.printName();
		fox.sound();
	}
}