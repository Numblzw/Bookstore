package logic;

import db.DBManager;

public abstract class AbstractManager implements Manager {

	public DBManager getDBManager() {
		return DBManager.getInstance();
	}

}
