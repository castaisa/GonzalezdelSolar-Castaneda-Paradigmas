package tp3;

import java.util.ArrayList;

public class Submarine {
	public static String ElSubmarinoHaSidoDestruido = "El submarino ha sido destruido!";
	public Coordinates position;
	public Direction direction;
	public ArrayList<Commands> possibleCommands;
	public DepthOfficer depthOfficer;

	public Submarine() {
		position = new Coordinates(0, 0);
		direction = new North();
		depthOfficer = new DepthOfficer();
		possibleCommands = new ArrayList<Commands>();
		possibleCommands.add(new Right());
		possibleCommands.add(new Left());
		possibleCommands.add(new Up());
		possibleCommands.add(new Down());
		possibleCommands.add(new Forward());
		possibleCommands.add(new IncredibleChocolateBrownieMoment());
	}

	public int getX() {
		return position.getX();
	}
	public int getY() {
		return position.getY();
	}
	public int getZ(){return depthOfficer.getZ();}


	public String getDirection() {
		return direction.getDirection();
	}

	 public void executeCommand(Character character) {
		 possibleCommands.stream()
		    .filter(command -> command.applies(character))
		    .forEach(command -> command.executeCommand(direction, this));
        }	
	 public void communicationChannel(String string) {
		 string.chars()
         .mapToObj(c -> (char) c)
         .forEach(character -> { executeCommand(character);
		 });
	 }
}
