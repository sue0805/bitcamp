package com.sue.open;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sue.open.guestbook.Criteria;
import com.sue.open.guestbook.service.GuestbookService;
import com.sue.open.mapper.GuestbookMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class GuestbookMapperTests {

	@Autowired
	private GuestbookMapper mapper;
	
	@Autowired
	private GuestbookService service;

	private String[] names = {"사자", "호랑이", "곰", "돼지", "여우", "강아지", "닭", "고양이", "토끼", "펭귄"};
	
//	@Test 
//	public void mapperTest01(){ Guestbook gb = new Guestbook();
//	
//		for(int i = 0; i < 30; i++) {
//			gb.setGname(names[(int)(Math.random()*10)]); 
//			gb.setGpassword("1234");
//			gb.setGcontent("Insert Test................");
//			
//			mapper.insertContent(gb); 
//		}
//	}
//	
//
//	@Test
//	public void mapperTest02() {
//		Guestbook gb = mapper.readContent(1);
//		log.info(gb);
//	}
//	
//	@Test
//	public void mapperTest03() {
//		mapper.getList().forEach(book -> log.info(book));
//	}
//	
//	@Test
//	public void mapperTest04() {
//		Guestbook gb = new Guestbook();
//		gb.setGname(names[(int)(Math.random()*10)]); 
//		gb.setGpassword("1234");
//		gb.setGcontent("Insert Test................");
//		
//		System.out.println(service.insertContent(gb));
//		
//		System.out.println(service.getList());
//		
//	}
	
	@Test
	public void mapperTest05() {
		Criteria cri = new Criteria(10, 3);
		cri.setPageNum(10);
		cri.setAmount(3);
		cri.setStartNum((cri.getPageNum()-1)*cri.getAmount());
		service.getList(cri).forEach(book -> log.info(book));
	}
}
