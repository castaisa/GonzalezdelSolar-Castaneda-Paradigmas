package linea;

public class BlueWon extends Finished {

    public static final String ganoElAzul = "¡Ganó el Azul!";

    public BlueWon(Linea currentGame) {
        super(currentGame);
    }

    public boolean itsRedsTurn() {
        return false;
    }

    public boolean itsBluesTurn() {
        return false;
    }

    public void redPlays(int column) {
        throw new RuntimeException(elJuegoYaTermino);
    }

    public void bluePlays(int column) {
        throw new RuntimeException(elJuegoYaTermino);
    }

    public boolean isValid() {
        return currentGame.getTriumphVariant().somebodyWon(currentGame) && currentGame.bluesTurn();
    }

    public String getGameResult() {
        return ganoElAzul;
    }
}
