package logic;

import java.util.List;

import bean.Admin;
import bean.Book;
import bean.User;

public class UserManager extends AbstractManager {
	//添加用户
	public void AddUser(User user){
		String usernameString = user.getUsername();
		String passwordString = user.getPassword();
		String emailString = user.getEmail();
		String addressString = user.getAddress();
		String sql = "INSERT INTO user (username,password,email,address)VALUES('" +
				usernameString +
				" ','" +
				passwordString +
				"','" +
				emailString +
				"','" +
				addressString +
				"')";
		getDBManager().update(sql);
	}
	//检测用户名
	public boolean checkUsername(String username){
		String sql = "select * from user where username = " + "'" + username +"'";		
		List list = getDBManager().query(sql);
		if(list == null || list.size() == 0 || list.get(0) == null){
			return true;
		}else{
			return false;
		}
	}
	//验证用户
	public User checkUser(User user){
		String sql = "select * from user where username = " + "'" + user.getUsername() + "'";
		List list = (List)getDBManager().query(sql);
		if(list == null || list.size() == 0 || list.get(0) == null){
			return null;
		}else {
			Object[] userObject = (Object[]) list.get(0);
			if(userObject[2].equals(user.getPassword())){
				user.setUid( Integer.parseInt(userObject[0].toString()) );
				user.setEmail((String) userObject[3]);
				user.setAddress((String) userObject[4]);
				return user;
			}else{
				return null;
			}
		}
	}
	//验证管理员
	public Admin checkAdmin(Admin admin){
		String sql = "select * from admin where username = " + "'" + admin.getUsername() + "'";
		List list = getDBManager().query(sql);
		if(list == null || list.size() == 0 || list.get(0) == null){
			return null;
		}else {
			Object[] adminObject = (Object[]) list.get(0);
			if(adminObject[2].equals(admin.getPassword())){
				admin.setAid(Integer.parseInt(adminObject[0].toString()));
				return admin;
			}else{
				return null;
			}
		}
	}
//	//删除用户
//	public boolean deleteUser() {
//		return true;
//	}
	//更新个人信息
	public void upDateUser(User user){
		String usernameString = user.getUsername();
		String passwordString = user.getPassword();
		String emailString = user.getEmail();
		String addressString = user.getAddress();
		Integer uid = user.getUid();
	//	String sql = "UPDATE user SET username = 'usernameString', password = 'passwordString', email = 'emailString', address = 'addressString' where uid = 'uid' ";
		String sql = "UPDATE user SET " +
			"username = '" + usernameString + "', " +
			"password = '" + passwordString + "', " +
			"email = '" + emailString + "', " +
			"address = '" + addressString + "' " +
			"where uid = " + uid ;
		getDBManager().update(sql);
	}
	//根据uid查找User
	public User findUserByUid(int uid){
		List list = null;
		User user = new User();
		list = getDBManager().query(
				"select * from user where uid = '" + uid + "'");
		if(list != null&&list.size()>0){
			Object[] userObject = (Object[]) list.get(0);
			user.setUid(Integer.parseInt(userObject[0].toString()));
			user.setUsername((String) userObject[1]);
			user.setPassword((String) userObject[2]);
			user.setEmail((String) userObject[3]);
			user.setAddress((String)userObject[4]);
			return user;
		}else{
			return null;
		}

	}

}
