package beans;

import java.util.Date;


public class CommentBean {

	private int id;
	private String text;
	private Date date;
	private int mark;
	private RecipeBean receipeBean;
	private UserBean userBean;

	public CommentBean() {
		super();
	}

	public CommentBean(int id, String text, Date date, int mark, RecipeBean receipeBean, UserBean userBean) {
		super();
		this.id = id;
		this.text = text;
		this.date = date;
		this.mark = mark;
		this.receipeBean = receipeBean;
		this.userBean = userBean;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public RecipeBean getReceipeBean() {
		return receipeBean;
	}

	public void setReceipeBean(RecipeBean receipeBean) {
		this.receipeBean = receipeBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	@Override
	public String toString() {
		return "CommentBean [id=" + id + ", text=" + text + ", date=" + date + ", mark=" + mark + ", receipeBean="
				+ receipeBean + ", userBean=" + userBean + "]";
	}

}
