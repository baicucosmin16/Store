import java.util.ArrayList;
import java.util.Iterator;

public abstract class Department extends ItemList implements Subject{

	private String nume;
	//public ArrayList<Item> items;
	//public ItemList items;
	public ArrayList<Customer> customers;
	public ArrayList<Customer> observers;
	public int id;

	public Department(){
		customers = new ArrayList<>();
		observers = new ArrayList<>();
		order = new ArrayList<>();
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public void enter(Customer newCustomer){
		customers.add(newCustomer);
	}
	
	public void exit(Customer deleteCustomer){
		if (customers.size() != 0){
			int index = customers.indexOf(deleteCustomer);
			if (index != -1)
				customers.remove(index);
		}
	}
	
	public ArrayList<Customer> getCustomers(){
		return customers;
	}

	public ArrayList<Customer> getObservers(){
		//System.out.println("SIZE  " + observers.size());
		return observers;
	}
	
	public int getID(){
		return id;
	}
	
	public void addItem(Item newItem){
		add(newItem);
		Notification notification = new Notification("add", id, newItem.getID());
		notifyAllObservers(notification);
	}
	
	public boolean addItem(Item newItem, int a){
		if (contains(newItem.getID()) == null){
			add(newItem);
			Notification notification = new Notification("add", id, newItem.getID());
			notifyAllObservers(notification);
			System.out.println("S-a adaugat " + newItem.getNume() + " " + newItem.getPret());
			return true;
		}
		return false;
	}

	public void modifyItem(int id, double pret){
		Item aux;
		if ((aux = contains(id)) != null){
			aux.setPret(pret);
			Notification notification = new Notification("modify", this.id, aux.getID());
			notifyAllObservers(notification);

		}
	}
	
	public void modifyItem(int id, double pret, String name){
		Item aux;
		if ((aux = contains(id)) != null){
			aux.setPret(pret);
			aux.setNume(name);
			Notification notification = new Notification("modify", this.id, aux.getID());
			notifyAllObservers(notification);
			System.out.println("S-a moficat" + aux.getNume() + " " + aux.getPret());
		}
	}

	public void removeItem(Item item){
		//System.out.println("AICI");
		if (remove(item)){
			//System.out.println("AICI");
			Notification notification = new Notification("remove", id, item.getID());
			notifyAllObservers(notification);
			System.out.println("S-a sters elementul " + item.getNume());
		}
	}
	
	public ArrayList<Item> getItems(){

		ArrayList<Item> retItems = new ArrayList<>();
		it.current = firstElem;
		Item aux;
		while (it.current != null){
			aux = (Item)it.current.value;
			retItems.add(aux);
			it.current = (Node)it.next();
		}
		return retItems;
	}

	public void addObserver(Customer newObserver){
		observers.add(newObserver);   //(METODA SE APELEAZA CAND UN CLIENT ADAUGA UN PRODUS DIN DEPARTAMENT LA WISHLIST)
	}

	public void removeObserver(Customer deleteObserver){
		if (observers.size() != 0){
			int index = observers.indexOf(deleteObserver);
			if (index != -1)
				observers.remove(index);
		}
	}

	public void notifyAllObservers(Notification notification){
		//System.out.println(notification.idItem + " " + notification.idDepartment);
		ArrayList<Customer> aux = new ArrayList<>();
		Iterator it = observers.iterator();
		while (it.hasNext()){
			aux.add((Customer)it.next());
		}
		if (aux != null) {
			Iterator iter = aux.iterator();
			Customer auxCus;
			/*if (notification.idItem == 82)
				System.out.println(observers.toString());*/
			while (iter.hasNext()) {
				auxCus = (Customer)iter.next();
				/*if (notification.idItem == 82)
					System.out.println(auxCus.getNume());*/
				/*if (notification.idItem == 82)
					System.out.println(aux.getNume());*/
				//System.out.println(notification.type.toString() + " " + notification.idItem + " " + aux.getNume());
				auxCus.update(notification);
			}
		}
	}
	
	public abstract void accept(ShoppingCart shopCart);

}
