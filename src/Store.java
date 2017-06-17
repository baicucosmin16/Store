import java.util.ArrayList;
import java.util.Iterator;

public class Store {
	
	private static Store instance = null;
	private String nume;
	private ArrayList<Department> departamente;
	public ArrayList<Customer> clienti;
	
	private Store(String nume){
		this.nume = nume;
		departamente = new ArrayList<>();
		clienti = new ArrayList<>();
	}
	
	private Store(){
		departamente = new ArrayList<>();
		clienti = new ArrayList<>();
	}
	
	public void setNume(String nume){
		this.nume = nume;
	}
	
	public String getNume(){
		return nume;
	}
	
	public void enter(Customer newCustomer){
		clienti.add(newCustomer);
	}
	
	public void exit(Customer deleteCustomer){
		if (clienti.size() != 0){
			int index = clienti.indexOf(deleteCustomer);
			if (index != -1)
				clienti.remove(index);
		}
	}
	
	public ShoppingCart getShoppingCart(Double suma){
		//TO DO (ShoppingCart cu suma)
		return new ShoppingCart(suma);
	}
	
	public ArrayList<Customer> getCustomers(){
		return clienti;
	}
	
	public ArrayList<Department> getDepartments(){
		return departamente;
	}
	
	public void addDepartment(Department newDepartment){
		departamente.add(newDepartment);

	}
	
	public Department getDepartment(Integer id){
		Department aux = null;
		Iterator it = departamente.iterator();
		while(it.hasNext()){
			aux = (Department)it.next();
			if (aux.id == (int)id){
				return aux;
			}
		}
		
		return aux;
	}
	
	public static Store getInstance(String nume){
		if (instance == null){
			instance = new Store(nume);
		}
		return instance;
	}
	
	public static Store getInstance(){
		if (instance == null){
			instance = new Store();
		}
		return instance;
	}
}
