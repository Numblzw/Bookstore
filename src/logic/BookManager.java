package logic;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import bean.Book;

public class BookManager extends AbstractManager {
	// ����ͼ��
	public void addBook(Book book) {
		String booknameString = book.getBookname();
		String pressString = book.getPress();
		String authorString = book.getAuthor();
		float price = book.getPrice();
		String introString = book.getIntro();
		int cate = book.getCate();
		String sql = "INSERT INTO book (bookname,press,author,price,intro,cate) VALUES ('"
				+ booknameString
				+ "','"
				+ pressString
				+ "','"
				+ authorString
				+ "'," + price + " ,'" + introString + "'," + cate + ")";
		getDBManager().update(sql);
		// System.out.println(sql);
	}

	// �õ�������
	public List getAllBook() {
		List list = null;
		list = getDBManager().query("select * from book order by bid");
		return list;
	}

	// ����bid��ѯ��Ŀ
	public Book getBookByBid(int bid) {
		List list = null;
		Book book = new Book();
		list = getDBManager().query(
				"select * from book where bid = '" + bid + "'");
		Object[] bookObject = (Object[]) list.get(0);
		book.setBookname((String) bookObject[1]);
		book.setPress((String) bookObject[2]);
		book.setAuthor((String) bookObject[3]);
		book.setPrice(Float.parseFloat(bookObject[4].toString()));
		book.setIntro((String) bookObject[5]);
		book.setCate(Integer.parseInt(bookObject[6].toString()));
		return book;
	}

	// �������������
	public void getBookByCate(Integer cateInt, DefaultTableModel tableModel) {
		List list = null;
		String sql = "select * from book where cate = " + "'" + cateInt + "'"
				+ "order by bid";
		list = getDBManager().query(sql);
		Iterator iterator = list.iterator();
		Object[] bookObject = null;
		while (iterator.hasNext()) {
			bookObject = (Object[]) iterator.next();
			tableModel.addRow(bookObject);
		}
	}

	// ��������������
	public void getBookByCondition(String bookNameString, String authorString,
			String pressString) {
		if (bookNameString != null) {

		}
	}

	// ����Ա��¼�鿴��
	public List getBookForAdmin() {
		List list = null;
		// String sql = "select b.bid, b.bookname, b.press, b.author, b.price,
		// sum(t.num) as bookSales from book as b, tradeinfo as t where b.bid =
		// t.bid group by t.bid";
		String sql = "select b.bid, b.bookname, b.press, b.author, b.price,as bookSales from book";

		list = getDBManager().query(sql);
		return list;
	}

	public List getBookSales() {
		// String sql = "select b.bid, sum(t.num) as bookSales from book as b,
		// tradeinfo as t where b.bid = t.bid group by t.bid";
		String sql = "select bid, sum(num) from tradeinfo group by uid order by bid";
		List list = getDBManager().query(sql);

		return list;
	}

	// ɾ��ͼ��
	public void deleteBook(Integer bookId) {
		String sql = "delete from book where bid = " + "'" + bookId + "'";
		getDBManager().update(sql);
	}

	// ��������
	public void addComments(String commentString, Integer uid, Integer bid) {
		String sql = "INSERT INTO bookcomment "
				+ "(bid, uid, comment) VALUES (" + bid + "," + uid + "," + "'"
				+ commentString + "')";
		getDBManager().update(sql);
	}

	// �鿴���Ƿ��Ѿ����۹�
	public boolean ifAddComments(Integer uid, Integer bid) {
		String sql = "select * from bookcomment where " + "uid = " + uid
				+ " and bid = " + bid;
		System.out.println(sql);
		List list = getDBManager().query(sql);
		if (list == null || list.size() == 0 || list.get(1) == null) {
			return false;
		} else {
			return true;
		}
	}

	// �õ�����
	public List getAllComments(Integer bid) {
		String sql = "select uid,comment from bookcomment where bid = " + bid
				+ " order by cid desc";
		System.out.println(sql);
		List list = getDBManager().query(sql);
		return list;
	}
	//����
	public void addAskBook(int uid, String bookNameString, String pressString,
			String authorString, int staticAskInt) {
		String sql = "insert askforbook (uid ,bookname,press,author,staticask) values ("
				+ uid
				+ ",'"
				+ bookNameString
				+ "','"
				+ pressString
				+ "','"
				+ authorString + "'," + staticAskInt + ")";
	//	System.out.println(sql);
		getDBManager().update(sql);
	}
	//�õ�����������Ϣ
	public List getAskBook(int uid){
		List list = null;
		String sql = "select bookname,staticask from askforbook where uid = " + uid;
		System.out.println(sql);
		list = getDBManager().query(sql);
		return list;
	}

}
