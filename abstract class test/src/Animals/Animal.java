package Animals;

import java.io.File;

public abstract class Animal {
	protected File picture;
	protected String food;
	protected float hunger;
	protected int[][] boundaries;
	protected int[] location;
	
	public abstract void makeNoise();
	public abstract void eat();
	public abstract void sleep();
	public abstract void roam();
}
