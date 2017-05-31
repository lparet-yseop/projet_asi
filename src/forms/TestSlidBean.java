package forms;

import javax.faces.bean.SessionScoped;

@SessionScoped
public class TestSlidBean {
	private Integer age;
	
	public TestSlidBean() {
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
