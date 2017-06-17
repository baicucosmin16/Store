
public interface Subject {

	public void addObserver(Customer o);
	public void removeObserver(Customer o);
	public void notifyAllObservers(Notification notification);
	
}
