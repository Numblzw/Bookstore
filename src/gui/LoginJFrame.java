package gui;

import gui.admin.AdminJFrame;
import gui.user.BookstoreJFrame;
import gui.user.RegJDialog;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bean.Admin;
import bean.SessionBean;
import bean.User;

import logic.UserManager;

public class LoginJFrame extends JFrame {

	private JComboBox comboBox;
	private JPasswordField tfPsw;
	private JTextField tfUsername;
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginJFrame frame = new LoginJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public LoginJFrame() {
		super();
		setTitle("图书销售系统");
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(100, 100, 551, 396);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JLabel label = new JLabel();
		label.setFont(new Font("", Font.BOLD, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("图书销售系统");
		label.setBounds(102, 41, 325, 41);
		getContentPane().add(label);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setText("用户名");
		label_1.setBounds(115, 158, 66, 18);
		getContentPane().add(label_1);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setText("密码");
		label_2.setBounds(117, 215, 66, 18);
		getContentPane().add(label_2);

		tfUsername = new JTextField();
		tfUsername.setBounds(213, 158, 114, 25);
		getContentPane().add(tfUsername);

		tfPsw = new JPasswordField();
		tfPsw.setBounds(214, 211, 114, 25);
		getContentPane().add(tfPsw);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				
				RegJDialog regJDialog = new RegJDialog();
				regJDialog.setModal(true);
				regJDialog.setVisible(true);
			}
		});
		button.setText("还没注册");
		button.setBounds(378, 158, 90, 21);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button_1.setText("退出");
		button_1.setBounds(396, 278, 74, 28);
		getContentPane().add(button_1);

		final JButton button_1_1 = new JButton();
		button_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if(comboBox.getSelectedItem().equals("顾客")){
					if(checkUser()!=null){
						new SessionBean().setCurrUser(checkUser());
						new BookstoreJFrame().setVisible(true);
						exit();
					}else{
						JOptionPane.showMessageDialog(null, "用户名或密码错误", "",
								JOptionPane.ERROR_MESSAGE);
					}
				}else if(comboBox.getSelectedItem().equals("管理员")){
					if(checkAdmin()!=null){
						new SessionBean().setCurrAdmin(checkAdmin());
						new AdminJFrame().setVisible(true);
						exit();
					}else{
						JOptionPane.showMessageDialog(null, "用户名或密码错误", "",
								JOptionPane.ERROR_MESSAGE);
					}
				}	
			}
		});
		button_1_1.setText("确定");
		button_1_1.setBounds(286, 278, 74, 28);
		getContentPane().add(button_1_1);

		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setText("选择身份");
		label_3.setBounds(115, 109, 66, 18);
		getContentPane().add(label_3);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"顾客", "管理员"}));
		comboBox.setBounds(213, 105, 114, 25);
		getContentPane().add(comboBox);
	}
	public void exit(){
		this.dispose();
	}
	public User checkUser(){
		User user = new User();
		user.setUsername(this.tfUsername.getText());
		user.setPassword(new String(this.tfPsw.getPassword()));
		UserManager userManager = new UserManager();
		if(userManager.checkUser(user)!=null){
			return userManager.checkUser(user);
		}else{
			return null;
		}
	}
	public Admin checkAdmin(){
		Admin admin = new Admin();
		admin.setUsername(this.tfUsername.getText());
		admin.setPassword(new String(this.tfPsw.getPassword()));
		UserManager userManager = new UserManager();
		if(userManager.checkAdmin(admin) != null){
			return userManager.checkAdmin(admin);
		}else{
			return null;
		}
		
	}

}
