package tp3;

public class Up extends Commands {
	public boolean applies(Character character) {
		return 'u' == character;
	}

	public void executeCommand(Direction direction, Submarine nemo) {
		nemo.depthOfficer.goUp(nemo);
	}
}
