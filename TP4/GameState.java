package linea;

import java.util.ArrayList;
import java.util.List;

public abstract class GameState {
        public static final String elJuegoAunNoHaTerminado = "El juego aun no ha terminado";
        protected Linea currentGame;
        protected List<GameState> correspondingState;

        public GameState(Linea currentGame ) {
                this.currentGame = currentGame;
        }

        public abstract boolean itsRedsTurn();

        public abstract boolean itsBluesTurn();
        public abstract void redPlays(int column);

        public abstract void bluePlays(int column);

        public abstract boolean isValid();

        public abstract String getGameResult();
        public GameState correspondingState() {
                correspondingState = new ArrayList<GameState>( List.of(
                        new BlueIsPlaying(currentGame),
                        new RedIsPlaying(currentGame),
                        new BlueWon(currentGame),
                        new RedWon(currentGame),
                        new Draw(currentGame)
                        ) );
                return correspondingState.stream().filter( (each ) -> each.isValid() ).findFirst().get();
        }
}