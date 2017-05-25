package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import beans.CookTypeBean;
import beans.ReceipeBean;
import dao.CookTypeDao;
import dao.ReceipeDao;

@ManagedBean
public class CookTypeController {
	
	List<CookTypeBean> cookTypeBeansList;
	
	public CookTypeController(){
		this.cookTypeBeansList = getAllReceipes();
	}
	
	public List<CookTypeBean> getAllReceipes() {
		CookTypeDao cookTypeDao = CookTypeDao.getInstance();

		List<CookTypeBean> list = (List<CookTypeBean>) cookTypeDao.getAll();
		return list;
	}

	public List<CookTypeBean> getCookTypeBeansList() {
		return cookTypeBeansList;
	}

	public void setCookTypeBeansList(List<CookTypeBean> cookTypeBeansList) {
		this.cookTypeBeansList = cookTypeBeansList;
	}
	
	
	

}
