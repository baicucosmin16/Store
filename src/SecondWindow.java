
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SecondWindow {

	public JFrame home;
	public static JPanel choose;
	//private JPanel store;
	public static JPanel customer;
	public StorePanel storePanel;
	public CustomerPanel customerPanel;
	
	public SecondWindow(){
		home = new JFrame();
		home.setTitle("Shop Online");
		//home.setResizable(false);
		home.setBounds(100, 100, 1000, 750);
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.setVisible(true);
		
		choose = new JPanel();
		setChoosePanel(choose);
		
		/*store = new JPanel();
		setStorePanel(store);*/
		storePanel = new StorePanel();
		
		customerPanel = new CustomerPanel();
		
		customer = new JPanel();
		setCustomerPanel(customer);
		
		choose.setVisible(false);
		storePanel.store.setVisible(false);
		customer.setVisible(false);
		customerPanel.custHome.setVisible(false);
		customerPanel.shopCart.setVisible(false);
		customerPanel.wishList.setVisible(false);
		
		home.getContentPane().add(storePanel.store, BorderLayout.CENTER);
		home.getContentPane().add(customer, BorderLayout.CENTER);
		home.getContentPane().add(choose, BorderLayout.CENTER);
		home.getContentPane().add(customerPanel.custHome, BorderLayout.CENTER);
		home.getContentPane().add(customerPanel.shopCart, BorderLayout.CENTER);
		home.getContentPane().add(customerPanel.wishList, BorderLayout.CENTER);
		
		
		choose.setVisible(true);
	}
	
	public void setChoosePanel(JPanel choose){
		choose.setForeground(new Color(0, 0, 0));
		choose.setBackground(new Color(0, 128, 128));
		choose.setBounds(0, 0, 1000, 750);
		choose.setLayout(null);
		
		JButton storeBtn = new JButton("STORE");
		storeBtn.setForeground(new Color(255, 255, 255));
		storeBtn.setFont(new Font("Tahoma", Font.BOLD, 30));
		storeBtn.setBackground(new Color(255, 69, 0));
		storeBtn.setBounds(158, 305, 270, 105);
		storeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				storePanel.store.setVisible(true);
				choose.setVisible(false);
			}
		});
		choose.add(storeBtn);
		
		JButton customerBtn = new JButton("CUSTOMER");
		customerBtn.setFont(new Font("Tahoma", Font.BOLD, 30));
		customerBtn.setForeground(new Color(255, 255, 255));
		customerBtn.setBackground(new Color(139, 69, 19));
		customerBtn.setBounds(540, 305, 270, 103);
		customerBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				customer.setVisible(true);
				choose.setVisible(false);
			}
		});
		choose.add(customerBtn);
		
		JLabel message = new JLabel("Welcome!");
		message.setForeground(new Color(255, 255, 255));
		message.setFont(new Font("Tahoma", Font.BOLD, 45));
		message.setBounds(367, 97, 251, 111);
		choose.add(message);
	}
	
	
	/*public void setStorePanel(JPanel store){
		store.setForeground(new Color(0, 0, 0));
		store.setBackground(new Color(0, 128, 128));
		store.setBounds(0, 0, 990, 700);
		store.setLayout(null);
		
		
	}*/
	
	
	public void setCustomerPanel(JPanel customer){
		customer.setForeground(new Color(0, 0, 0));
		customer.setBackground(new Color(0, 128, 128));
		customer.setBounds(0, 0, 1000, 750);
		customer.setLayout(null);
		
		JLabel message = new JLabel("Select the customer:");
		message.setForeground(new Color(255, 255, 255));
		message.setFont(new Font("Tahoma", Font.BOLD, 45));
		message.setBounds(255, 97, 700, 111);
		customer.add(message);
		
		Store store = Store.getInstance();
		ArrayList<Customer> customers = store.getCustomers();
		String[] cust = new String[customers.size() + 1];
		Iterator it = customers.iterator();
		cust[0] = "";
		int i = 1;
		Customer aux;
		while (it.hasNext()){
			aux = (Customer)it.next();
			cust[i] = aux.getNume();
			i++;
		}
		
		JComboBox chooseCustomer = new JComboBox(cust);
		chooseCustomer.setFont(new Font("Tahoma", Font.BOLD, 16));
		chooseCustomer.setBounds(305, 220, 374, 47);
		customer.add(chooseCustomer);
		
		JButton backBtn = new JButton("Back");
		backBtn.setBounds(12, 13, 163, 25);
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				choose.setVisible(true);
				customer.setVisible(false);
			}
		});
		customer.add(backBtn);
		
		JButton enter = new JButton("ENTER");
		enter.setForeground(new Color(255, 255, 255));
		enter.setFont(new Font("Tahoma", Font.BOLD, 30));
		enter.setBackground(new Color(255, 69, 0));
		enter.setBounds(360, 325, 270, 105);
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if (!chooseCustomer.getSelectedItem().toString().equals("")){
					customerPanel.setCustHome(chooseCustomer.getSelectedItem().toString());
					customerPanel.custHome.setVisible(true);
					customer.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null, "Select a customer!");
				}
			}
		});
		customer.add(enter);
		
	}
}
