package tp3;

public class West extends Direction {
	public String getDirection() {
		return "west";
	}
	public void turnLeft(Submarine nemo) {
		nemo.direction = new South();
	}
	public void turnRight(Submarine nemo) {
		nemo.direction = new North();
	}
	public void goForward(Submarine nemo) {
		nemo.position.add(nemo ,-1, 0);
	}
}
