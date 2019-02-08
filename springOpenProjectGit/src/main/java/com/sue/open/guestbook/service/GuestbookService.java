package com.sue.open.guestbook.service;

import java.util.List;

import com.sue.open.guestbook.Criteria;
import com.sue.open.guestbook.Guestbook;

public interface GuestbookService {
	public boolean insertContent(Guestbook guestbook);
	public Guestbook readContent(int no);
	public List<Guestbook> getList();
	public List<Guestbook> getList(Criteria cri);
	public int countList();
	public boolean delete(int no);
	public boolean checkPW(int no, String gpassword);
}
