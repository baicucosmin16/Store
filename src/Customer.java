import java.util.ArrayList;
import java.util.Iterator;

public class Customer implements Observer{

	private String nume;
	public ShoppingCart shopCart;
	public WishList wishList;
	public ArrayList<Notification> notificari;
	
	public Customer(String nume, double buget, String strategy){
		this.nume = nume;
		Store store = Store.getInstance();
		wishList = new WishList(strategy);
		shopCart = store.getShoppingCart(buget);
		notificari = new ArrayList<>();
	}

	public String getNume(){
		return nume;
	}

	public int notSize(){
		return notificari.size();
	}

	public boolean addItemShopCart(Item item){
		boolean aux = shopCart.add(item);
		return aux;
	}

	public boolean removeItemShopCart(Item item){
		boolean aux = shopCart.remove(item);
		return aux;
	}

	public boolean addItemWishList(Item item){
		if (wishList.add(item)){
			Department department = item.getDepartment();
			if (!department.observers.contains(this)){
				department.observers.add(this);
			}
			return true;
		}
		return false;
	}

	public boolean removeItemWishList(Item item){
		if (wishList.remove(item)){
			Department aux = item.getDepartment();
			if (!wishList.containFromDepart(aux.getID())){
				aux.observers.remove(this);
			}
			return true;
		}
		return false;
	}

	@Override
	public void update(Notification notification) {
		/*if (notification.idItem == 82 && getNume().equals("Andreea")) {
			System.out.println("SE ADAUGA NOTIFICAREA");
			Store store = Store.getInstance();
			Department aux = store.getDepartment(notification.idDepartment);
			System.out.println(aux.observers.toString());
		}*/
		notificari.add(notification);
		Item auxFirst, auxSecond;
		int idItem = notification.idItem;
		double initialBuget = shopCart.buget;
		if (notification.type.toString().equals("MODIFY")){
			if ((auxFirst = shopCart.contains(idItem)) != null){
				double initialPriceItem = auxFirst.getPret();
				shopCart.buget += auxFirst.getPret();
				Store store = Store.getInstance();
				Department department = store.getDepartment(notification.idDepartment);
				auxSecond = department.contains(idItem);
				auxFirst.setPret(auxSecond.getPret());
				shopCart.buget -= auxFirst.getPret();
				if (shopCart.buget < 0)
					shopCart.remove(auxFirst);
					shopCart.buget = initialBuget + initialPriceItem;
			}

			if ((auxFirst = wishList.contains(idItem)) != null){
				Store store = Store.getInstance();
				Department department = store.getDepartment(notification.idDepartment);
				auxSecond = department.contains(idItem);
				auxFirst.setPret(auxSecond.getPret());
			}
		}

		if (notification.type.toString().equals("REMOVE")){
			if ((auxFirst = shopCart.contains(idItem)) != null){
				//shopCart.remove(auxFirst);
				removeItemShopCart(auxFirst);
			}

			if ((auxFirst = wishList.contains(idItem)) != null){
				//wishList.remove(auxFirst);
				removeItemWishList(auxFirst);
			}
		}

	}

	public String toString(){
		return getNume();
	}

}
