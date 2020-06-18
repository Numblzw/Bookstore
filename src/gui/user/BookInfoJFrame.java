package gui.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import bean.Book;
import bean.SessionBean;

import logic.BookManager;
import logic.UserManager;

public class BookInfoJFrame extends JFrame {
	
	final JLabel label1 = new JLabel();
	final JLabel label2 = new JLabel();
	final JLabel label3 = new JLabel();
	final JLabel label4 = new JLabel();
	private JTextArea textArea4 = new JTextArea();
	private JTextArea textArea3 = new JTextArea();
	private JTextArea textArea2 = new JTextArea();
	private JTextArea textArea1 = new JTextArea();
	private JTextArea textAreaComments;
	private JTextArea textArea;
	
	private final JLabel[] labels = {label1, label2, label3, label4};
	private final JTextArea[] textAreas = { textArea1,textArea2,textArea3,textArea4};
	
	private Integer bookId;
	private int pageNum = 0;
	private static final int pageSize = 4;
	List listComments = null;
	private int pageTotal;
	BookManager bookManager = new BookManager();
	public BookInfoJFrame(final int bookId) {
		super();
		this.bookId = bookId;
		BookManager bookManager = new BookManager();
		Book book = bookManager.getBookByBid(bookId);
		setResizable(false);
		setTitle("图书信息");
		getContentPane().setLayout(null);
		setBounds(100, 100, 793, 460);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		panel.setLayout(null);
		panel.setBounds(10, 10, 199, 401);
		getContentPane().add(panel);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setText("书名");
		label.setBounds(0, 22, 47, 18);
		panel.add(label);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setText("出版社");
		label_1.setBounds(0, 81, 47, 18);
		panel.add(label_1);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setText("作者");
		label_2.setBounds(0, 142, 47, 18);
		panel.add(label_2);

		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setText("价格/元");
		label_3.setBounds(0, 205, 47, 18);
		panel.add(label_3);

		final JLabel label_4 = new JLabel();
		label_4.setText(book.getBookname());
		label_4.setBounds(61, 22, 128, 18);
		panel.add(label_4);

		final JLabel label_4_1 = new JLabel();
		label_4_1.setText(book.getPress());
		label_4_1.setBounds(61, 81, 128, 18);
		panel.add(label_4_1);

		final JLabel label_4_2 = new JLabel();
		label_4_2.setText(book.getAuthor());
		label_4_2.setBounds(61, 142, 128, 18);
		panel.add(label_4_2);

		final JLabel label_4_3 = new JLabel();

		label_4_3.setText(Float.toString(book.getPrice()));
		label_4_3.setBounds(61, 205, 128, 18);
		panel.add(label_4_3);

		final JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3_1.setText("简介");
		label_3_1.setBounds(0, 262, 47, 18);
		panel.add(label_3_1);

		textArea = new JTextArea();
		textArea.setBounds(23, 287, 166, 104);
		panel.add(textArea);
		textArea.setText(book.getIntro());
		textArea.setEditable(false);

		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "用户评论",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.setLayout(null);
		panel_1.setBounds(215, 0, 383, 411);
		getContentPane().add(panel_1);

		final JLabel label_5 = new JLabel();
		label_5.setText("发表评论");
		label_5.setBounds(10, 22, 90, 18);
		panel_1.add(label_5);
		//
		
		label1.setText("New JLabel1");
		label1.setBounds(10, 95, 90, 18);
		panel_1.add(label1);

		
		label2.setText("New JLabel2");
		label2.setBounds(10, 169, 90, 18);
		panel_1.add(label2);
		
		
		label3.setText("New JLabel3");
		label3.setBounds(10, 240, 90, 18);
		panel_1.add(label3);

		textAreaComments = new JTextArea();
		textAreaComments.setBounds(10, 46, 363, 44);
		panel_1.add(textAreaComments);

		textArea1 = new JTextArea();
		textArea1.setEditable(false);
		textArea1.setBounds(10, 119, 363, 44);
		panel_1.add(textArea1);

		textArea2 = new JTextArea();
		textArea2.setEditable(false);
		textArea2.setBounds(10, 193, 363, 41);
		panel_1.add(textArea2);

		textArea3 = new JTextArea();
		textArea3.setEditable(false);
		textArea3.setBounds(10, 264, 363, 43);
		panel_1.add(textArea3);
		//
		
		label4.setText("New JLabel4");
		label4.setBounds(10, 313, 90, 18);
		panel_1.add(label4);

		textArea4 = new JTextArea();
		textArea4.setEditable(false);
		textArea4.setBounds(10, 345, 363, 42);
		panel_1.add(textArea4);
		

		final JButton button_2_1 = new JButton();
		button_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (pageNum > 0) {
					pageNum--;
				} else {
					pageNum = 0;
				}
				showComments(bookId);
			}
		});
		button_2_1.setText("上一页");
		button_2_1.setBounds(637, 123, 105, 28);
		getContentPane().add(button_2_1);
		
		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button.setText("返回");
		button.setBounds(637, 266, 105, 28);
		getContentPane().add(button);

		final JButton button_2 = new JButton();
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (pageNum < pageTotal) {
					pageNum++;
				} else {
					pageNum = pageTotal;
				}

				showComments(bookId);
			}
		});
		button_2.setText("下一页");
		button_2.setBounds(637, 197, 105, 28);
		getContentPane().add(button_2);

		final JButton button_3 = new JButton();
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				makeComments(getTextAreaString(),getBid(),getUid());
			}
		});
		button_3.setText("发表评论");
		button_3.setBounds(637, 50, 102, 28);
		getContentPane().add(button_3);

		final JButton button_4 = new JButton();
		button_4.setText("刷新");
		button_4.setBounds(637, 334, 105, 28);
		getContentPane().add(button_4);
		
		listComments = bookManager.getAllComments(bookId); //取出所有的评论
		this.pageTotal = listComments.size()/pageSize;
		if(listComments.size()%pageSize != 0){
			this.pageTotal++;
		}
		showComments(bookId);

	}
	public String getTextAreaString(){
		return this.textAreaComments.getText();
	}
	public int getBid(){
		return this.bookId;
	}
	public int getUid(){
		return new SessionBean().getCurrUser().getUid();
	}
	//做评论
	public void makeComments(String commentString,int uid,int bid){
		if(this.textAreaComments.getText().length() == 0){
			JOptionPane.showMessageDialog(null,"请填入评论！");
		}else{
			bookManager.addComments(commentString, uid, bid);
			JOptionPane.showMessageDialog(null,"评论成功！");
			this.textAreaComments.setText("");
		}
	}
	//显示评论
	@SuppressWarnings("unchecked")
	public void showComments(Integer bookId){
		if(listComments == null){
			return;
		}
		int start = pageNum * pageSize;
		int end = start + pageSize;
		end = end < listComments.size() ? end : listComments.size();

		for (int j = 0; j < pageSize; ++j) {
			this.labels[j].setText("");
			this.textAreas[j].setText("");
		}
		for(int i = start; i < end; i++){
			Object[] objectComments = (Object[]) listComments.get(i);
			String usernameString = objectComments[0].toString();
			String commentString = objectComments[1].toString();
			this.labels[i - start].setText(usernameString);
			this.textAreas[i - start].setText(commentString);
			System.out.println(this.textAreas[i - start].getText());
		}
		textArea1.repaint();
		textArea2.updateUI();
		
	}
	//退出
	public void exit(){
		this.dispose();
	}

}
