package beans;

public class ReceipeBean {

	private int id;
	private int duration;
	private int difficulty;
	private int nbPeople;
	private String title;
	private String description;
	private String pathPhoto;
	private CookTypeBean cookTypeBean;

	public ReceipeBean() {
	}

	
	
	public ReceipeBean(int duration, int difficulty, int nbPeople, String title, String description, String pathPhoto,
			CookTypeBean cookTypeBean) {
		super();
		this.duration = duration;
		this.difficulty = difficulty;
		this.nbPeople = nbPeople;
		this.title = title;
		this.description = description;
		this.pathPhoto = pathPhoto;
		this.cookTypeBean = cookTypeBean;
	}


	public ReceipeBean(int id, int duration, int difficulty, int nbPeople, String title, String description,
			String pathPhoto, CookTypeBean cookTypeBean) {
		super();
		this.id = id;
		this.duration = duration;
		this.difficulty = difficulty;
		this.nbPeople = nbPeople;
		this.title = title;
		this.description = description;
		this.pathPhoto = pathPhoto;
		this.cookTypeBean = cookTypeBean;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getDuration() {
		return duration;
	}



	public void setDuration(int duration) {
		this.duration = duration;
	}



	public int getDifficulty() {
		return difficulty;
	}



	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}



	public int getNbPeople() {
		return nbPeople;
	}



	public void setNbPeople(int nbPeople) {
		this.nbPeople = nbPeople;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getPathPhoto() {
		return pathPhoto;
	}



	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}



	public CookTypeBean getCookTypeBean() {
		return cookTypeBean;
	}



	public void setCookTypeBean(CookTypeBean cookTypeBean) {
		this.cookTypeBean = cookTypeBean;
	}



	@Override
	public String toString() {
		return "ReceipeBean [id=" + id + ", duration=" + duration + ", difficulty=" + difficulty + ", nbPeople="
				+ nbPeople + ", title=" + title + ", description=" + description + ", pathPhoto=" + pathPhoto
				+ ", cookTypeBean=" + cookTypeBean + "]";
	}




	
	
}
