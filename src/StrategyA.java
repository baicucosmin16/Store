

public class StrategyA implements Strategy{

    public StrategyA(){

    }

    public Item execute(WishList wishList){

        return wishList.getItemMinPrice();
    }
}
