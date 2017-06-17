

public class StrategyC implements Strategy{

    public StrategyC(){

    }

    public Item execute(WishList wishList){

        int id;

        try{
            id = (int)wishList.order.get(0);
            wishList.order.remove(0);
            System.out.println(id);
            return wishList.contains(id);
        }
        catch (IndexOutOfBoundsException e){

        }
        return null;
    }
}
