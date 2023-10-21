package tp3;

public class Left extends Commands {
	public boolean applies(Character character) {
		return character == 'l';
	}

	public void executeCommand(Direction direction, Submarine nemo) {
		direction.turnLeft(nemo);
	}
}
