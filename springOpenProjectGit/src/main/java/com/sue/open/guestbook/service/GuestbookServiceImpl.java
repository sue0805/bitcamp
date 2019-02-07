package com.sue.open.guestbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sue.open.guestbook.Criteria;
import com.sue.open.guestbook.Guestbook;
import com.sue.open.guestbook.dao.GuestbookDAO;

@Service
public class GuestbookServiceImpl implements GuestbookService{

	@Autowired
	private GuestbookDAO dao;
	
	@Override
	public boolean insertContent(Guestbook guestbook) {
		return dao.insertContent(guestbook);
	}

	@Override
	public Guestbook readContent(int no) {
		return dao.readContent(no);
	}

	@Override
	public List<Guestbook> getList(Criteria cri) {
		return dao.getListWithPaging(cri);
	}

	@Override
	public int countList() {
		return dao.countList();
	}

}
