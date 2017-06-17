
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;

public class Output {

    Store store;

    public Output(){

        store = Store.getInstance();
        runEvents();
    }

    public void runEvents(){
        DecimalFormat df = new DecimalFormat("0.00");
        FileReader eventsFile = null;
        FileWriter outputFile = null;
        BufferedReader eventsBr = null;
        BufferedWriter outputBr = null;
        try{
            eventsFile = new FileReader("events.txt");
            eventsBr = new BufferedReader(eventsFile);
            outputFile = new FileWriter("output.txt");
            outputBr = new BufferedWriter(outputFile);
            String str;
            int nrEvents;
            while((str = eventsBr.readLine()) != null){
                nrEvents = Integer.parseInt(str);
                for (int i = 0; i < nrEvents; i++){
                    str = eventsBr.readLine();
                    //System.out.println(str);
                    String[] event = str.split(";");
                    switch (event[0]){
                        case "addItem":
                            addItem(Integer.parseInt(event[1]),event[2], event[3]);
                            //Department aux = store.getDepartment(0);
                            //System.out.println(aux.observers.toString());
                            break;
                        case "delItem":
                            delItem(Integer.parseInt(event[1]), event[2], event[3]);
                            break;
                        case "addProduct":
                            addProduct(Integer.parseInt(event[1]), Integer.parseInt(event[2]), Double.parseDouble(event[3]), event[4]);
                            break;
                        case "modifyProduct":
                            modifyProduct(Integer.parseInt(event[1]), Integer.parseInt(event[2]), Double.parseDouble(event[3]));
                            break;
                        case "delProduct":
                            delProduct(Integer.parseInt(event[1]));
                            break;
                        case "getItem":
                            Item item = getItem(event[1]);
                            outputFile.write(item.toString());
                            outputFile.write("\n");
                            break;
                        case "getItems":
                            ArrayList<Item> items = getItems(event[1], event[2]);
                            outputFile.write(items.toString());
                            outputFile.write("\n");
                            break;
                        case "getTotal":
                            double total = getTotal(event[1], event[2]);
                            total = Math.round(total * 100.0) / 100.0;
                            outputFile.write(df.format(total));
                            outputFile.write("\n");
                            break;
                        case "accept":
                            accept(Integer.parseInt(event[1]), event[2]);
                            break;
                        case "getObservers":
                            ArrayList<Customer> observers = getObservers(Integer.parseInt(event[1]));
                            outputFile.write(observers.toString());
                            outputFile.write("\n");
                            break;
                        case "getNotifications":
                            ArrayList<Notification> notifications = getNotifications(event[1]);
                            outputFile.write(notifications.toString());
                            outputFile.write("\n");
                            //System.out.println("HERE");
                            break;
                    }
                }
            }

        }
        catch (FileNotFoundException e){
            System.out.println(e.getStackTrace());
        }
        catch (IOException e){
            System.out.println(e.getStackTrace());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if (eventsFile != null){
                try {
                    eventsFile.close();
                    eventsBr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputFile != null){
                try {
                    outputFile.close();
                    //outputBr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public Customer getCustomerByName(String name){

        Iterator it = store.getCustomers().iterator();
        Customer aux;
        while(it.hasNext()){
            aux = (Customer)it.next();
            if (aux.getNume().equals(name))
                return aux;
        }
        return null;
    }

    public Item getItemById(int id){

        ArrayList<Department> departments = store.getDepartments();
        Iterator it = departments.iterator();
        Department auxDep;
        Item auxItem;
        while(it.hasNext()){
            auxDep = (Department)it.next();
            if ((auxItem = auxDep.contains(id)) != null){
                return auxItem;
            }
        }
        return null;
    }

    public void addItem(int id, String lista, String customerName){

        Customer customer = getCustomerByName(customerName);
        Item item = getItemById(id);
        if (customer != null){
            if (item != null){
                switch (lista){
                    case "ShoppingCart":
                        customer.addItemShopCart(item);
                        break;
                    case "WishList":
                        customer.addItemWishList(item);
                        break;
                }
            }
        }

    }

    public void delItem(int id, String lista, String customerName){
        Customer customer = getCustomerByName(customerName);
        Item item = getItemById(id);
        if (customer != null){
            if (item != null){
                switch (lista){
                    case "ShoppingCart":
                        customer.removeItemShopCart(item);
                        break;
                    case "WishList":
                        customer.removeItemWishList(item);
                        break;
                }
            }
        }
    }

    public void addProduct(int idDep, int idItem, double pret, String name){
        Department department = store.getDepartment(idDep);
        Item newItem = new Item();
        newItem.setID(idItem);
        newItem.setPret(pret);
        newItem.setDepartment(department);
        newItem.setNume(name);
        department.addItem(newItem);

    }

    public void modifyProduct(int idDep, int idItem, double pret){
        Department department = store.getDepartment(idDep);
        if (department != null){
            department.modifyItem(idItem, pret);
        }
    }

    public void delProduct(int idItem){
        Item auxItem = getItemById(idItem);
        if(auxItem != null){
            ArrayList<Department> departments = store.getDepartments();
            Iterator it = departments.iterator();
            while(it.hasNext()){
                Department auxDep = (Department)it.next();

                if (auxDep.contains(auxItem)){
                    auxDep.removeItem(auxItem);
                    return;
                }
            }
        }
    }
    public Item getItem(String customerName){

        Customer customer = getCustomerByName(customerName);
        if (customer != null){
            Item item = customer.wishList.strategy.execute(customer.wishList);
            if (customer.addItemShopCart(item)){
                customer.removeItemWishList(item);
            }
            return item;
        }
        return null;
    }

    public ArrayList<Item> getItems(String lista, String customerName){

        Item item = null;
        Customer customer = getCustomerByName(customerName);
        switch (lista){
            case "ShoppingCart":
                Item auxItem;
                ArrayList<Item> aux = customer.shopCart.getItems();
                /*if (customer.getNume().equals("Marian"))
                    System.out.println(customer.shopCart.getItems().toString());*/
                ShoppingCart auxShopCart = new ShoppingCart(customer.shopCart.buget + 100);
                customer.shopCart.it.current = customer.shopCart.firstElem;
                while (customer.shopCart.it.current != null){
                    auxItem = (Item)customer.shopCart.it.current.value;
                    auxShopCart.add(auxItem.clona(auxItem));
                    customer.shopCart.it.current = (ItemList.Node)customer.shopCart.it.next();
                }

                /*if (customer.getNume().equals("Marian"))
                    System.out.println(auxShopCart.getItems().toString());*/
                //customer.shopCart = auxShopCart;
                return auxShopCart.getItems();

            case "WishList":
                return customer.wishList.getItems();
        }
        return null;
    }

    public double getTotal(String lista, String customerName){
        Customer customer = getCustomerByName(customerName);
        if (customer != null){
            switch (lista){
                case "ShoppingCart":
                    return customer.shopCart.getTotalPrice();
                case "WishList":
                    return customer.wishList.getTotalPrice();
            }
        }
        return 0;
    }

    public void accept(int idDep, String customerName){
        Department department = store.getDepartment(idDep);
        Customer customer = getCustomerByName(customerName);
        department.accept(customer.shopCart);
    }

    public ArrayList<Customer> getObservers(int idDep){

        Department department = store.getDepartment(idDep);
        //System.out.println(department.observers.toString());
        ArrayList<Customer> aux = department.observers;
        //System.out.println("AICI");
        return aux;
    }

    public ArrayList<Notification> getNotifications(String customerName){
        Customer customer = getCustomerByName(customerName);
        //System.out.println("NUMAR NOTIFICARI " + customer.notSize());
        return customer.notificari;
    }
}
