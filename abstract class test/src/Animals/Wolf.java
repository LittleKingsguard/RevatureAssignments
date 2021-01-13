package Animals;

public class Wolf extends Canine{

	public void makeNoise() {
		System.out.println("The wolf howls!");
	}
	
	public void eat() {
		this.hunger = 0;
		System.out.println("The wolf eats some " + this.food + "!");
	}
}
