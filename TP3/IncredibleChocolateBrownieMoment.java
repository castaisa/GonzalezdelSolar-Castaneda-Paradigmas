package tp3;

public class IncredibleChocolateBrownieMoment extends Commands {
	public boolean applies(Character character) {
		return 'm' == character;
	}

	public void executeCommand(Direction direction, Submarine nemo) {
		nemo.depthOfficer.liberateCapsule();
	}
}
