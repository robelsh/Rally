
public class Node {
	
	protected Vehicule v;
	protected Node next;
	
    
	public Node(Vehicule v) {
		super();
		this.v = v;
		this.next = null;
	}
	


	public Vehicule getVehicule() {
		return v;
	}

	public void setVehicule(Vehicule v) {
		this.v = v;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	
}
