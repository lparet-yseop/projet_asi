package beans;

public class NoteBean {

	private int id;
	private int idReceipe;
	private int idUser;
	private String title;
	private String note;

	public NoteBean() {
	}

	public NoteBean(int idReceipe, int idUser, String title, String note) {
		super();
		this.idReceipe = idReceipe;
		this.idUser = idUser;
		this.title = title;
		this.note = note;
	}

	public NoteBean(int id, int idReceipe, int idUser, String title, String note) {
		super();
		this.id = id;
		this.idReceipe = idReceipe;
		this.idUser = idUser;
		this.title = title;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdReceipe() {
		return idReceipe;
	}

	public void setIdReceipe(int idReceipe) {
		this.idReceipe = idReceipe;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "NoteBean [id=" + id + ", idReceipe=" + idReceipe + ", idUser="
				+ idUser + ", title=" + title + ", note=" + note + "]";
	}

	

}
