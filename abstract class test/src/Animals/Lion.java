package Animals;


public class Lion extends Feline{


	
	public void makeNoise() {
		System.out.println("The lion roars!");
	}
	
	public void eat() {
		this.hunger = 0;
		System.out.println("The lion eats some " + this.food + "!");
	}
}
