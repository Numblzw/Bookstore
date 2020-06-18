package gui.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logic.BookManager;
import logic.TradeManager;
import logic.UserManager;
import util.GetBookStatic;
import bean.SessionBean;

public class ManageOrderJFrame extends JFrame {
	private static int STATIC_WAIT = 0;
	private static int STATIC_SENDING = 1;
	private static int STATIC_HAVEDONE = 2;
	private JTable table;
	private String[] columnNames = { "������", "�û���", "����", "����", "����״̬" };
	DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

	public ManageOrderJFrame() {
		super();
		setTitle("������");
		getContentPane().setLayout(null);
		setBounds(100, 100, 619, 375);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 453, 308);
		getContentPane().add(scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				changeToSending();
			}
		});
		button.setText("�ѷ���");
		button.setBounds(483, 86, 86, 28);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				changeToHaveDone();
			}
		});
		button_1.setText("�����͵�");
		button_1.setBounds(483, 135, 86, 28);
		getContentPane().add(button_1);

		final JButton button_2 = new JButton();
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				deleteOrder();
			}
		});
		button_2.setText("ɾ��");
		button_2.setBounds(483, 184, 86, 28);
		getContentPane().add(button_2);

		final JButton button_2_1 = new JButton();
		button_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button_2_1.setText("����");
		button_2_1.setBounds(483, 236, 86, 28);
		getContentPane().add(button_2_1);

		showTable();

		final JButton button_3 = new JButton();
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				changeToWait();
			}
		});
		button_3.setText("�ȴ�����");
		button_3.setBounds(483, 39, 86, 28);
		getContentPane().add(button_3);
	}

	// ��ʾtable
	public void showTable() {
		Integer rows = this.tableModel.getRowCount();
		for (int row = rows - 1; row >= 0; --row) {
			this.tableModel.removeRow(row);
		}
		List list = null;
		int uid;
		int bid;
		BookManager bookManager = new BookManager();
		UserManager userManager = new UserManager();
		TradeManager tradeManager = new TradeManager();
		list = tradeManager.getAllList();
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Object[] object = (Object[]) iterator.next();
			Object[] objectTable = new Object[5];
			objectTable[0] = object[0];
			uid = Integer.parseInt(object[1].toString());
			String userNameString = userManager.findUserByUid(uid)
					.getUsername();
			objectTable[1] = userNameString;
			bid = Integer.parseInt(object[2].toString());
			String bookNameString = bookManager.getBookByBid(bid).getBookname();
			objectTable[2] = bookNameString;
			objectTable[3] = object[3];
			int staticInt = Integer.parseInt(object[4].toString());
			String staticString = new GetBookStatic().getBookStatic(staticInt);
			objectTable[4] = staticString;
			this.tableModel.addRow(objectTable);
		}
	}

	// ɾ������
	public void deleteOrder() {
		boolean flag = true;
		int confirmDelete;
		int selectedRowCount = this.table.getSelectedRowCount();
		if (selectedRowCount == 0) {
			JOptionPane.showMessageDialog(null, "��ѡ�񶩵�");
			flag = false;
		}
		if (flag) {
			confirmDelete = JOptionPane.showConfirmDialog(this, "ȷ��ɾ����", "", 0,
					3);
			if (confirmDelete == 0) {
				TradeManager tradeManager = new TradeManager();
				int[] selectedRowIndex = this.table.getSelectedRows();// �õ�ѡ�е�������
				Integer[] tids = new Integer[this.table.getSelectedRowCount()]; // �������ڴ洢��ѡ��ÿ�е����
				for (int i = 0; i < selectedRowCount; i++) {
					tids[i] = Integer.parseInt(this.tableModel.getValueAt(
							selectedRowIndex[i], 0).toString());
					if (this.tableModel.getValueAt(selectedRowIndex[i], 4)
							.equals("�ȴ�����")
							|| this.tableModel.getValueAt(selectedRowIndex[4],
									3).equals("�ѷ���")) {
						JOptionPane.showMessageDialog(null, "δ�ɹ����׵���Ʒ����ɾ����");
						return;
					}
					// bookManager.deleteBook(bookIds[i]);
				}
				for (int j = 0; j < selectedRowCount; j++) {
					tradeManager.deleteOrder(tids[j]);
				}
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				this.showTable();
			}
		}
	}
	//��Ϊ�ѷ���״̬
	public void changeToSending(){
		TradeManager tradeManager = new TradeManager();
		int selectedRowCount = this.table.getSelectedRowCount();
		int[] selectedRowIndex = this.table.getSelectedRows();
		for(int i = 0; i < selectedRowCount; i++){
			int tid = Integer.parseInt((String) this.table.getValueAt(selectedRowIndex[i], 0).toString());
			tradeManager.changeStatic(tid, this.STATIC_SENDING);
		}
		showTable();	
	}
	//�ȴ�����
	public void changeToWait(){
		TradeManager tradeManager = new TradeManager();
		int selectedRowCount = this.table.getSelectedRowCount();
		int[] selectedRowIndex = this.table.getSelectedRows();
		for(int i = 0; i < selectedRowCount; i++){
			int tid = Integer.parseInt((String) this.table.getValueAt(selectedRowIndex[i], 0).toString());
			tradeManager.changeStatic(tid, this.STATIC_WAIT);
		}
		showTable();	
	}
	//�����͵�
	public void changeToHaveDone(){
		TradeManager tradeManager = new TradeManager();
		int selectedRowCount = this.table.getSelectedRowCount();
		int[] selectedRowIndex = this.table.getSelectedRows();
		for(int i = 0; i < selectedRowCount; i++){
			int tid = Integer.parseInt((String) this.table.getValueAt(selectedRowIndex[i], 0).toString());
			tradeManager.changeStatic(tid, this.STATIC_HAVEDONE);
		}
		showTable();
	}
	public void exit() {
		this.dispose();
	}

}
