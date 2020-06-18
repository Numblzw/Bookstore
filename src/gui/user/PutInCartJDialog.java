package gui.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logic.TradeManager;

public class PutInCartJDialog extends JDialog {
//	private static HashMap orderMap = new HashMap(); 
	private static final long serialVersionUID = 1L;
	private JComboBox comboBoxNum;
//	public static List<Integer> listForJList = new ArrayList();
	public PutInCartJDialog(final int bookId, final String bookNameString,final DefaultListModel listModel) {
		super();
		setResizable(false);
		setTitle("购物车");
		getContentPane().setLayout(null);
		setBounds(400, 200, 360, 213);

		comboBoxNum = new JComboBox();
		comboBoxNum.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBoxNum.setBounds(221, 50, 52, 27);
		getContentPane().add(comboBoxNum);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("购买");
		label.setBounds(46, 54, 52, 18);
		getContentPane().add(label);

		final JLabel label_1 = new JLabel();
		label_1.setText("本");
		label_1.setBounds(279, 54, 41, 18);
		getContentPane().add(label_1);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setText(bookNameString);
		label_2.setBounds(94, 54, 121, 18);
		getContentPane().add(label_2);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				putInCartConfirm(bookId,bookNameString,listModel);
			}
		});
		button.setText("确定");
		button.setBounds(87, 128, 69, 28);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				exit();
			}
		});
		button_1.setText("取消");
		button_1.setBounds(208, 128, 69, 28);
		getContentPane().add(button_1);
		
	}
	public int getBookNum(){
		return this.comboBoxNum.getSelectedIndex() + 1;
	}
	public void putInCartConfirm(int bookId, String bookNameString,DefaultListModel listModel){
		String BookString = bookNameString + "    " + getBookNum() + "本";
//		this.listForJList.add(bookId); //缓存到list里，留删除用
		listModel.addElement(BookString);//购物车里显示
		new TradeManager().getOrderMap().put(bookId, this.comboBoxNum.getSelectedIndex()+1);//放入orderMap
		exit();
	}
	public void exit(){
		this.dispose();
	}
	
}
