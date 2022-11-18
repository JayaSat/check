import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;

public class VenuProduction extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JPanel panel1 = new JPanel();
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private JLabel lblId;
	private JLabel lblModule;
	private JLabel lblScreen;
	private JComboBox comboBox;
	private JFrame f;
	/**
	 * Launch the application.
	 */
		public VenuProduction() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 750, 496);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Wellcome to VenU Production");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(167, 27, 362, 32);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the details....... ");
		lblNewLabel_1.setBounds(78, 70, 248, 42);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(299, 150, 310, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(299, 200, 310, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(299, 250, 310, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(299, 300, 310, 20);
		panel.add(textField_3);
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(299, 330, 310, 20);
		panel.add(textField_4);
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(299, 360, 310, 20);
		panel.add(textField_5);
		
		lblNewLabel_2 = new JLabel("Enter the URL ID: ");
		lblNewLabel_2.setBounds(78, 150, 250, 14);
		panel.add(lblNewLabel_2);
		
		lblId = new JLabel("Enter the course Name: ");
		lblId.setBounds(78, 199, 250, 14);
		panel.add(lblId);
		
		lblModule = new JLabel("Enter the Module Number: ");
		lblModule.setBounds(78, 250, 250, 14);
		panel.add(lblModule);
		
		lblScreen = new JLabel("Enter the Screen Number: ");
		lblScreen.setBounds(78, 300, 250, 14);
		panel.add(lblScreen);
		
		lblScreen = new JLabel("Enter the browser CHrome, IE, Edge, Firefox: ");
		lblScreen.setBounds(78, 330, 250, 14);
		panel.add(lblScreen);
		lblScreen = new JLabel("Enter the Yes to view the remedation ");
		lblScreen.setBounds(78, 360, 250, 14);
		panel.add(lblScreen);
		
		JButton btnNewButton = new JButton("GO");
		btnNewButton.setBounds(299, 390, 90, 25);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = 0;
				int mod = 0;
				int scr = 0;
				
				try {
				String t0=textField.getText().trim();
				id=Integer.parseInt(t0);
				}catch(Exception a) {
					 f=new JFrame(); 
						
					 JOptionPane.showMessageDialog(f,"Please enter URL ID properly","Alert",JOptionPane.WARNING_MESSAGE);
				}
				try {
					String t2=textField_2.getText().trim();
					mod=Integer.parseInt(t2);
					}catch(Exception a) {
						 f=new JFrame(); 
							
						 JOptionPane.showMessageDialog(f,"Please enter module number properly","Alert",JOptionPane.WARNING_MESSAGE);
					}
				try {
					String t3=textField_3.getText().trim();
					scr=Integer.parseInt(t3);
					}catch(Exception a) {
						 f=new JFrame(); 
							
						 JOptionPane.showMessageDialog(f,"Please enter screen properly","Alert",JOptionPane.WARNING_MESSAGE);
					}
				String t1=textField_1.getText().trim();
				String t4=textField_4.getText().trim();
				String t5=textField_5.getText().trim();
				
				Clickthrogh ex=new Clickthrogh();
				try {
					ex.mainProd(id, t1, mod, scr,t4, t5);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
			}
		});

		
	}

}
