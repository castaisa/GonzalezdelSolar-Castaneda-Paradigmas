package paquete;

public class CajaLlena extends Caja{
	
	private Object elemento;
	
	public CajaLlena(Object cargo) {
		elemento = cargo;
	}
	
	public Object Return() {
		return elemento;
	}
}