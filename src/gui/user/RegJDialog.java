package gui.user;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logic.UserManager;

import bean.User;

import db.DBManager;



public class RegJDialog extends JDialog {

	private JPasswordField pswConfirm;
	private JPasswordField psw;
	private JTextField tfAddress;
	private JTextField tfEmail;
	private JTextField tfUsername;

	public RegJDialog() {
		super();
		this.setResizable(false);
		getContentPane().setLayout(null);
		setTitle("用户注册");
		setBounds(100, 100, 505, 300);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button.setText("退出");
		button.setBounds(346, 204, 66, 28);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				creatUser();
			}
		});
		button_1.setText("确定");
		button_1.setBounds(237, 204, 66, 28);
		getContentPane().add(button_1);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setText("用户名");
		label.setBounds(29, 48, 66, 18);
		getContentPane().add(label);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setText("密码");
		label_1.setBounds(29, 101, 66, 18);
		getContentPane().add(label_1);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setText("密码确认");
		label_2.setBounds(249, 101, 66, 18);
		getContentPane().add(label_2);

		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setText("邮箱");
		label_3.setBounds(271, 48, 44, 18);
		getContentPane().add(label_3);

		tfUsername = new JTextField();
		tfUsername.setBounds(122, 46, 110, 22);
		getContentPane().add(tfUsername);

		tfEmail = new JTextField();
		tfEmail.setBounds(331, 48, 110, 22);
		getContentPane().add(tfEmail);

		final JLabel label_4 = new JLabel();
		label_4.setText("地址");
		label_4.setBounds(69, 160, 28, 18);
		getContentPane().add(label_4);

		tfAddress = new JTextField();
		tfAddress.setBounds(124, 158, 255, 22);
		getContentPane().add(tfAddress);

		psw = new JPasswordField();
		psw.setBounds(122, 99, 110, 22);
		getContentPane().add(psw);

		pswConfirm = new JPasswordField();
		pswConfirm.setBounds(331, 99, 110, 22);
		getContentPane().add(pswConfirm);
	}
	public void exit(){
		this.dispose();
	}
	public void creatUser(){
		boolean pswValue = false;
		boolean flag = true;
		String a = new String(this.psw.getPassword());
		String b = new String(this.pswConfirm.getPassword());
		if (a.equals(b))
			pswValue = true;
		if (this.tfUsername.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "用户名不能为空", "警告",
					JOptionPane.ERROR_MESSAGE);
			flag = false;
		} else if (new String(this.psw.getPassword()).trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "密码不能为空", "警告",
					JOptionPane.ERROR_MESSAGE);
			flag = false;
		}else if (!pswValue) {
			JOptionPane.showMessageDialog(null, "密码两次填写要一致", "警告",
					JOptionPane.ERROR_MESSAGE);
			flag = false;
		}else if(this.tfEmail.getText().equals("")){
			JOptionPane.showMessageDialog(null, "邮箱不能为空", "警告",
					JOptionPane.ERROR_MESSAGE);
			flag = false;
		}else if(this.tfAddress.getText().equals("")){
			JOptionPane.showMessageDialog(null, "地址不能为空", "警告",
					JOptionPane.ERROR_MESSAGE);
			flag = false;
		}
		if (flag) {
			User user = new User();
			user.setUsername(this.tfUsername.getText());
			user.setPassword(new String(this.psw.getPassword()));
			user.setEmail(this.tfEmail.getText());
			user.setAddress(this.tfAddress.getText());
			UserManager userManager = new UserManager();
			
			if(userManager.checkUsername(this.tfUsername.getText())){
				userManager.AddUser(user);
				JOptionPane.showMessageDialog(null, "注册成功");
			}else{
				JOptionPane.showMessageDialog(null, "用户名已被占用，请另换一个", "警告",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
