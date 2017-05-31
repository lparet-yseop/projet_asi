package beans;

public class CookTypeBean {

	public int id;
	public String text;
	


	public CookTypeBean() {
		super();
	}

	public CookTypeBean(String text) {
		super();
		this.text = text;
	}

	public CookTypeBean(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	@Override
	public String toString() {
		return "CookType [id=" + id + ", text=" + text + "]";
	}
	
	
	
}
