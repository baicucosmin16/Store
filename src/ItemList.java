
import java.util.*;

public abstract class ItemList {

	Node<Item> firstElem = null, lastElem = null;
	ItemIterator it = new ItemIterator();
	ArrayList<Integer> order;

	public static class Node<K> implements Comparator<K>{

		K value;
		Node<K> next;
		Node<K> prev;

		public Node(K value){
			this(value, null, null);
		}

		public Node(K value, Node next, Node prev){
			this.value = value;
			this.next = next;
			this.prev = prev;
		}

		@Override
		public int compare(K o1, K o2) {
			Node<Item> a = (Node<Item>)o1;
			Node<Item> b = (Node<Item>)o2;
			if (a.value.getPret() == b.value.getPret()){
				if (a.value.getNume().compareTo(b.value.getNume()) > 0){
					return 1;
				}
				else if (a.value.getNume().compareTo(b.value.getNume()) < 0){
					return -1;
				}
				else{
					return 0;
				}
			}
			else{
				if (a.value.getPret() - b.value.getPret() > 0){
					return 1;
				}
				else{
					return -1;
				}
			}
		}
	}

	class ItemIterator<K> implements ListIterator<K>{

		protected ItemList.Node current = firstElem;
		protected int currentInd = 0;

		public boolean hasNext() {
			return current.next != null;
		}

		@Override
		public K next() {
			return (K)current.next;
		}

		@Override
		public boolean hasPrevious() {
			return current.prev != null;
		}

		@Override
		public K previous() {
			return (K)current.prev;
		}

		@Override
		public int nextIndex() {
			if (this.hasNext())
				return currentInd + 1;
			return -1;
		}

		@Override
		public int previousIndex() {
			if (this.hasPrevious())
				return currentInd - 1;
			return -1;
		}

		@Override
		public void remove() {
			if (current == null){
				return;
			}
			else if (current == firstElem && current.next == null){
				current = null;
			}
			else if (current.next == null){
				current.prev.next = null;
				current = null;
			}
			else{
				current.prev.next = current.next;
				current.next.prev = current.prev;
				current = null;
			}
		}

		@Override
		public void set(K o) {
			current.value = ((Node)o).value;
		}

		@Override
		public void add(K o) {

		}
	}

	public boolean add(Item element){
		Node newNode = new Node(element);
		it.current = firstElem;
		it.currentInd = 0;
		if (firstElem == null){
			newNode.next = null;
			newNode.prev = null;
			firstElem = newNode;
			lastElem = newNode;
			return true;
		}
		else{
			while (it.current != null && it.current.compare(newNode, it.current) > 0){
				it.current = (Node)it.next();
				it.currentInd++;
			}
			if (it.current == firstElem){
				newNode.next = firstElem;
				newNode.prev = null;
				firstElem.prev = newNode;
				firstElem = newNode;
				return true;
			}
			else if (it.current == null){
				newNode.prev = lastElem;
				newNode.next = null;
				lastElem.next = newNode;
				lastElem = newNode;
				return true;
			}
			else{
				Node aux = (Node)it.current.prev;
				newNode.next = it.current;
				newNode.prev = aux;
				it.current.prev = newNode;
				aux.next = newNode;
				return true;
			}

		}
	}

	public boolean addAll(Collection<? extends Item> c){

		Iterator itr = c.iterator();
		while (itr.hasNext()){
			if (!add((Item)itr.next()))
				return false;
		}
		return true;
	}

	public Item getItem(int index){
		it.current = firstElem;
		int currentIndex = 0;
		while (it.current != null){
			if (currentIndex == index)
				return (Item)it.current.value;
			it.current = (Node)it.next();
			currentIndex++;
		}
		return null;
	}

	public Node<Item> getNode(int index){
		it.current = firstElem;
		int currentIndex = 0;
		while (it.current != null){
			if (currentIndex == index){
				return (Node<Item>)it.current;
			}
			it.current = (Node)it.next();
			currentIndex++;
		}
		return null;
	}

	public int indexOf(Item item){
		it.current = firstElem;
		int currentIndex = 0;
		while(it.current != null){
			if (item == it.current.value){
				return currentIndex;
			}
			it.current = (Node)it.next();
			currentIndex++;
		}
		return -1;
	}

	public int indexOf(Node<Item> node){
		it.current = firstElem;
		int currentIndex = 0;
		while(it.current != null){
			if (node == it.current){
				return currentIndex;
			}
			it.current = (Node)it.next();
			currentIndex++;
		}
		return -1;
	}

	public boolean contains(Node<Item> node){
		it.current = firstElem;
		while (it.current != null){
			if (it.current == node){
				return true;
			}
			it.current = (Node)it.next();
		}
		return false;
	}

