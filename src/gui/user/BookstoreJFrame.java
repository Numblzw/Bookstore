package gui.user;


import gui.AboutUsJDialog;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import logic.BookManager;
import logic.TradeManager;

import bean.SessionBean;

public class BookstoreJFrame extends JFrame {

	private JList cartList;
	private JComboBox comboBox;
	private JTextField tfPress;
	private JTextField tfAuthor;
	private JTextField tfBookName;
	private JTable tableBook;
	private JTable tableHotBook;
	DefaultListModel listModel;
	String[] columnNames = { "���", "����", "������", "����", "�۸�/Ԫ" };
	String[] colunmNamesHotBook = {"���", "����" };
	DefaultTableModel tableModel = new DefaultTableModel(this.columnNames, 0);
	DefaultTableModel tableModel2 = new DefaultTableModel(this.colunmNamesHotBook,0);
	PutInCartJDialog putInCartJDialog;

	public BookstoreJFrame() {
		super();
		setTitle("ͼ������ϵͳ");
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(100, 100, 903, 511);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(25, 10, 176, 79);
		getContentPane().add(panel);

		final JLabel label = new JLabel();
		label.setBounds(10, 10, 66, 18);
		label.setText("��ӭ��");
		panel.add(label);

		final JLabel label_1 = new JLabel();
		label_1.setBounds(66, 10, 100, 18);
		label_1.setText(SessionBean.getCurrUser().getUsername());
		panel.add(label_1);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				editInfo();
			}
		});
		button.setBounds(23, 51, 128, 18);
		button.setText("�޸ĸ�����Ϣ");
		panel.add(button);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(216, 10, 513, 443);
		getContentPane().add(panel_1);

		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 137, 503, 248);
		panel_1.add(scrollPane_1);

		tableBook = new JTable(this.tableModel);
		tableBook.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableBook.getColumnModel().getColumn(4).setPreferredWidth(20);
		tableBook.getColumnModel().getColumn(3).setPreferredWidth(25);
	//	tableBook.isCellEditable(0, 0);
		scrollPane_1.setViewportView(tableBook);

		final JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 69, 483, 62);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		final JLabel label_3 = new JLabel();
		label_3.setBounds(94, 9, 66, 18);
		panel_3.add(label_3);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("����");

		tfBookName = new JTextField();
		tfBookName.setBounds(86, 33, 87, 22);
		panel_3.add(tfBookName);

		final JLabel label_3_1 = new JLabel();
		label_3_1.setBounds(207, 9, 66, 18);
		panel_3.add(label_3_1);
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("����");

		tfAuthor = new JTextField();
		tfAuthor.setBounds(199, 33, 87, 22);
		panel_3.add(tfAuthor);

		final JLabel label_3_2 = new JLabel();
		label_3_2.setBounds(319, 9, 66, 18);
		panel_3.add(label_3_2);
		label_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_2.setText("������");

		tfPress = new JTextField();
		tfPress.setBounds(309, 33, 87, 22);
		panel_3.add(tfPress);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				searchBookByCondition();
			}
		});
		button_1.setBounds(407, 35, 66, 20);
		panel_3.add(button_1);
		button_1.setText("����");

		final JLabel label_6 = new JLabel();
		label_6.setText("��ȷ����");
		label_6.setBounds(0, 35, 66, 18);
		panel_3.add(label_6);

		final JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(10, 10, 483, 53);
		panel_1.add(panel_4);

		final JLabel label_5 = new JLabel();
		label_5.setBounds(0, 14, 66, 18);
		panel_4.add(label_5);
		label_5.setText("��������");

		final JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(78, 14, 27, 18);
		panel_4.add(label_4);
		label_4.setText("��");

		comboBox = new JComboBox();
		comboBox.setBounds(111, 10, 106, 27);
		panel_4.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ȫ��", "������", "������", "��ѧ��", "������", "������", "������", "�Ļ���"}));

		final JButton button_1_1 = new JButton();
		button_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				searchBookByCate();
			}
		});
		button_1_1.setBounds(250, 13, 66, 20);
		panel_4.add(button_1_1);
		button_1_1.setText("����");

		final JButton button_2_1 = new JButton();
		button_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				putInCart(listModel);
			}
		});
		button_2_1.setText("���빺�ﳵ");
		button_2_1.setBounds(387, 405, 106, 28);
		panel_1.add(button_2_1);

		final JButton button_2_2 = new JButton();
		button_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				seeBookInfo();
			}
		});
		button_2_2.setText("�鿴");
		button_2_2.setBounds(286, 405, 71, 28);
		panel_1.add(button_2_2);

		final JButton button_3 = new JButton();
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				askForBook();
			}
		});
		button_3.setText("������");
		button_3.setBounds(182, 405, 73, 28);
		panel_1.add(button_3);

		final JButton button_5 = new JButton();
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				showTradeInfo();
			}
		});
		button_5.setBounds(62, 405, 92, 28);
		panel_1.add(button_5);
		button_5.setText("������ѯ");

		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(25, 95, 176, 356);
		getContentPane().add(panel_2);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(62, 10, 66, 18);
		label_2.setText("����ͼ��");
		panel_2.add(label_2);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 34, 172, 267);
		panel_2.add(scrollPane);

		tableHotBook = new JTable(this.tableModel2);
		scrollPane.setViewportView(tableHotBook);
		this.tableHotBook.getColumnModel().getColumn(0).setPreferredWidth(20);

		final JButton button_2 = new JButton();
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				seeBookInfo();
			}
		});
		button_2.setText("�鿴");
		button_2.setBounds(91, 318, 75, 28);
		panel_2.add(button_2);

		final JLabel label_7 = new JLabel();
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setText("���ﳵ");
		label_7.setBounds(759, 34, 86, 18);
		getContentPane().add(label_7);

		final JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(745, 63, 134, 206);
		getContentPane().add(panel_5);
		listModel = new DefaultListModel();
		cartList = new JList(listModel);

		cartList.setBounds(0, 0, 134, 206);
		panel_5.add(cartList);

		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		final JMenu menu = new JMenu();
		menu.setText("�˵�");
		menuBar.add(menu);

		final JMenuItem newItemMenuItem_1 = new JMenuItem();
		newItemMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				editInfo();
			}
		});
		newItemMenuItem_1.setText("�޸ĸ�����Ϣ");
		menu.add(newItemMenuItem_1);

		final JMenuItem newItemMenuItem = new JMenuItem();
		newItemMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		newItemMenuItem.setText("�˳�");
		menu.add(newItemMenuItem);

		final JMenu menu_1 = new JMenu();
		menu_1.setText("����");
		menuBar.add(menu_1);

		final JMenuItem newItemMenuItem_3 = new JMenuItem();
		newItemMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				showAboutUs();
			}
		});
		newItemMenuItem_3.setText("����");
		menu_1.add(newItemMenuItem_3);

		showBook();

		final JButton button_4 = new JButton();
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button_4.setText("�˳�");
		button_4.setBounds(777, 407, 79, 28);
		getContentPane().add(button_4);

		final JButton button_4_1 = new JButton();
		button_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				tradeConfirm();
			}
		});
		button_4_1.setText("ȷ��");
		button_4_1.setBounds(777, 356, 79, 28);
		getContentPane().add(button_4_1);

		final JButton button_4_1_1 = new JButton();
		button_4_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				deleteOrder();
			}
		});
		button_4_1_1.setText("���");
		button_4_1_1.setBounds(777, 306, 79, 28);
		getContentPane().add(button_4_1_1);
	}
	//��ʾ������Ŀ
	@SuppressWarnings("unchecked")
	public void showBook() {
		BookManager bookManager = new BookManager();
		List list = bookManager.getAllBook();
		Iterator iterator = list.iterator();
		Object[] bookObject = null;
		while (iterator.hasNext()) {
			bookObject = (Object[]) iterator.next();
			this.tableModel.addRow(bookObject);
		}
	}

	public void putInCart(DefaultListModel listModel) {
		int bookid;
		int selectedNum = this.tableBook.getSelectedRowCount();
		int selectedIndex = this.tableBook.getSelectedRow();
		if (selectedNum == 1) {
			bookid = Integer.parseInt(this.tableModel.getValueAt(selectedIndex,
					0).toString());
			String bookName = (String) this.tableModel.getValueAt(
					selectedIndex, 1);
			putInCartJDialog = new PutInCartJDialog(bookid, bookName, listModel);
			putInCartJDialog.setModal(true);
			putInCartJDialog.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "һ��ֻ��ѡһ��");
		}
	}

	public void tradeConfirm() {
		int flag = JOptionPane.showConfirmDialog(this, "ȷ�Ϲ���", "", 0, 3);
		if (flag == 0) {
			TradeManager tradeManager = new TradeManager();
			tradeManager.tradeComplete();
			this.listModel.removeAllElements();
		} else if (flag == 1) {
		}
	}

	// ɾ�����ﳵ�еĶ���
	public void deleteOrder() {
		this.listModel.removeAllElements();
		new TradeManager().getOrderMap().clear();
		// int[] selectedIndices = this.cartList.getSelectedIndices();
		// for(int i = 0; i < selectedIndices.length; i ++){
		// this.listModel.remove(selectedIndices[i]); //����ɾ��
		// Object object1 = putInCartJDialog.listForJList;//
		// putInCartJDialog.listForJList.remove(selectedIndices[i]);
		// Object object2 = putInCartJDialog.listForJList; //
		// new
		// TradeManager().getOrderMap().remove(putInCartJDialog.listForJList.get(selectedIndices[i]));
	}
	public void exit(){
		int flag = JOptionPane.showConfirmDialog(this, "ȷ���˳���", "", 0, 3);
		if (flag == 0) {
			SessionBean.setCurrUser(null);
			this.dispose();
		} else if (flag == 1) {
		}
	}
	//ͼ����Ϣ
	public void seeBookInfo(){
		if(this.tableBook.getSelectedRowCount()==1){
			int bookId = Integer.parseInt(this.tableModel.getValueAt(this.tableBook.getSelectedRow(), 0).toString());
			new BookInfoJFrame(bookId).setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null,"��ѡ��һ����");
		}	
	}
	//�����������
	public void searchBookByCate(){
		int rowCount = this.tableModel.getRowCount();
		int cateInt = this.comboBox.getSelectedIndex();
		for (int i = 0; i < rowCount; i++) {
			this.tableModel.removeRow(0);
		}
		if(cateInt == 0){
			//JOptionPane.showMessageDialog(null,"��ѡ�����");
			showBook();
		}else{
			BookManager bookManager = new BookManager();
			bookManager.getBookByCate(cateInt, tableModel);
		}
	}
	//��ȷ��ѯ
	public void searchBookByCondition(){
		
	}
	
	public void editInfo(){
		EditInfoJDialog editInfoJDialog = new EditInfoJDialog();
		editInfoJDialog.setModal(true);
		editInfoJDialog.setVisible(true);
	}
	//��ʾ����
	public void showAboutUs(){
		AboutUsJDialog aboutUsJDialog = new AboutUsJDialog();
		aboutUsJDialog.setModal(true);
		aboutUsJDialog.setVisible(true);
	}
	//��ʾ������ѯ
	public void showTradeInfo(){
		SeeOrderJFrame seeOrderJFrame = new SeeOrderJFrame();
		seeOrderJFrame.setVisible(true);
	}
	//��ʾ������
	public void askForBook(){
		AskForBookJFrame askForBookJFrame = new AskForBookJFrame();
		askForBookJFrame.setVisible(true);
	}
	
}
