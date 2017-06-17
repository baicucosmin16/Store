
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow {
	
	private JFrame home;
	private JPanel first;
	private JPanel upload;
	
	public MainWindow(){
		
		home = new JFrame();
		home.setTitle("Shop Online");
		home.setBounds(100, 100, 700, 600);
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		first = new JPanel();
		setFirstProp(first);
		
		
		upload = new JPanel();
		setUploadProp(upload);
		upload.setVisible(false);
		first.setVisible(false);
		
		home.getContentPane().add(upload, BorderLayout.CENTER);
		home.getContentPane().add(first, BorderLayout.CENTER);
		first.setVisible(true);
		
		
		
		
		
	}
	
	public void setFirstProp(JPanel first){
		first.setBackground(new Color(0, 128, 128));
		first.setBounds(0, 0, 700, 600);
		//home.getContentPane().add(first, BorderLayout.CENTER);
		first.setLayout(null);
		
		JButton uploadFiles = new JButton("Create New Store");
		uploadFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				upload.setVisible(true);
				first.setVisible(false);
			}
		});
		uploadFiles.setFont(new Font("Tahoma", Font.BOLD, 15));
		uploadFiles.setBounds(215, 408, 250, 54);
		
		BufferedImage mypicture = null;
		try{
			mypicture = ImageIO.read(new File("logo.png"));
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		JLabel imageLabel = new JLabel(new ImageIcon(mypicture));
		imageLabel.setBounds(107, 40, 470, 270);
		first.add(imageLabel);
		
		
		first.add(uploadFiles);
		
		
	}
	
	public void setUploadProp(JPanel upload){
		
		JFileChooser addFile = new JFileChooser();
		
		upload.setBackground(new Color(0, 128, 128));
		upload.setBounds(0, 0, 700, 600);
		//home.getContentPane().add(upload, BorderLayout.CENTER);
		upload.setLayout(null);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg){
				first.setVisible(true);
				upload.setVisible(false);
			}
		});
		backBtn.setBackground(new Color(128, 128, 128));
		backBtn.setForeground(new Color(0, 0, 0));
		backBtn.setBounds(12, 27, 100, 25);
		upload.add(backBtn);
		
		JLabel selStore = new JLabel("Selectati fisierul store.txt:");
		selStore.setForeground(new Color(255, 255, 255));
		selStore.setFont(new Font("Tahoma", Font.BOLD, 22));
		selStore.setBounds(12, 60, 335, 36);
		upload.add(selStore);
		
		JTextField textStore = new JTextField();
		textStore.setEditable(false);
		textStore.setBounds(12, 109, 287, 30);
		upload.add(textStore);
		textStore.setColumns(10);
		
		JButton openStoreBtn = new JButton("Open");
		openStoreBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg){
				addFile.setCurrentDirectory(new java.io.File("."));
				addFile.setDialogTitle("Adauga fisierul store.txt");
				addFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if (addFile.showOpenDialog(openStoreBtn) == JFileChooser.APPROVE_OPTION) {
					String path = addFile.getSelectedFile().getAbsolutePath();
					System.out.println(path.substring(path.length() - 9, path.length()));
					if (path.substring(path.length() - 9, path.length()).equals("store.txt")){
						textStore.setText(path);
					}
					else{
						JOptionPane.showMessageDialog(null, "Fisierul selectat nu este bun");
					}
				}
			}
		});
		openStoreBtn.setBackground(new Color(128, 128, 128));
		openStoreBtn.setForeground(new Color(0, 0, 0));
		openStoreBtn.setBounds(12, 152, 112, 36);
		upload.add(openStoreBtn);
		
		JLabel selCustomers = new JLabel("Selectati fisierul customers.txt:");
		selCustomers.setForeground(new Color(255, 255, 255));
		selCustomers.setFont(new Font("Tahoma", Font.BOLD, 22));
		selCustomers.setBounds(12, 239, 371, 36);
		upload.add(selCustomers);
		
		JTextField textCustomers = new JTextField();
		textCustomers.setEditable(false);
		textCustomers.setBounds(12, 288, 287, 30);
		upload.add(textCustomers);
		textCustomers.setColumns(10);
		
		JButton openCurstomerBtn = new JButton("Open");
		openCurstomerBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg){
				addFile.setCurrentDirectory(new java.io.File("."));
				addFile.setDialogTitle("Adauga fisierul customer.txt");
				addFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if (addFile.showOpenDialog(openCurstomerBtn) == JFileChooser.APPROVE_OPTION) {
					String path = addFile.getSelectedFile().getAbsolutePath();
					System.out.println(path.substring(path.length() - 13, path.length()));
					if (path.substring(path.length() - 13, path.length()).equals("customers.txt")){
						textCustomers.setText(path);
					}
					else{
						JOptionPane.showMessageDialog(null, "Fisierul selectat nu este bun");
					}
				}
			}
		});
		openCurstomerBtn.setBackground(new Color(128, 128, 128));
		openCurstomerBtn.setBounds(12, 331, 112, 36);
		upload.add(openCurstomerBtn);
		
		JButton newStore = new JButton("Generate Store");
		newStore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg){
				if (!textStore.getText().equals("") && !textCustomers.getText().equals("")){
					FactoryGraphic populate = new FactoryGraphic(textStore.getText(), textCustomers.getText());
					home.dispose();
					SecondWindow secWind = new SecondWindow();
				}
				else{
					JOptionPane.showMessageDialog(null, "Trebuie sa alegeti cele doua fisiere!");
				}
			}
		});
		newStore.setFont(new Font("Tahoma", Font.BOLD, 15));
		newStore.setBounds(227, 428, 218, 54);
		upload.add(newStore);
		
	}
	
	public static void main(String[] args){
				try {
					MainWindow window = new MainWindow();
					window.home.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
	}

}
