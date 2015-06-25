


public class ListVehicule {
	
	private Node first;
	

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	
	
	//Test vide
	public boolean isEmpty() {
		return first == null;
	}

	//Vider liste
	public void empty() {
		first = null;
		
	}
	//Ins�rer d�but
	public void insertFirst(Node v) {
		v.setNext(first);
		first = v;
		
	}


	//Ins�rer fin 
	public void insertLast(Node v) {
		
		if (first == null)
			first = v;
		else {
			
			Node elem = first;
			
			while (elem.getNext() != null) {
				elem = elem.getNext();
			}
			elem.setNext(v);
		}

	}
}