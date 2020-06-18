package gui.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import util.GetAskForBookStatic;

import logic.BookManager;
import bean.SessionBean;

public class AskForBookJFrame extends JFrame {
	private static int STATIC_YES = 1;
	private static int STATIC_NO = 0;
	private JTable table;
	private JTextField tfAuthor;
	private JTextField tfPress;
	private JTextField tfBookName;
	BookManager bookManager = new BookManager();
	String[] columnNamesString = { "书名", "状态" };
	DefaultTableModel tableModel = new DefaultTableModel(columnNamesString, 0);

	public AskForBookJFrame() {
		super();
		getContentPane().setLayout(null);
		setTitle("求书栏");
		setBounds(100, 100, 615, 384);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "发布求书",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		panel.setLayout(null);
		panel.setBounds(10, 30, 290, 247);
		getContentPane().add(panel);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(33, 28, 50, 18);
		panel.add(label);
		label.setText("书名");

		tfBookName = new JTextField();
		tfBookName.setBounds(118, 26, 125, 22);
		panel.add(tfBookName);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(33, 90, 50, 18);
		panel.add(label_1);
		label_1.setText("出版社");

		tfPress = new JTextField();
		tfPress.setBounds(118, 88, 125, 22);
		panel.add(tfPress);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(33, 151, 50, 18);
		panel.add(label_2);
		label_2.setText("作者");

		tfAuthor = new JTextField();
		tfAuthor.setBounds(118, 149, 125, 22);
		panel.add(tfAuthor);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				askForBook();
			}
		});
		button_1.setBounds(156, 200, 87, 28);
		panel.add(button_1);
		button_1.setText("发布求书");

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button.setBounds(477, 296, 87, 28);
		getContentPane().add(button);
		button.setText("返回");

		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "我的发布",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.setLayout(null);
		panel_1.setBounds(306, 30, 283, 247);
		getContentPane().add(panel_1);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 258, 214);
		panel_1.add(scrollPane);

		table = new JTable(this.tableModel);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);

		showTable();
	}

	public void askForBook() {
		if (this.tfBookName.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "书名必须填写！");
		} else {
			String bookNameString = null;
			String pressString = null;
			String authorString = null;
			int staticAskInt = this.STATIC_NO;
			bookNameString = this.tfBookName.getText();
			pressString = this.tfPress.getText();
			authorString = this.tfAuthor.getText();
			bookManager.addAskBook(new SessionBean().getCurrUser().getUid(),
					bookNameString, pressString, authorString, staticAskInt);
			JOptionPane.showMessageDialog(null, "发布成功");
			this.tfAuthor.setText("");
			this.tfBookName.setText("");
			this.tfPress.setText("");
		}
		this.showTable();
	}

	public void exit() {
		this.dispose();
	}

	public void showTable(){
		int rowCount = this.tableModel.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			this.tableModel.removeRow(0);
		}
		List list = bookManager.getAskBook(new SessionBean().getCurrUser().getUid());
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			Object[] object = (Object[]) iterator.next();
			Object[] objectTable = new Object[2];
			objectTable[0] = object[0];
			objectTable[1] = new GetAskForBookStatic().getAskForBookStatic(Integer.parseInt(object[1].toString()));
			this.tableModel.addRow(objectTable);
		}
	}
}
