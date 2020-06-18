package bean;

public class SessionBean {
	private static User currUser = null;
	private static Admin currAdmin = null;

	public static User getCurrUser() {
		return currUser;
	}
	public static void setCurrUser(User currUser) {
		SessionBean.currUser = currUser;
	}
	public static Admin getCurrAdmin() {
		return currAdmin;
	}
	public static void setCurrAdmin(Admin currAdmin) {
		SessionBean.currAdmin = currAdmin;
	}
	
}
