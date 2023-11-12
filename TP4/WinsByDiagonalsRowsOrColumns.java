package linea;

public class WinsByDiagonalsRowsOrColumns extends TriumphVariant {

    public boolean applies(Character triumphVariantChar) {
        return 'C' == Character.toUpperCase(triumphVariantChar);
    }

    public Character getCharacter() {
        return 'C';
    }

    public boolean somebodyWon(Linea currentGame) {
        return currentGame.winByRow() || currentGame.winByColumn() || currentGame.winByDiagonal();
    }
}
