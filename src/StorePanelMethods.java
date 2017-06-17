
import java.util.ArrayList;
import java.util.Iterator;

public class StorePanelMethods {

	static Store store = Store.getInstance();
	
	public Item getItemById(int id){

        ArrayList<Department> departments = store.getDepartments();
        Iterator it = departments.iterator();
        Department auxDep;
        Item auxItem;
        while(it.hasNext()){
            auxDep = (Department)it.next();
            if ((auxItem = auxDep.contains(id)) != null){
                return auxItem;
            }
        }
        return null;
    }
	
	public static int getIdDepartbyName(String depart){
		ArrayList<Department> departments = store.getDepartments();
		Department auxDep;
		Iterator it = departments.iterator();
		while(it.hasNext()){
            auxDep = (Department)it.next();
            if (auxDep.getNume().equals(depart)){
                return auxDep.getID();
            }
        }
		return -1;
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
	
	public static void modifyProduct(String depart, int idItem, double pret, String name){
	
		if (getIdDepartbyName(depart) != -1){
	        Department department = store.getDepartment(getIdDepartbyName(depart));
	        if (department != null){
	            department.modifyItem(idItem, pret, name);
	        }
		}
    }
	
	public static boolean addProduct(int id, String name, double pret, String department){

		Department departament = getDepartbyName(department);
		Item it = new Item();
		it.setID(id);
		it.setNume(name);
		it.setPret(pret);
		it.setDepartment(departament);
		if (departament.addItem(it, 0)){
			return true;
		}
		return false;
	}
	
	public static void removeProduct(int id, String name, double pret, String department){

		Department departament = getDepartbyName(department);
		Item it = new Item();
		it.setID(id);
		it.setNume(name);
		it.setPret(pret);
		it.setDepartment(departament);
		departament.removeItem(it);
	}
}
