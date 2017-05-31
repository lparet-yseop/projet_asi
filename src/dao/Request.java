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
	SELECT_WITHOUT_CRITERIA("SELECT `REC_ID`, `REC_DURATION`, `REC_DIFFICULTY`, `REC_NB_PEOPLE`, `REC_TITLE`, `REC_DESCRIPTION`, `REC_PATH_PHOTO`, COT.COT_ID,COT.COT_TEXT FROM T_E_RECIPE_REC AS REC "),
	
	INNER_JOIN_COOK_TYPE("INNER JOIN T_E_COOK_TYPE_COT AS COT ON COT.COT_ID=REC.COT_ID "),
	
	SELECT_RECEIPT("SELECT `REC_ID`, `REC_DURATION`, `REC_DIFFICULTY`, `REC_NB_PEOPLE`, `REC_TITLE`, `REC_DESCRIPTION`, `REC_PATH_PHOTO`, `COT_ID` FROM T_E_RECIPE_REC where REC_ID=?"),

	
	INSERT_RECEIPE("INSERT INTO binome36.RECEIPE(`REC_ID`, `REC_DURATION`, `REC_DIFFICULTY`, `REC_NB_PEOPLE`, `REC_TITLE`, `REC_DESCRIPTION`, `REC_PATH_PHOTO`, `COT_ID`)"
			+ " VALUES"
			+ " (null,?,?,?,?,?,?,?)"),
	
	UPDATE_RECEIPE("UPDATE binome36.RECEIPE"
			+ " SET `name`=?,`REC_DESCRIPTION`=?,`REC_TITLE`=?,`nbPersons`=?,`complexity`=?,`type`=?, `image`=?"
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
			+ "where idUser = ?;"),

	
	
	//COOK_TYPE
	
	SELECT_COOK_TYPE("SELECT COT_ID, COT_TEXT  FROM T_E_COOK_TYPE_COT AS COT ;");
	
	
	
	private String query;

	private Request(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}
}
