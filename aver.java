ackage paquete;
//a ver
import java.util.ArrayList;

public class Queue {
	//private Questate state
	
  private ArrayList<Object> list;
  //private int objeto;

  public Queue() {
        list = new ArrayList<Object>();
    }

  public boolean isEmpty() {
	  //return this.state.isempty()
	  //if (this.objeto == 5) {
	//	  return true;
	  if (list.isEmpty()){
		// TODO Auto-generated method stub
		return true; }
	  return false;// porque solo retorna true
	}

	public Queue add( Object  cargo ) {
		list.add(cargo);
		// TODO Auto-generated method stub
		return this;
	}

	public Object take() {
    // TODO Auto-generated method stub
		if (list.isEmpty()) {
			  return ("Queue is empty");}
		return (list.remove(0));// que significa wue solo devuelve null
	}

	public Object head() {
		// TODO Auto-generated method stub
		if (list.isEmpty()) {
		  return ("Queue is empty");}
		return (list.get(0));
	}

	public int size() {
		// TODO Auto-generated method stub
		return (list.size());
	}

}

