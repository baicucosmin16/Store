
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
import javax.swing.JComboBox;
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

public class StorePanel {
	
	JPanel store;
	JPanel navBar;
	JPanel manage;
	JButton back;
	JButton delete;
	JScrollPane scrollTable;
	JTable table;
	JTextField idTextFieldEdit;
	JTextField nameTextFieldEdit;
	JTextField pretTextFieldEdit;
	JTextField departTextFieldEdit;
	DefaultTableModel aux;
	
	public StorePanel(){
		store = new JPanel();
		setStorePanel(store);
	}
	
	public void setStorePanel(JPanel store){
		store.setForeground(new Color(0, 0, 0));
		store.setBackground(new Color(0, 128, 128));
		store.setBounds(0, 0, 1000, 750);
		store.setLayout(null);
	
		setNavBar();
		setManage();
		
		
		scrollTable = new JScrollPane();
		scrollTable.setBounds(211, 13, 759, 435);
		store.add(scrollTable);
		
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
	    		int i = table.getSelectedRow();
	    		idTextFieldEdit.setText(aux.getValueAt(i, 0).toString());
	    		nameTextFieldEdit.setText(aux.getValueAt(i, 1).toString());
	    		pretTextFieldEdit.setText(aux.getValueAt(i, 2).toString());
	    		departTextFieldEdit.setText(aux.getValueAt(i, 3).toString());
	    	}
	    });
	    
	    Store storeIns = Store.getInstance();
	    ArrayList<Department> departments = storeIns.getDepartments();
	    Department departament;
	    ArrayList<Item> items;
	    
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
		    
	    /*TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel>();
	    table.setRowSorter(sort);*/
	   
		
		scrollTable.setViewportView(table);
		
		
		
	}
	
	public void setNavBar(){
		
		navBar = new JPanel();
		navBar.setBounds(12, 13, 187, 677);
		store.add(navBar);
		navBar.setLayout(null);
		
		back = new JButton("Back");
		back.setBounds(12, 13, 163, 25);
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				SecondWindow.choose.setVisible(true);
				store.setVisible(false);
			}
		});
		navBar.add(back);
		
		delete = new JButton("Delete");
		delete.setBounds(12, 60, 163, 25);
		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				int i = table.getSelectedRow();
				if (i != -1){
					int id = Integer.parseInt(aux.getValueAt(i, 0).toString());
					String name = aux.getValueAt(i, 1).toString();
					double pret = Double.parseDouble(aux.getValueAt(i, 2).toString());
					String department = aux.getValueAt(i, 3).toString();
					StorePanelMethods.removeProduct(id, name, pret, department);
					aux.removeRow(i);
				}
				else{
					JOptionPane.showMessageDialog(null, "Selectati un produs pentru a putea sterge!");
				}
			}
		});
		navBar.add(delete);
		
		
	}
	
	public void setManage(){
		manage = new JPanel();
		manage.setBounds(211, 461, 759, 229);
		store.add(manage);
		manage.setLayout(null);
		
		
		JLabel idLabelAdd = new JLabel("Id: ");
		idLabelAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idLabelAdd.setBounds(12, 13, 90, 22);
		manage.add(idLabelAdd);
		
		JLabel nameLabelAdd = new JLabel("Nume:");
		nameLabelAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameLabelAdd.setBounds(12, 48, 90, 22);
		manage.add(nameLabelAdd);
		
		JLabel pretLabelAdd = new JLabel("Pret:");
		pretLabelAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pretLabelAdd.setBounds(12, 83, 90, 22);
		manage.add(pretLabelAdd);
		
		JLabel departLabelAdd = new JLabel("Department:");
		departLabelAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		departLabelAdd.setBounds(12, 118, 90, 22);
		manage.add(departLabelAdd);
		
		JTextField idTextField = new JTextField();
		idTextField.setBounds(114, 14, 239, 22);
		manage.add(idTextField);
		idTextField.setColumns(10);
		
		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(114, 49, 239, 22);
		manage.add(nameTextField);
		nameTextField.setColumns(10);
		
		JTextField pretTextField = new JTextField();
		pretTextField.setBounds(114, 84, 239, 22);
		manage.add(pretTextField);
		pretTextField.setColumns(10);
		
		
		String[] departament = {"", "BookDepartment", "MusicDepartment", "SoftwareDepartment", "VideoDepartment"};
		JComboBox boxAdd = new JComboBox(departament);
		boxAdd.setBounds(114, 119, 239, 22);
		manage.add(boxAdd);
		
		
		JButton add = new JButton("Add");
		add.setBounds(114, 175, 97, 25);
		manage.add(add);
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if (!idTextField.getText().equals("") && !nameTextField.getText().equals("") && !pretTextField.getText().equals("")
						&& !boxAdd.getSelectedItem().toString().equals("")){
					
					int id = Integer.parseInt(idTextField.getText());
					String name = nameTextField.getText();
					double pret = Double.parseDouble(pretTextField.getText());
					String department = boxAdd.getSelectedItem().toString();
					if (StorePanelMethods.addProduct(id, name, pret, department)){
						Object[] obj = new Object[4];
				    	obj[0] = idTextField.getText();
				    	obj[1] = nameTextField.getText();
				    	obj[2] = pretTextField.getText();
				    	obj[3] = boxAdd.getSelectedItem().toString();
				    	aux.addRow(obj);
					}
					else{
						JOptionPane.showMessageDialog(null, "Acest produs exista deja in magazin!");
					}
					
					/*int i = table.getSelectedRow();
					aux.setValueAt(nameTextFieldEdit.getText(),i, 1);
					aux.setValueAt(pretTextFieldEdit.getText(),i, 2);
					StorePanelMethods.modifyProduct(departTextFieldEdit.getText(), Integer.parseInt(idTextFieldEdit.getText()), 
							Double.parseDouble(pretTextFieldEdit.getText()), nameTextFieldEdit.getText());*/
				}
				else{
					JOptionPane.showMessageDialog(null, "Completati toate campurile corespunzator!");
				}
			}
		});
		
		
		
		JLabel idLabelEdit = new JLabel("Id: ");
		idLabelEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idLabelEdit.setBounds(406, 13, 90, 22);
		manage.add(idLabelEdit);
		
		JLabel nameLabelEdit = new JLabel("Nume:");
		nameLabelEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameLabelEdit.setBounds(406, 48, 90, 22);
		manage.add(nameLabelEdit);
		
		JLabel pretLabelEdit = new JLabel("Pret:");
		pretLabelEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pretLabelEdit.setBounds(406, 83, 90, 22);
		manage.add(pretLabelEdit);
		
		JLabel departLabelEdit = new JLabel("Department:");
		departLabelEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		departLabelEdit.setBounds(406, 118, 90, 22);
		manage.add(departLabelEdit);
		
		idTextFieldEdit = new JTextField();
		idTextFieldEdit.setEditable(false);
		idTextFieldEdit.setBounds(508, 14, 239, 22);
		manage.add(idTextFieldEdit);
		idTextFieldEdit.setColumns(10);
		
		nameTextFieldEdit = new JTextField();
		nameTextFieldEdit.setBounds(508, 49, 239, 22);
		manage.add(nameTextFieldEdit);
		nameTextFieldEdit.setColumns(10);
		
		pretTextFieldEdit = new JTextField();
		pretTextFieldEdit.setBounds(508, 84, 239, 22);
		manage.add(pretTextFieldEdit);
		pretTextFieldEdit.setColumns(10);
		
		departTextFieldEdit = new JTextField();
		departTextFieldEdit.setEditable(false);
		departTextFieldEdit.setBounds(508, 119, 239, 22);
		manage.add(departTextFieldEdit);
		
		JButton edit = new JButton("Edit");
		edit.setBounds(508, 175, 97, 25);
		manage.add(edit);
		
		edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if (!nameTextFieldEdit.getText().equals("") && !pretTextFieldEdit.getText().equals("")){
					int i = table.getSelectedRow();
					aux.setValueAt(nameTextFieldEdit.getText(),i, 1);
					aux.setValueAt(pretTextFieldEdit.getText(),i, 2);
					StorePanelMethods.modifyProduct(departTextFieldEdit.getText(), Integer.parseInt(idTextFieldEdit.getText()), 
							Double.parseDouble(pretTextFieldEdit.getText()), nameTextFieldEdit.getText());
				}
				else{
					JOptionPane.showMessageDialog(null, "Completati toate campurile corespunzator!");
				}
			}
		});
		
	}
	

}
