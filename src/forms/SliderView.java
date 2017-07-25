package forms;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class SliderView {
	public int value = 0;

	public SliderView() {
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}