
public class SoftwareDepartment extends Department{

    private static SoftwareDepartment instance = null;

    private SoftwareDepartment(String nume){
        super();
        setNume(nume);
    }

    private SoftwareDepartment(){

    }

    public void accept(ShoppingCart shopCart) {
        shopCart.visit(this);
    }

    public static SoftwareDepartment getInstance(String nume){
        if (instance == null){
            instance = new SoftwareDepartment(nume);
            return instance;
        }
        return instance;
    }

    public static SoftwareDepartment getInstance(){
        if (instance == null){
            instance = new SoftwareDepartment();
            return instance;
        }
        return instance;
    }
    
    public String toString(){
    	return "SoftwareDepartment";
    }
}
