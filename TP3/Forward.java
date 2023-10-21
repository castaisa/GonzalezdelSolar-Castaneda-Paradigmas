package tp3;

public class Forward extends Commands {
	public boolean applies(Character character) {
		return 'f' == character;
	}

	public void executeCommand(Direction direction, Submarine nemo) {
		nemo.direction.goForward(nemo);
	}
}
