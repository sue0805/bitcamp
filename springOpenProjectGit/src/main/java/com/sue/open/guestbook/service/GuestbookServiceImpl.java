package com.sue.open.guestbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sue.open.guestbook.Criteria;
import com.sue.open.guestbook.Guestbook;
import com.sue.open.mapper.GuestbookMapper;

@Service
public class GuestbookServiceImpl implements GuestbookService{

	@Autowired
	private GuestbookMapper mapper;
	
	public boolean insertContent(Guestbook guestbook) {
		boolean result = true;
		
		try {
			mapper.insertContent(guestbook);
		} catch(Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}

	public Guestbook readContent(int no) {
		
		return mapper.readContent(no);
	}
	
	public List<Guestbook> getList(){
		
		List<Guestbook> list = new ArrayList<>();
		mapper.getList().forEach(guestbook -> list.add(guestbook));
		
		return list;
	}
	
	public int countList() {
		return mapper.countList();
	}

	@Override
	public List<Guestbook> getList(Criteria cri) {
		List<Guestbook> list = new ArrayList<>();
		mapper.getListWithPaging(cri).forEach(book -> list.add(book));
		
		return list;
	}

}
