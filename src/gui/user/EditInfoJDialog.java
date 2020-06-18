package gui.user;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import bean.SessionBean;
import bean.User;

import logic.UserManager;

public class EditInfoJDialog extends JDialog {

	private JTextField tfAddress;
	private JTextField tfEmail;
	private JPasswordField pswConfirm;
	private JPasswordField psw;
	public EditInfoJDialog() {
		super();
		setResizable(false);
		getContentPane().setLayout(null);
		setTitle("修改个人信息");
		setBounds(100, 100, 500, 257);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setText("密码");
		label.setBounds(36, 38, 58, 18);
		getContentPane().add(label);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setText("密码确认");
		label_1.setBounds(36, 109, 58, 18);
		getContentPane().add(label_1);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setText("邮箱");
		label_2.setBounds(266, 38, 41, 18);
		getContentPane().add(label_2);

		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setText("地址");
		label_3.setBounds(266, 109, 41, 18);
		getContentPane().add(label_3);

		psw = new JPasswordField();
		psw.setBounds(114, 36, 134, 28);
		getContentPane().add(psw);

		pswConfirm = new JPasswordField();
		pswConfirm.setBounds(114, 107, 134, 28);
		getContentPane().add(pswConfirm);

		tfEmail = new JTextField();
		tfEmail.setBounds(334, 36, 114, 28);
		getContentPane().add(tfEmail);

		tfAddress = new JTextField();
		tfAddress.setBounds(334, 107, 114, 28);
		getContentPane().add(tfAddress);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				editUserConfirm();
			}
		});
		button.setText("确认");
		button.setBounds(221, 162, 88, 28);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button_1.setText("返回");
		button_1.setBounds(336, 162, 88, 28);
		getContentPane().add(button_1);
		//
	}
	public void editUserConfirm(){
		boolean flag = true;
		if(this.psw.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "请填入密码");
			flag = false;
		}else if(this.tfAddress.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "请填入地址");
			flag = false;
		}else if(this.tfEmail.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "请填入邮箱");
			flag = false;
		}else if(!new String(this.psw.getPassword()).equals(new String(this.pswConfirm.getPassword()))){
			JOptionPane.showMessageDialog(null, "两次密码要一致");
			flag = false;
		}
		if(flag){
			User user = new SessionBean().getCurrUser();
			user.setPassword(new String(this.psw.getPassword()));
			user.setEmail(this.tfEmail.getText());
			user.setAddress(this.tfAddress.getText());
			UserManager userManager = new UserManager();
			userManager.upDateUser(user);
			JOptionPane.showMessageDialog(null, "更新个人信息成功！");
		}
	}
	public void exit(){
		this.dispose();
	}

}
