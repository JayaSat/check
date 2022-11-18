import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.Color;

public class VenuStagingExam extends JFrame implements ActionListener  {

	private JPanel contentPane;
	private JPanel contentPane1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPanel panel1 = new JPanel();
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private JLabel lblId;
	private JLabel lblModule;
	private JLabel lblScreen;
	private JComboBox comboBox;
	private JCheckBox chckbxPreviewMode;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JLabel lblPreviewMode;
	private JFrame f;
	/**
	 * Launch the application.
	 */
	private static String preview;
	
	public VenuStagingExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 750, 496);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		// started this group
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(200, 330, 50, 23);
		panel.add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(260, 330, 50, 23);
		panel.add(rdbtnNo);
		
		lblPreviewMode = new JLabel("Preview Mode: ");
		lblPreviewMode.setBounds(78, 330, 140, 23);
		panel.add(lblPreviewMode);
		
	/*	JButton btnCheck = new JButton("Check");
		btnCheck.setBounds(366, 300, 89, 23);
		panel.add(btnCheck);
		
		ButtonGroup bg=new ButtonGroup(); 
		bg.add(rdbtnYes);
		bg.add(rdbtnNo); 
		  
		btnCheck.addActionListener(this);    
		add(rdbtnYes);add(rdbtnNo);add(btnCheck);  */
		
		
		//ending the code 
		
		JLabel lblNewLabel = new JLabel("Wellcome to VenU Staging Assessment ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(167, 27, 362, 32);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the Required details ");
		lblNewLabel_1.setBounds(78, 70, 248, 42);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(299, 130, 310, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(299, 180, 310, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(299, 230, 310, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(299, 280, 310, 20);
		panel.add(textField_3);
		
		lblNewLabel_2 = new JLabel("Please Enter the URL Id:");
		lblNewLabel_2.setBounds(78, 130, 200, 14);
		panel.add(lblNewLabel_2);
		
		lblId = new JLabel("Please Enter the Course Name:");
		lblId.setBounds(78, 180, 200, 14);
		panel.add(lblId);
		
		lblModule = new JLabel("Please Enter Excel file Name:");
		lblModule.setBounds(78, 230, 200, 14);
		panel.add(lblModule);
		
		lblScreen = new JLabel("Please Enter the Sheet Name:");
		lblScreen.setBounds(78, 280, 200, 14);
		panel.add(lblScreen);
		
		
		JButton btnNewButton = new JButton("GO");
		btnNewButton.setBounds(337, 325, 89, 23);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=0;
				try {
				String t0=textField.getText();
				id=Integer.parseInt(t0);
				}catch(Exception a) {
					 f=new JFrame(); 
						
					 JOptionPane.showMessageDialog(f,"Please enter URL ID properly","Alert",JOptionPane.WARNING_MESSAGE);
				}
				String t1=textField_1.getText();
				String t2=textField_2.getText();
				String t3=textField_3.getText();
				if(rdbtnYes.isSelected()){
					
				
				//Clickthrogh exam=new Clickthrogh();
				ExcelReader exam = new ExcelReader();
				try {
					exam.stagingPreview(id, t1, t2, t3);
					//exam.staging(id, t1, t2, t3);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}else if (rdbtnNo.isSelected()) {
					ExcelReader exam = new ExcelReader();
				try {
					exam.staging45(id, t1, t2, t3);
					//exam.staging(id, t1, t2, t3);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}else {
					
					  f=new JFrame(); 
					
					 JOptionPane.showMessageDialog(f,"Please select the Mode","Alert",JOptionPane.WARNING_MESSAGE);  
					//JOptionPane.
				/*String t4=textField_4.getText();
				String t5=textField_5.getText();
				String t6=textField_6.getText();
				String t7=textField_7.getText();*/
				
			}
			}
			});

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(rdbtnYes.isSelected()){    
			 preview="YES"; 
			}    
			if(rdbtnNo.isSelected()){    
				preview="NO";
			}    
			 
	}

}
