package paquete;
import java.util.ArrayList;

public class Queue {
	
private ArrayList<Box> listOfBoxes;
public static EmptyBox emptyBox;

public Queue() {
	listOfBoxes = new ArrayList<Box>();
	emptyBox = new EmptyBox();
	listOfBoxes.add( emptyBox );
	}

public boolean isEmpty(){
	 return listOfBoxes.size() == 1;
	}

public Queue add( Object  cargo ) {
	Box nonEmptyBox = new NonEmptyBox( cargo );
	listOfBoxes.add(this.size(), nonEmptyBox);
	return this;
	}

public Object take() {
	Object cargoIndex0 = getCargoInIndex0();
	listOfBoxes.remove(0); 
	return cargoIndex0;	
	}

public Object head() {
	return getCargoInIndex0();
	}

public int size() {
	return (listOfBoxes.size() -1) ;
	}

private Object getCargoInIndex0() {
	Box boxInIndex0 = listOfBoxes.get(0);
	Object cargoIndex0 = boxInIndex0.returnCargo();
	return cargoIndex0;
	}


}