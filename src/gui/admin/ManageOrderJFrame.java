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
	private String[] columnNames = { "订单号", "用户名", "书名", "数量", "订单状态" };
	DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

	public ManageOrderJFrame() {
		super();
		setTitle("管理订单");
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
		button.setText("已发货");
		button.setBounds(483, 86, 86, 28);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				changeToHaveDone();
			}
		});
		button_1.setText("货已送到");
		button_1.setBounds(483, 135, 86, 28);
		getContentPane().add(button_1);

		final JButton button_2 = new JButton();
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				deleteOrder();
			}
		});
		button_2.setText("删除");
		button_2.setBounds(483, 184, 86, 28);
		getContentPane().add(button_2);

		final JButton button_2_1 = new JButton();
		button_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button_2_1.setText("返回");
		button_2_1.setBounds(483, 236, 86, 28);
		getContentPane().add(button_2_1);

		showTable();

		final JButton button_3 = new JButton();
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				changeToWait();
			}
		});
		button_3.setText("等待发货");
		button_3.setBounds(483, 39, 86, 28);
		getContentPane().add(button_3);
	}

	// 显示table
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

	// 删除订单
	public void deleteOrder() {
		boolean flag = true;
		int confirmDelete;
		int selectedRowCount = this.table.getSelectedRowCount();
		if (selectedRowCount == 0) {
			JOptionPane.showMessageDialog(null, "请选择订单");
			flag = false;
		}
		if (flag) {
			confirmDelete = JOptionPane.showConfirmDialog(this, "确认删除？", "", 0,
					3);
			if (confirmDelete == 0) {
				TradeManager tradeManager = new TradeManager();
				int[] selectedRowIndex = this.table.getSelectedRows();// 得到选中的行序列
				Integer[] tids = new Integer[this.table.getSelectedRowCount()]; // 数组用于存储行选中每行的书号
				for (int i = 0; i < selectedRowCount; i++) {
					tids[i] = Integer.parseInt(this.tableModel.getValueAt(
							selectedRowIndex[i], 0).toString());
					if (this.tableModel.getValueAt(selectedRowIndex[i], 4)
							.equals("等待发货")
							|| this.tableModel.getValueAt(selectedRowIndex[4],
									3).equals("已发货")) {
						JOptionPane.showMessageDialog(null, "未成功交易的商品不能删除！");
						return;
					}
					// bookManager.deleteBook(bookIds[i]);
				}
				for (int j = 0; j < selectedRowCount; j++) {
					tradeManager.deleteOrder(tids[j]);
				}
				JOptionPane.showMessageDialog(null, "删除成功");
				this.showTable();
			}
		}
	}
	//改为已发货状态
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
	//等待发货
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
	//货已送到
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
