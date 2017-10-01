
import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ShoppingCart extends ItemList implements Visitor{

    double buget;

    public ShoppingCart(double buget){
        this.buget = buget;
        order = new ArrayList<>();
    }

    public boolean add(Item elementOrig){
        Item element = elementOrig.clona(elementOrig);
        Node newNode = new Node(element);
        it.current = firstElem;
        it.currentInd = 0;
        if (element.getPret() <= buget) {
            if (firstElem == null) {
                newNode.next = null;
                newNode.prev = null;
                firstElem = newNode;
                lastElem = newNode;
                buget -= element.getPret();
                return true;
            } else {
                while (it.current != null && it.current.compare(newNode, it.current) > 0) {
                    it.current = (Node) it.next();
                    it.currentInd++;
                }
                if (it.current == firstElem) {
                    newNode.next = firstElem;
                    newNode.prev = null;
                    firstElem.prev = newNode;
                    firstElem = newNode;
                    buget -= element.getPret();
                    return true;
                } else if (it.current == null) {
                    newNode.prev = lastElem;
                    newNode.next = null;
                    lastElem.next = newNode;
                    lastElem = newNode;
                    buget -= element.getPret();
                    return true;
                } else {
                    Node aux = (Node) it.current.prev;
                    newNode.next = it.current;
                    newNode.prev = aux;
                    it.current.prev = newNode;
                    aux.next = newNode;
                    buget -= element.getPret();
                    return true;
                }

            }
        }
        return false;
    }

    public boolean addAll(Collection<? extends Item> c){

        Iterator itr = c.iterator();
        while (itr.hasNext()){
            if (!add((Item)itr.next()))
                return false;
        }
        return true;
    }



    @Override
    public void visit(BookDepartment bookDepartment) {
        buget += getTotalPrice();
        Item aux;
        Node next;
        it.current = firstElem;
        while(it.current != null){
            aux = (Item)it.current.value;
            if (aux.getDepartment().getID() == bookDepartment.getID()){
                //aux.setPret(Math.round((aux.getPret() - 0.1 * aux.getPret())*100.0)/100.0);
                aux.setPret(aux.getPret() - 0.1 * aux.getPret());
                Item newitem = aux.clona(aux);
                next = (Node)it.next();
                remove(aux);
                add(newitem);
                it.current = next;

            }
            else {
                it.current = (Node) it.next();
            }
        }
        buget -= getTotalPrice();

    }

    @Override
    public void visit(MusicDepartment musicDepartment) {
        Item aux;
        it.current = firstElem;
        double sum = 0;
        while(it.current != null){
            aux = (Item)it.current.value;
            if (aux.getDepartment().getID() == musicDepartment.getID())
                sum += aux.getPret();
            it.current = (Node)it.next();
        }
        buget += 0.1 * sum;
    }

    @Override
    public void visit(SoftwareDepartment softwareDepartment) {
        Item aux = (Item)softwareDepartment.firstElem.value;
        Node next;
        it.current = firstElem;
        if (buget < aux.getPret()){
            buget += getTotalPrice();
            while(it.current != null){
                aux = (Item)it.current.value;
                if (aux.getDepartment().getID() == softwareDepartment.getID()){
                    aux.setPret(aux.getPret() - 0.2 * aux.getPret());
                    Item newitem = aux.clona(aux);
                    next = (Node)it.next();
                    remove(aux);
                    add(newitem);
                    it.current = next;
                }
                else {
                    it.current = (Node)it.next();
                }
            }
            buget -= getTotalPrice();
        }

    }

    @Override
    public void visit(VideoDepartment videoDepartment) {
        Node next;
        buget += getTotalPrice();
        double sum = 0;
        Item aux;
        it.current = firstElem;
        while(it.current != null){
            aux = (Item)it.current.value;
            if(aux.getDepartment().getID() == videoDepartment.getID())
                sum += aux.getPret();
            it.current = (Node)it.next();
        }
        aux = (Item)videoDepartment.lastElem.value;
        if (sum > aux.getPret()){
            it.current = firstElem;
            while(it.current != null){
                aux = (Item)it.current.value;
                if(aux.getDepartment().getID() == videoDepartment.getID()) {
                    aux.setPret(aux.getPret() - 0.15 * aux.getPret());
                    Item newitem = aux.clona(aux);
                    next = (Node)it.next();
                    remove(aux);
                    add(newitem);
                    it.current = next;
                }
                else {
                    it.current = (Node) it.next();
                }
            }
        }
        it.current = firstElem;
        buget -= getTotalPrice();
        buget += 0.5 * sum;


    }
}
