package tp3;

public class East extends Direction {

	public String getDirection() {
		return "east";
	}
	public void turnLeft(Submarine nemo) {
		nemo.direction = new North();
	}
	public void turnRight(Submarine nemo) {
		nemo.direction = new South();
	}
	public void goForward(Submarine nemo) {
		nemo.position.add(nemo,1, 0);
	}
}
