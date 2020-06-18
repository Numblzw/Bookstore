package gui.user;

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
import util.GetBookStatic;
import bean.SessionBean;

public class SeeOrderJFrame extends JFrame {
	private JTable table;
	private String[]columnNames = {"������","����","����","����״̬"};
	DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
	public SeeOrderJFrame() {
		super();
		getContentPane().setLayout(null);
		setTitle("�鿴����");
		setBounds(100, 100, 585, 407);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 24, 396, 310);
		getContentPane().add(scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				deleteOrder();
			}
		});
		button.setText("ȡ������");
		button.setBounds(459, 68, 86, 28);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button_1.setText("����");
		button_1.setBounds(455, 201, 90, 28);
		getContentPane().add(button_1);
		
		showTable();

		final JButton button_2 = new JButton();
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				
			}
		});
		button_2.setText("ˢ��");
		button_2.setBounds(455, 137, 90, 28);
		getContentPane().add(button_2);
	}
	
	public void showTable(){
		Integer rows = this.tableModel.getRowCount();
		for (int row = rows - 1; row >= 0; --row) {
			this.tableModel.removeRow(row);
		}
		List list = null;
		int uid = SessionBean.getCurrUser().getUid();
		int bid ;
		BookManager bookManager = new BookManager();
		TradeManager tradeManager = new TradeManager();
		list = tradeManager.getTradeInfo(uid);
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			Object[] object = (Object[]) iterator.next();
			Object[] objectTable = new Object[4];
			objectTable[0] = object[0];
			bid = Integer.parseInt(object[2].toString());
			String bookNameString = bookManager.getBookByBid(bid).getBookname();
			objectTable[1] = bookNameString;
			objectTable[2] = object[3];
			int staticInt = Integer.parseInt(object[4].toString());
			String staticString = new GetBookStatic().getBookStatic(staticInt);
			objectTable[3] = staticString ;
			this.tableModel.addRow(objectTable);
		}
	}
	public void exit(){
		this.dispose();
	}
	public void deleteOrder(){
		boolean flag = true;
		int confirmDelete;
		int selectedRowCount = this.table.getSelectedRowCount();
		if(selectedRowCount == 0){
				JOptionPane.showMessageDialog(null,"��ѡ�񶩵�");
				flag = false;
			}
		if (flag) {
			 confirmDelete = JOptionPane.showConfirmDialog(this, "ȷ��ɾ����", "", 0, 3);
			 if(confirmDelete == 0){
				 	TradeManager tradeManager = new TradeManager();
					int[] selectedRowIndex = this.table.getSelectedRows();//�õ�ѡ�е�������
					Integer[] tids = new Integer[this.table.getSelectedRowCount()]; //�������ڴ洢��ѡ��ÿ�е����
					for(int i = 0; i < selectedRowCount ; i++){
						tids[i] = Integer.parseInt(this.tableModel.getValueAt(selectedRowIndex[i], 0).toString());
						if(this.tableModel.getValueAt(selectedRowIndex[i], 3).equals("�ѷ���")){
							JOptionPane.showMessageDialog(null,"�ѷ�����Ʒ����ɾ����");
							return;
						}
					//	bookManager.deleteBook(bookIds[i]);
					}
					for(int j = 0; j < selectedRowCount ; j++){
						tradeManager.deleteOrder(tids[j]);
					}
					JOptionPane.showMessageDialog(null,"ɾ���ɹ�");
					this.showTable();
			}
		}
	}

}
