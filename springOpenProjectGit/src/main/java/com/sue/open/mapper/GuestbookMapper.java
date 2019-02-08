package com.sue.open.mapper;

import java.util.List;

import com.sue.open.guestbook.Criteria;
import com.sue.open.guestbook.Guestbook;

public interface GuestbookMapper {
	
	public void insertContent(Guestbook guestbook);
	public Guestbook readContent(int no);
	public List<Guestbook> getList();
	public List<Guestbook> getListWithPaging(Criteria cri);
	public int countList();
	public void delete(int no);
	public String getPW(int no);
}
