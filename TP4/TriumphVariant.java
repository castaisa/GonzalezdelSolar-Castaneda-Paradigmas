package linea;

public abstract class TriumphVariant {
    public abstract boolean applies(Character triumphVariantChar);

    public abstract boolean somebodyWon(Linea currentGame);

    public abstract Character getCharacter();
}
