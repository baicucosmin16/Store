
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class CustomerPanelMethods {

	public static Store store = Store.getInstance();
	public static DecimalFormat df = new DecimalFormat("0.00");
	
	
	public static ArrayList<Item> getShopCart(String customer){
		
		ArrayList<Customer> customers = store.getCustomers();
		Customer aux = null;
		Customer theOne = null;
		Iterator it = customers.iterator();
		while(it.hasNext()){
			aux = (Customer)it.next();
			if (aux.getNume().equals(customer))
				theOne = aux;
		}
		
		if (theOne != null)
			return theOne.shopCart.getItems();
		return null;
	}
	
public static ArrayList<Item> getWishList(String customer){
		
		ArrayList<Customer> customers = store.getCustomers();
		Customer aux = null;
		Customer theOne = null;
		Iterator it = customers.iterator();
		while(it.hasNext()){
			aux = (Customer)it.next();
			if (aux.getNume().equals(customer))
				theOne = aux;
		}
		
		if (theOne != null)
			return theOne.wishList.getItems();
		return null;
	}
	
	
	public static String getBuget(String customer){
		
		
		
		ArrayList<Customer> customers = store.getCustomers();
		Customer aux = null;
		Customer theOne = null;
		Iterator it = customers.iterator();
		while(it.hasNext()){
			aux = (Customer)it.next();
			if (aux.getNume().equals(customer))
				theOne = aux;
		}
		
		if (theOne != null){
			return df.format(theOne.shopCart.buget);
		}
		return "";
	}
	
	
	public static String getTotal(String customer){
		
		ArrayList<Customer> customers = store.getCustomers();
		Customer aux = null;
		Customer theOne = null;
		Iterator it = customers.iterator();
		while(it.hasNext()){
			aux = (Customer)it.next();
			if (aux.getNume().equals(customer))
				theOne = aux;
		}
		
		if (theOne != null){
			return df.format(theOne.shopCart.getTotalPrice());
		}
		return "";
	}
	
	public static String getNoProducts(String customer){
		ArrayList<Customer> customers = store.getCustomers();
		Customer aux = null;
		Customer theOne = null;
		Iterator it = customers.iterator();
		while(it.hasNext()){
			aux = (Customer)it.next();
			if (aux.getNume().equals(customer))
				theOne = aux;
		}
		
		if (theOne != null){
			return String.valueOf(theOne.shopCart.noItems());
		}
		return "";
		
	}
	
	public static String getRecomandation(String customer){
		ArrayList<Customer> customers = store.getCustomers();
		Customer aux = null;
		Item item = null;
		Customer theOne = null;
		Iterator it = customers.iterator();
		while(it.hasNext()){
			aux = (Customer)it.next();
			if (aux.getNume().equals(customer))
				theOne = aux;
		}
		
		if (theOne != null){
			item = theOne.wishList.strategy.execute(theOne.wishList);
		}
		if (item != null){
			return item.toString();
		}
		return "No recomandation";
	}
	
	public static Department getDepartbyName(String depart){
		ArrayList<Department> departments = store.getDepartments();
		Department auxDep;
		Iterator it = departments.iterator();
		while(it.hasNext()){
            auxDep = (Department)it.next();
            if (auxDep.getNume().equals(depart)){
                return auxDep;
            }
        }
		return null;
	}
	
	public static Customer getCustomer(String customer){
		
		ArrayList<Customer> customers = store.getCustomers();
		Iterator it = customers.iterator();
		Customer aux;
		while(it.hasNext()){
			aux = (Customer)it.next();
			if (aux.getNume().equals(customer))
				return aux;
		}
		return null;
	}
	
	public static boolean addProduct(int id, String name, double pret, String department, String customer){

		Department departament = getDepartbyName(department);
		Item it = new Item();
		it.setID(id);
		it.setNume(name);
		it.setPret(pret);
		it.setDepartment(departament);
		Customer theOne = getCustomer(customer);
		if (theOne.addItemShopCart(it)){
			return true;
		}
		return false;
	}
	
	public static boolean removeProduct(int id, String name, double pret, String department, String customer){

		Department departament = getDepartbyName(department);
		Item it = new Item();
		it.setID(id);
		it.setNume(name);
		it.setPret(pret);
		it.setDepartment(departament);
		Customer theOne = getCustomer(customer);
		if(theOne.removeItemShopCart(it)){
			theOne.shopCart.buget += it.getPret();
			return true;
		}
		return false;
	}
	
	
	public static boolean addProductWish(int id, String name, double pret, String department, String customer){

		Department departament = getDepartbyName(department);
		Item it = new Item();
		it.setID(id);
		it.setNume(name);
		it.setPret(pret);
		it.setDepartment(departament);
		Customer theOne = getCustomer(customer);
		return theOne.addItemWishList(it);
	}
	
	public static boolean removeProductWish(int id, String name, double pret, String department, String customer){

		Department departament = getDepartbyName(department);
		Item it = new Item();
		it.setID(id);
		it.setNume(name);
		it.setPret(pret);
		it.setDepartment(departament);
		Customer theOne = getCustomer(customer);
		return theOne.removeItemWishList(it);
	}
	
	
	public static boolean setNewShopCart(String customer, String buget){
		
		Customer theOne = getCustomer(customer);
		double bug = Double.parseDouble(buget);
		theOne.shopCart = new ShoppingCart(bug);
		return true;
	}
	
	public static String addRecomandation(String item, String customer){
		if (!item.equals("No recomandation")){
			String[] aux = item.split(";");
			String nume = aux[0];
			int id = Integer.parseInt(aux[1]);
			double pret = Double.parseDouble(aux[2]);
			String department = aux[3];
			if (addProduct(id, nume, pret, department, customer)){
				removeProductWish(id, nume, pret, department, customer);
				return item;
			}
			return "";
		}
		else{
			return "";
		}
	}
	
}
