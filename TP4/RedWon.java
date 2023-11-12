package linea;

public class RedWon extends Finished {

    public static final String ganoElRojo = "¡Ganó el Rojo!";

    public RedWon(Linea currentGame) {
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
        return currentGame.getTriumphVariant().somebodyWon(currentGame) && currentGame.redsTurn();
    }

    public String getGameResult() {
        return ganoElRojo;
    }
}
