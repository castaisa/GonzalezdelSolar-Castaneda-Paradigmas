package linea;

public class Draw extends Finished {

    public static final String huboUnEmpate = "Hubo un empate";

    public Draw(Linea currentGame) {
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
        return currentGame.boardIsFull() && !currentGame.getTriumphVariant().somebodyWon(currentGame);
    }

    public String getGameResult() {
        return huboUnEmpate;
    }
}
