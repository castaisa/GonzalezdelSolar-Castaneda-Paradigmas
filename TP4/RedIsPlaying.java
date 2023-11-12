package linea;
public class RedIsPlaying extends GameState {

    public static String noEsElTurnoDelAzul = "No es el turno del Azul";

    public RedIsPlaying(Linea currentGame) {
        super(currentGame);
    }

    public boolean itsRedsTurn() {
        return true;
    }

    public boolean itsBluesTurn() {
        return false;
    }

    public boolean termino() {
        return false;
    }

    public void redPlays(int column) {
        currentGame.addRedPieceAt(column);
        currentGame.switchGameState( correspondingState() );
    }

    public void bluePlays(int column) {
        throw new RuntimeException(noEsElTurnoDelAzul);
    }

    public boolean isValid() {
        return !currentGame.redsTurn() && currentGame.bluesTurn() && !currentGame.boardIsFull() &&!currentGame.finished();
    }

    public String getGameResult() {
        return elJuegoAunNoHaTerminado;
    }
}