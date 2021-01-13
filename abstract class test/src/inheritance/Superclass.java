package inheritance;

public class Superclass {
	private int x;

	public Superclass(int x) {
		super();
		this.x = x;
		System.out.println("Parameterized Constructor for Superclass");
	}

	public Superclass() {
		super();
		System.out.println("Unparameterized Constructor for Superclass");
	}
	
	
}
