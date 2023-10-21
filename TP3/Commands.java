package tp3;

public abstract class Commands {
	public abstract boolean applies(Character character);
	public abstract void executeCommand(Direction direction, Submarine nemo);
}
