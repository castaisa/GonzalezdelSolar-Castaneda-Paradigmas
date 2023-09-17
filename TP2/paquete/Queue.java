package paquete;
import java.util.ArrayList;

public class Queue {
	
private ArrayList<Caja> listacajas;
public static CajaVacia cajavacia;

public Queue() {
	listacajas = new ArrayList<Caja>();
	cajavacia = new CajaVacia();
	listacajas.add( cajavacia );
	}

public boolean isEmpty(){
	 return listacajas.size() == 1;
	}

public Queue add( Object  cargo ) {
	Caja cajallena = new CajaLlena( cargo );
	listacajas.add(listacajas.size() -1, cajallena);
	return this;
	}

public Object take() {
	Caja cajaASacar = listacajas.get(0);
	Object elemento = cajaASacar.Return();
	listacajas.remove(0); 
	return elemento;	
	}

public Object head() {
	Caja cabeza = listacajas.get(0);
	Object elemento = cabeza.Return();
	return elemento;
	}

public int size() {
	return (listacajas.size() -1) ;
	}

}