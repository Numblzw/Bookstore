package gui.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ManageAskForBookJFrame extends JFrame {

	private JTable table;
	private String[] columnNameString = {"求书号","书名","出版社","作者","状态"};
	DefaultTableModel tableModel = new DefaultTableModel(columnNameString,0);
	public ManageAskForBookJFrame() {
		super();
		getContentPane().setLayout(null);
		setBounds(100, 100, 600, 375);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 413, 308);
		getContentPane().add(scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		final JButton button = new JButton();
		button.setText("尚无此书");
		button.setBounds(465, 59, 89, 28);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.setText("此书已到");
		button_1.setBounds(465, 121, 89, 28);
		getContentPane().add(button_1);

		final JButton button_2 = new JButton();
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button_2.setText("返回");
		button_2.setBounds(465, 238, 89, 28);
		getContentPane().add(button_2);

		final JButton button_3 = new JButton();
		button_3.setText("删除");
		button_3.setBounds(465, 180, 89, 28);
		getContentPane().add(button_3);
	}
	public void exit(){
		this.dispose();
	}

}
