package linea;
public class BlueIsPlaying extends GameState {

    public static String noEsElTurnoDelRojo = "No es el turno del Rojo";

    public BlueIsPlaying(Linea currentGame) {
        super(currentGame);
    }

    public boolean itsRedsTurn() {
        return false;
    }

    public boolean itsBluesTurn() {
        return true;
    }

    public boolean finished() {
        return false;
    }

    public void redPlays(int column) {
        throw new RuntimeException(noEsElTurnoDelRojo);
    }

    public void bluePlays(int column) {
        currentGame.addBluePieceAt(column);
        currentGame.switchGameState( correspondingState() );
    }

    public boolean isValid() {
        return !currentGame.bluesTurn() && currentGame.redsTurn() && !currentGame.boardIsFull() && !currentGame.finished();
    }

    public String getGameResult() {
        return elJuegoAunNoHaTerminado;
    }
}