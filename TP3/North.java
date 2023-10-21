package tp3;

public class North extends Direction {
	public String getDirection() {
		return "north";
	}
	public void turnLeft(Submarine nemo) {
		nemo.direction = new West();
	}
	public void turnRight(Submarine nemo) {
		nemo.direction = new East();
	}
	public void goForward(Submarine nemo) {
		nemo.position.add(nemo,0, 1);
	}
}
