package tp4;

import java.util.ArrayList;

public class Juego {

    private int base;
    private int altura;
    private char varianteDeTriunfo;

    private EstadoDeJuego estadoDeJuego;
    private ArrayList<ArrayList<String>> tablero;

    public Juego(int base, int altura, Character varianteDeTriunfo) {
        this.base = base;
        this.altura = altura;
        this.varianteDeTriunfo = varianteDeTriunfo;
        this.estadoDeJuego = new JuegaElRojo( this );
        this.tablero = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < base; i++) {
            tablero.add(new ArrayList<String>());
        }

    }
    public int getBase() {
        return base;
    }
    public int getAltura() {
        return altura;
    }
    public int getVarianteDeTriunfo() {
        return varianteDeTriunfo;
    }

    public boolean turnoRojo() {
        return estadoDeJuego.esTurnoRojo();
    }
    public boolean turnoAzul() {
        return estadoDeJuego.esTurnoAzul();
    }

    public boolean termino() {
        return estadoDeJuego.termino();
    }

    public void juegaElRojo() {
        estadoDeJuego.juegaElRojo(this);
    }
    public void juegaElAzul() {
        estadoDeJuego.juegaElAzul(this);
    }

    public void cambiarEstadoDeJuego(EstadoDeJuego estadoDeJuego) {
        this.estadoDeJuego = estadoDeJuego;
    }

   public void agregarFichaEn(int columna) {
       if (estadoDeJuego.esTurnoAzul() && tablero.get(columna).size() < altura) {
           tablero.get(columna).add("A");
       }
       if (estadoDeJuego.esTurnoRojo() && tablero.get(columna).size() < altura) {
           tablero.get(columna).add("R");
       }

   }

    public boolean tableroLleno() {
        return tablero.stream().allMatch( (each) -> each.size() == altura );
    }
}