	public boolean contains(Item item){
		it.current = firstElem;
		while (it.current != null){
			if (it.current.value == item){
				return true;
			}
			it.current = (Node)it.next();
		}
		return false;
	}

	public Item contains(int id){
		it.current = firstElem;
		Item aux;
		while(it.current != null){
			aux = (Item)it.current.value;
			if (aux.getID() == id)
				return aux;
			it.current = (Node)it.next();
		}
		return null;
	}

	public boolean containFromDepart(int id){
		it.current = firstElem;
		Item aux;
		while(it.current != null){
			aux = (Item)it.current.value;
			if (aux.getDepartment().getID() == id)
				return true;
			it.current = (Node)it.next();
		}
		return false;
	}

	public Item remove(int index){
		it.current = firstElem;
		int currentIndex = 0;
		if (firstElem == null)
			return null;
		if (firstElem == lastElem){
			Item aux = (Item)firstElem.value;
			if(currentIndex == index){
				firstElem = null;
				lastElem = null;
				order.remove((Integer)aux.getID());
				return aux;
			}
			return null;
		}
		while(it.current != null){
			if (currentIndex == index){
				if (it.current == firstElem){
					Item aux = (Item)it.current.value;
					firstElem = firstElem.next;
					firstElem.prev = null;
					order.remove((Integer)aux.getID());
					return aux;
				}
				else if(it.current == lastElem){
					Item aux = (Item)it.current.value;
					lastElem = lastElem.prev;
					lastElem.next = null;
					order.remove((Integer)aux.getID());
					return aux;
				}
				else{
					Item aux = (Item)it.current.value;
					it.current.prev.next = it.current.next;
					it.current.next.prev = it.current.prev;
					order.remove((Integer)aux.getID());
					return aux;
				}
			}
			it.current = (Node)it.next();
			currentIndex++;
		}

		return null;
	}

	public boolean remove(Item item){
		Item a;
		it.current = firstElem;
		if(item == null)
			return false;
		if (firstElem == null)
			return false;
		if (firstElem == lastElem){
			System.out.println("AICI");
			if (firstElem.value.getID() == item.getID()){
				firstElem = null;
				lastElem = null;
				order.remove((Integer)item.getID());
				return true;
			}
			return false;
		}
		while(it.current != null){
			if ((a = (Item)it.current.value).getID() == item.getID()){
				if (it.current == firstElem){
					firstElem = firstElem.next;
					firstElem.prev = null;
					Item aux = (Item)it.current.value;
					order.remove((Integer)item.getID());
					return true;
				}
				else if(it.current == lastElem){
					lastElem = lastElem.prev;
					lastElem.next = null;
					Item aux = (Item)it.current.value;
					order.remove((Integer)item.getID());
					return true;
				}
				else{
					it.current.prev.next = it.current.next;
					it.current.next.prev = it.current.prev;
					Item aux = (Item)it.current.value;
					order.remove((Integer)item.getID());
					return true;
				}
			}
			it.current = (Node)it.next();
		};
		return false;
	}

	public boolean removeAll(Collection<? extends Item> collection){

		Iterator itr = collection.iterator();
		while (itr.hasNext()){
			if (!remove((Item)itr.next()))
				return false;
		}
		return true;
	}

	public boolean isEmpty(){
		return firstElem == null;
	}


	//public ListIterator<Item> listIterator(int index);
	//public ListIterator<Item> listIterator();

	public Double getTotalPrice(){
		it.current = firstElem;
		double sum = 0;
		Item aux;
		while (it.current != null){
			aux = (Item)it.current.value;
			sum += aux.getPret();
			it.current = (Node)it.next();
		}
		return sum;
	}


	public Item getItemMinPrice(){
		double min;
		Item retItem, aux;
		if (firstElem == null){
			return null;
		}
		else{
			min = firstElem.value.getPret();
			retItem = (Item)firstElem.value;
			it.current = firstElem.next;
			while(it.current != null){
				aux = (Item)it.current.value;
				if (aux.getPret() < min){
					min = aux.getPret();
					retItem = aux;
				}
				it.current = (Node)it.next();
			}
			return retItem;
		}
	}

	public ArrayList<Item> getItems(){

		ArrayList<Item> items = new ArrayList<>();
		it.current = firstElem;
		while(it.current != null){
			items.add((Item)it.current.value);
			it.current = (Node)it.next();
		}
		return items;
	}
	
	public int noItems(){
		int i = 0;
		it.current = firstElem;
		while(it.current != null){
			i++;
			it.current = (Node)it.next();
		}
		return i;
	}

}
