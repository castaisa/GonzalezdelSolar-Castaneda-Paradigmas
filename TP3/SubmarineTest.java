package tp3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SubmarineTest {

	@Test public void test00estaEnLaPosicionDireccionYProfundidadInicial() {
		Submarine nemo = new Submarine();
		assertPositionAndDirection(nemo, 0, 0, 0, "north");
	}

	@Test public void test01ComandoVacioNoCambiaLaPosicion() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("");
		assertPosition(nemo, 0, 0, 0);
	}

	@Test public void test02ComandoDDeciendeUnaUnidad() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("d");
		assertZ(nemo, 1);
	}

	@Test public void test03ComandoUNoSubeEstandoEnLaSuperficie() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("u");
		assertZ(nemo, 0);
	}

	@Test public void test04NemoSubeALaSuperficieEstandoEnElPrimerNivelDeProfundidad() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("du");
		assertZ(nemo, 0);
	}

	@Test public void test05NemoPuedeDescenderAlSegundoNivelDeProfundidad() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("ddd");
		assertPositionAndDirection(nemo, 0, 0, 3, "north");
	}

	@Test public void test06ComandoLHaceRotar90GradosALaIzquierda() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("l");
		assertDirection(nemo, "west");
	}

	@Test public void test07ComandoRHaceRotar90GradosALaDerecha() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("r");
		assertDirection(nemo, "east");
	}

	@Test public void test08ComandoRRHaceRotar180GradosALaDerecha() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("rr");
		assertDirection(nemo, "south");
	}

	@Test public void test09ComandoLLHaceRotar180GradosALaIzquierda() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("ll");
		assertDirection(nemo, "south");
	}

	@Test public void test10DaUnaVueltaYQuedaEnELMismoLugar() {
		Submarine nemo = new Submarine();
		Submarine dori = new Submarine();
		nemo.communicationChannel("l");
		dori.communicationChannel("lllll");
		assertEquals(nemo.getDirection(), dori.getDirection());
	}

	@Test public void test11ComandoRRRRHaceUnaVueltaYTerminaEnLaMismaPosicion() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("rrrr");
		assertDirection(nemo, "north");
	}

	@Test public void test12ComandoFEstandoEnNorteAvanzaUnaUnidadEnY() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("f");
		assertPosition(nemo, 0, 1, 0);
	}

	@Test public void test13ComandoFFEstandoEnNorteAvanzaDosUnidadesEnY() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("ff");
		assertPosition(nemo, 0, 2, 0);
	}

	@Test public void test14ComandoFAvanzaUnaUnidadEnXEstandoMirandoAlEste() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("rf");
		assertPosition(nemo, 1, 0, 0);
	}

	@Test public void test15ComandoFDisminuyeUnaUnidadEnXEstandoMirandoAlOeste() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("lf");
		assertPosition(nemo, -1, 0, 0);
	}

	@Test public void test16ComandoFDisminuyeUnaUnidadEnYEstandoMirandoAlSur() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("rrf");
		assertPosition(nemo, 0, -1, 0);
	}

	@Test public void test17LanzarLaCapsulaEnLaSuperficieNoAfectaElSubmarino() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("m");
		assertPositionAndDirection(nemo, 0, 0, 0, "north");
	}

	@Test public void test18LanzarLaCapsulaAUnNivelDeProfundidadNoAfectaElSubmarino() {
		Submarine nemo = new Submarine();
		nemo.communicationChannel("dm");
		assertPositionAndDirection(nemo, 0, 0, 1, "north");
	}

	@Test public void test19LanzarLaCapsulaAMasDeUnNivelDeProfundidadDestruyeElSubmarino() {
		Submarine nemo = new Submarine();
		assertEquals(Submarine.ElSubmarinoHaSidoDestruido,
				assertThrows(Exception.class,
						() -> nemo.communicationChannel("ddm")).getMessage());
	}

	private void assertPosition(Submarine nemo, int x, int y, int z) {
		assertEquals(nemo.getX(), x);
		assertEquals(nemo.getY(), y);
		assertEquals(nemo.getZ(), z);
	}
	private void assertZ(Submarine nemo, int z) {
		assertEquals(nemo.getZ(), z);
	}
	private void assertDirection(Submarine nemo, String direction) {
		assertEquals(nemo.getDirection(), direction);
	}
	private void assertPositionAndDirection(Submarine nemo, int x, int y, int z, String direccion) {
		assertPosition(nemo, nemo.getX(), nemo.getY(), nemo.getZ());
		assertDirection(nemo, nemo.getDirection());
	}
}

