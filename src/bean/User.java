package bean;

public class User extends Person{
	int uid;
	String username;
	String password;
	String email;
	String address;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public User(){
		
	}
	public User(int uid, String username, String password, String email,
			String address) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
	}
}
