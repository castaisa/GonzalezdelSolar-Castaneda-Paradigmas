package paquete;

public class CajaVacia extends Caja {

	public static final String QueueIsEmpty = "Queue is empty";

	public Object Return() {
        throw new Error(QueueIsEmpty);
    }
}