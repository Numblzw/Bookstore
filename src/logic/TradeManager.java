package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import db.DBManager;
import javax.swing.JOptionPane;

import bean.SessionBean;

public class TradeManager extends AbstractManager{
	private static HashMap<Integer, Integer> orderMap = new HashMap<Integer,Integer>(); 
	
	public HashMap getOrderMap(){
		return orderMap;
	}
	//购买图书
	@SuppressWarnings("static-access")
	public void tradeComplete(){
		Integer currUserId = new SessionBean().getCurrUser().getUid();
		Object object = orderMap;
		if(orderMap.size()==0 || orderMap == null){
			JOptionPane.showMessageDialog(null, "请选择图书");
		}else{
			Set<Integer> mapSet=orderMap.keySet();
			Iterator<Integer> iterator=mapSet.iterator(); 
			while(iterator.hasNext()){
				Integer bookId = iterator.next();
				Integer num = orderMap.get(bookId);
				String sql1 = "INSERT INTO tradeinfo (uid,bid,num,tradestatic) VALUES (" + "'" +
						currUserId +
						"','" +
						bookId + 
						"','" +
						num + "'," + 0 + ")";
				getDBManager().update(sql1);//插入tradeinfo
			}
			JOptionPane.showMessageDialog(null, "购买成功！");
		}
	}
	//删除订单
	public void deleteOrder(int tid){
		String sql = "delete from tradeinfo where tid = " + tid;
		getDBManager().update(sql);
	}
	//根据uid得到订单
	public List getTradeInfo(int uid){
		String sql = "select * from tradeinfo where uid = " + uid;
		List list = getDBManager().query(sql);
		return list;
	}
	//得到所有订单
	public List getAllList(){
		String sql = "select * from tradeinfo";
		List list = getDBManager().query(sql);
		return list;
	}
	//根据tid改变状态
	public void changeStatic(int tid, int staticInt){
		String sql = "update tradeinfo SET tradestatic = " +
				staticInt +" where tid = " +tid;
	//	System.out.println(sql);
		getDBManager().update(sql);
	}
}
