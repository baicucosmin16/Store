import java.util.Date;

public class Notification {

    public enum notificationType {ADD, REMOVE, MODIFY}

    Date date;
    int idDepartment;
    int idItem;
    notificationType type;

    public Notification(String typeNot, int idDepartment, int idItem){
        date = new Date();
        this.idDepartment = idDepartment;
        this.idItem = idItem;
        switch (typeNot){
            case "add":
                type = notificationType.ADD;
                break;
            case "remove":
                type = notificationType.REMOVE;
                break;
            case "modify":
                type = notificationType.MODIFY;
                break;
        }
    }

    public Notification clone(Notification not){

        Notification aux = new Notification(not.toString().toLowerCase(), not.idDepartment, not.idItem);
        aux.date = new Date();
        aux.date = not.date;
        return aux;
    }

    public String toString(){
        return type.toString() + ";" + Integer.toString(idItem) + ";" + Integer.toString(idDepartment);
    }

}
