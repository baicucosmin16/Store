
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CustomerPanel {

	public String customer;
	public JPanel custHome;
	public JPanel shopCart;
	public JPanel wishList;
	public JPanel navBar;
	public JPanel navBarWish;
	public JLabel hello;
	public JScrollPane scrollTable;
	public JTable table;
	public DefaultTableModel aux;
	public JScrollPane scrollTableshop;
	public JTable tableShop;
	public DefaultTableModel aux2;
	public JScrollPane scrollTableWish;
	public JTable tableWish;
	public DefaultTableModel auxWish;
	public JScrollPane scrollTableWishList;
	public JTable tableWishList;
	public DefaultTableModel auxWishList;
	public JTextField textFieldBuget;
	public JTextField textFieldTotal;
	public JTextField textNoProducts;
	public JTextField textRecomandation;
	
	
	public CustomerPanel(){
		
		custHome = new JPanel();
		shopCart = new JPanel();
		wishList = new JPanel();
		
		custHome.setForeground(new Color(0, 0, 0));
		custHome.setBackground(new Color(0, 128, 128));
		custHome.setBounds(0, 0, 1000, 750);
		custHome.setLayout(null);
		
		hello = new JLabel();
		hello.setForeground(new Color(255, 255, 255));
		hello.setFont(new Font("Tahoma", Font.BOLD, 40));
		hello.setBounds(304, 55, 465, 93);
		custHome.add(hello);
		
		JButton shopCartBtn = new JButton("ShoppingCart");
		shopCartBtn.setForeground(new Color(255, 255, 255));
		shopCartBtn.setFont(new Font("Tahoma", Font.BOLD, 30));
		shopCartBtn.setBackground(new Color(255, 69, 0));
		shopCartBtn.setBounds(158, 305, 270, 105);
		shopCartBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				shopCart.setVisible(true);
				custHome.setVisible(false);
			}
		});
		custHome.add(shopCartBtn);
		
		JButton wishBtn = new JButton("WishList");
		wishBtn.setFont(new Font("Tahoma", Font.BOLD, 30));
		wishBtn.setForeground(new Color(255, 255, 255));
		wishBtn.setBackground(new Color(139, 69, 19));
		wishBtn.setBounds(540, 305, 270, 103);
		wishBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				wishList.setVisible(true);
				custHome.setVisible(false);
			}
		});
		custHome.add(wishBtn);
		
		JButton backBtn = new JButton("Back");
		backBtn.setBounds(12, 13, 163, 25);
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				SecondWindow.customer.setVisible(true);
				custHome.setVisible(false);
			}
		});
		custHome.add(backBtn);
		
		JButton homeBtn = new JButton("Home");
		homeBtn.setBounds(12, 55, 163, 25);
		homeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				SecondWindow.choose.setVisible(true);
				custHome.setVisible(false);
			}
		});
		custHome.add(homeBtn);
		
		
		setShopCartPanel();
		setWishListPanel();
		

		
	}
	
	public void setShopCartPanel(){

		shopCart.setForeground(new Color(0, 0, 0));
		shopCart.setBackground(new Color(0, 128, 128));
		shopCart.setBounds(0, 0, 1000, 750);
		shopCart.setLayout(null);
		
		
		navBar = new JPanel();
		navBar.setBounds(12, 13, 187, 677);
		shopCart.add(navBar);
		navBar.setLayout(null);
		
		JButton back = new JButton("Back");
		back.setBounds(12, 13, 163, 25);
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				custHome.setVisible(true);
				shopCart.setVisible(false);
			}
		});
		navBar.add(back);
		
				
		scrollTable = new JScrollPane();
		scrollTable.setBounds(211, 13, 759, 350);
		shopCart.add(scrollTable);
		
		table = new JTable();
		Object[] coloane = {"Id","Name","Price","Department"};
	    aux = new DefaultTableModel();
	    aux.setColumnIdentifiers(coloane);
	    table.setModel(aux);
	    JTableHeader header = table.getTableHeader();
	    header.setPreferredSize(new Dimension(100, 32));
	    header.setBackground(new Color(66,244,155));
	    header.setFont(new Font("Tahoma", Font.BOLD, 14));
	    
	    TableRowSorter<TableModel> sorter  = new TableRowSorter<TableModel>(table.getModel());
	    table.setRowSorter(sorter);
	    
	    table.addMouseListener(new MouseAdapter(){
	    	public void mouseClicked(MouseEvent e){
	    	}
	    });
	    
	    scrollTable.setViewportView(table);
	    
	    JPanel addRemove = new JPanel();
	    addRemove.setBounds(211,363, 759, 55);
		shopCart.add(addRemove);
		addRemove.setLayout(null);
		
		JButton add = new JButton("Add");
		add.setBounds(70, 13, 163, 25);
		add.setBackground(new Color(66, 244, 128));
		add.setForeground(Color.WHITE);
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				int i  = table.getSelectedRow();
				if (i != -1){
					int id = Integer.parseInt(aux.getValueAt(i, 0).toString());
					String name = aux.getValueAt(i, 1).toString();
					double pret = Double.parseDouble(aux.getValueAt(i, 2).toString());
					String department = aux.getValueAt(i, 3).toString();
					if (CustomerPanelMethods.addProduct(id, name, pret, department, customer)){
						Object[] obj = new Object[4];
				    	obj[0] = aux.getValueAt(i, 0);
				    	obj[1] = aux.getValueAt(i, 1);
				    	obj[2] = aux.getValueAt(i, 2);
				    	obj[3] = aux.getValueAt(i, 3);
				    	aux2.addRow(obj);
				    	textFieldBuget.setText(CustomerPanelMethods.getBuget(customer));
					    textFieldTotal.setText(CustomerPanelMethods.getTotal(customer));
					    textNoProducts.setText(CustomerPanelMethods.getNoProducts(customer));
					    textRecomandation.setText(CustomerPanelMethods.getRecomandation(customer));
					}
					else{
						JOptionPane.showMessageDialog(null, "Bugetul nu permite adaugarea acestui produs!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Selectati un produs pentru a putea adauga!");
				}
			}
		});
		addRemove.add(add);
		
		JButton remove = new JButton("Remove");
		remove.setBounds(500, 13, 163, 25);
		remove.setBackground(new Color(240, 75, 66));
		remove.setForeground(Color.WHITE);
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				int i  = tableShop.getSelectedRow();
				if (i != -1){
					int id = Integer.parseInt(aux2.getValueAt(i, 0).toString());
					String name = aux2.getValueAt(i, 1).toString();
					double pret = Double.parseDouble(aux2.getValueAt(i, 2).toString());
					String department = aux2.getValueAt(i, 3).toString();
					if(CustomerPanelMethods.removeProduct(id, name, pret, department, customer)){
						/*Object[] obj = new Object[4];
				    	obj[0] = aux.getValueAt(i, 0);
				    	obj[1] = aux.getValueAt(i, 1);
				    	obj[2] = aux.getValueAt(i, 2);
				    	obj[3] = aux.getValueAt(i, 3);*/
						aux2.removeRow(i);
					    textFieldBuget.setText(CustomerPanelMethods.getBuget(customer));
						textFieldTotal.setText(CustomerPanelMethods.getTotal(customer));
						textNoProducts.setText(CustomerPanelMethods.getNoProducts(customer));
						textRecomandation.setText(CustomerPanelMethods.getRecomandation(customer));
					}
					else{
						JOptionPane.showMessageDialog(null, "Obiectul nu a fost sters!");
					}	
				}
				else{
					JOptionPane.showMessageDialog(null, "Selectati un produs pentru a putea sterge!");
				}
			}
		});
		addRemove.add(remove);
		
		scrollTableshop = new JScrollPane();
		scrollTableshop.setBounds(211, 418, 759, 220);
		shopCart.add(scrollTableshop);
		
		tableShop = new JTable();
		Object[] coloane1 = {"Id","Name","Price","Department"};
	    aux2 = new DefaultTableModel();
	    aux2.setColumnIdentifiers(coloane1);
	    tableShop.setModel(aux2);
	    JTableHeader header1 = tableShop.getTableHeader();
	    header1.setPreferredSize(new Dimension(100, 32));
	    header1.setBackground(new Color(66,244,155));
	    header1.setFont(new Font("Tahoma", Font.BOLD, 14));
	    
	    tableShop.addMouseListener(new MouseAdapter(){
	    	public void mouseClicked(MouseEvent e){
	    	}
	    });
	    
	    TableRowSorter<TableModel> sort  = new TableRowSorter<TableModel>(tableShop.getModel());
	    tableShop.setRowSorter(sort);
	    
	    scrollTableshop.setViewportView(tableShop);
	    
	    
	    JLabel buget = new JLabel("Buget:");
	    buget.setFont(new Font("Tahoma", Font.BOLD, 16));
	    buget.setBounds(12, 192, 73, 28);
		navBar.add(buget);
		
		textFieldBuget = new JTextField();
		textFieldBuget.setEditable(false);
		textFieldBuget.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldBuget.setBounds(12, 226, 116, 28);
		navBar.add(textFieldBuget);
		textFieldBuget.setColumns(10);
		
		JLabel total = new JLabel("Total:");
		total.setFont(new Font("Tahoma", Font.BOLD, 16));
		total.setBounds(12, 267, 73, 28);
		navBar.add(total);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setEditable(false);
		textFieldTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldTotal.setBounds(12, 308, 116, 28);
		navBar.add(textFieldTotal);
		textFieldTotal.setColumns(10);
		
		JLabel nrProducts = new JLabel("No products:");
		nrProducts.setFont(new Font("Tahoma", Font.BOLD, 16));
		nrProducts.setBounds(12, 349, 116, 28);
		navBar.add(nrProducts);
		
		textNoProducts = new JTextField();
		textNoProducts.setEditable(false);
		textNoProducts.setBounds(12, 390, 116, 28);
		navBar.add(textNoProducts);
		textNoProducts.setColumns(10);
		
		
		JButton buyButton = new JButton("BUY");
		buyButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//int i  = tableShop.getSelectedRow();
				if (aux2.getRowCount() > 0){
					String buget = textFieldBuget.getText();
					while(aux2.getRowCount() > 0){
						aux2.removeRow(0);
					}
					CustomerPanelMethods.setNewShopCart(customer, buget);
					textFieldBuget.setText(CustomerPanelMethods.getBuget(customer));
					textFieldTotal.setText(CustomerPanelMethods.getTotal(customer));
					textNoProducts.setText(CustomerPanelMethods.getNoProducts(customer));
					textRecomandation.setText(CustomerPanelMethods.getRecomandation(customer));
				}
				else{
					JOptionPane.showMessageDialog(null, "Nu aveti nici un produs in ShopCart!");
				}
				
			}
		});
		buyButton.setBounds(12, 456, 116, 48);
		navBar.add(buyButton);
		
		JPanel recomandation = new JPanel();
		recomandation.setBounds(211, 638, 759, 52);
		shopCart.add(recomandation);
		recomandation.setLayout(null);
		
		JLabel recomLabel = new JLabel("Good for you:");
		recomLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		recomLabel.setBounds(12, 13, 123, 31);
		recomandation.add(recomLabel);
		
		textRecomandation = new JTextField();
		textRecomandation.setEditable(false);
		textRecomandation.setBounds(138, 18, 463, 28);
		recomandation.add(textRecomandation);
		textRecomandation.setColumns(10);
		
		JButton addRecomandation = new JButton("Add");
		addRecomandation.setBackground(new Color(66, 244, 128));
		addRecomandation.setForeground(Color.WHITE);
		addRecomandation.setBounds(635, 17, 97, 25);
		addRecomandation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = textRecomandation.getText();
				System.out.println(item);
				if (!CustomerPanelMethods.addRecomandation(item, customer).equals("")){
					String res = CustomerPanelMethods.addRecomandation(item, customer);
					String[] aux = res.split(";");
					Object[] obj = new Object[4];
			    	obj[0] = aux[1];
			    	obj[1] = aux[0];
			    	obj[2] = aux[2];
			    	obj[3] = aux[3];
			    	aux2.addRow(obj);
			    	for (int i = 0; i < auxWishList.getRowCount(); i++){
			    		if (auxWishList.getValueAt(i, 0).toString().equals(aux[1].toString()))
			    			auxWishList.removeRow(i);
			    	}
				    textFieldBuget.setText(CustomerPanelMethods.getBuget(customer));
					textFieldTotal.setText(CustomerPanelMethods.getTotal(customer));
					textNoProducts.setText(CustomerPanelMethods.getNoProducts(customer));
					textRecomandation.setText(CustomerPanelMethods.getRecomandation(customer));
				}
				else{
					JOptionPane.showMessageDialog(null, "Nu aveti suficient buget sau nu este nici o recomandare disponibila!");
				}
				
			}
		});
		recomandation.add(addRecomandation);
	    
	}
	
	public void setWishListPanel(){
		
		wishList.setForeground(new Color(0, 0, 0));
		wishList.setBackground(new Color(0, 128, 128));
		wishList.setBounds(0, 0, 1000, 750);
		wishList.setLayout(null);
		
		
		scrollTableWish = new JScrollPane();
		scrollTableWish.setBounds(211, 13, 759, 350);
		wishList.add(scrollTableWish);
		
		tableWish = new JTable();
		Object[] coloane = {"Id","Name","Price","Department"};
	    auxWish = new DefaultTableModel();
	    auxWish.setColumnIdentifiers(coloane);
	    tableWish.setModel(auxWish);
	    JTableHeader headerWish = tableWish.getTableHeader();
	    headerWish.setPreferredSize(new Dimension(100, 32));
	    headerWish.setBackground(new Color(66,244,155));
	    headerWish.setFont(new Font("Tahoma", Font.BOLD, 14));
	    
	    TableRowSorter<TableModel> sorter  = new TableRowSorter<TableModel>(tableWish.getModel());
	    tableWish.setRowSorter(sorter);
	    
	    scrollTableWish.setViewportView(tableWish);
	    
	    JPanel addRemoveWish = new JPanel();
	    addRemoveWish.setBounds(211,363, 759, 55);
		wishList.add(addRemoveWish);
		addRemoveWish.setLayout(null);
		
		JButton add = new JButton("Add");
		add.setBounds(70, 13, 163, 25);
		add.setBackground(new Color(66, 244, 128));
		add.setForeground(Color.WHITE);
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				int i  = tableWish.getSelectedRow();
				if (i != -1){
					int id = Integer.parseInt(auxWish.getValueAt(i, 0).toString());
					String name = auxWish.getValueAt(i, 1).toString();
					double pret = Double.parseDouble(auxWish.getValueAt(i, 2).toString());
					String department = auxWish.getValueAt(i, 3).toString();
					if (CustomerPanelMethods.addProductWish(id, name, pret, department, customer)){
						Object[] obj = new Object[4];
				    	obj[0] = auxWish.getValueAt(i, 0);
				    	obj[1] = auxWish.getValueAt(i, 1);
				    	obj[2] = auxWish.getValueAt(i, 2);
				    	obj[3] = auxWish.getValueAt(i, 3);
				    	auxWishList.addRow(obj);
					    textRecomandation.setText(CustomerPanelMethods.getRecomandation(customer));
					}
					else{
						JOptionPane.showMessageDialog(null, "Bugetul nu permite adaugarea acestui produs!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Selectati un produs pentru a putea adauga!");
				}
				
			}
		});
		addRemoveWish.add(add);
		
		JButton remove = new JButton("Remove");
		remove.setBounds(500, 13, 163, 25);
		remove.setBackground(new Color(240, 75, 66));
		remove.setForeground(Color.WHITE);
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				int i  = tableWishList.getSelectedRow();
				if (i != -1){
					int id = Integer.parseInt(auxWishList.getValueAt(i, 0).toString());
					String name = auxWishList.getValueAt(i, 1).toString();
					double pret = Double.parseDouble(auxWishList.getValueAt(i, 2).toString());
					String department = auxWishList.getValueAt(i, 3).toString();
					if(CustomerPanelMethods.removeProductWish(id, name, pret, department, customer)){
						/*Object[] obj = new Object[4];
				    	obj[0] = aux.getValueAt(i, 0);
				    	obj[1] = aux.getValueAt(i, 1);
				    	obj[2] = aux.getValueAt(i, 2);
				    	obj[3] = aux.getValueAt(i, 3);*/
						auxWishList.removeRow(i);
					    /*textFieldBuget.setText(CustomerPanelMethods.getBuget(customer));
						textFieldTotal.setText(CustomerPanelMethods.getTotal(customer));
						textNoProducts.setText(CustomerPanelMethods.getNoProducts(customer));*/
						textRecomandation.setText(CustomerPanelMethods.getRecomandation(customer));
					}
					else{
						JOptionPane.showMessageDialog(null, "Obiectul nu a fost sters!");
					}	
				}
				else{
					JOptionPane.showMessageDialog(null, "Selectati un produs pentru a putea sterge!");
				}
				
			}
		});
		addRemoveWish.add(remove);
	    
	    scrollTableWishList = new JScrollPane();
		scrollTableWishList.setBounds(211, 418, 759, 271);
		wishList.add(scrollTableWishList);
	    
	    tableWishList = new JTable();
		Object[] coloane1 = {"Id","Name","Price","Department"};
	    auxWishList = new DefaultTableModel();
	    auxWishList.setColumnIdentifiers(coloane1);
	    tableWishList.setModel(auxWishList);
	    JTableHeader headerWishList = tableWishList.getTableHeader();
	    headerWishList.setPreferredSize(new Dimension(100, 32));
	    headerWishList.setBackground(new Color(66,244,155));
	    headerWishList.setFont(new Font("Tahoma", Font.BOLD, 14));
	    
	    TableRowSorter<TableModel> sort  = new TableRowSorter<TableModel>(tableWishList.getModel());
	    tableWishList.setRowSorter(sort);
	    
	    scrollTableWishList.setViewportView(tableWishList);
	    
	    
	    navBarWish = new JPanel();
	    navBarWish.setBounds(12, 13, 187, 677);
		wishList.add(navBarWish);
		navBarWish.setLayout(null);
		
		JButton back = new JButton("Back");
		back.setBounds(12, 13, 163, 25);
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				custHome.setVisible(true);
				wishList.setVisible(false);
			}
		});
		navBarWish.add(back);
		
		JButton addToShopCart = new JButton("Add to ShopCart");
		addToShopCart.setBounds(12, 50, 163, 25);
		addToShopCart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				int i  = tableWishList.getSelectedRow();
				if (i != -1){
					int id = Integer.parseInt(auxWishList.getValueAt(i, 0).toString());
					String name = auxWishList.getValueAt(i, 1).toString();
					double pret = Double.parseDouble(auxWishList.getValueAt(i, 2).toString());
					String department = auxWishList.getValueAt(i, 3).toString();
					if (CustomerPanelMethods.addProduct(id, name, pret, department, customer)){
						CustomerPanelMethods.removeProductWish(id, name, pret, department, customer);
						Object[] obj = new Object[4];
				    	obj[0] = auxWishList.getValueAt(i, 0);
				    	obj[1] = auxWishList.getValueAt(i, 1);
				    	obj[2] = auxWishList.getValueAt(i, 2);
				    	obj[3] = auxWishList.getValueAt(i, 3);
				    	aux2.addRow(obj);
				    	auxWishList.removeRow(i);
				    	textFieldBuget.setText(CustomerPanelMethods.getBuget(customer));
						textFieldTotal.setText(CustomerPanelMethods.getTotal(customer));
						textNoProducts.setText(CustomerPanelMethods.getNoProducts(customer));
					    textRecomandation.setText(CustomerPanelMethods.getRecomandation(customer));
					}
					else{
						JOptionPane.showMessageDialog(null, "Bugetul nu permite adaugarea acestui produs!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Selectati un produs pentru a putea adauga!");
				}
				
			}
		});
		navBarWish.add(addToShopCart);
		
		
	}
	
	public void setCustHome(String customer){
		
		this.customer = customer;

		hello.setText("Hello, " + customer + "!");
		
		setDinamicShopCart();
		setDinamicWishList();
	}
	
	
	public void setDinamicShopCart(){
		
		Store storeIns = Store.getInstance();
	    ArrayList<Department> departments = storeIns.getDepartments();
	    Department departament;
	    ArrayList<Item> items;
	    
	    while(aux.getRowCount() > 0){
	    	aux.removeRow(0);
	    }
	    while(aux2.getRowCount() > 0){
	    	aux2.removeRow(0);
	    }
	    
	    Iterator it = departments.iterator();
	    while (it.hasNext()){
	    	departament = (Department)it.next();
	    	items = departament.getItems();
	    	for (Item item: items){
		    	Object[] obj = new Object[4];
		    	obj[0] = item.getID();
		    	obj[1] = item.getNume();
		    	obj[2] = item.getPret();
		    	obj[3] = item.getDepartment().toString();
		    	aux.addRow(obj);
		    }
	    }
	    
	    ArrayList<Item> custShopCart = CustomerPanelMethods.getShopCart(customer);
	    for (Item item: custShopCart){
	    	Object[] obj = new Object[4];
	    	obj[0] = item.getID();
	    	obj[1] = item.getNume();
	    	obj[2] = item.getPret();
	    	obj[3] = item.getDepartment().toString();
	    	aux2.addRow(obj);
	    }
	    
	    textFieldBuget.setText(CustomerPanelMethods.getBuget(customer));
	    textFieldTotal.setText(CustomerPanelMethods.getTotal(customer));
	    textNoProducts.setText(CustomerPanelMethods.getNoProducts(customer));
	    textRecomandation.setText(CustomerPanelMethods.getRecomandation(customer));
		
	}
	
	
	public void setDinamicWishList(){
		
		Store storeIns = Store.getInstance();
	    ArrayList<Department> departments = storeIns.getDepartments();
	    Department departament;
	    ArrayList<Item> items;
	    
	    while(auxWish.getRowCount() > 0){
	    	auxWish.removeRow(0);
	    }
	    while(auxWishList.getRowCount() > 0){
	    	auxWishList.removeRow(0);
	    }
	    
	    Iterator it = departments.iterator();
	    while (it.hasNext()){
	    	departament = (Department)it.next();
	    	items = departament.getItems();
	    	for (Item item: items){
		    	Object[] obj = new Object[4];
		    	obj[0] = item.getID();
		    	obj[1] = item.getNume();
		    	obj[2] = item.getPret();
		    	obj[3] = item.getDepartment().toString();
		    	auxWish.addRow(obj);
		    }
	    }
	    
	    ArrayList<Item> custShopCart = CustomerPanelMethods.getWishList(customer);
	    for (Item item: custShopCart){
	    	Object[] obj = new Object[4];
	    	obj[0] = item.getID();
	    	obj[1] = item.getNume();
	    	obj[2] = item.getPret();
	    	obj[3] = item.getDepartment().toString();
	    	auxWishList.addRow(obj);
	    }
	}
}
