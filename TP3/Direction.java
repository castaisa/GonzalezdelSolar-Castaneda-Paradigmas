package tp3;

import tp3.Coordinates;
import tp3.Submarine;

public abstract class Direction {
	public abstract String getDirection();
	
	public abstract void turnLeft(Submarine nemo);

	public abstract void turnRight(Submarine nemo);
	
	public abstract void goForward(Submarine nemo);
}
