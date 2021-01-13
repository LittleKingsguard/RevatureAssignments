package Animals;

import java.util.Arrays;
import java.util.Random;

public class Feline extends Animal{

	
	public void makeNoise() {
		System.out.println("The feline makes noises!");
	}
	
	public void eat() {
		this.hunger = 0;
		System.out.println("The feline eats some " + this.food + "!");
	}
	public void sleep() {
		this.hunger += 0.5;
		System.out.println("The feline takes a nap!");
	}
	public void roam() {
		System.out.println();
		System.out.print("The feline travels from " + Arrays.toString(this.location));
		Random rand = new Random(System.currentTimeMillis());
		int dimension = this.boundaries[0][1]-this.boundaries[0][0];
		this.location[0] = Math.abs(rand.nextInt()) % dimension + this.boundaries[0][0];
		dimension = this.boundaries[1][1]-this.boundaries[1][0];
		this.location[1] = Math.abs(rand.nextInt()) % dimension + this.boundaries[1][0];
		System.out.print(" to  " + Arrays.toString(this.location));
		System.out.println();
	}
	public Feline() {
		this.food = "meat";
		this.boundaries = new int[][]{{-10, 10}, {-10, 10}};
		this.location = new int[] {0,0};
	}
}
