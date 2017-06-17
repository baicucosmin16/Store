
public class MusicDepartment extends Department{

    private static MusicDepartment instance = null;

    private MusicDepartment(String nume){
        super();
        setNume(nume);
    }

    private MusicDepartment(){

    }

    public void accept(ShoppingCart shopCart) {
        shopCart.visit(this);
    }

    public static MusicDepartment getInstance(String nume){
        if (instance == null){
            instance = new MusicDepartment(nume);
            return instance;
        }
        return instance;
    }

    public static MusicDepartment getInstance(){
        if (instance == null){
            instance = new MusicDepartment();
            return instance;
        }
        return instance;
    }
    
    public String toString(){
    	return "MusicDepartment";
    }
}
