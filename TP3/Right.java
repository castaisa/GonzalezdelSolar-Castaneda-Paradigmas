package tp3;

public class Right extends Commands {
	public boolean applies(Character character) {
		return character == 'r';
	}
	public void executeCommand(Direction direction, Submarine nemo) {
		direction.turnRight(nemo);
	}
}
