package dao;

public enum Request {

	// USER
	SELECT_ALL_USERS("SELECT USR_ID, USR_LOGIN, USR_PASSWORD, USR_FIRSTNAME, USR_LASTNAME, USR_DATE_BIRTH, USR_EMAIL, USR_ADMIN"
				+ " FROM T_E_USER_USR;"),

	SELECT_USER("SELECT USR_ID, USR_LOGIN, USR_PASSWORD, USR_FIRSTNAME, USR_LASTNAME, USR_DATE_BIRTH, USR_EMAIL, USR_ADMIN"
			+ " FROM T_E_USER_USR WHERE USR_ID=?;"),
	
	SELECT_USER_BY_LOGIN("SELECT USR_ID, USR_LOGIN, USR_PASSWORD, USR_FIRSTNAME, USR_LASTNAME, USR_DATE_BIRTH, USR_EMAIL, USR_ADMIN"
			+ " FROM T_E_USER_USR WHERE USR_LOGIN=? and USR_PASSWORD=? ;"),

	INSERT_USER("INSERT INTO T_E_USER_USR"
			+ "(USR_ID, USR_LOGIN, USR_PASSWORD, USR_FIRSTNAME, USR_LASTNAME, USR_DATE_BIRTH, USR_EMAIL, USR_ADMIN)"
			+ "VALUES(null, ?, ?, ?, ?, ?, ?, 0);"),
	
	
	// RECEIPT
	SELECT_ALL_RECEIPT("SELECT `id`, `name`, `resume`, `details`, `nbPersons`, `complexity`, `type`, `image` FROM binome36.RECEIPE"),
	
	SELECT_RECEIPT("SELECT `id`, `name`, `resume`, `details`, `nbPersons`, `complexity`, `type`, image FROM binome36.RECEIPE where id=?"),

	INSERT_RECEIPE("INSERT INTO binome36.RECEIPE(`id`, `name`, `resume`, `details`, `nbPersons`, `complexity`, `type`, image)"
			+ " VALUES"
			+ " (null,?,?,?,?,?,?,?)"),
	
	UPDATE_RECEIPE("UPDATE binome36.RECEIPE"
			+ " SET `name`=?,`resume`=?,`details`=?,`nbPersons`=?,`complexity`=?,`type`=?, `image`=?"
			+ " WHERE id=?"),
			
	INSERT_NOTE("INSERT INTO binome36.NOTE (id, idReceipe, title, note, idUser)"
			+ " VALUES"
			+ "(null, ?, ?, ?, ?);"),
			
	UPDATE_NOTE("UPDATE binome36.NOTE"
			+ " SET idReceipe=?, title=?, note=?, idUser=?"
			+ " WHERE id=?;"),
			
	SELECT_ALL_NOTES_FOR_RECEIPE("SELECT id, idReceipe, title, note, idUser FROM binome36.NOTE "
			+ "where idReceipe = ?;"),
	
	SELECT_ALL_NOTES_FOR_USER("SELECT id, idReceipe, title, note, idUser FROM binome36.NOTE "
			+ "where idUser = ?;");

	private String query;

	private Request(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}
}
