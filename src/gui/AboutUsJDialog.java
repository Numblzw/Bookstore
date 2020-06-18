package gui;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class AboutUsJDialog extends JDialog {

	public AboutUsJDialog() {
		super();
		setResizable(false);
		setTitle("关于");
		getContentPane().setLayout(null);
		setBounds(100, 100, 311, 259);

		final JLabel label = new JLabel();
		label.setText("数据库大作业作者");
		label.setBounds(38, 36, 127, 18);
		getContentPane().add(label);

		final JLabel label_1 = new JLabel();
		label_1.setText("1710300312");
		label_1.setBounds(38, 83, 66, 18);
		getContentPane().add(label_1);


		final JLabel label_1_1 = new JLabel();
		label_1_1.setText("左佳琪");
		label_1_1.setBounds(131, 83, 34, 18);
		getContentPane().add(label_1_1);

	}

}
