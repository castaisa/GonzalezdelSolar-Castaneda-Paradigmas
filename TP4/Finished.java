package linea;

public abstract class Finished extends GameState{
    public static String elJuegoYaTermino = "El juego ya termino";

    public Finished(Linea juegoActual) {
        super(juegoActual);
    }

}
