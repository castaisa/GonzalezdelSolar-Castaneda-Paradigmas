package paquete;

public class EmptyBox extends Box {

    public static final String QueueIsEmpty = "Queue is empty";

	public Object returnCargo() {
        throw new Error(QueueIsEmpty);
    }
}