
import java.text.DecimalFormat;
import java.util.Date;

public class Item{
	
	private String nume;
	private int ID;
	private double pret;
	private Department departament;
	//private Date date;
	
	public void setNume(String nume){
		this.nume = nume;
	}
	
	public String getNume(){
		return nume;
	}
	
	public void setID(int ID){
		this.ID = ID;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setPret(double pret){
		//this.pret = Math.round(pret * 100.0) / 100.0;
		this.pret = pret;
	}
	
	public double getPret(){
		return pret;
	}
	
	public void setDepartment(Department department){
		this.departament = department;
	}
	
	public Department getDepartment(){
		return departament;
	}

	/*public void setDate(){
		date = new Date();
	}

	public Date getDate(){
		return date;
	}*/

	public Item clona(Item model){
		Item aux = new Item();
		aux.setNume(model.getNume());
		aux.setID(model.getID());
		aux.setPret(model.getPret());
		aux.setDepartment(model.getDepartment());
		return aux;
	}

	public String toString(){
		DecimalFormat f = new DecimalFormat("0.00");
		return nume + ";" + ID + ";"  +  f.format(Math.round(pret * 100.00) / 100.00) + ";" +  departament.toString();
	}
}
