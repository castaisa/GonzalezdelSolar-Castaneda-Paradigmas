package tp3;

public class Coordinates {
	private int coordinateX;
	private int coordinateY;

	public Coordinates(int x, int y) {
		coordinateX = x;
		coordinateY = y;
	}

	public void add(Submarine nemo, int xAdder, int yAdder) {
		nemo.position =  new Coordinates(coordinateX + xAdder, coordinateY + yAdder);
	}

	public int getX() {
		return coordinateX;
	}

	public int getY() {
		return coordinateY;
	}
}
