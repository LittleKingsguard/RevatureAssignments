package inheritance;

public class Subclass extends Superclass{
	private int y;

	public Subclass(int y) {
		super(y);
		this.y = y;
		System.out.println("Parameterized Constructor for Subclass");
	}

	public Subclass() {
		super();
		System.out.println("Unparameterized Constructor for Subclass");
	}
	
	
	public static void main(String []args) {
		Subclass sub = new Subclass(3);
	}
}