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

public class ConvergenceLMS extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	private JPanel panel1 = new JPanel();
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	private JLabel lblId;
	private JLabel lblModule;
	private JLabel lblScreen;
	private JComboBox comboBox;
	private JFrame f;
	/**
	 * Launch the application.
	 */
	
	public ConvergenceLMS() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 750, 496);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Wellcome to Convergence LMS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(167, 27, 362, 32);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the details.....  ");
		lblNewLabel_1.setBounds(78, 70, 248, 42);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(350, 150, 300, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(350, 200, 300, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(350, 250, 300, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(350, 300, 145, 20);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(500, 300, 145, 20);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(500, 330, 145, 20);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(500, 360, 145, 20);
		panel.add(textField_6);
		
		
		lblNewLabel_2 = new JLabel("Please Enter the Course name: ");
		lblNewLabel_2.setBounds(78, 150, 250, 14);
		panel.add(lblNewLabel_2);
		
		
		
		lblModule = new JLabel("Please Enter the module Number: ");
		lblModule.setBounds(78, 200, 250, 14);
		panel.add(lblModule);
		
		lblScreen = new JLabel("Please Enter the Screen Number: ");
		lblScreen.setBounds(78, 250, 250, 14);
		panel.add(lblScreen);
		
		lblNewLabel_3 = new JLabel("Please Enter the Username and Password: ");
		lblNewLabel_3.setBounds(78, 300, 250, 14);
		panel.add(lblNewLabel_3);
		
		lblScreen = new JLabel("Please Enter browser Chrome, IE, Edge, Firefox");
		lblScreen.setBounds(78, 330, 250, 14);
		panel.add(lblScreen);
		
		lblScreen = new JLabel("Please Enter YES to view the remedations:");
		lblScreen.setBounds(78, 360, 250, 14);
		panel.add(lblScreen);
		
		JButton btnNewButton = new JButton("Go");
		btnNewButton.setBounds(350, 370, 89, 23);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int mod = 0;
				int scr = 0;
				
				try {
					String t2=textField_1.getText().trim();
					mod=Integer.parseInt(t2);
					}catch(Exception a) {
						 f=new JFrame(); 
							
						 JOptionPane.showMessageDialog(f,"Please enter module number properly","Alert",JOptionPane.WARNING_MESSAGE);
					}
				try {
					String t3=textField_2.getText().trim();
					scr=Integer.parseInt(t3);
					}catch(Exception a) {
						 f=new JFrame(); 
							
						 JOptionPane.showMessageDialog(f,"Please enter screen properly","Alert",JOptionPane.WARNING_MESSAGE);
					}
				String t1=textField.getText().trim();
				String mail=textField_3.getText().trim();
				String pass=textField_4.getText().trim();
				String t4=textField_5.getText().trim();
				String t5=textField_6.getText().trim();
				
				Clickthrogh ex=new Clickthrogh();
				try {
					ex.mainConvergence(t1, mod, scr, mail, pass, t4, t5);
					
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
