package Animals;

public class AnimalManager {
	public static void main(String []args) {
		Animal[] arr = {new Lion(), new Wolf()};
		for ( Animal j : arr) {
			j.makeNoise();
			j.eat();
			j.sleep();
			j.roam();
		}
	}

}
