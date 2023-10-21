package tp3;

public class Down extends Commands {
	public boolean applies(Character character) {
		return 'd' == character;
	}

	public void executeCommand(Direction direction, Submarine nemo) {
		nemo.depthOfficer.goDown();
	}
}
