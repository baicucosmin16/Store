
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Factory {

    public Factory(){
        populateStore();
        addCustomersInStore();
        Output output = new Output();
    }

    public void populateStore(){

        Store store = Store.getInstance();
        FileReader storeFile = null;
        BufferedReader storeBr = null;
        String str;
        try{
            storeFile = new FileReader("store.txt");
            storeBr = new BufferedReader(storeFile);
            str = storeBr.readLine();
            int nrItems;
            store.setNume(str);
            while((str = storeBr.readLine()) != null){
                String[] department = str.split(";");
                switch (department[0]){
                    case "BookDepartment":
                        BookDepartment bookDepartment = BookDepartment.getInstance();
                        bookDepartment.id = Integer.parseInt(department[1]);
                        bookDepartment.setNume(department[0]);
                        store.addDepartment(bookDepartment);
                        nrItems = Integer.parseInt(storeBr.readLine());
                        for (int i = 0; i < nrItems; i++) {
                            str = storeBr.readLine();
                            addItem(str, bookDepartment);
                        }
                        break;
                    case "MusicDepartment":
                        MusicDepartment musicDepartment = MusicDepartment.getInstance();
                        musicDepartment.id = Integer.parseInt(department[1]);
                        musicDepartment.setNume(department[0]);
                        store.addDepartment(musicDepartment);
                        nrItems = Integer.parseInt(storeBr.readLine());
                        for (int i = 0; i < nrItems; i++) {
                            str = storeBr.readLine();
                            addItem(str, musicDepartment);
                        }
                        break;
                    case "VideoDepartment":
                        VideoDepartment videoDepartment = VideoDepartment.getInstance();
                        videoDepartment.id = Integer.parseInt(department[1]);
                        videoDepartment.setNume(department[0]);
                        store.addDepartment(videoDepartment);
                        nrItems = Integer.parseInt(storeBr.readLine());
                        for (int i = 0; i < nrItems; i++) {
                            str = storeBr.readLine();
                            addItem(str, videoDepartment);
                        }
                        break;
                    case "SoftwareDepartment":
                        SoftwareDepartment softwareDepartment = SoftwareDepartment.getInstance();
                        softwareDepartment.id = Integer.parseInt(department[1]);
                        softwareDepartment.setNume(department[0]);
                        store.addDepartment(softwareDepartment);
                        nrItems = Integer.parseInt(storeBr.readLine());
                        for (int i = 0; i < nrItems; i++) {
                            str = storeBr.readLine();
                            addItem(str, softwareDepartment);
                        }
                        break;
                }
            }
            //System.out.println(store.getDepartments().size());
            /*Iterator it = store.getDepartments().iterator();
            while (it.hasNext()){
                Department aux = (Department)it.next();
                System.out.println(aux.getNume());
                System.out.println(aux.getItems().toString());
            }*/

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
            if (storeFile != null){
                try {
                    storeFile.close();
                    storeBr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void addItem(String str, Department department){
        String[] item = str.split(";");
        Item newItem = new Item();
        newItem.setDepartment(department);
        newItem.setNume(item[0]);
        newItem.setID(Integer.parseInt(item[1]));
        newItem.setPret(Double.parseDouble(item[2]));
        department.add(newItem);
    }


    public void addCustomersInStore(){

        Store store = Store.getInstance();
        FileReader customerFile = null;
        BufferedReader customerBr = null;
        String str;
        try{
            customerFile = new FileReader("customers.txt");
            customerBr = new BufferedReader(customerFile);
            while((str = customerBr.readLine()) != null){
                int nrItems = Integer.parseInt(str);
                for (int i = 0; i < nrItems; i++){
                    str = customerBr.readLine();
                    //System.out.println(str);
                    String[] customer = str.split(";");
                    Customer newCustomer = new Customer(customer[0], Double.parseDouble(customer[1]), customer[2]);
                    store.enter(newCustomer);
                }
            }
            //System.out.println(store.getCustomers().toString());


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
            if (customerFile != null){
                try {
                    customerFile.close();
                    customerBr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
