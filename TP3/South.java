package tp3;

public class South extends Direction {
	public String getDirection() {
		return "south";
	}
	public void turnLeft(Submarine nemo) {
		nemo.direction = new East();
	}
	public void turnRight(Submarine nemo) {
		nemo.direction = new West();
	}
	public void goForward(Submarine nemo) {
		nemo.position.add(nemo,0, -1);
	}
}
