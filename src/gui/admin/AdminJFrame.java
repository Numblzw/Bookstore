package gui.admin;

import gui.AboutUsJDialog;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import logic.BookManager;

import bean.Book;
import bean.SessionBean;

public class AdminJFrame extends JFrame {

	private JComboBox comboBox;
	private JTextArea textAreaIntro;
	private JTextField tfPrice;
	private JTextField tfAuthor;
	private JTextField tfPress;
	private JTextField tfBookName;

	public AdminJFrame() {
		super();
		setResizable(false);
		setTitle("管理员界面");
		getContentPane().setLayout(null);
		setBounds(100, 100, 679, 501);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "添加图书", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setLayout(null);
		panel.setBounds(166, 29, 481, 356);
		getContentPane().add(panel);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setText("书名");
		label.setBounds(24, 35, 44, 18);
		panel.add(label);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setText("出版社");
		label_1.setBounds(24, 80, 44, 18);
		panel.add(label_1);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setText("作者");
		label_2.setBounds(24, 127, 44, 18);
		panel.add(label_2);

		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setText("价格");
		label_3.setBounds(24, 173, 44, 18);
		panel.add(label_3);

		tfBookName = new JTextField();
		tfBookName.setBounds(91, 30, 263, 28);
		panel.add(tfBookName);

		tfPress = new JTextField();
		tfPress.setBounds(91, 76, 263, 28);
		panel.add(tfPress);

		tfAuthor = new JTextField();
		tfAuthor.setBounds(91, 123, 105, 28);
		panel.add(tfAuthor);

		tfPrice = new JTextField();
		tfPrice.setBounds(91, 169, 105, 28);
		panel.add(tfPrice);

		final JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setText("简介");
		label_4.setBounds(24, 219, 44, 18);
		panel.add(label_4);

		textAreaIntro = new JTextArea();
		textAreaIntro.setBounds(91, 219, 351, 52);
		panel.add(textAreaIntro);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "艺术类", "教育类", "科学类", "健康类", "军事类", "外语类", "文化类"}));
		comboBox.setBounds(90, 291, 106, 27);
		panel.add(comboBox);

		final JLabel label_4_1 = new JLabel();
		label_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4_1.setText("分类");
		label_4_1.setBounds(24, 295, 44, 18);
		panel.add(label_4_1);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				seeAllBook();
			}
		});
		button.setText("管理图书");
		button.setBounds(25, 106, 112, 28);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				orderManage();
			}
		});
		button_1.setText("查看订单");
		button_1.setBounds(25, 177, 115, 28);
		getContentPane().add(button_1);

		final JButton button_3 = new JButton();
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				addBook();
			}
		});
		button_3.setText("上传图书");
		button_3.setBounds(542, 402, 106, 28);
		getContentPane().add(button_3);

		final JLabel label_5 = new JLabel();
		label_5.setText("欢迎您");
		label_5.setBounds(10, 47, 54, 18);
		getContentPane().add(label_5);

		final JLabel label_5_1 = new JLabel();
		label_5_1.setText(new SessionBean().getCurrAdmin().getUsername());
		label_5_1.setBounds(71, 47, 89, 18);
		getContentPane().add(label_5_1);

		final JButton button_4 = new JButton();
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button_4.setText("退出");
		button_4.setBounds(431, 402, 74, 28);
		getContentPane().add(button_4);

		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		final JMenu menu = new JMenu();
		menu.setText("菜单");
		menuBar.add(menu);

		final JMenuItem newItemMenuItem_1 = new JMenuItem();
		newItemMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				seeAllBook();
			}
		});
		newItemMenuItem_1.setText("管理图书");
		menu.add(newItemMenuItem_1);

		final JMenuItem newItemMenuItem = new JMenuItem();
		newItemMenuItem.setText("查看订单");
		menu.add(newItemMenuItem);

		final JMenuItem newItemMenuItem_4 = new JMenuItem();
		newItemMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		newItemMenuItem_4.setText("退出");
		menu.add(newItemMenuItem_4);

		final JMenu menu_1 = new JMenu();
		menu_1.setText("帮助");
		menuBar.add(menu_1);

		final JMenuItem newItemMenuItem_2 = new JMenuItem();
		newItemMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				showAboutUs();
			}
		});
		newItemMenuItem_2.setText("关于");
		menu_1.add(newItemMenuItem_2);

		final JButton button_1_1 = new JButton();
		button_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				manageAskForBook();
			}
		});
		button_1_1.setText("查看求书栏");
		button_1_1.setBounds(25, 257, 115, 28);
		getContentPane().add(button_1_1);
	}
	//增加图书
	public void addBook() {
		boolean flag = true;
		boolean judgeAdd = true;
		if(this.tfBookName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请填写书名");
			judgeAdd = false;
		} else if (this.tfAuthor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请填作者名");
			judgeAdd = false;
		} else if (this.tfPress.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请填出版社");
			judgeAdd = false;
		} else if (this.tfPrice.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请填写价格");
			flag = false;
			judgeAdd = false;
		}else if(this.comboBox.getSelectedIndex() == 0){
			JOptionPane.showMessageDialog(null, "请选择分类");
			judgeAdd = false;
		} else if (flag) {
			try {
				Float.parseFloat(this.tfPrice.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "价格请填写数字");
				this.tfPrice.setText("");
				judgeAdd = false;
			}
		}
		int testInt = this.comboBox.getSelectedIndex();
		if (judgeAdd) {
			Book book = new Book();
			book.setBookname(this.tfBookName.getText());
			book.setAuthor(this.tfAuthor.getText());
			book.setPress(this.tfPress.getText());
			book.setPrice(Float.parseFloat(this.tfPrice.getText()));
			book.setIntro(this.textAreaIntro.getText());
			book.setCate(this.comboBox.getSelectedIndex());
			BookManager bookManager = new BookManager();
			bookManager.addBook(book);
			JOptionPane.showMessageDialog(null, "添加成功");
			this.tfAuthor.setText("");
			this.tfPress.setText("");
			this.tfBookName.setText("");
			this.tfPrice.setText("");
			this.textAreaIntro.setText("");
		}
	}
	public void seeAllBook(){
		BookManageJFrame seeAllBookJFrame = new BookManageJFrame();
		seeAllBookJFrame.setVisible(true);
	}
	public void exit(){
		int flag = JOptionPane.showConfirmDialog(this, "确认退出？", "", 0, 3);
		if (flag == 0) {
			SessionBean.setCurrAdmin(null);
			this.dispose();
		} else if (flag == 1) {
		}
	}
	public void showAboutUs(){
		AboutUsJDialog aboutUsJDialog = new AboutUsJDialog();
		aboutUsJDialog.setModal(true);
		aboutUsJDialog.setVisible(true);
	}
	public void orderManage(){
		ManageOrderJFrame manageOrderJFrame = new ManageOrderJFrame();
		manageOrderJFrame.setVisible(true);
	}
	public void manageAskForBook(){
		ManageAskForBookJFrame manageAskForBookJFrame = new ManageAskForBookJFrame();
		manageAskForBookJFrame.setVisible(true);
	}

}
