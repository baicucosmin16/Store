

public class StrategyB implements Strategy{

    public StrategyB(){

    }

    public Item execute(WishList wishList){

        if(wishList.firstElem == null)
            return null;
        return wishList.firstElem.value;
    }
}
