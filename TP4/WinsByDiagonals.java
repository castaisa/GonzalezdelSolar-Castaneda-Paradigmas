package linea;

public class WinsByDiagonals extends TriumphVariant {

    public boolean applies(Character triumphVariantChar) {
        return 'B' == Character.toUpperCase(triumphVariantChar);
    }

    public boolean somebodyWon(Linea currentGame) {
        return currentGame.winByDiagonal();
    }


    public Character getCharacter() {
        return 'B';
    }
}
