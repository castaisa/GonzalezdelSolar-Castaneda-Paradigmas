package paquete;

public class NonEmptyBox extends Box{
	
	private Object savedCargo;
	
	public NonEmptyBox(Object cargo) {
		savedCargo = cargo;
	}
	
	public Object returnCargo() {
		return savedCargo;
	}
}