package linea;

public class WinsByRowsOrColumns extends TriumphVariant {

    public boolean applies(Character triumphVariantChar) {
        return 'A' == Character.toUpperCase(triumphVariantChar);
    }


    public boolean somebodyWon(Linea currentGame) {
        return currentGame.winByRow() || currentGame.winByColumn();
    }


    public Character getCharacter() {
        return 'A';
    }
}
