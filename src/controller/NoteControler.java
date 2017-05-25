package controller;

import java.util.List;

import beans.NoteBean;
import dao.NoteDao;

public class NoteControler {

	public boolean addNote(NoteBean note) {
		NoteDao noteDao = NoteDao.getInstance();

		boolean rs = noteDao.add(note.getIdReceipe(), note.getIdUser(),
				note.getNote(), note.getTitle());

		return rs;
	}

	public boolean edit(NoteBean note) {
		NoteDao noteDao = NoteDao.getInstance();

		boolean rs = noteDao.edit(note.getId(), note.getIdReceipe(),
				note.getIdUser(), note.getNote(), note.getTitle());

		return rs;
	}

	public List<NoteBean> getNotesForReceipe(int receipeId) {
		NoteDao noteDao = NoteDao.getInstance();

		List<NoteBean> list = noteDao.getAllForReceipe(receipeId);

		return list;
	}

	public List<NoteBean> getNotesForUsers(int userId) {
		NoteDao noteDao = NoteDao.getInstance();

		List<NoteBean> list = noteDao.getNotesForUsers(userId);

		return list;
	}

	/**
	 * To test
	 * 
	 */
	public static void main(String[] args) {

		NoteControler nc = new NoteControler();

		NoteBean bean = new NoteBean(2, 2, "Kro kro bon !",
				"Miam miam miam les crepes étaient trop bonnes !");
		boolean addReceipt = nc.addNote(bean);
		System.out.println("Add receipe: " + addReceipt);

		bean.setTitle("Exceptionnel !");
		bean.setId(1);
		boolean editUser = nc.edit(bean);
		System.out.println("User edit: " + editUser);

		List<NoteBean> list = nc.getNotesForReceipe(1);
		System.out.println("List for receipe 1:" + list);

		list = nc.getNotesForUsers(1);
		System.out.println("List for user 1:" + list);

	}
}
