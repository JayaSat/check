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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class SelectionV1 extends JFrame implements ActionListener {

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
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectionV1 frame = new SelectionV1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public SelectionV1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setForeground(new Color(0, 128, 128));
		panel1.setBounds(10, 23, 634, 357);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome Please select the server to go....");
		lblNewLabel.setBounds(35, 11, 541, 39);
		panel1.add(lblNewLabel);
		
		panel1.setBounds(0, 0, 734, 446);
		comboBox = new JComboBox();
		comboBox.setBounds(35, 77, 541, 35);
		panel1.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("VenuStagingExam")){
					comboBox.disable();
					SelectionV1 fram = new SelectionV1();
					//fram.setVisible(false); 
					//fram.comboBox.disable();
					VenuStagingExam frame1 = new VenuStagingExam();
					frame1.setVisible(true);
					
					
					}else if(comboBox.getSelectedItem().equals("VenuStaging")){
						comboBox.disable();
						SelectionV1 fram = new SelectionV1();
						//fram.setVisible(false); 
						//fram.comboBox.disable();
						VenuStaging frame1 = new VenuStaging();
						frame1.setVisible(true);
						
						
						}else if(comboBox.getSelectedItem().equals("VenuProduction")){
							comboBox.disable();
							SelectionV1 fram = new SelectionV1();
							//fram.setVisible(false); 
							//fram.comboBox.disable();
							VenuProduction frame1 = new VenuProduction();
							frame1.setVisible(true);
							
							}else if(comboBox.getSelectedItem().equals("Convergence")){
								comboBox.disable();
								SelectionV1 fram = new SelectionV1();
								//fram.setVisible(false); 
								//fram.comboBox.disable();
								ConvergenceLMS frame1 = new ConvergenceLMS();
								frame1.setVisible(true);
								 
								}else if(comboBox.getSelectedItem().equals("OnlineTest")){
									comboBox.disable();
									SelectionV1 fram = new SelectionV1();
									//fram.setVisible(false); 
									//fram.comboBox.disable();
									OnlineTesting frame1 = new OnlineTesting();
									frame1.setVisible(true);
									
									}
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Please select...","VenuStagingExam", "VenuStaging", "VenuProduction", "Convergence", "OnlineTest"}));
		
		
	}
	public void actionPerformed(ActionEvent e){    
		
}
}