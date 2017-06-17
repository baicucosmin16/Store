

public class VideoDepartment extends Department{

    private static VideoDepartment instance = null;

    private VideoDepartment(String nume){
        super();
        setNume(nume);
    }

    private VideoDepartment(){

    }

    public void accept(ShoppingCart shopCart) {
        shopCart.visit(this);
    }

    public static VideoDepartment getInstance(String nume){
        if (instance == null){
            instance = new VideoDepartment(nume);
            return instance;
        }
        return instance;
    }

    public static VideoDepartment getInstance(){
        if (instance == null){
            instance = new VideoDepartment();
            return instance;
        }
        return instance;
    }
    
    public String toString(){
    	return "VideoDepartment";
    }
}
