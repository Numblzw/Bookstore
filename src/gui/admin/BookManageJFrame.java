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

import bean.SessionBean;

import logic.BookManager;

public class BookManageJFrame extends JFrame {

	private JTable table;
	String[] columnNames = {"���", "����", "������", "����", "�۸�", "����"};
	DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
	public BookManageJFrame() {
		super();
		getContentPane().setLayout(null);
		setResizable(false);
		setTitle("����ͼ��");
		setBounds(100, 100, 655, 444);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 512, 398);
		getContentPane().add(scrollPane);

		table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		scrollPane.setViewportView(table);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				deleteBook();
			}
		});
		button.setText("ɾ������");
		button.setBounds(544, 96, 95, 28);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				
			}
		});
		button_1.setText("�鿴");
		button_1.setBounds(544, 166, 95, 28);
		getContentPane().add(button_1);

		final JButton button_2 = new JButton();
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button_2.setText("����");
		button_2.setBounds(544, 235, 95, 28);
		getContentPane().add(button_2);
		
		showAllBook();
	}
	//��ʾ���е��鼰�����Ϣ
	public void showAllBook(){
		Integer rows = this.tableModel.getRowCount();
		for (int row = rows - 1; row >= 0; --row) {
			this.tableModel.removeRow(row);
		}
		BookManager bookManager = new BookManager();
		List booksList = bookManager.getAllBook();
		List salesList = bookManager.getBookSales();
		int salesIndex = 0;
		for (int i = 0; i < booksList.size(); ++i) {
			//�������е��飬ȡÿ�����bid����������bid��Ӧ��
			Object[] bookObject = (Object[]) booksList.get(i);
			bookObject[5] = (Integer) 0;
			Integer bid = Integer.parseInt(bookObject[0].toString()) ;
			if (salesIndex < salesList.size()) {
				Object[] saleObject = (Object[]) salesList.get(salesIndex);
				Integer saleBid = Integer.parseInt( saleObject[0].toString());
				if (bid.equals(saleBid)) {
					bookObject[5] = saleObject[1];
					++salesIndex;
				}
			}
			this.tableModel.addRow(bookObject);
		}
	}
	public void deleteBook(){
		boolean flag = true;
		int confirmDelete;
		int selectedRowCount = this.table.getSelectedRowCount();
		if(selectedRowCount == 0){
				JOptionPane.showMessageDialog(null,"��ѡ��ͼ��");
				flag = false;
			}
		if (flag) {
			 confirmDelete = JOptionPane.showConfirmDialog(this, "ȷ��ɾ����", "", 0, 3);
			 if(confirmDelete == 0){
				 BookManager bookManager = new BookManager();
					int[] selectedRowIndex = this.table.getSelectedRows();//�õ�ѡ�е�������
					Integer[] bookIds = new Integer[this.table.getSelectedRowCount()]; //�������ڴ洢��ѡ��ÿ�е����
					for(int i = 0; i < selectedRowCount ; i++){
						bookIds[i] = Integer.parseInt(this.tableModel.getValueAt(selectedRowIndex[i], 0).toString());
					//	bookManager.deleteBook(bookIds[i]);
					}
					for(int j = 0; j < selectedRowCount ; j++){
						bookManager.deleteBook(bookIds[j]);
					}
					JOptionPane.showMessageDialog(null,"ɾ���ɹ�");
					this.showAllBook();
			}
		}
	}
	public void exit(){
		this.dispose();
	}

}
