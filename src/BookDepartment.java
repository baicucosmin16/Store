import java.util.ArrayList;

public class BookDepartment extends Department{

    private static BookDepartment instance = null;

    private BookDepartment(String nume){
        super();
        setNume(nume);
    }

    private BookDepartment(){

    }

    public void accept(ShoppingCart shopCart) {
        shopCart.visit(this);
    }

    public static BookDepartment getInstance(String nume){
        if (instance == null){
            instance = new BookDepartment(nume);
            return instance;
        }
        return instance;
    }

    public static BookDepartment getInstance(){
        if (instance == null){
            instance = new BookDepartment();
            return instance;
        }
        return instance;
    }
    
    public String toString(){
    	return "BookDepartment";
    }
}
