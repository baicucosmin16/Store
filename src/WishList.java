
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

public class WishList extends ItemList{

    Strategy strategy;


    public WishList(String strategie){
        switch (strategie){
            case "A":
                strategy = new StrategyA();
                break;
            case "B":
                strategy = new StrategyB();
                break;
            case "C":
                strategy = new StrategyC();
                break;
        }
        order = new ArrayList<>();

    }

    public boolean compare(Node newNode, Node current){

        Item newItem = (Item)newNode.value;
        Item currentItem = (Item)current.value;
        if (newItem.getNume().compareTo(currentItem.getNume()) > 0){
            return true;
        }
        else if (newItem.getNume().compareTo(currentItem.getNume()) > 0){
            if (newItem.getPret() > currentItem.getPret())
                return true;
        }
        return false;
    }

    public boolean add(Item elementOrig){   //METODA VA FI ABSTRACTA
        Item element = elementOrig.clona(elementOrig);
        //element.setDate();
        Node newNode = new Node(element);
        it.current = firstElem;
        if (firstElem == null){
            newNode.next = null;
            newNode.prev = null;
            firstElem = newNode;
            lastElem = firstElem;
            order.add(0, element.getID());
            return true;
        }
        else{
            while (it.current != null && compare(newNode, it.current)){
                it.current = (Node)it.next();
                it.currentInd++;
            }
            if (it.current == firstElem){
                newNode.next = firstElem;
                newNode.prev = null;
                firstElem.prev = newNode;
                firstElem = newNode;
                order.add(0, element.getID());
                return true;
            }
            else if (it.current == null){
                newNode.prev = lastElem;
                newNode.next = null;
                lastElem.next = newNode;
                lastElem = newNode;
                order.add(0, element.getID());
                return true;
            }
            else{
                Node aux = (Node)it.current.prev;
                newNode.next = it.current;
                newNode.prev = aux;
                it.current.prev = newNode;
                aux.next = newNode;
                order.add(0, element.getID());
                return true;
            }
        }

    }

    public boolean addAll(Collection<? extends Item> c){

        Iterator itr = c.iterator();
        while (itr.hasNext()){
            if (!add((Item)itr.next()))
                return false;
        }
        return true;
    }


    public void executeStrategy(){

        strategy.execute(this);
    }

   /* public void afisare(){


        it.current = firstElem;
        while (it.current != null){
            Item a = (Item)it.current.value;
            System.out.println(a.getNume() + " " + a.getPret());
            it.current = (Node)it.next();
        }
    }*/

    /*public Item getMinDate(){
        Item retItem, aux;
        if (firstElem == null){
            return null;
        }
        else{
            retItem = (Item)firstElem.value;
            it.current = firstElem.next;
            while(it.current != null){
                aux = (Item)it.current.value;
                if (aux.getDate().compareTo(retItem.getDate()) > 0){
                    retItem = aux;
                }
                it.current = (Node)it.next();
            }
            return retItem;
        }
    }*/

}
