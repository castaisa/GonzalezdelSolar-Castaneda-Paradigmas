package linea;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import tp4a.VarianteDeTriunfo;

import static org.junit.jupiter.api.Assertions.*;

public class LineaTest {

    @Test void test00elJuegoNoEmpiezaTerminado() {
        assertFalse(new Linea(4, 4, 'A').finished());
    }
    @Test void test01NuevoJuegoConMedidasYVarianteAdecuadas() {
        Linea game = new Linea(4, 4, 'A');
        assertEquals(game.getWidth(), 4);
        assertEquals(game.getHeight(), 4);
        assertEquals(game.getTriumphsVariantChar(), 'A');
    }
    @Test public void test02IngresarUnaVarianteNoValidaTiraError(){
        assertThrowsLike( () -> new Linea(4, 4, 'D'), Linea.varianteDeTriunfoNoValida);
    }
    @Test public void test03IngresarUnTableroDeAnchoNegativoTiraError(){
        assertThrowsLike( () -> new Linea(-4, 4, 'A'), Linea.lasDimensionesDebenSerPositivas);
    }
    @Test public void test04ElCaracterEnMinisculaDeUnaVarianteDeTriunfoValidaEsValido(){
        Linea gameWithAAsTriumphVariant = new Linea(4, 4, 'a');
        Linea gameWithBAsTriumphVariant = new Linea(4, 4, 'b');
        Linea gameWithCAsTriumphVariant = new Linea(4, 4, 'c');
        assertEquals(gameWithAAsTriumphVariant.getTriumphsVariantChar(), 'A');
        assertEquals(gameWithAAsTriumphVariant.getTriumphVariant().getClass(), WinsByRowsOrColumns.class);
        assertEquals(gameWithBAsTriumphVariant.getTriumphsVariantChar(), 'B');
        assertEquals(gameWithBAsTriumphVariant.getTriumphVariant().getClass(), WinsByDiagonals.class);
        assertEquals(gameWithCAsTriumphVariant.getTriumphsVariantChar(), 'C');
        assertEquals(gameWithCAsTriumphVariant.getTriumphVariant().getClass(), WinsByDiagonalsRowsOrColumns.class);
    }
    @Test public void test05EmpiezaJugandoElRojo() {
        Linea game = new Linea(4, 4, 'A');
        assertTrue(game.redsTurn());
        assertFalse(game.bluesTurn());
        assertThrowsLike( () -> game.playBlueAt(1), RedIsPlaying.noEsElTurnoDelAzul);
    }
    @Test public void test06RojoNoPuedeJugarDosVecesSeguidas() {
        Linea game = new Linea(4, 4, 'A');
        assertTrue(game.redsTurn());
        game.playRedAt(1);
        assertThrowsLike( () -> game.playRedAt(1), BlueIsPlaying.noEsElTurnoDelRojo);

    }
    @Test public void test07AzulNoPuedeJugarDosVecesSeguidas() {
        Linea game = new Linea(4, 4, 'A');
        assertTrue(game.redsTurn());
        game.playRedAt(1);
        assertFalse(game.redsTurn());
        assertTrue(game.bluesTurn());
        game.playBlueAt(1);
        assertThrowsLike( () -> game.playBlueAt(1), RedIsPlaying.noEsElTurnoDelAzul);
        assertFalse(game.bluesTurn());
        assertTrue(game.redsTurn());
    }
    @Test public void test08ElTurnoCambiaCorrectamente() {
        Linea game = new Linea(4, 4, 'A');
        assertTrue(game.redsTurn());
        game.playRedAt(1);
        assertFalse(game.redsTurn());
        assertTrue(game.bluesTurn());
        game.playBlueAt(1);
        assertFalse(game.bluesTurn());
        assertTrue(game.redsTurn());
    }
    @Test public void test09TirarFichaFueraDeLosLimitesDelTableroDaError() {
        Linea game = new Linea(4, 4, 'A');
        assertThrowsLike(() -> game.playRedAt(5), Linea.laFichaFueIngresadaFueraDelTablero);
    }
    @Test public void test10TirarFichaEnUnaColumnaLlenaDaError() {
        Linea game = testGanaPorColumnaSegunVarianteDeTriunfo('B');
        assertThrowsLike(() -> game.playBlueAt(1), Linea.laColumnaEstaLlena);
    }
    @Test public void test11GanaRojoPorColumnaVarianteDeTriunfoA() {
        Linea game = testGanaPorColumnaSegunVarianteDeTriunfo('A');
        assertTrue(game.finished());
        assertEquals(game.getGameResult(), RedWon.ganoElRojo);
    }
    @Test public void test12NoGanaRojoPorColumnaVarianteDeTriunfoB() {
        Linea game = testGanaPorColumnaSegunVarianteDeTriunfo('B');
        assertFalse(game.finished());
        assertEquals(game.getGameResult(), GameState.elJuegoAunNoHaTerminado);
    }
    @Test public void test13GanaRojoPorColumnaVarianteDeTriunfoC() {
        Linea game = testGanaPorColumnaSegunVarianteDeTriunfo('C');
        assertTrue(game.finished());
        assertEquals(game.getGameResult(), RedWon.ganoElRojo);
    }
    @Test public void test14GanaPorFilaVarianteDeTriunfoA() {
        Linea game = GanaRojoPorFilaVarianteDeTriunfo('A');
        assertTrue(game.finished());
        assertEquals(game.getGameResult(), RedWon.ganoElRojo);
    }
    @Test public void test15NoGanaPorFilaVarianteDeTriunfoB() {
        Linea game = GanaRojoPorFilaVarianteDeTriunfo('B');
        assertFalse(game.finished());
        assertEquals(game.getGameResult(), GameState.elJuegoAunNoHaTerminado);
    }
    @Test public void test16GanaPorFilaVarianteDeTriunfoC() {
        Linea game = GanaRojoPorFilaVarianteDeTriunfo('C');
        assertTrue(game.finished());
        assertEquals(game.getGameResult(), RedWon.ganoElRojo);
    }
    @Test public void test17NoGanaSiLasFichasConsecutivasEnUnaFilaNoSonIguales() {
        Linea game = new Linea(6, 6, 'C');
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(2);
        game.playRedAt(4);
        assertFalse(game.finished());
    }
    @Test public void test18NoGanaPorDiagonalVarianteDeTriunfoA() {
        Linea game = GanaRojoPorDiagonalVarianteDeTriunfo('A');
        assertFalse(game.finished());
        assertEquals(game.getGameResult(), GameState.elJuegoAunNoHaTerminado);
    }
    @Test public void test19GanaPorDiagonalVarianteDeTriunfoB() {
        Linea game = GanaRojoPorDiagonalVarianteDeTriunfo('B');
        assertTrue(game.finished());
        assertEquals(game.getGameResult(), RedWon.ganoElRojo);
    }
    @Test public void test20GanaPorDiagonalVarianteDeTriunfoC() {
        Linea game = GanaRojoPorDiagonalVarianteDeTriunfo('C');
        assertTrue(game.finished());
        assertEquals(game.getGameResult(), RedWon.ganoElRojo);
    }
    @Test public void test21GanarPorDiagonalDecreciente() {
        Linea game = new Linea(4, 4, 'C');
        game.playRedAt(4);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(4);
        game.playRedAt(1);
        assertTrue(game.finished());
    }
    @Test public void test22NoSePuedeTirarUnaFichaSiElTableroEstaLleno() {
        Linea game = tableroLLeno();
        assertTrue(game.boardIsFull());
        assertTrue(game.finished());
        assertEquals(Finished.elJuegoYaTermino,
                assertThrows(Exception.class, () -> game.playRedAt(1))
                        .getMessage());
    }
    @Test public void test23NoSePuedeTirarUnaFichaSiElJuegoYaTermino() {
        Linea game = GanaRojoPorDiagonalVarianteDeTriunfo('B');
        assertTrue(game.finished());
        assertEquals(Finished.elJuegoYaTermino,
                assertThrows(Exception.class, () -> game.playBlueAt(1))
                        .getMessage());

    }
    @Test public void test24NoSePuedenPonerMasFichasUnaVezQueElJuegoYaHayaTerminado() {
        Linea game = GanaRojoPorFilaVarianteDeTriunfo('A');
        assertEquals(Finished.elJuegoYaTermino,
                Assertions.assertThrows(Exception.class, () -> game.playBlueAt(1)).getMessage());
    }
    private static void assertThrowsLike( Executable executable, String message) {
        assertEquals(message,
                assertThrows(Exception.class,  executable)
                        .getMessage());
    }

    private static Linea testGanaPorColumnaSegunVarianteDeTriunfo( char varianteDeTriunfo ) {
        Linea game = new Linea(4, 4, varianteDeTriunfo);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        return game;
    }
    private Linea GanaRojoPorFilaVarianteDeTriunfo(Character variantedeTriunfo) {
        Linea game = new Linea(6, 6, variantedeTriunfo);
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(4);
        game.playBlueAt(5);
        game.playRedAt(5);

        return game;
    }
    private Linea GanaRojoPorDiagonalVarianteDeTriunfo(Character varianteDeTriunfo) {
        Linea game = new Linea(4, 4, varianteDeTriunfo);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(4);
        game.playBlueAt(1);
        game.playRedAt(4);

        return game;
    }
    private Linea tableroLLeno() {
        Linea game = new Linea(4, 4, 'A');
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(2);
        game.playBlueAt(1);
        game.playRedAt(4);
        game.playBlueAt(3);

        return game;
    }
}
